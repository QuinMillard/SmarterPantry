package com.macadamian.smartpantry.ui.activities;

import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sam on 2016-01-24.
 */
public class RecipeGrabber {
    HttpURLConnection urlConnection;
    URL url;
    JSONObject object;
    InputStream inStream;

    JSONObject getLink(String[] ingredients) {

        StringBuilder urlString = new StringBuilder();
        urlString.append("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients/");
        for (String s : ingredients)
            urlString.append(s + ",");

        try {
            url = new URL(urlString.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.connect();
            inStream = urlConnection.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            String temp, response = "";
            while ((temp = bReader.readLine()) != null) {
                response += temp;
            }
            object = (JSONObject) new JSONTokener(response).nextValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
    return object;
    }

}
