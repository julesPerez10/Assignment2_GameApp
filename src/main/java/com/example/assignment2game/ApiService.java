package com.example.assignment2game;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ApiService {
    private static final OkHttpClient client = new OkHttpClient();

    // Method to fetch games from the API using the search query
    public static String fetchGames(String query) throws IOException {
        String apiKey = "411569e40e194ebfa5356fcebed148c1"; // Your API key
        String url = "https://api.rawg.io/api/games?key=" + apiKey + "&search=" + query;
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
