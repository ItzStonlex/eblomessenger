package org.stonlexx.messenger.server;

import io.netty.channel.socket.SocketChannel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import org.stonlexx.messenger.base.MessengerConstants;
import org.stonlexx.messenger.protocol.channel.AbstractRemoteClientChannel;
import org.stonlexx.messenger.protocol.channel.AbstractServerChannel;
import org.stonlexx.messenger.protocol.packet.Packet;

public final class MessengerServer extends AbstractServerChannel {

    public MessengerServer() {
        super(MessengerConstants.SERVER_HOST, MessengerConstants.SERVER_PORT, 2);
    }

    void onStart() {
        // ...
    }


    @Override
    protected AbstractRemoteClientChannel newClientChannel(SocketChannel channel) {
        return new ClientChannelHandler(channel);
    }

    private static class ClientChannelHandler extends AbstractRemoteClientChannel {

        public ClientChannelHandler(SocketChannel channel) {
            super(channel);
        }

        @Override
        public void process(@NonNull Packet packet) throws Exception {
            super.process(packet);
        }

    }
}
