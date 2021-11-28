package org.stonlexx.messenger.app.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.stonlexx.messenger.protocol.objects.Attachment;
import org.stonlexx.messenger.protocol.objects.Message;
import org.stonlexx.messenger.protocol.packet.type.MessageSend;
import org.stonlexx.messenger.app.MessengerApp;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageBuilder {

    public static MessageBuilder newBuilder() {
        return new MessageBuilder();
    }

    private final List<Attachment<?>> attachments = new ArrayList<>();

    private int userId;
    private int chatId;

    private long time;

    private String body;

    private boolean edited;
    private boolean deleted;

    public MessageBuilder user(int userId) {
        this.userId = userId;
        return this;
    }

    public MessageBuilder chat(int chatId) {
        this.chatId = chatId;
        return this;
    }

    public MessageBuilder time(long time) {
        this.time = time;
        return this;
    }

    public MessageBuilder text(String body) {
        this.body = body;
        return this;
    }

    public MessageBuilder edited(boolean edited) {
        this.edited = edited;
        return this;
    }

    public MessageBuilder deleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public MessageBuilder attachment(Attachment<?> attachment) {
        attachments.add(attachment);
        return this;
    }


    public Message build() {
        return new Message(userId, chatId, (time < 0 ? System.currentTimeMillis() : time), (body == null ? "" : body), attachments, edited, deleted);
    }

    public void send() {
        MessengerApp.getInstance().getConnector().sendPacket(new MessageSend());
    }

}
