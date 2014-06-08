package com.frugs.syncsms.clientapplication;

import com.frugs.syncsms.contract.Sms;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmsSyncerServerTest {
    private SmsSyncerServer smsSyncerServer;

    @Before
    public void setUp() throws Exception {
        smsSyncerServer = new SmsSyncerServer(new StubSmsReceiverFactory());
        smsSyncerServer.startServer();
    }

    @After
    public void tearDown() throws Exception {
        smsSyncerServer.stopServer();
    }

    @Test
    public void should_notify_sms_received() throws Exception {

    }

    private static class StubSmsReceiverFactory implements SmsReceiverFactory {

        private List<Sms> smsList = Collections.synchronizedList(new ArrayList<Sms>());

        @Override
        public SmsReceiver createSmsReceiver() {
            return new SmsReceiver() {
                @Override
                public void receiveSms(Sms sms) {
                    smsList.add(sms);
                }
            };
        }

        public List<Sms> receivedSmsList() {
            return smsList;
        }
    }
}
