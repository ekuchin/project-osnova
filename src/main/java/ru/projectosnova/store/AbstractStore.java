package ru.projectosnova.store;

import ru.projectosnova.config.ConfigStorage;
import ru.projectosnova.config.ConfigType;

public abstract class AbstractStore {
    protected ConfigStorage config;
    protected ConfigType type;

    public AbstractStore(ConfigType type, ConfigStorage config) {
        this.type = type;
        this.config = config;
    }

    public ConfigStorage getConfig() {
        return config;
    }

    public void setConfig(ConfigStorage config) {
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
    //abstract public boolean update(String id, Object object)throws Exception;
    //abstract public boolean delete(String id)throws Exception;

    //Collection operations
    //findAll
    //findByKey

}
