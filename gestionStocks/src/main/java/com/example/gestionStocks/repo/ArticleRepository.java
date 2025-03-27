package com.example.gestionStocks.repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
    public Optional<ArticleEntity> findByArticle(String articleName);
}