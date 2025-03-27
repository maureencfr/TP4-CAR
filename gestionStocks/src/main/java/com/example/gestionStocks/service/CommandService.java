package com.example.gestionStocks.service;

import com.example.gestionStocks.repo.ArticleEntity;
import com.example.gestionStocks.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandService {
    @Autowired
    private ArticleRepository repo;

    public Optional<ArticleEntity> getCommands(Long id){
        return repo.findById(id);
    }

    public String createCommand(String article, int qte){
        ArticleEntity entity= new ArticleEntity(article, qte);
        repo.save(entity);
        return "user";
    }

    public List<ArticleEntity> getCommands(){
        return (List<ArticleEntity>) repo.findAll();
    }

    public ArticleEntity getArticleById(Long id){
        return repo.findById(id).orElse(null);
    }
}