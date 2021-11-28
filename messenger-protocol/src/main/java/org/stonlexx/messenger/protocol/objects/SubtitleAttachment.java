package org.stonlexx.messenger.protocol.objects;

import io.netty.buffer.ByteBuf;
import javafx.scene.media.SubtitleTrack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubtitleAttachment implements Attachment<SubtitleAttachment> {

    private final Type type = Type.SUBTITLE;

    private SubtitleTrack subtitleTrack;


    @Override
    public SubtitleAttachment serialize(ByteBuf out) {
        return this;
    }

    @Override
    public SubtitleAttachment deserialize(ByteBuf in) {
        return this;
    }
}
