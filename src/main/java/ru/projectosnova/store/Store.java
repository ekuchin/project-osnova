package ru.projectosnova.store;

import ru.projectosnova.config.ConfigStore;
import ru.projectosnova.config.ConfigType;

public abstract class Store {
    protected ConfigStore config;
    protected ConfigType type;

    public Store(ConfigType type, ConfigStore config) {
        this.type = type;
        this.config = config;
    }

    public ConfigStore getConfig() {
        return config;
    }

    public void setConfig(ConfigStore config) {
        this.config = config;
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