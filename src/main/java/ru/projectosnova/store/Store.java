package ru.projectosnova.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.projectosnova.config.Config;

@Service
public class Store {

    private Config config;

    public Store() {
    }
    @Autowired
    public Store(Config config) {
        this.config = config;
    }

    public void create(String type, Object obj) throws Exception {
        AbstractStore store = config.getStore(type);


    }

}
