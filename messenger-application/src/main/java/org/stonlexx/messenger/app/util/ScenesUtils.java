package org.stonlexx.messenger.app.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.stonlexx.messenger.app.MessengerApp;
import org.stonlexx.messenger.app.controller.AbstractPageController;
import org.stonlexx.messenger.base.MessengerConstants;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

@UtilityClass
public class ScenesUtils {

    // Для быстрого перехода между страницами, будем кешировать их на 5 минут
    private final Cache<String, Scene> sceneCache = CacheBuilder.newBuilder()
            .expireAfterAccess(5, TimeUnit.MINUTES)
            .build();

    // Для получения контроллеров, чтобы инициализировать кешированные сцены
    private final Map<Parent, AbstractPageController> sceneControllersMap
            = new HashMap<>();


    public static Stage CURRENT_SCREEN          = null;

    public static final String WELCOME_FXML     = ("welcome.fxml");
    public static final String SIGN_IN_FXML     = ("signin.fxml");
    public static final String LOG_IN_FXML      = ("login.fxml");
    public static final String HOME_FXML        = ("homepage.fxml");
    public static final String PROFILE_FXML     = ("profile.fxml");
    public static final String CHAT_FXML        = ("chat.fxml");


    @SneakyThrows({IOException.class})
    private Parent loadFxml(@NonNull String fxml) {
        URL fxmlUrl = MessengerApp.class.getResource("/fxml/" + fxml);

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        Parent parent = fxmlLoader.load();

        sceneControllersMap.put(parent, fxmlLoader.getController());

        return parent;
    }

    public void redirect(@NonNull String fxml) {
        MessengerApp.getInstance().getSchedulerManager().cancelAll();
        sceneCache.cleanUp();

        synchronized (MessengerApp.getInstance()) {

            if (CURRENT_SCREEN != null && sceneCache.asMap().containsKey(fxml)) {
                Scene scene = sceneCache.asMap().get(fxml);
                sceneControllersMap.get(scene.getRoot()).initialize();

                CURRENT_SCREEN.setScene(scene);

            } else {

                Scene scene = new Scene(loadFxml(fxml));

                CURRENT_SCREEN.setScene(scene);
                sceneCache.put(fxml, scene);
            }
        }
    }

    public void open(@NonNull Stage primaryStage, @NonNull String fxml) {
        MessengerApp.getInstance().getSchedulerManager().cancelAll();
        sceneCache.cleanUp();

        synchronized (MessengerApp.getInstance()) {
            Scene scene = CURRENT_SCREEN != null ? sceneCache.asMap().get(fxml) : null;

            if (scene == null) {
                sceneCache.put(fxml, scene = new Scene(loadFxml(fxml)));

            } else {

                sceneControllersMap.get(scene.getRoot()).initialize();
            }

            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);

            primaryStage.setOnCloseRequest(event -> MessengerApp.getInstance().onShutdown());
            primaryStage.getIcons().add(new Image(MessengerApp.class.getResourceAsStream("/images/Icon.jpg")));

            primaryStage.setTitle(MessengerConstants.LAUNCHER_TITLE + " " + MessengerConstants.LAUNCHER_VERSION);
            primaryStage.setResizable(false);

            (CURRENT_SCREEN = primaryStage).show();
        }
    }

    public ContextMenu initContextMenu(@NonNull Node node,
                                       @NonNull BiConsumer<ActionEvent, MenuItem> itemClickAction,
                                       @NonNull String... menuItems) {

        synchronized (MessengerApp.getInstance()) {
            ContextMenu contextMenu = new ContextMenu();

            for (String menuItemText : menuItems) {

                MenuItem menuItem = new MenuItem(menuItemText);
                menuItem.setOnAction(event -> itemClickAction.accept(event, menuItem));

                contextMenu.getItems().add(menuItem);
            }

            node.setOnMouseClicked(event -> contextMenu.show(node, event.getScreenX(), event.getScreenY()));
            return contextMenu;
        }
    }

}
