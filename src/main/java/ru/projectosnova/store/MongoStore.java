package ru.projectosnova.store;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import ru.projectosnova.config.ConfigConnection;
import ru.projectosnova.config.ConfigType;
import ru.projectosnova.utils.Transform;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MongoStore extends Store {

    private MongoDatabase mongodb;

    public MongoStore(ConfigType type, ConfigConnection connection) {
        super(type, connection);
        //System.out.println("Mongodb connection string: " +getConnectionString());
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
        Document doc = Document.parse(toJson(object));
        return mongodb.getCollection(type.getCollection()).insertOne(doc).getInsertedId().asObjectId().getValue().toString();
    }

    @Override
    public <T> T read(String id, Class<T> targetClass) {
        Bson flt = Filters.eq("_id", new ObjectId(id));

        String jsonResult = mongodb.getCollection(type.getCollection()).find(flt).first().toJson();
        if (targetClass==String.class){
            return (T) jsonResult;
        }
        else{
            return Transform.jsonToObject(jsonResult,targetClass);
        }
    }

    @Override
    public boolean update(String id, Object object, boolean replaceAll) throws Exception {
        Bson flt = Filters.eq("_id", new ObjectId(id));
        Document update = Document.parse(toJson(object));

        if (replaceAll){
            return mongodb.getCollection(type.getCollection()).replaceOne(flt,update).getModifiedCount()==1;
        }
        else{
            Document setUpdate=new Document("$set",update);
            return mongodb.getCollection(type.getCollection()).updateOne(flt,setUpdate).getModifiedCount()==1;
        }
    }

    @Override
    public boolean delete(String id) {
        Bson flt = Filters.eq("_id", new ObjectId(id));
        return mongodb.getCollection(type.getCollection()).deleteOne(flt).getDeletedCount()==1;
    }

    @Override
    public List<String> findAllAsList(String collection) throws Exception {
         return mongodb.getCollection(collection).find()
                .into(new ArrayList<>())
                .stream().map(Document::toJson)
                .collect(Collectors.toList());
    }

    //Short version with default collection
    public List<String> findAllAsList() throws Exception {
        return findAllAsList(type.getCollection());
    }

    @Override
    public String findAllAsJson(String collection) throws Exception {
        //TODO implement
        return null;
    }
}
