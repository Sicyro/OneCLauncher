package org.example.model;

import java.io.Serializable;

public class DataBase implements Serializable {

    private static int id = 1;
    private String baseName;
    private String baseServer;
    private String title;

    private DataBase() {
    }

    public DataBase(String baseName, String baseServer, String title) {
        this.baseName = baseName + "_" + id;
        this.baseServer = baseServer;
        this.title = title + " " + id++;
    }

    public String getConnectionString() {
        return "Srv=" + baseServer + "; Name=" + baseName;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
