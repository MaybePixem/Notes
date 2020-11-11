package com.example.notes.model;

import com.orm.SugarRecord;

import java.util.List;

public class Note extends SugarRecord {

    private String title;
    private String text;
    private List<String> imagePaths;
    private String folder;

    public Note() {
    }

    public Note(String title, String text, List<String> imagePaths, String folder) {
        this.title = title;
        this.text = text;
        this.imagePaths = imagePaths;
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }
}
