package ru.projectosnova.store;

import ru.projectosnova.config.ConfigStorage;
import ru.projectosnova.config.ConfigType;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class DominoStore extends AbstractStore {

    public DominoStore(ConfigType type, ConfigStorage config) {
        super(type, config);
    }

    public String create(Object object)throws Exception{
        //TODO return id
        return("id");
    }

    @Override
    public Object read(String id) throws Exception {
        String url=this.getConfig().getUrl()+"/api/data/documents/unid/"+id+"?compact=true";

        HttpResponse<String> response = sendRequest(url, "GET","","");

        if (response.statusCode()==200){
            return response.body(); // ??? JSON
        }
        else{
            throw new Exception(response.body());
        }
    }

    //Private
    private String getBasicAuthToken(){
        String token = this.getConfig().getUsername()+":"+this.getConfig().getPassword();
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
public class DominoDataSource extends DataSource {

    //Create
    public String create(String objectTypeName, String json, String params)throws Exception{

        String url=getUrl()+"/api/data/documents?form="+objectTypeName;

        HttpResponse<String> response = sendRequest(url, "POST",json,params);

        if (response.statusCode()==201){
            String unid=response.headers().allValues("location").get(0);
            unid=unid.substring(unid.indexOf("unid/")+5);
            return(unid);
        }
        else{
            throw new Exception("Error "+response.statusCode()+" "+response.body());
        }

    }

    public String create(String objectTypeName, String json)throws Exception{
        return(this.create(objectTypeName,json,""));
    }

    //Read
    public String read(String unid,String params) throws Exception {
        String url=getUrl()+"/api/data/documents/unid/"+unid+"?compact=true";

        HttpResponse<String> response = sendRequest(url, "GET","",params);

        if (response.statusCode()==200){
            return response.body();
        }
        else{
            throw new Exception(response.body());
        }
    }

    public String read(String unid) throws Exception {
        return this.read(unid,"");
    }

    //Update
    public boolean update(String unid, String json, boolean replaceAllItems, String params)throws Exception{

        String url=getUrl()+"/api/data/documents/unid/"+unid;

        String method="PATCH";
        if (replaceAllItems){
            method="PUT";
        }

        HttpResponse<String> response = sendRequest(url, method,json,params);

        if (response.statusCode()==200){
            return(true);
        }
        else{
            throw new Exception(response.body());
        }

    }

    public boolean update(String unid, String json)throws Exception{
        return this.update(unid,json, false,"");
    }
    public boolean update(String unid, String json, boolean replaceAllItems)throws Exception{
        return this.update(unid,json, replaceAllItems,"");
    }
    public boolean update(String unid, String json, String params)throws Exception{
        return this.update(unid,json, false,"");
    }

    //Delete
    public boolean delete(String unid, String params)throws Exception{

        String url=getUrl()+"/api/data/documents/unid/"+unid;

        HttpResponse<String> response = sendRequest(url, "DELETE","",params);

        if (response.statusCode()==200){
            return(true);
        }
        else{
            throw new Exception(response.body());
        }
    }

    public boolean delete(String unid)throws Exception{
        return this.delete(unid,"");
    }

    public String getCollection(String collection, String params) throws Exception {
        String url=getUrl()+"/api/data/collections/name/"+collection;

        HttpResponse<String> response = sendRequest(url, "GET","",params);

        if (response.statusCode()==200){
            return response.body();
        }
        else{
            throw new Exception(response.body());
        }

    }

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