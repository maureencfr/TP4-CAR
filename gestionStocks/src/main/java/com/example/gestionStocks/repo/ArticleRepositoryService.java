package com.example.gestionStocks.repo;

import org.springframework.stereotype.Service;

@Service
public class ArticleRepositoryService {

    private final ArticleRepository repo;

    public ArticleRepositoryService(ArticleRepository repo) {
        this.repo = repo;
    }

    public void save(ArticleEntity entity){
        this.repo.save(entity);
    }
}