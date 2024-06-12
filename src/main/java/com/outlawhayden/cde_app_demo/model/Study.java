package com.outlawhayden.cde_app_demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "studies")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "study_name")
    private String title;

    @Column(name = "study_description")
    private String description;

    @Column(name = "published")
    private boolean published;

    public Study() {
    }

    public Study(String study_name, String study_description, boolean published) {
        this.title = study_name;
        this.description = study_description;
        this.published = published;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Study [id=" + id + ", title=" + title + ", description=" + description + ", published=" + published
                + "]";
    }
    
    //more methods here
}
