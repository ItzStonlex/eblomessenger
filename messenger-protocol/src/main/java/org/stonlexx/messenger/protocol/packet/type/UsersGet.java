package org.stonlexx.messenger.protocol.packet.type;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;
import org.stonlexx.messenger.protocol.packet.Packet;
import org.stonlexx.messenger.protocol.packet.PacketProcessor;

public final class UsersGet {

    public static class Request extends Packet {

        @Override
        public void process(@NonNull PacketProcessor processor) throws Exception {
        }

        @Override
        public void read(@NonNull ByteBuf buf) throws Exception {
        }

        @Override
        public void write(@NonNull ByteBuf buf) throws Exception {
        }

    }

    public static class Response extends Packet {

        @Override
        public void process(@NonNull PacketProcessor processor) throws Exception {
        }

        @Override
        public void read(@NonNull ByteBuf buf) throws Exception {
        }

        @Override
        public void write(@NonNull ByteBuf buf) throws Exception {
        }

    }

}
