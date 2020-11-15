package ru.projectosnova.config;

import ru.projectosnova.store.Store;

import java.util.ArrayList;

//Not @component because it is @bean :)
public class Config {

    private ArrayList<ConfigConnection> connections;
    private ArrayList<ConfigType> types;

    public Config() {}

    public Config(ArrayList<ConfigConnection> connections, ArrayList<ConfigType> types) {
        this.connections = connections;
        this.types = types;
    }

    public Store getStore(String category, String typeName) throws Exception {
      ConfigType type = types.stream()
              .filter(t->t.getCategory().equals(category))
              .filter(t->t.getName().equals(typeName))
              .findFirst()
              .orElseThrow(()->new Exception("Object type not found - "+category+", "+typeName));

      ConfigConnection connection = connections.stream()
              .filter(t->t.getName().equals(type.getConnection()))
              .findFirst()
              .orElseThrow(()->new Exception("Store type not found - "+type.getConnection()));

        return Store.getStore(connection, type);
    }

    public void addStore(ConfigConnection store){
        this.connections.add(store);
    }

    public void addType(ConfigType type){
        this.types.add(type);
    }

    public ArrayList<ConfigConnection> getConnections() {
        return connections;
    }

    public ArrayList<ConfigType> getTypes() {
        return types;
    }
}
