package com.vicente.vicenteboot.service.impl;

import com.vicente.vicenteboot.entity.Article;
import com.vicente.vicenteboot.repository.ArticleRepository;
import com.vicente.vicenteboot.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shiweisen
 * @since 2018-08-09
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 获取所有用户列表
     * @return
     */
    public List<Article> getArticleList(){
        List<Article> list =articleRepository.findAll();
        return  list;
    }

    /**
     * 通过姓名获取用户信息
     * @param title 用户姓名
     * @return
     */
    public Article getByTitle(String title) {
        List<Article> list = articleRepository.findByTitle(title);
        if (!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    /**
     * 新增用户信息
     * @param article 用户信息
     * @return
     */
    public  Article addArticle(Article article) {
        return articleRepository.save(article);
    }

}
