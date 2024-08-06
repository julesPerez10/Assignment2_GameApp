package com.example.assignment2game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import com.example.assignment2game.model.Game;

public class DetailsController {

    @FXML
    private ImageView gameImageView;

    @FXML
    private Label gameNameLabel;

    @FXML
    private Label gameDescriptionLabel;

    @FXML
    private Label gameCompanyLabel;

    @FXML
    private Label gameReleaseYearLabel;

    @FXML
    private Label gameRatingLabel;

    @FXML
    private Label gamePlatformsLabel;

    @FXML
    private Label gameGenresLabel;

    @FXML
    private Label gameWebsiteLabel;

    // Method to set game details in the UI
    public void setGame(Game game) {
        gameNameLabel.setText(game.getName());
        gameDescriptionLabel.setText(game.getDescription());
        gameCompanyLabel.setText("Company: " + game.getCompany());
        gameReleaseYearLabel.setText("Release Year: " + game.getReleaseYear());
        gameRatingLabel.setText("Rating: " + game.getRating());
        gamePlatformsLabel.setText("Platforms: " + game.getPlatforms());
        gameGenresLabel.setText("Genres: " + game.getGenres());
        gameWebsiteLabel.setText("Website: " + game.getWebsite());
        gameImageView.setImage(new Image(game.getImageUrl()));
    }

    // Method to close the details window and go back
    @FXML
    private void goBack() {
        Stage stage = (Stage) gameNameLabel.getScene().getWindow();
        stage.close();
    }
}
