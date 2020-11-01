package ru.projectosnova.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.projectosnova.store.Store;
import ru.projectosnova.store.*;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class Config {

    @Autowired
    private ConfigTypeRepository repoTypes;
    @Autowired
    private ConfigStoreRepository repoStorages;

    private ArrayList<ConfigStore> storages;
    private ArrayList<ConfigType> types;

    public Config() {
        ConfigStore domino = new ConfigStore("domino-test", "domino","https://localhost:443", "domino", "domino");
        ConfigStore mongo = new ConfigStore("mongo-test", "mongo","mongodb://localhost:27017", "mongo", "mongo");

        ConfigType dogs = new ConfigType("mk","dogs","domino-test","/osnova/config.nsf");
        ConfigType cats = new ConfigType("mk","cats", "mongo-test", "/osnova-config");

        this.storages = new ArrayList<>(Arrays.asList(domino,mongo));
        this.types = new ArrayList<>(Arrays.asList(dogs,cats));

        //storages = repoStorages.findAll();
        //types = repoTypes.findAll();
    }

    public Store getStore(String category, String typeName) throws Exception {
      ConfigType type = types.stream()
              .filter(t->t.getCategory().equals(category))
              .filter(t->t.getName().equals(typeName))
              .findFirst()
              .orElseThrow(()->new Exception("Object type not found - "+category+", "+typeName));

      ConfigStore config = storages.stream()
              .filter(t->t.getName().equals(type.getStorage()))
              .findFirst()
              .orElseThrow(()->new Exception("Store type not found - "+type.getStorage()));

        return getStore(type, config);
    }

    private Store getStore(ConfigType type, ConfigStore config) throws Exception {
        switch(config.getType()) {
            case "domino":
                return new DominoStore(type, config);
            case "mongo":
                return new MongoStore(type, config);
            default:
                throw new Exception("Store type not found - "+config.getName());

        }
    }

    public void addStore(ConfigStore store){
        this.storages.add(store);
    }

    public void addType(ConfigType type){
        this.types.add(type);
    }


}
