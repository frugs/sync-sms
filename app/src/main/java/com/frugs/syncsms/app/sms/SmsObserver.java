package com.frugs.syncsms.app.sms;

public interface SmsObserver {
    void notifySmsReceived(Sms sms);
}
