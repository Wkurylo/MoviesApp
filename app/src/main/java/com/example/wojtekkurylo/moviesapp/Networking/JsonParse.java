//package com.example.wojtekkurylo.moviesapp.Networking;
//
//import android.util.Log;
//
//import com.example.wojtekkurylo.moviesapp.Model.MovieComponent;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
///**
// * {@link JsonParse} perform JSON parse;
// */
//
//public class JsonParse {
//
//
//    public static ArrayList<MovieComponent> extractNews(String jsonResponse)
//    {
//        // If the JSON string is empty or null, then return early.
//        if (jsonResponse.isEmpty()) {
//            return null;
//        }
//
//        ArrayList<MovieComponent> movieArray = new ArrayList<>();
//        try{
//
//            JSONObject rootObject = new JSONObject(jsonResponse);
//            JSONArray resultsArray = rootObject.getJSONArray("results");
//
//            for (int i = 0; i < resultsArray.length(); i++) {
//
//                JSONObject currentObject = resultsArray.getJSONObject(i);
//
//                String title = currentObject.getString("title");
//                String releaseDate = currentObject.getString("release_date");
//                String posterUrl = currentObject.getString("poster_path");
//                Number average = currentObject.getDouble("vote_average");
//                String overview = currentObject.getString("overview");
//
//                // Create Movie Object
//                MovieComponent movie = new MovieComponent(title, releaseDate, posterUrl, average, overview);
//                // Add created Objects to ArrayList<MovieComponent> @ i-th position
//                movieArray.add(i, movie);
//            }
//
//
//
//        }catch (JSONException e) {
//            // If any of above Object || Array isEmpty then print Log && exit without crashing
//            Log.e("JsonParse", "Error in extractNews method in JsonParse Class", e);
//        }
//
//        return movieArray;
//    }
//
//}
