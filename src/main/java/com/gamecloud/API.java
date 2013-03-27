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

    public static JSONObject readDeveloper1(String id, String password) throws APIException {
        return new APIClient(id, password, path("/developer/"+id)).get();
    }

    public static JSONObject readDeveloper2(String id, String token) throws APIException {
        return new APIClient(token, path("/developer/"+id)).get();
    }

    public static void updateDevelope1r(String id, String password, JSONObject developer) throws APIException {
        new APIClient(id, password, path("/developer/"+id)).put(developer);
    }

    public static void updateDeveloper2(String id, String token, JSONObject developer) throws APIException {
        new APIClient(token, path("/developer/"+id)).put(developer);
    }

    public static void deleteDeveloper1(String id, String password) throws APIException {
        new APIClient(id, password, path("/developer/"+id)).delete();
    }

    public static void deleteDeveloper2(String id, String token) throws APIException {
        new APIClient(token, path("/developer/"+id)).delete();
    }

    public static void createGame1(String id, String password, JSONObject game) throws APIException {
        new APIClient(id, password, path("/developer/"+id+"/game")).post1(game);
    }

    public static void createGame2(String id, String token, JSONObject game) throws APIException {
        new APIClient(token, path("/developer/"+id+"/game")).post1(game);
    }

    public static JSONObject readGame1(String id, String password) throws APIException {
        return new APIClient(id, password, path("/developer/"+id+"/game")).get();
    }

    public static JSONObject readGame2(String id, String token) throws APIException {
        return new APIClient(token, path("/developer/"+id+"/game")).get();
    }

    public static JSONObject readGame1(String id, String password, String gameKey) throws APIException {
        return new APIClient(id, password, path("/developer/"+id+"/game/"+gameKey)).get();
    }

    public static JSONObject readGame2(String id, String token, String gameKey) throws APIException {
        return new APIClient(token, path("/developer/"+id+"/game/"+gameKey)).get();
    }

    public static void updateGame1(String id, String password, String gameKey, JSONObject game) throws APIException {
        new APIClient(id, password, path("/developer/"+id+"/game/"+gameKey)).put(game);
    }

    public static void updateGame2(String id, String token, String gameKey, JSONObject game) throws APIException {
        new APIClient(token, path("/developer/"+id+"/game/"+gameKey)).put(game);
    }

    public static void deleteGame1(String id, String password, String gameKey) throws APIException {
        new APIClient(id, password, path("/developer/"+id+"/game/"+gameKey)).delete();
    }

    public static void deleteGame2(String id, String token, String gameKey) throws APIException {
        new APIClient(token, path("/developer/"+id+"/game/"+gameKey)).delete();
    }

    private static String path(String resource){
        return builder.append(resource).toString();
    }
}