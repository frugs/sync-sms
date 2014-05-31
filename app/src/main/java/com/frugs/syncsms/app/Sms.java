package com.frugs.syncsms.app;

public class Sms {
    private final String senderDisplayName;
    private final String senderPhoneNumber;
    private final String messageContent;

    public Sms(String senderDisplayName, String senderPhoneNumber, String messageContent) {
        this.senderDisplayName = senderDisplayName;
        this.senderPhoneNumber = senderPhoneNumber;
        this.messageContent = messageContent;
    }

    public String getSenderDisplayName() {
        return senderDisplayName;
    }

    public String getSenderPhoneNumber() {
        return senderPhoneNumber;
    }

    public String getMessageContent() {
        return messageContent;
    }
}
