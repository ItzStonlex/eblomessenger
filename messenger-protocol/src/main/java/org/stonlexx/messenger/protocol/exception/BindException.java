package org.stonlexx.messenger.protocol.exception;

import org.stonlexx.messenger.protocol.channel.AbstractChannel;

public class BindException extends Exception {

    public BindException(AbstractChannel channel, Throwable cause) {
        super("Unable to bind server [" + channel.getAddress() + ":" + channel.getPort() + "]", cause);
    }

}
