package ru.projectosnova.store;

import ru.projectosnova.config.ConfigConnection;
import ru.projectosnova.config.ConfigType;

public abstract class Store {
    protected ConfigConnection connection;
    protected ConfigType type;

    public Store(ConfigType type, ConfigConnection config) {
        this.type = type;
        this.connection = config;
    }

    public ConfigConnection getConnection() {
        return connection;
    }

    public void setConnection(ConfigConnection connection) {
        this.connection = connection;
    }

    public ConfigType getType() {
        return type;
    }

    public void setType(ConfigType type) {
        this.type = type;
    }

    //CRUD operations
    abstract public String create(Object object)throws Exception;
    abstract public Object read(String id)throws Exception;
    abstract public boolean update(String id, Object object)throws Exception;
    abstract public boolean delete(String id)throws Exception;

    //Collection operations
    abstract public Object findAll(String collection) throws Exception;
    //findByKey
}