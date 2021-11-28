package org.stonlexx.messenger.protocol.objects;

import io.netty.buffer.ByteBuf;
import javafx.scene.media.VideoTrack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoAttachment implements Attachment<VideoAttachment> {

    private final Type type = Type.VIDEO;

    private VideoTrack videoTrack;


    @Override
    public VideoAttachment serialize(ByteBuf out) {
        return this;
    }

    @Override
    public VideoAttachment deserialize(ByteBuf in) {
        return this;
    }
}
