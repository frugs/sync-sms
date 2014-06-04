package com.frugs.syncsms.test.util;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.net.ServerSocketFactory.getDefault;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestSmsClient {
    private final int port = 25761;

    private SmsMessage smsMessageReceived;
    private Lock lock = new ReentrantLock();

    public void captureNextSmsMessage() throws IOException {
        final ServerSocket serverSocket = getDefault().createServerSocket(port);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = serverSocket.accept();
                    String json = IOUtils.toString(socket.getInputStream(), UTF_8);
                    socket.close();

                    lock.lock();

                    smsMessageReceived = new Gson().fromJson(json, SmsMessage.class);

                    lock.unlock();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void verifySms(String sender, String message) throws InterruptedException {
        lock.lock();
        for (int i = 0; smsMessageReceived == null && i < 10; i++) {
            lock.unlock();
            Thread.sleep(500);
            lock.lock();
        }

        assertNotNull("No sms received.", smsMessageReceived);
        assertEquals("Incorrect sender phone number.", sender, smsMessageReceived.senderPhoneNumber);
        assertEquals("Incorrect message content.", message, smsMessageReceived.messageContent);

        lock.unlock();
    }

    private static class SmsMessage {
        public String senderPhoneNumber;
        public String messageContent;
    }
}