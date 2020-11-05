package ru.projectosnova.config;

public class ConfigType {

    private Long id;
    private String category;
    private String name;
    private String connection;
    private String uri;

    public ConfigType(String category, String name,  String connection, String uri) {
        this.category = category;
        this.name = name;
        this.connection = connection;
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

    @Override
    public String toString() {
        return "ObjectType{" +
                "name='" + name + '\'' +
                ", storage='" + connection + '\'' +
                '}';
    }
}
