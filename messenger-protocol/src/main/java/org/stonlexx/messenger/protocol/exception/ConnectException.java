package org.stonlexx.messenger.protocol.exception;

import org.stonlexx.messenger.protocol.channel.AbstractChannel;

public class ConnectException extends Exception {

    public ConnectException(AbstractChannel channel, Throwable cause) {
        super("Unable to connect to server [" + channel.getAddress() + ":" + channel.getPort() + "]", cause);
    }

}
