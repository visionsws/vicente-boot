package com.vicente.vicenteboot.service;

import com.vicente.vicenteboot.entity.Article;

import java.util.List;

/**
 * @author shiweisen
 * @since 2018-08-09
 */
public interface ArticleService {

    List<Article> getArticleList();

    Article getByTitle(String title);

    Article addArticle(Article article);


}
