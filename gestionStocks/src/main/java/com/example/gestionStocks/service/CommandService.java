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

    public void updateStockFromCommand(String message) {
        String[] parts = message.split(";");
        if (parts.length < 2) return;

            for (int i = 1; i < parts.length; i++) {
                String[] productData = parts[i].split(",");
                if (productData.length != 2) continue;

                String productName = productData[0];
                int quantityOrdered = Integer.parseInt(productData[1]);

                ArticleEntity article = repo.findByArticle(productName)
                        .orElse(null);

                if(article != null){
                    article.setQuantite(article.getQuantite() - quantityOrdered);
                    repo.save(article);
                }

            }
    }
}