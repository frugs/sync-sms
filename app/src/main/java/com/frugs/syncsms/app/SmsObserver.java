package com.frugs.syncsms.app;

public interface SmsObserver {
    void notifySmsReceived(Sms sms);
}
