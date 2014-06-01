package com.frugs.syncsms.app.sms.sync;

import com.frugs.syncsms.app.sms.Sms;
import org.json.JSONException;
import org.json.JSONObject;

class SmsJsonMapper {
    public JSONObject toJson(Sms sms) throws JSONException {
        return new JSONObject().put("senderDisplayName", sms.getSenderDisplayName())
                .put("senderPhoneNumber", sms.getSenderPhoneNumber())
                .put("messageContent", sms.getMessageContent());
    }
}
