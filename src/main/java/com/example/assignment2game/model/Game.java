package com.example.assignment2game.model;

public class Game {
    private String name;
    private String description;
    private String imageUrl;
    private String id;
    private String company;
    private String releaseYear;
    private String genres;
    private String rating;
    private String platforms;
    private String website;

    // Constructor to initialize the Game object
    public Game(String name, String description, String imageUrl, String id, String company, String releaseYear, String genres, String rating, String platforms, String website) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.id = id;
        this.company = company;
        this.releaseYear = releaseYear;
        this.genres = genres;
        this.rating = rating;
        this.platforms = platforms;
        this.website = website;
    }

    // Getters and setters for the fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
