package org.stonlexx.messenger.protocol.packet;

import lombok.NonNull;
import org.stonlexx.messenger.protocol.packet.type.*;

@SuppressWarnings({"unused", "RedundantThrows"})
public interface PacketProcessor {

    /**
     * Process packet
     */
    default void process(@NonNull Packet packet) throws Exception {
        packet.process(this);
    }

    /**
     * Process connect
     */
    default void active() throws Exception {
        // for implementation
    }

    /**
     * Process disconnect
     */
    default void inactive() throws Exception {
        // for implementation
    }

    /**
     * Process error
     */
    default void process(@NonNull Throwable throwable) throws Exception {
        throwable.printStackTrace();

        // for implementation
    }


    default void process(LoadUser loadUser) {
        // override me for handle.
    }

    default void process(MessageDelete messageDelete) {
        // override me for handle.
    }

    default void process(MessageEdit messageEdit) {
        // override me for handle.
    }

    default void process(MessageSend messageSend) {
        // override me for handle.
    }

    default void process(OnlineStatus onlineStatus) {
        // override me for handle.
    }

    default void process(ProfileUpdate profileUpdate) {
        // override me for handle.
    }

    default void process(UsersGet.Request usersGet) {
        // override me for handle.
    }

    default void process(UsersGet.Response usersGet) {
        // override me for handle.
    }

}
