package org.example.model;

import java.io.Serializable;
import java.util.Objects;

public class DataBase implements Serializable {

    private String baseName;
    private String baseServer;
    private String title;
    private String additionalLaunchOptions;

    public DataBase() {
    }

    public DataBase(String baseName, String baseServer, String title, String additionalLaunchOptions) {
        this.baseName = baseName + "_";
        this.baseServer = baseServer;
        this.title = title + " ";
        this.additionalLaunchOptions = additionalLaunchOptions;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataBase dataBase = (DataBase) o;
        return Objects.equals(baseName, dataBase.baseName) && Objects.equals(baseServer, dataBase.baseServer) && Objects.equals(title, dataBase.title) && Objects.equals(additionalLaunchOptions, dataBase.additionalLaunchOptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseName, baseServer, title, additionalLaunchOptions);
    }
}
