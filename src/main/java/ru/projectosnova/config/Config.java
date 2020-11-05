package ru.projectosnova.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.projectosnova.store.Store;
import ru.projectosnova.store.*;

import java.util.ArrayList;

@Service
public class Config {

    @Value("${osnova.config.type}")
    private String type;

    @Value("${osnova.config.host}")
    private String host;

    @Value("${osnova.config.username}")
    private String username;

    @Value("${osnova.config.password}")
    private String password;

    @Value("${osnova.config.uri}")
    private String uri;

    @Value("${osnova.config.collection.stores}")
    private String сollectionStores;

    @Value("${osnova.config.collection.types}")
    private String сollectionTypes;

    private ArrayList<ConfigConnection> stores;
    private ArrayList<ConfigType> types;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Config() throws Exception {

        //TODO config must be @Bean

        /*
        Limitaton:
        Configuraton of stores and types must be stored on one server,
        one database, but probably in different collections
         */

        /*
        ConfigConnection configConnection = new ConfigConnection("config", type,host, username, password);
        ConfigType configDB = new ConfigType("system","stores","config",uri);



        Store configStore = getStore(configConnection, configDB);

        String str = configStore.findAll(сollectionStores).toString();
        String typ = configStore.findAll(сollectionTypes).toString();

        System.out.println(str);
        System.out.println(typ);

         */
        //storages = repoStorages.findAll();
        //types = repoTypes.findAll();
    }

    public Store getStore(String category, String typeName) throws Exception {
      ConfigType type = types.stream()
              .filter(t->t.getCategory().equals(category))
              .filter(t->t.getName().equals(typeName))
              .findFirst()
              .orElseThrow(()->new Exception("Object type not found - "+category+", "+typeName));

      ConfigConnection store = stores.stream()
              .filter(t->t.getName().equals(type.getConnection()))
              .findFirst()
              .orElseThrow(()->new Exception("Store type not found - "+type.getConnection()));

        return getStore(store, type);
    }

    private Store getStore(ConfigConnection config, ConfigType type) throws Exception {
        switch(config.getType()) {
            case "domino":
                return new DominoStore(type, config);
            case "mongodb":
                return new MongoStore(type, config);
            default:
                throw new Exception("Store type not found - "+config.getName());

        }
    }

    public void addStore(ConfigConnection store){
        this.stores.add(store);
    }

    public void addType(ConfigType type){
        this.types.add(type);
    }


}
