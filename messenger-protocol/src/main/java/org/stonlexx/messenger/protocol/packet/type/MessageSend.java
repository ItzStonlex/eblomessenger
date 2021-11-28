package org.stonlexx.messenger.protocol.packet.type;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.stonlexx.messenger.protocol.objects.Message;
import org.stonlexx.messenger.protocol.packet.Packet;
import org.stonlexx.messenger.protocol.packet.PacketProcessor;
import org.stonlexx.messenger.protocol.util.PacketUtils;

@AllArgsConstructor
@NoArgsConstructor
public class MessageSend extends Packet {

    private Message message;

    @Override
    public void process(@NonNull PacketProcessor processor) throws Exception {
        processor.process(this);
    }

    @Override
    public void read(@NonNull ByteBuf buf) throws Exception {
        this.message = PacketUtils.readSerialization(buf, Message::new);
    }

    @Override
    public void write(@NonNull ByteBuf buf) throws Exception {
        PacketUtils.writeSerialization(buf, message);
    }

}
