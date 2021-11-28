package org.stonlexx.messenger.protocol.channel;

import io.netty.channel.socket.SocketChannel;
import lombok.NonNull;
import org.stonlexx.messenger.protocol.packet.Packet;

public abstract class AbstractRemoteServerChannel extends AbstractRemoteChannel {

    protected final AbstractClientChannel client;

    public AbstractRemoteServerChannel(AbstractClientChannel client, SocketChannel channel) {
        super(channel);

        this.client = client;
    }

    @Override
    public void process(@NonNull Packet packet) throws Exception {
        packet.process(this);

        handlePacket(packet);
    }

    @Override
    public void inactive() {
        client.reconnect();
    }
}
