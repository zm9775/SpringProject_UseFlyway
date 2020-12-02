package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "keywords")
public class Keyword {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "keywords",fetch = FetchType.EAGER)
    private List<Document> documents = new ArrayList<>();

    public Keyword() {
    }

    public Keyword(int id, String title) {
        this.id = id;
        this.title = title;
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

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
