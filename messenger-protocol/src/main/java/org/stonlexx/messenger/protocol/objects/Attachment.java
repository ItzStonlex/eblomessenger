package org.stonlexx.messenger.protocol.objects;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.stonlexx.messenger.protocol.packet.Serializable;

public interface Attachment<T extends Attachment<T>> extends Serializable<T> {

    static Attachment<?> create(Type type) {
        return type.newInstance();
    }

    Type getType();


    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    @RequiredArgsConstructor
    enum Type {

        AUDIO(AudioAttachment.class),
        IMAGE(ImageAttachment.class),
        SUBTITLE(SubtitleAttachment.class),
        VIDEO(VideoAttachment.class),
        ;

        Class<? extends Attachment<?>> extend;

        @SneakyThrows
        Attachment<?> newInstance() {
            return extend.newInstance();
        }
    }
}
