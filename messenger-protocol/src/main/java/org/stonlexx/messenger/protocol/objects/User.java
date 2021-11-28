package org.stonlexx.messenger.protocol.objects;

import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.stonlexx.messenger.protocol.packet.Serializable;
import org.stonlexx.messenger.protocol.util.PacketUtils;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable<User> {

    private int uniqueId;

    private String userId;

    private ImageAttachment icon;

    private TIntList chats;


    @Override
    public User serialize(ByteBuf out) {
        out.writeInt(uniqueId);

        PacketUtils.writeString(out, userId);
        PacketUtils.writeSerialization(out, icon);

        PacketUtils.writeIntArray(out, chats.toArray());
        return this;
    }

    @Override
    public User deserialize(ByteBuf in) {
        uniqueId = in.readInt();

        userId = PacketUtils.readString(in, Short.MAX_VALUE);
        icon = PacketUtils.readSerialization(in, ImageAttachment::new);

        chats = new TIntArrayList(PacketUtils.readIntArray(in));
        return this;
    }

}
