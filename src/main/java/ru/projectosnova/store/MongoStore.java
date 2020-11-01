package ru.projectosnova.store;

import ru.projectosnova.config.ConfigStorage;
import ru.projectosnova.config.ConfigType;

public class MongoStore extends AbstractStore{

    public MongoStore(ConfigType type, ConfigStorage config) {
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
}
