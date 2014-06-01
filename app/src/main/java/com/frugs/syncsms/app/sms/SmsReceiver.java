package com.frugs.syncsms.app.sms;

import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import com.google.inject.Inject;
import roboguice.receiver.RoboBroadcastReceiver;

import static android.provider.Telephony.Sms.Intents.getMessagesFromIntent;

public class SmsReceiver extends RoboBroadcastReceiver {

    @Inject
    private SmsObserver smsObserver;

    @Override
    protected void handleReceive(Context context, Intent intent) {
        SmsMessage[] messages = getMessagesFromIntent(intent);
    }
}
