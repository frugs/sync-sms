package com.frugs.syncsms.app;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HardCodedConfiguration implements ReadOnlyConfiguration {
    @Override
    public InetAddress getClientAddress() {
        InetAddress client = null;
        try {
            client = InetAddress.getByName("192.168.0.3");
        } catch (UnknownHostException ignored) {}

        return client;
    }

    @Override
    public int getClientPort() {
        return 25761;
    }
}
