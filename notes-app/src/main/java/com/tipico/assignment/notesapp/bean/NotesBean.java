package com.tipico.assignment.notesapp.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

@JsonIgnoreProperties
@JsonRootName(value = "note")
public class NotesBean implements Serializable {
    private Long id;
    private String title;
    private String author;
    private String day;
    private String month;
    private String text;

    public NotesBean() {
    }

    public NotesBean(Long id, String title, String author, String day, String month, String text) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.day = day;
        this.month = month;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
