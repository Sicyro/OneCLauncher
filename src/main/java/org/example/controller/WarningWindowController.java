package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WarningWindowController {

    @FXML
    private Label label;
    @FXML
    private Button ok;
    @FXML
    private Button cansel;
    private String result;
    @FXML
    protected void onOk() {
        result = "ok";
        closeStage();
    }
    @FXML
    protected void onCansel() {
        result = "cansel";
        closeStage();
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

    public void setMode(Mod mod) {
        if (mod == null) return;

        switch (mod){
            case OKCANSEL:
                break;
            case OK: cansel.setVisible(false);
        }
    }

    private void closeStage() {
        // Получаем ссылку на текущее окно и закрываем его
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }

    public String  getResult() {
        return result;
    }

    enum Mod {
        OKCANSEL,
        OK
    }
}
