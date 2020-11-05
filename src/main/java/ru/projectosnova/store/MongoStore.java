package ru.projectosnova.store;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import ru.projectosnova.config.ConfigConnection;
import ru.projectosnova.config.ConfigType;

public class MongoStore extends Store {

    private MongoDatabase mongodb;

    public MongoStore(ConfigType type, ConfigConnection connection) {
        super(type, connection);

        //String mongoUri = "mongodb+srv://m220student:m220password@mflix.avani.mongodb.net/test";

        System.out.println("Mongodb connection string:");
        System.out.println(getConnectionString());

        // instantiate database and collection objects
        this.mongodb = MongoClients.create(getConnectionString()).getDatabase(getDbName());
    }

    private String getConnectionString(){

        String result = "mongodb+srv://";
        if (connection.getUsername()!=""){
            result+=connection.getUsername()+":"+connection.getPassword()+"@";
        }
        result+=connection.getHost()+type.getUri();
        return result;
    }

    private String getDbName(){
        return type.getUri().substring(type.getUri().lastIndexOf("/"));
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
        //MongoCollection<Document> result = mongodb.getCollection(collection);
        return mongodb.getCollection(collection).find();
    }


}
