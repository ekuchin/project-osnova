package ru.projectosnova.store;

import ru.projectosnova.config.ConfigConnection;
import ru.projectosnova.config.ConfigType;

import java.util.List;

public abstract class Store {
    protected ConfigConnection connection;
    protected ConfigType type;

    public Store(ConfigType type, ConfigConnection config) {
        this.type = type;
        this.connection = config;
    }

    public static Store getStore(ConfigConnection connection, ConfigType type) throws Exception {
        switch(connection.getType()) {
            case "domino":
                return new DominoStore(type, connection);
            case "mongodb":
                return new MongoStore(type, connection);
            default:
                throw new Exception("Store type not found - "+connection.getName());

        }
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
    abstract public List<String> findAllAsList(String collection) throws Exception;
    abstract public String findAllAsJson(String collection) throws Exception;
    //findByKey
}