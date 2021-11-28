package org.stonlexx.messenger.protocol.objects;

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
public class Message implements Serializable<Message> {

    private int userId;
    private int chatId;

    private long time;

    private String body;
    private List<Attachment<?>> attachments;

    private boolean edited;
    private boolean deleted;

    @Override
    public Message serialize(ByteBuf out) {
        out.writeInt(userId);
        out.writeInt(chatId);

        out.writeLong(time);

        PacketUtils.writeString(out, body);
        PacketUtils.writeCollection(out, attachments, attachment -> {

            PacketUtils.writeEnum(out, attachment.getType());
            attachment.serialize(out);
        });

        out.writeBoolean(edited);
        out.writeBoolean(deleted);

        return this;
    }

    @SuppressWarnings("all")
    @Override
    public Message deserialize(ByteBuf in) {
        userId = in.readInt();
        chatId = in.readInt();

        time = in.readLong();

        body = PacketUtils.readString(in, Short.MAX_VALUE);
        attachments = PacketUtils.<Attachment, List>readCollection(in, new ArrayList<>(),
                () -> Attachment.create(PacketUtils.readEnum(in, Attachment.Type.class)).deserialize(in));

        edited = in.readBoolean();
        deleted = in.readBoolean();

        return this;
    }
}
