package com.frugs.syncsms.clientapplication


public class Application {
    SymsSyncerServer smsSyncerServer = new SmsSyncerServer(new SmsReceiverFactory() {
        public SmsReceiver createSmsReceiver() {
            return null;
        }
    });

    public void main(String[] args) {
        smsSyncerServer.startServer()
    }
}
