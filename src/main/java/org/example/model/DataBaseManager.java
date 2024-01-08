package org.example.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {

    private static final String FILENAME = "databases.dat";

    private ObservableList<DataBase> dataBaseList;

    public DataBaseManager() {
        this.dataBaseList = FXCollections.observableArrayList();
    }

    public void addDataBase(DataBase database) {
        dataBaseList.add(database);
    }

    public ObservableList<DataBase> getDataBaseList() {
        return dataBaseList;
    }

    public void remove(DataBase db) {
        dataBaseList.remove(db);
    }

    public ObservableList<DataBase> filtered(String newValue) {
        return dataBaseList.filtered(item -> item.getTitle().toLowerCase().contains(newValue.toLowerCase()));
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(new ArrayList<>(dataBaseList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        //Проверка существования
        File file = new File(FILENAME);
        if (!file.exists()) return;

        //Загрузка файла
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            dataBaseList.setAll((List<DataBase>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
