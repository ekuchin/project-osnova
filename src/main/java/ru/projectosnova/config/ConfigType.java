package ru.projectosnova.config;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConfigType {
    @Id
    private String name;
    private String category;
    private String storage;

    public ConfigType(String name, String storage) {
        this.name = name;
        this.storage = storage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ObjectType{" +
                "name='" + name + '\'' +
                ", storage='" + storage + '\'' +
                '}';
    }
}
