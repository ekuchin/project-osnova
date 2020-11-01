package ru.projectosnova.store;

import ru.projectosnova.config.ConfigStore;
import ru.projectosnova.config.ConfigType;

public class MongoStore extends Store {

    public MongoStore(ConfigType type, ConfigStore config) {
        super(type, config);
    }

    @Override
    public String create(Object object) throws Exception {
        return null;
    }

    @Override
    public Object read(String id) throws Exception {
        return null;
    }

    @Override
    public boolean update(String id, Object object) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public Object findAll(String collection) throws Exception {
        return null;
    }


}
