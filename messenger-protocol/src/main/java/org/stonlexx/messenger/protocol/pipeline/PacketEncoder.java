package org.stonlexx.messenger.protocol.pipeline;

import org.stonlexx.messenger.protocol.metrics.PerformanceMetrics;
import org.stonlexx.messenger.protocol.packet.Packet;
import org.stonlexx.messenger.protocol.util.PacketUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.EncoderException;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.RequiredArgsConstructor;
import org.stonlexx.messenger.protocol.packet.PacketDirection;
import org.stonlexx.messenger.protocol.packet.PacketProtocol;

@RequiredArgsConstructor
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    private final PacketDirection direction;
    private PacketProtocol state = PacketProtocol.HANDSHAKE;

    public void upgradeConnection(PacketProtocol newState) {
        state = newState;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf buf) throws Exception {
        int packetId = direction.getMapper(state).getPacketId(packet.getClass());

        if (packetId == -1) {
            throw new EncoderException("Tried to send unregistered packet: [Packet: " + packet + ", State: " + state + "]");
        }

        PacketUtils.writeVarInt(buf, packetId);
        packet.writePacket(buf);

        PerformanceMetrics.SENT_PACKETS.addValue(1);
    }
}