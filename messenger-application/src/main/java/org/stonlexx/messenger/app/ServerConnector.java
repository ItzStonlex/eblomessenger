package org.stonlexx.messenger.app;

import io.netty.channel.socket.SocketChannel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.stonlexx.messenger.base.MessengerConstants;
import org.stonlexx.messenger.protocol.channel.AbstractClientChannel;
import org.stonlexx.messenger.protocol.channel.AbstractRemoteServerChannel;
import org.stonlexx.messenger.protocol.packet.Packet;

public class ServerConnector extends AbstractClientChannel {

    public ServerConnector() {
        super(MessengerConstants.SERVER_HOST, MessengerConstants.SERVER_PORT, 2);
    }

    @Override
    protected AbstractRemoteServerChannel newServerChannel(SocketChannel channel) {
        return new ServerChannelHandler(this, channel);
    }

    private static class ServerChannelHandler extends AbstractRemoteServerChannel {

        public ServerChannelHandler(AbstractClientChannel client, SocketChannel channel) {
            super(client, channel);
        }

        @Override
        public void process(@NonNull Packet packet) throws Exception {
            super.process(packet);
        }
    }

}
