package com.frugs.syncsms.app;

import java.net.InetAddress;

public interface ReadOnlyConfiguration {
    InetAddress getClientAddress();

    int getClientPort();
}
