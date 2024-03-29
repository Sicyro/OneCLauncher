package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.model.DataBase;

public class CreationNewDataBaseController {

    @FXML
    private TextField baseName;
    @FXML
    private TextField baseServer;
    @FXML
    private TextField title;
    @FXML
    private TextField additionalLaunchOptions;
    @FXML
    private Button close;

    @FXML
    protected void close() {
        closeStage();
    }

    @FXML
    protected void save() {
        closeStage();
    }

    private void closeStage() {
        // Получаем ссылку на текущее окно и закрываем его
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    public DataBase getDB() {
        if (baseName.getText().isEmpty()
                && baseServer.getText().isEmpty()
                && title.getText().isEmpty()
                && additionalLaunchOptions.getText().isEmpty())
            return null;

        return new DataBase(baseName.getText(),
                baseServer.getText(),
                title.getText(),
                additionalLaunchOptions.getText());
    }
}
