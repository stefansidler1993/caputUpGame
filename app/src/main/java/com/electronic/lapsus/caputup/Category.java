package com.electronic.lapsus.caputup;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

    private int id;
    private String title;
    private List<String> words;
    private String imageUrl;

    public Category(int id, String title, List<String> words, String imgUrl) {
        this.id = id;
        this.title = title;
        this.words = words;
        this.imageUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
