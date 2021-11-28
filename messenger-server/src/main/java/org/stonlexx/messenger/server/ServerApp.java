package org.stonlexx.messenger.server;

public class ServerApp {

    public static void main(String[] args) {
        MessengerServer server = new MessengerServer();
        server.bindAsynchronous(() -> {

            server.onStart();
            System.out.println("[Bind] Connection was success bind on " + server.getSocketAddress());
        });
    }

}
