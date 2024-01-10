package org.example.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.OneCLauncherApplication;
import org.example.model.DataBase;
import org.example.model.DataBaseManager;

import java.io.IOException;

public class OneCLauncherController {

    private DataBaseManager dataBaseManager = new DataBaseManager();
    @FXML
    private ListView<DataBase> listView;
    @FXML
    private Hyperlink hyperlink;
    @FXML
    private TextField searchField;
    @FXML
    private Button clearSearchField;
    @FXML
    private ImageView addBase;

    public void initialize() {
        dataBaseManager.loadFromFile();

        listView.setItems(dataBaseManager.getDataBaseList());

        // Добавляем слушатель изменений в текстовом поле
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Фильтруем элементы в списке по введенному тексту
            if (newValue.equals("")) {
                clearSearchField.setVisible(false);
                listView.setItems(dataBaseManager.getDataBaseList());
            } else {
                clearSearchField.setVisible(true);
                listView.setItems(
                        dataBaseManager.filtered(newValue)
                );
            }
        });
    }

    @FXML
    protected void onRunEnterpriseButtonClick() {
        DataBase db = getCurrentLineDB();
        if (db == null) {
            System.out.println("База данных не выбрана");
            return;
        }

        runCommand("Предприятие(" + db.getConnectionString() + ")");
    }

    @FXML
    protected void onRunConfiguratorButtonClick() {
        DataBase db = getCurrentLineDB();
        if (db == null) {
            System.out.println("База данных не выбрана");
            return;
        }
        runCommand("Конфигуратор(" + db.getConnectionString() + ")");
    }

    private void runCommand(String command) {
        // Код для выполнения команды
        System.out.println("Выполняется команда: " + command);
        // Пример выполнения команды
//        String vcommand = "calc";
//        ProcessBuilder processBuilder = new ProcessBuilder(vcommand.split("\\s+"));
//        try {
//            Process process = processBuilder.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @FXML
    protected void addBase() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OneCLauncherApplication.class.getResource("CreationNewDataBase.fxml"));
        Parent root = fxmlLoader.load();
        CreationNewDataBaseController controller = fxmlLoader.getController();

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(listView.getScene().getWindow());
        dialogStage.setTitle("Добавление информационной базы");
        dialogStage.setScene(new Scene(root));
        dialogStage.showAndWait();

        DataBase db = controller.getDB();
        if (db == null) return;

        dataBaseManager.addDataBase(db);
        dataBaseManager.saveToFile();
    }

    @FXML
    protected void deleteBase() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(OneCLauncherApplication.class.getResource("WarningWindow.fxml"));
        Parent root = fxmlLoader.load();
        WarningWindowController controller = fxmlLoader.getController();

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(listView.getScene().getWindow());
        dialogStage.setResizable(false);
        dialogStage.setTitle("Предупреждение!");
        dialogStage.setScene(new Scene(root));


        DataBase db = getCurrentLineDB();
        if (db == null) {
            controller.setLabel("База не выбрана!");
            controller.setMode(WarningWindowController.Mod.OK);
            dialogStage.showAndWait();
            return;
        }

        controller.setLabel("Вы действительно хотите удалить базу?");
        dialogStage.showAndWait();

        if (controller.getResult().toLowerCase().equals("cansel")) return;

        dataBaseManager.remove(db);
        dataBaseManager.saveToFile();
    }

    @FXML
    protected void handleListViewClick() {
        DataBase db = getCurrentLineDB();
        if (db == null) return;

        hyperlink.setText(db.getConnectionString());
    }

    private DataBase getCurrentLineDB() {
        DataBase db = listView.getSelectionModel().getSelectedItem();
        return db;
    }

    @FXML
    protected void clearSearchTextField() {
        searchField.setText("");
    }

}