package org.stonlexx.messenger.protocol.objects;

import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.stonlexx.messenger.protocol.packet.Serializable;
import org.stonlexx.messenger.protocol.util.PacketUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Chat implements Serializable<Chat> {

    private int peerId;

    private boolean multiplied;

    private TIntList memberIds;

    private List<Message> messages;

    @Override
    public Chat serialize(ByteBuf out) {
        out.writeInt(peerId);

        out.writeBoolean(multiplied);

        PacketUtils.writeIntArray(out, memberIds.toArray());
        PacketUtils.writeCollection(out, messages, message -> message.serialize(out));

        return this;
    }

    @SuppressWarnings("all")
    @Override
    public Chat deserialize(ByteBuf in) {
        peerId = in.readInt();

        multiplied = in.readBoolean();

        memberIds = new TIntArrayList(PacketUtils.readIntArray(in));
        messages = PacketUtils.<Message, List>readCollection(in, new ArrayList<>(), () -> PacketUtils.readSerialization(in, Message::new));

        return this;
    }
}
