package org.example.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.model.DataBase;
import org.example.model.DataBaseManager;

public class OneCLauncherController {

    private DataBaseManager dataBaseManager = new DataBaseManager();
    ;
    @FXML
    private ListView<DataBase> listView;
    @FXML
    private Hyperlink hyperlink;
    @FXML
    private TextField searchField;
    @FXML
    private Button clearSearchField;

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

//        searchField.setOnKeyTyped(keyEvent -> {
//            searchField.requestFocus();
//        });
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
    protected void addBase() {
        // Код для добавления базы в список
        DataBase db = new DataBase("dev_g", "kratos", "Новая база"); // Замените на реальную логику
        dataBaseManager.addDataBase(db);
        dataBaseManager.saveToFile();
    }

    @FXML
    protected void deleteBase() {
        // Код для удаления базы из списка
        DataBase db = getCurrentLineDB();
        if (db == null) return;

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