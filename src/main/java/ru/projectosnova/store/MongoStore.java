package ru.projectosnova.store;

import ru.projectosnova.config.ConfigStorage;

public class MongoStore extends AbstractStore {

    public MongoStore(ConfigStorage config) {
        super(config);
    }

    @Override
    public String create(String objectTypeName, String json, String params) throws Exception {
        return null;
    }
}
