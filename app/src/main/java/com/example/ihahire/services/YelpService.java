package com.example.ihahire.services;

import com.example.ihahire.models.Shop;
import com.example.ihahire.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Callback;
import retrofit2.Response;

public class YelpService {

    public static void findShops(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YELP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YELP_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.YELP_API_KEY)
                .build();

        Call call = client.newCall(request);
//        call.enqueue((okhttp3.Callback) callback);
    }

    public List<Shop> processResults(Response response){
        List<Shop> shops = new ArrayList<>();
        try{
            String jsonData = response.body().toString();
            JSONObject yelpJSON = new JSONObject(jsonData);
            JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");
            if (response.isSuccessful()){
                for (int i = 0; i < businessesJSON.length(); i++){
                    JSONObject shopJSON = businessesJSON.getJSONObject(i);
                    String name = shopJSON.getString("name");
                    String phone = shopJSON.optString("display_phone", "Phone not available");
                    String website = shopJSON.getString("url");
                    double rating = shopJSON.getDouble("rating");
                    String imageUrl = shopJSON.getString("image_url");
                    double latitude = shopJSON.getJSONObject("coordinates").getDouble("latitude");
                    double longitude = shopJSON.getJSONObject("coordinates").getDouble("longitude");
                    ArrayList<String> address = new ArrayList<>();
                    JSONArray addressJSON = shopJSON.getJSONObject("location").getJSONArray("display_address");
                    for (int y = 0; y < addressJSON.length(); y++){
                        address.add(addressJSON.get(y).toString());
                    }
                    ArrayList<String> categories = new ArrayList<>();
                    JSONArray categoriesJSON = shopJSON.getJSONArray("categories");
                    for (int y = 0; y < categoriesJSON.length(); y++){
                        categories.add(categoriesJSON.getJSONObject(y).getString("title"));
                    }
                    Shop shop = new Shop(name, phone, website, rating,
                            imageUrl, address, latitude, longitude, categories);
                    shops.add(shop);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return shops;
    }



}
