package com.vicente.vicenteboot.repository;

import com.vicente.vicenteboot.entity.Article;
import com.vicente.vicenteboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author shiweisen
 * @since 2018-08-09
 */
public interface ArticleRepository extends JpaRepository<Article,String> {

    //查询用户名称
    List<Article> findByTitle(String title);

}
