package ru.projectosnova.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigConnection {

    private String name;
    private String type;
    private String protocol;
    private String host;
    private String port;
    private String username;
    private String password;

    public ConfigConnection() {
    }

    public ConfigConnection(String name, String type, String protocol, String host, String port, String username, String password) {
        this.name = name;
        this.type = type;
        this.protocol = protocol;
        this.host = host;
        this.port = port;
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

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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
