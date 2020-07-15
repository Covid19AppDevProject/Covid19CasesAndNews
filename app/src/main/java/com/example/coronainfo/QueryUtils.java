package com.example.coronainfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils   {
    //we use
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();
    //to make consistent the logtag


    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */



    private QueryUtils(){

}


 private static List<Earth>extractJsonRespose(String earthJson) {
        if(TextUtils.isEmpty(earthJson)){
            return null;
        }

     List<Earth> earthquakes=new ArrayList<Earth>();
    try {
        JSONArray jsonroot = new JSONArray(earthJson);

        for (int i = 0; i <jsonroot.length(); i++) {


            JSONObject firstJson = jsonroot.getJSONObject(i);
            String country = firstJson.getString("country");
            long cases = firstJson.getLong("cases");
            long todaycases = firstJson.getLong("todayCases");
            long deaths = firstJson.getLong("deaths");
            long todaydeaths = firstJson.getLong("todayDeaths");
            long recovered = firstJson.getLong("recovered");
            long active = firstJson.getLong("active");
            long critical = firstJson.getLong("critical");
            Earth earth = new Earth(country, cases, todaycases, deaths, todaydeaths, recovered, active, critical);
            earthquakes.add(earth);

        }



    }
        /*
        firstJson=jsonroot.getJSONObject(7);
        country=firstJson.getString("country");
        cases=firstJson.getLong("cases");
        todaycases=firstJson.getLong("todayCases");
        deaths=firstJson.getLong("deaths");
        todaydeaths=firstJson.getLong("todayDeaths");
        recovered=firstJson.getLong("recovered");
        active=firstJson.getLong("active");
        critical=firstJson.getLong("critical");
        earth=new Earth(country,cases,todaycases,deaths,todaydeaths,recovered,active,critical);
        earthquakes.add(earth);
        firstJson=jsonroot.getJSONObject(8);
        country=firstJson.getString("country");
        cases=firstJson.getLong("cases");
        todaycases=firstJson.getLong("todayCases");
        deaths=firstJson.getLong("deaths");
        todaydeaths=firstJson.getLong("todayDeaths");
        recovered=firstJson.getLong("recovered");
        active=firstJson.getLong("active");
        critical=firstJson.getLong("critical");
        earth=new Earth(country,cases,todaycases,deaths,todaydeaths,recovered,active,critical);
        earthquakes.add(earth);
        firstJson=jsonroot.getJSONObject(9);
        country=firstJson.getString("country");
        cases=firstJson.getLong("cases");
        todaycases=firstJson.getLong("todayCases");
        deaths=firstJson.getLong("deaths");
        todaydeaths=firstJson.getLong("todayDeaths");
        recovered=firstJson.getLong("recovered");
        active=firstJson.getLong("active");
        critical=firstJson.getLong("critical");
        earth=new Earth(country,cases,todaycases,deaths,todaydeaths,recovered,active,critical);
        earthquakes.add(earth);
        firstJson=jsonroot.getJSONObject(10);
        country=firstJson.getString("country");
        cases=firstJson.getLong("cases");
        todaycases=firstJson.getLong("todayCases");
        deaths=firstJson.getLong("deaths");
        todaydeaths=firstJson.getLong("todayDeaths");
        recovered=firstJson.getLong("recovered");
        active=firstJson.getLong("active");
        critical=firstJson.getLong("critical");
        earth=new Earth(country,cases,todaycases,deaths,todaydeaths,recovered,active,critical);
        earthquakes.add(earth);
        firstJson=jsonroot.getJSONObject(11);
        country=firstJson.getString("country");
        cases=firstJson.getLong("cases");
        todaycases=firstJson.getLong("todayCases");
        deaths=firstJson.getLong("deaths");
        todaydeaths=firstJson.getLong("todayDeaths");
        recovered=firstJson.getLong("recovered");
        active=firstJson.getLong("active");
        critical=firstJson.getLong("critical");
        earth=new Earth(country,cases,todaycases,deaths,todaydeaths,recovered,active,critical);
        earthquakes.add(earth);
        firstJson=jsonroot.getJSONObject(12);
        country=firstJson.getString("country");
        cases=firstJson.getLong("cases");
        todaycases=firstJson.getLong("todayCases");
        deaths=firstJson.getLong("deaths");
        todaydeaths=firstJson.getLong("todayDeaths");
        recovered=firstJson.getLong("recovered");
        active=firstJson.getLong("active");
        critical=firstJson.getLong("critical");
        earth=new Earth(country,cases,todaycases,deaths,todaydeaths,recovered,active,critical);
        earthquakes.add(earth);
        firstJson=jsonroot.getJSONObject(13);
        country=firstJson.getString("country");
        cases=firstJson.getLong("cases");
        todaycases=firstJson.getLong("todayCases");
        deaths=firstJson.getLong("deaths");
        todaydeaths=firstJson.getLong("todayDeaths");
        recovered=firstJson.getLong("recovered");
        active=firstJson.getLong("active");
        critical=firstJson.getLong("critical");
        earth=new Earth(country,cases,todaycases,deaths,todaydeaths,recovered,active,critical);
        earthquakes.add(earth);
 */


    catch(JSONException e){
        Log.e("QueryUtils","problem in parsing json",e);

    }

       return  earthquakes; }


    public static List<Earth> fetchEarthquakeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<Earth> earthquakes = extractJsonRespose(jsonResponse);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static  String makeHttpRequest(URL url) throws IOException {
         String JsonResponse="";
         if(url==null){
             return JsonResponse;
         }
           HttpURLConnection urlconnection = null;
           InputStream inputStream = null;
         try {
             urlconnection = (HttpURLConnection) url.openConnection();
             urlconnection.setReadTimeout(40000);
             urlconnection.setConnectTimeout(15000);
             urlconnection.setRequestMethod("GET");
             urlconnection.connect();
             if (urlconnection.getResponseCode() == 200) {
                 inputStream = urlconnection.getInputStream();
                 JsonResponse = readFromStream(inputStream);
             }
         }
             catch( IOException e){
                 Log.e(LOG_TAG,"empty jsonResponse",e);
             }
            finally{
                 if(urlconnection!=null){
                     urlconnection.disconnect();
             }
                 if(inputStream!=null)
                     inputStream.close();
                 }
         return JsonResponse;
         }

    private static String readFromStream(InputStream inputStream) throws IOException{
         StringBuilder output=new StringBuilder();
         if(inputStream!=null){
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader input=new BufferedReader(inputStreamReader);
        //readline method read a single line of text from the console  that is store in the object of bufferdreader
             String line=input.readLine();
         while(line!=null){
             output.append(line);
             line=input.readLine();
         }

         }
         //ouput is an object or instance which is string representation so we invoke the tostring()
        return output.toString();

    }



}
