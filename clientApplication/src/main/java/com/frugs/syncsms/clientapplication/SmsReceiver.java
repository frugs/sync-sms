package com.frugs.syncsms.clientapplication;

import com.frugs.syncsms.contract.Sms;

public interface SmsReceiver {
    public void receiveSms(Sms sms);
}
