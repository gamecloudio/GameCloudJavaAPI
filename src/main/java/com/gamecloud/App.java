package com.gamecloud;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Example
 *
 */
public class App
{
    public static void main( String[] args ) throws APIException, JSONException
    {
        JSONObject developer = new JSONObject();
        developer.put("id", "baam");
        developer.put("password", "baam");
        developer.put("email", "baam");
        API.createDeveloper(developer);
    }
}