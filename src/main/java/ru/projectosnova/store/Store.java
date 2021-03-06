package ru.projectosnova.store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    //====================
    //Object can be any Object except string, or JSON string
    abstract public String create(Object object)throws Exception;

    abstract public <T> T read(String id, Class<T> targetClass)throws Exception;
    abstract public String read(String id) throws Exception;

    //Object can be any Object except string, or JSON string
    abstract public boolean update(String id, Object object, boolean replaceAll)throws Exception;
    abstract public boolean delete(String id)throws Exception;

    //Collection operations
    //====================
    abstract public List<String> findAll(String collection) throws Exception;
    abstract public List<String> findAll() throws Exception;

    //findByKey

    //Create many
    //Update many = by filter + template
    //Delete many = by filter

    //Common operations
    protected String toJson(Object object) throws JsonProcessingException {

        if(object instanceof String){
            return (String) object;
        }
        else{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        }
    }
}