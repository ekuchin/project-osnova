package ru.projectosnova.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.projectosnova.store.AbstractStore;
import ru.projectosnova.store.*;

import java.util.Arrays;
import java.util.List;

@Service
public class Config {

    @Autowired
    private ConfigTypeRepository repoTypes;
    @Autowired
    private ConfigStorageRepository repoStorages;

    private List<ConfigStorage> storages;
    private List<ConfigType> types;

    public Config() {
        ConfigStorage domino = new ConfigStorage("domino-test", "domino", "https://localhost:443/osnova/config.nsf", "domino", "domino");
        ConfigStorage mongo = new ConfigStorage("mongo-test", "mongo", "mongodb://localhost:27017/osnova-config", "mongo", "mongo");

        ConfigType cars = new ConfigType("cars","domino-test");
        ConfigType cats = new ConfigType("cats","mongo-test");

        this.storages = Arrays.asList(domino,mongo);
        this.types = Arrays.asList(cars,cats);

        //storages = repoStorages.findAll();
        //types = repoTypes.findAll();
    }

    public AbstractStore getStore(String typeName) throws Exception {
      ConfigType type = types.stream()
              .filter(t->t.getName().equals(typeName))
              .findFirst()
              .orElseThrow(()->new Exception("Object type not found - "+typeName));

      ConfigStorage config = storages.stream()
              .filter(t->t.getName().equals(type.getStorage()))
              .findFirst()
              .orElseThrow(()->new Exception("Store type not found - "+type.getStorage()));

        return getStore(config);
    };

    private AbstractStore getStore(ConfigStorage config) throws Exception {
        switch(config.getType()) {
            case "domino":
                return new DominoStore(config);
            case "mongo":
                return new MongoStore(config);
            default:
                throw new Exception("Store type not found - "+config.getName());

        }
    }

}
