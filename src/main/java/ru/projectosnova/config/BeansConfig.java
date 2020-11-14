package ru.projectosnova.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.projectosnova.store.Store;
import ru.projectosnova.utils.Transform;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Configuration
public class BeansConfig {

    @Value("${osnova.config.type}")
    private String type;

    @Value("${osnova.config.protocol}")
    private String protocol;

    @Value("${osnova.config.host}")
    private String host;

    @Value("${osnova.config.port}")
    private String port;

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

        ConfigConnection configConnection = new ConfigConnection("config", type, protocol, host, port, username, password);
        ConfigType configDB = new ConfigType("system","stores","config",uri,сollectionStores);
        ConfigType typeDB = new ConfigType("system","stores","config",uri, сollectionTypes);

        Store configStore = Store.getStore(configConnection, configDB);
        Store typeStore = Store.getStore(configConnection, typeDB);

        ArrayList<ConfigConnection> connections = configStore
                .findAllAsList(сollectionStores).stream()
                .map(s-> Transform.jsonToObject(s,ConfigConnection.class))
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<ConfigType> types = typeStore
                .findAllAsList(сollectionTypes).stream()
                .map(s-> Transform.jsonToObject(s,ConfigType.class))
                .collect(Collectors.toCollection(ArrayList::new));

        return new Config(connections, types);
    }

}