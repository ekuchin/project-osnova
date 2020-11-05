package ru.projectosnova.store;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ru.projectosnova.config.ConfigConnection;
import ru.projectosnova.config.ConfigType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MongoStore extends Store {

    private MongoDatabase mongodb;

    public MongoStore(ConfigType type, ConfigConnection connection) {
        super(type, connection);
        System.out.println("Mongodb connection string: " +getConnectionString());
        MongoClient mongoClient = MongoClients.create(getConnectionString());
        this.mongodb = mongoClient.getDatabase(getDbName());
    }

    private String getConnectionString(){

        String result = connection.getProtocol();
        if (!connection.getUsername().equals("")){
            result+=connection.getUsername()+":"+connection.getPassword()+"@";
        }
        result+=connection.getHost();
        if (!connection.getProtocol().equals("")){
            result+=":"+connection.getPort();
        }
        result+=type.getUri();
        return result;
    }

    private String getDbName(){
        return type.getUri().substring(type.getUri().lastIndexOf("/")+1);
    }

    @Override
    public String create(Object object) throws Exception {
        //TODO implement
        return null;
    }

    @Override
    public Object read(String id) throws Exception {
        //TODO implement
        return null;
    }

    @Override
    public boolean update(String id, Object object) throws Exception {
        //TODO implement
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        //TODO implement
        return false;
    }

    @Override
    public List<String> findAllAsList(String collection) throws Exception {
         return mongodb.getCollection(collection).find()
                .into(new ArrayList<Document>())
                .stream().map(d->d.toJson())
                .collect(Collectors.toList());
    }

    @Override
    public String findAllAsJson(String collection) throws Exception {
        //TODO implement
        return null;
    }


}
