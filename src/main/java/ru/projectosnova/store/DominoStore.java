package ru.projectosnova.store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.projectosnova.config.ConfigStore;
import ru.projectosnova.config.ConfigType;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class DominoStore extends Store {

    public DominoStore(ConfigType type, ConfigStore config) {
        super(type, config);
    }

    //CRUD operations
    //==========================
    public String create(Object object)throws Exception{

        String url=getUrl()+"/api/data/documents?form="+type.getName();
        String json =getJson(object);

        HttpResponse<String> response = sendRequest(url, "POST",json,"");

        if (response.statusCode()!=201){
            throw new Exception("Error "+response.statusCode()+" "+response.body());
        }
        String id=response.headers().allValues("location").get(0);
        id = id.substring(id.indexOf("id/")+5);
        return(id);

    }

    //==========================
    @Override
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

    public boolean update(String id, Object object, boolean replaceAll)throws Exception{
        String url=getUrl()+"/api/data/documents/unid/"+id;

        String method="PATCH";
        if (replaceAll){
            method="PUT";
        }

        String json=getJson(object);

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
    public boolean delete(String id)throws Exception {
        String url=getUrl()+"/api/data/documents/unid/"+id;

        HttpResponse<String> response = sendRequest(url, "DELETE","","");

        if (response.statusCode()!=200){
            throw new Exception(response.body());
        }
        return true;
    }

    //Collection operations
    //==========================
    public Object findAll(String collection) throws Exception {
        String url=getUrl()
                +"/api/data/collections/name/"+collection;

        HttpResponse<String> response = sendRequest(url, "GET","","");

        if (response.statusCode()==200){
            return response.body();
        }
        else{
            throw new Exception(response.body());
        }
    }

    //==========================
    //Private
    private String getUrl(){
        return this.getConfig().getHost()
                +this.getType().getUri();
    }

    private String getBasicAuthToken(){
        String token = this.getConfig().getUsername()+":"+this.getConfig().getPassword();
        token = "Basic "+ Base64.getEncoder().encodeToString(token.getBytes());
        return (token);
    }

    private String getJson(Object object) throws JsonProcessingException {

        if(object instanceof String){
            return (String) object;
        }
        else{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        }

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
public class DominoDataSource extends DataSource {

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



}
 */