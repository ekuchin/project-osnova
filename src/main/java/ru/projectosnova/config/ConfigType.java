package ru.projectosnova.config;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConfigType {

    @Id
    private Long id;
    private String category;
    private String name;
    private String storage;
    private String uri;

    public ConfigType(String category, String name,  String storage, String uri) {
        this.category = category;
        this.name = name;
        this.storage = storage;
        this.uri = uri;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "ObjectType{" +
                "name='" + name + '\'' +
                ", storage='" + storage + '\'' +
                '}';
    }
}
