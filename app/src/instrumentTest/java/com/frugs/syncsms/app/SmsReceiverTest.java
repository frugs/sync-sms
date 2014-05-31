package com.frugs.syncsms.app;

import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;
import com.google.inject.Binder;
import com.google.inject.Module;

import java.util.ArrayList;
import java.util.List;

import static com.frugs.syncsms.infrastructure.RoboInjectorFactory.createInjector;
import static org.mockito.Mockito.mock;

public class SmsReceiverTest extends InstrumentationTestCase {

    private static final Context testContext = new MockContext();

    private SmsReceiver smsReceiver;
    private SmsObserver mockSmsObserver;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());

        mockSmsObserver = mock(SmsObserver.class);

        smsReceiver = createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(SmsObserver.class).toInstance(mockSmsObserver);
            }
        }).getInstance(SmsReceiver.class);
    }

    public void test_text_sms_received() throws Exception {
        Intent textSmsReceivedIntent = mock(Intent.class);


        smsReceiver.handleReceive(testContext, textSmsReceivedIntent);
    }

    private static class StubSmsObserver implements SmsObserver {
        private List<Sms> receivedSmsList = new ArrayList<Sms>();

        @Override
        public void notifySmsReceived(Sms sms) {
            receivedSmsList.add(sms);
        }

        public List<Sms> getReceivedSmsList() {
            return receivedSmsList;
        }
    }
}
