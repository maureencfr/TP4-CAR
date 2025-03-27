package com.example.gestionStocks.repo;

import jakarta.persistence.*;

@Entity
@Table(name = "ACCOUNT")
public class ArticleEntity {

    @Column(name = "ARTICLE")
    private String article;

    @Column(name = "QUANTITE")
    private int quantite;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public ArticleEntity() {}

    public ArticleEntity(String article, int quantite) {
        this.quantite = quantite;
        this.article = article;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
