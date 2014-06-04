package com.frugs.syncsms.test;

import com.frugs.syncsms.test.util.TestSmsClient;
import org.apache.commons.net.telnet.TelnetClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.net.InetAddress.getByName;
import static java.nio.charset.Charset.defaultCharset;

public class AcceptanceTest {
    private final InetAddress emulatorAddress = localhost();
    private final int emulatorPort = 5554;
    private TestSmsClient testSmsClient;

    @Before
    public void setUp() throws Exception {
        testSmsClient = new TestSmsClient();
    }

    // FIXME: Weird issue where sometimes the sms isn't sent sometimes.
    @Test
    public void when_receiving_a_text_sms_should_notify_client_with_sms_details() throws Exception {
        String sender = "084357389";
        String message = "hello.this.is.a.test.message";

        testSmsClient.captureNextSmsMessage();
        sendSmsToEmulator(sender, message);

        testSmsClient.verifySms(sender, message);
    }

    private void sendSmsToEmulator(String senderPhoneNumber, String messageContent) throws IOException {
        TelnetClient telnetClient = new TelnetClient();
        telnetClient.connect(emulatorAddress, emulatorPort);

        OutputStream outputStream = telnetClient.getOutputStream();
        outputStream.write(("sms send " + senderPhoneNumber + " " + messageContent + "\n").getBytes(defaultCharset()));
        outputStream.flush();

        telnetClient.disconnect();
    }

    private static InetAddress localhost() {
        try {
            return getByName("localhost");
        } catch (UnknownHostException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }
    }
}
