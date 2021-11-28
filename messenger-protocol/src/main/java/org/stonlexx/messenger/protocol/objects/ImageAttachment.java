package org.stonlexx.messenger.protocol.objects;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import sun.misc.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageAttachment implements Attachment<ImageAttachment> {

    private final Type type = Type.IMAGE;


    private int userId;

    private InputStream imageStream;

    @SneakyThrows
    @Override
    public ImageAttachment serialize(ByteBuf out) {
        out.writeInt(userId);
        out.writeBytes(IOUtils.readAllBytes(imageStream));

        return this;
    }

    @Override
    public ImageAttachment deserialize(ByteBuf in) {
        userId = in.readInt();
        imageStream = new ByteArrayInputStream(in.array());

        return this;
    }

}
