package com.gamecloud;

import org.json.JSONObject;

public class API {

    private static final String DOMAIN = "http://gamecloudio.com";
    private static final StringBuilder builder = new StringBuilder(DOMAIN);

    public static JSONObject createToken(String user, String pass) throws APIException {
        return new APIClient(user, pass, path("/token")).get();
    }

    public static void createDeveloper(JSONObject developer) throws APIException {
        new APIClient(path("/developer")).post1(developer);
    }

    public static JSONObject readDeveloper(String id) throws APIException {
        return new APIClient(path("/developer/"+id)).get();
    }

    public static void updateDeveloper(String id, JSONObject developer) throws APIException {
        new APIClient(path("/developer/"+id)).put(developer);
    }

    public static void deleteDeveloper(String id) throws APIException {
        new APIClient(path("/developer/"+id)).delete();
    }

    public static void createGame(String developerId, JSONObject game) throws APIException {
        new APIClient(path("/developer/"+developerId+"/game")).post1(game);
    }

    public static JSONObject readGame(String developerId) throws APIException {
        return new APIClient(path("/developer/"+developerId+"/game")).get();
    }

    public static JSONObject readGame(String developerId, String gameKey) throws APIException {
        return new APIClient(path("/developer/"+developerId+"/game/"+gameKey)).get();
    }

    public static void updateGame(String developerId, String gameKey, JSONObject game) throws APIException {
        new APIClient(path("/developer/"+developerId+"/game/"+gameKey)).put(game);
    }

    public static void deleteGame(String developerId, String gameKey) throws APIException {
        new APIClient(path("/developer/"+developerId+"/game/"+gameKey)).delete();
    }

    private static String path(String resource){
        return builder.append(resource).toString();
    }
}