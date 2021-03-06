package ru.projectosnova.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigType {

    private String category;
    private String name;
    private String connection;
    private String uri;
    private String collection;

    public ConfigType() {}

    public ConfigType(String category, String name, String connection, String uri, String collection) {
        this.category = category;
        this.name = name;
        this.connection = connection;
        this.uri = uri;
        this.collection = collection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
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

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        return "ConfigType{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", connection='" + connection + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
