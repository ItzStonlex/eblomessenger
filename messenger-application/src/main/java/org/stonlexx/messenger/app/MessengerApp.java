package org.stonlexx.messenger.app;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.stonlexx.messenger.base.MessengerConstants;
import org.stonlexx.messenger.base.scheduler.SchedulerManager;
import org.stonlexx.messenger.app.util.ScenesUtils;
import org.stonlexx.messenger.protocol.packet.PacketProtocol;
import org.stonlexx.messenger.protocol.packet.type.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessengerApp extends Application {

    @Getter
    private static MessengerApp instance; {
        instance = this;
    }


    SchedulerManager schedulerManager = MessengerConstants.SCHEDULER_MANAGER;

    @NonFinal
    ServerConnector connector;

    void newServerConnection() {
        connector = new ServerConnector();
        connector.connectAsynchronous(e -> connector.reconnect(), () -> {

            registerPackets();
            System.out.println("[Connect] Connection was success listening on " + connector.getSocketAddress());
        });
    }

    void registerPackets() {
        PacketProtocol.PLAY.TO_SERVER.registerPacket(0x00, LoadUser.class);
        PacketProtocol.PLAY.TO_CLIENT.registerPacket(0x00, LoadUser.class);

        PacketProtocol.PLAY.TO_SERVER.registerPacket(0x01, MessageDelete.class);
        PacketProtocol.PLAY.TO_SERVER.registerPacket(0x02, MessageEdit.class);
        PacketProtocol.PLAY.TO_SERVER.registerPacket(0x03, MessageSend.class);
        PacketProtocol.PLAY.TO_SERVER.registerPacket(0x04, OnlineStatus.class);
        PacketProtocol.PLAY.TO_SERVER.registerPacket(0x05, ProfileUpdate.class);

        PacketProtocol.PLAY.TO_CLIENT.registerPacket(0x06, UsersGet.Response.class);
        PacketProtocol.PLAY.TO_SERVER.registerPacket(0x06, UsersGet.Request.class);
    }


    @Override
    public void start(Stage primaryStage) {
        ScenesUtils.open(primaryStage, ScenesUtils.WELCOME_FXML);
        newServerConnection();
    }

    public void onShutdown() {
        System.exit(0);
    }

}
