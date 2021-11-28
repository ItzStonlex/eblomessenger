package org.stonlexx.messenger.protocol.response;

import org.stonlexx.messenger.protocol.packet.Packet;

public interface FullResponseHandler<ResponsePacket extends Packet> {

    void handleResponse(ResponsePacket packet, Throwable throwable);

}
