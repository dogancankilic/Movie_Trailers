package com.dogancankilic.klasikfilmler.model;

public class Model {

    private String name;
    private String rating;
    private String category;
    private String studio;
    private String image_url;
    private String movie_link;


    public Model() {
    }

    public Model(String name, String rating, String category, String studio, String image_url,String movie_link) {
        this.name = name;
        this.rating = rating;
        this.category = category;
        this.studio = studio;
        this.image_url = image_url;
        this.movie_link = movie_link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getMovie_link() {
        return movie_link;
    }

    public void setMovie_link(String movie_link) {
        this.movie_link = movie_link;
    }
}
