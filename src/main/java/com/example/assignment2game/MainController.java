package com.example.assignment2game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.example.assignment2game.model.Game;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MainController {

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> listView;

    private ObservableList<Game> games = FXCollections.observableArrayList();

    private static final String API_URL = "https://api.rawg.io/api/games?key=411569e40e194ebfa5356fcebed148c1&search=";
    private static final String DETAILS_API_URL = "https://api.rawg.io/api/games/";

    // Method to search games based on user input
    @FXML
    public void searchGames() {
        String query = searchField.getText().trim();
        if (!query.isEmpty()) {
            try {
                String jsonResponse = fetchGames(query);
                parseAndDisplayGames(jsonResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to fetch games from the API using the search query
    private String fetchGames(String query) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL + query)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                return response.body().string();
            } else {
                return "";
            }
        }
    }

    // Method to parse JSON response and display game names in the ListView
    private void parseAndDisplayGames(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonArray results = jsonObject.getAsJsonArray("results");
        games.clear();
        for (int i = 0; i < results.size(); i++) {
            JsonObject gameJson = results.get(i).getAsJsonObject();
            String name = gameJson.get("name").getAsString();
            String imageUrl = gameJson.get("background_image").getAsString();
            String id = gameJson.get("id").getAsString();
            games.add(new Game(name, "Fetching description...", imageUrl, id, "Fetching company...", "Fetching release year...", "Fetching genres...", "Fetching rating...", "Fetching platforms...", "Fetching website..."));
        }
        ObservableList<String> gameNames = FXCollections.observableArrayList();
        for (Game game : games) {
            gameNames.add(game.getName());
        }
        listView.setItems(gameNames);
    }

    // Method to display game details in a new window when a game is selected
    @FXML
    private void displayGameDetails() {
        String selectedGameName = listView.getSelectionModel().getSelectedItem();
        if (selectedGameName != null) {
            for (Game game : games) {
                if (game.getName().equals(selectedGameName)) {
                    try {
                        String jsonResponse = fetchGameDetails(game.getId());
                        JsonObject gameDetailsJson = JsonParser.parseString(jsonResponse).getAsJsonObject();
                        game.setDescription(gameDetailsJson.has("description_raw") ? gameDetailsJson.get("description_raw").getAsString() : "No description available.");
                        game.setCompany(gameDetailsJson.has("developers") ? gameDetailsJson.getAsJsonArray("developers").get(0).getAsJsonObject().get("name").getAsString() : "No company available.");
                        game.setReleaseYear(gameDetailsJson.has("released") ? gameDetailsJson.get("released").getAsString().substring(0, 4) : "No release year available.");
                        game.setGenres(parseGenres(gameDetailsJson.getAsJsonArray("genres")));
                        game.setRating(gameDetailsJson.has("rating") ? gameDetailsJson.get("rating").getAsString() : "No rating available.");
                        game.setPlatforms(parsePlatforms(gameDetailsJson.getAsJsonArray("platforms")));
                        game.setWebsite(gameDetailsJson.has("website") ? gameDetailsJson.get("website").getAsString() : "No website available.");

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/assignment2game/DetailsView.fxml"));
                        Parent root = loader.load();
                        DetailsController detailsController = loader.getController();
                        detailsController.setGame(game);
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL); // Ensure the window is modal
                        stage.setTitle(game.getName());
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add(getClass().getResource("/com/example/assignment2game/styles.css").toExternalForm());
                        stage.setScene(scene);
                        stage.setMinWidth(800); // Minimum width
                        stage.setMinHeight(600); // Minimum height
                        stage.setResizable(true); // Allow resizing
                        stage.setMaximized(true); // Open in full screen
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    // Method to fetch game details from the API using the game ID
    private String fetchGameDetails(String gameId) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(DETAILS_API_URL + gameId + "?key=411569e40e194ebfa5356fcebed148c1")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                return response.body().string();
            } else {
                return "";
            }
        }
    }

    // Method to parse genres from JSON array to a string
    private String parseGenres(JsonArray genresArray) {
        StringBuilder genres = new StringBuilder();
        for (int i = 0; i < genresArray.size(); i++) {
            JsonObject genreObj = genresArray.get(i).getAsJsonObject();
            genres.append(genreObj.get("name").getAsString());
            if (i < genresArray.size() - 1) {
                genres.append(", ");
            }
        }
        return genres.toString();
    }

    // Method to parse platforms from JSON array to a string
    private String parsePlatforms(JsonArray platformsArray) {
        StringBuilder platforms = new StringBuilder();
        for (int i = 0; i < platformsArray.size(); i++) {
            JsonObject platformObj = platformsArray.get(i).getAsJsonObject().getAsJsonObject("platform");
            platforms.append(platformObj.get("name").getAsString());
            if (i < platformsArray.size() - 1) {
                platforms.append(", ");
            }
        }
        return platforms.toString();
    }
}
