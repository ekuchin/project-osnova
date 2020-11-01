package ru.projectosnova.config;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConfigStore {
    @Id
    private String name;
    private String type;
    private String host;
    private String username;
    private String password;

    public ConfigStore(String name, String type, String host, String username, String password) {
        this.name = name;
        this.type = type;
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ConfigStore{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
