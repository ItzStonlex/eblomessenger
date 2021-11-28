package org.stonlexx.messenger.protocol.channel;

import io.netty.channel.socket.SocketChannel;
import lombok.NonNull;
import org.stonlexx.messenger.protocol.packet.Packet;

public abstract class AbstractRemoteClientChannel extends AbstractRemoteChannel {

    public AbstractRemoteClientChannel(SocketChannel channel) {
        super(channel);
    }

    @Override
    public void process(@NonNull Packet packet) throws Exception {
        packet.process(this);

        handlePacket(packet);
    }

}
