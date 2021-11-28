package org.stonlexx.messenger.app.controller;

import javafx.fxml.FXML;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class AbstractPageController {

    @FXML
    ResourceBundle resources;

    @FXML
    URL location;


    @FXML
    public abstract void initialize();

}
