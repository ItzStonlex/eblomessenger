package org.stonlexx.messenger.protocol.objects;

import io.netty.buffer.ByteBuf;
import javafx.scene.media.AudioTrack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AudioAttachment implements Attachment<AudioAttachment> {

    private final Type type = Type.AUDIO;

    private AudioTrack audioTrack;

    @Override
    public AudioAttachment serialize(ByteBuf out) {
        return this;
    }

    @Override
    public AudioAttachment deserialize(ByteBuf in) {
        return this;
    }

}
