package org.stonlexx.messenger.protocol.packet;

public enum PacketProtocol {

    HANDSHAKE,
    PLAY;

    public final PacketMapper
            TO_SERVER = new PacketMapper(),
            TO_CLIENT = new PacketMapper();

}