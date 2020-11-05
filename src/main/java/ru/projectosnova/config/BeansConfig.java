package ru.projectosnova.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.projectosnova.store.Store;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeansConfig {

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

        /*
        Limitaton:
        Configuraton of stores and types must be stored on one server,
        one database, can be stored in different collections
         */

    @Bean("config")
    public Config createConfig() throws Exception {

        ConfigConnection configConnection = new ConfigConnection("config", type,host, username, password);
        ConfigType configDB = new ConfigType("system","stores","config",uri);

        Store configStore = Store.getStore(configConnection, configDB);

        List<String> str = configStore.findAllAsList(сollectionStores);
        List<String> typ = configStore.findAllAsList(сollectionTypes);

        System.out.println("Stores");
        str.forEach(System.out::println);
        //System.out.println(str);
        //System.out.println(typ);

        ArrayList<ConfigConnection> connections = null;
        ArrayList<ConfigType> types = null;

        //storages = repoStorages.findAll();
        //types = repoTypes.findAll();

        return new Config(connections, types);
    }

}
