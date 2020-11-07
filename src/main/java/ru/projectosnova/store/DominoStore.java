package ru.projectosnova.store;

import ru.projectosnova.config.ConfigConnection;
import ru.projectosnova.config.ConfigType;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.List;

public class DominoStore extends Store {

    public DominoStore(ConfigType type, ConfigConnection config) {
        super(type, config);
    }

    //CRUD operations
    //==========================
    @Override
    public String create(Object object)throws Exception{

        String url=getUrl()+"/api/data/documents?form="+type.getName();
        String json = toJson(object);

        HttpResponse<String> response = sendRequest(url, "POST",json,"");

        if (response.statusCode()!=201){
            throw new Exception("Error "+response.statusCode()+" "+response.body());
        }
        String id=response.headers().allValues("location").get(0);
        id = id.substring(id.indexOf("id/")+5);
        return(id);

    }

    @Override
    public <T> T read(String id, Class<T> targetClass) throws Exception {
        return null;
    }

    //==========================
    //@Override
    public Object read(String id) throws Exception {
        String url=getUrl()
                +"/api/data/documents/unid/"+id+"?compact=true";

        HttpResponse<String> response = sendRequest(url, "GET","","");

        if (response.statusCode()!=200){
            throw new Exception(response.body());
        }
        return response.body();
    }

    //==========================
    @Override
    public boolean update(String id, Object object, boolean replaceAll)throws Exception{
        String url=getUrl()+"/api/data/documents/unid/"+id;

        String method="PATCH";
        if (replaceAll){
            method="PUT";
        }

        String json=toJson(object);

        HttpResponse<String> response = sendRequest(url, method,json,"");

        if (response.statusCode()!=200){
            throw new Exception(response.body());
        }
        return true;
    }

    public boolean update(String id, Object object)throws Exception{
        return update(id, object, false);
    }

    //==========================
    @Override
    public boolean delete(String id)throws Exception {
        String url=getUrl()+"/api/data/documents/unid/"+id;

        HttpResponse<String> response = sendRequest(url, "DELETE","","");

        if (response.statusCode()!=200){
            throw new Exception(response.body());
        }
        return true;
    }

    @Override
    public List<String> findAllAsList(String collection) throws Exception {
        //TODO implement
        return null;
    }

    //Collection operations
    //==========================
    @Override
    public String findAllAsJson(String collection) throws Exception {
        String url=getUrl()
                +"/api/data/collections/name/"+collection;

        HttpResponse<String> response = sendRequest(url, "GET","","");

        if (response.statusCode()==200){
            //TODO parse Json-String as List of Json Strings ???
            return response.body();
        }
        else{
            throw new Exception(response.body());
        }
    }

    //==========================
    //Private
    private String getUrl(){
        return this.getConnection().getHost()
                +this.getType().getUri();
    }

    private String getBasicAuthToken(){
        String token = this.getConnection().getUsername()+":"+this.getConnection().getPassword();
        token = "Basic "+ Base64.getEncoder().encodeToString(token.getBytes());
        return (token);
    }

    private HttpResponse<String> sendRequest(String url, String method, String json, String params) throws IOException, InterruptedException {

        if(!params.equals("")) {
            url=url+"?"+params;
        }
        HttpClient client = HttpClient.newBuilder()
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method(method,HttpRequest.BodyPublishers.ofString(json))
                .setHeader("Authorization", getBasicAuthToken())
                .setHeader("Accept-Charset", "UTF-8")
                .setHeader("Content-Type", "application/json")
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

}

/*
    public String searchByKey(String collection, String key, boolean exactMatch, String params) throws Exception {
        String keyParams="systemcolumns=0x0000&compact=true";
        keyParams+="&keys="+key+"&keysexactmatch=";
        if(exactMatch) {
            keyParams+="true";
        }
        else {
            keyParams+="false";
        }
        if (!params.equals("")){
            keyParams+="&"+params;
        }
        return getCollection(collection,keyParams);
    }
 */