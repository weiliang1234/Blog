package com.example.demo.model.vo;

import com.example.demo.model.entity.Article;
import com.example.demo.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo {

    private Article article;

    private User author;

    //是否对本篇文章点赞
    private boolean flag;

    private List<Article> articles = new ArrayList<>();

    public ArticleVo(Article article, User author) {
        this.article = article;
        this.author = author;
    }

    public ArticleVo(List<Article> articles, User author) {
        this.author = author;
        this.articles = articles;
    }

    public ArticleVo(Article article, User author, boolean flag) {
        this.article = article;
        this.author = author;
        this.flag = flag;
    }
}
