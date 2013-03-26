package com.gamecloud;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class APIClient extends Client {

    private WebResource resource;

    public APIClient(String url) {
        this.resource = resource(url);
    }

    public APIClient(String user, String pass, String url) {
        addFilter(new HTTPBasicAuthFilter(user, pass));
        this.resource = resource(url);
    }

    public APIClient(String token, String url) {
        this.resource = resource(url);
        this.resource.header("gc_auth_token", token);
    }

    public JSONObject get() throws APIException {
        Builder builder = resource.accept("application/json");
        ClientResponse response = builder.get(ClientResponse.class);
        validateResponse(response);
        return toJson(response);
    }

    public void post1(JSONObject object) throws APIException {
        Builder builder = resource.type("application/json");
        ClientResponse response = builder.post(ClientResponse.class, object.toString());
        validateResponse(response);
    }

    public JSONObject post2(JSONObject object) throws APIException {
        Builder builder = resource.accept("application/json").type("application/json");
        ClientResponse response = builder.post(ClientResponse.class, object.toString());
        validateResponse(response);
        return toJson(response);
    }

    public void put(JSONObject object) throws APIException {
        Builder builder = resource.type("application/json");
        ClientResponse response = builder.put(ClientResponse.class, object.toString());
        validateResponse(response);
    }

    public void delete() throws APIException{
        Builder builder = resource.getRequestBuilder();
        ClientResponse response = builder.delete(ClientResponse.class);
        validateResponse(response);
    }
    
    private JSONObject toJson(ClientResponse response) throws APIException{
        try {
            return new JSONObject(response.getEntity(String.class));
        } catch (ClientHandlerException e) {
            throw new APIException(e);
        } catch (UniformInterfaceException e) {
            throw new APIException(e);
        } catch (JSONException e) {
            throw new APIException(e);
        }
    }

    private boolean validateResponse(ClientResponse response) throws APIException{
        int code = response.getClientResponseStatus().getStatusCode();
        if(code == 200 || code == 201 || code == 204) {
            return true;
        }else {
            String msg = response.getClientResponseStatus().toString();
            throw new APIException("API request failed: "+code+"/"+msg);
        }
    }
}