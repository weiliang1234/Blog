package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.AgreeMapper;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.entity.Agree;
import com.example.demo.model.entity.Article;
import com.example.demo.model.entity.Comment;
import com.example.demo.model.entity.User;
import com.example.demo.model.vo.ArticleVo;
import com.example.demo.service.ArticleService;
import com.example.demo.utils.DataTimeUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private AgreeMapper agreeMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private CommentMapper commentMapper;

    @Override
    @Transactional
    public void agree(String id,String userId) {
        //Article article = articleMapper.selectById(id);
        QueryWrapper<Agree> wrapper = new QueryWrapper<Agree>();
        wrapper.eq("aid", id).eq("uid", userId);
        Agree agree = agreeMapper.selectOne(wrapper);
        if(agree == null) {
            Agree myagree = new Agree();
            myagree.setAid(id);
            myagree.setUid(userId);
            myagree.setAgreeCount(1);
            agreeMapper.insert(myagree);
        }else {
            agreeMapper.delete(wrapper);
        }
        //查询当前文章共有多少点赞量
        QueryWrapper<Agree> wrapper2 = new QueryWrapper<Agree>();
        Integer count = agreeMapper.selectCount(wrapper2.eq("aid", id).eq("agree_count", 1));
        //更新文章点赞量
        Article article = articleMapper.selectById(id);
        article.setAgreeCount(count);
        articleMapper.updateById(article);
    }

    @Override
    public Article save(Article article) {
        article.setAgreeCount(0);
        article.setCommentCount(0);
        article.setCreateAt(DataTimeUtil.getNowTimeString());
        article.setId(UUID.randomUUID().toString());
        articleMapper.insert(article);
        return article;
    }

    @Override
    public List<ArticleVo> findAll(String type) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        if (!type.equals("all")) {
            wrapper.in("type", type);
        }
        return assembly(wrapper);
    }

    @Override
    public List<ArticleVo> search(String value) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.like("content", value).or().like("title", value);
        return assembly(wrapper);
    }

    @Override
    public ArticleVo findById(String id,String userId) {
        Article article = articleMapper.selectById(id);
        User user = userMapper.selectById(article.getUid());
        //查询当前用户是否对文章点赞
        QueryWrapper<Agree> wrapper = new QueryWrapper<Agree>();
        wrapper.eq("aid", id).eq("uid", userId);
        Agree agree = agreeMapper.selectOne(wrapper);
        user.setPassword("");
        user.setPhone("");
        if (agree == null) {
            //没有对此篇文章点赞
            return new ArticleVo(article, user, false);
        }else {
            //已经对此篇文章点赞
            return new ArticleVo(article, user, true);
        }
    }

    @Override
    public ArticleVo findAllByUserId(String uid) {
        QueryWrapper<Article> wrapper = new QueryWrapper<Article>();
        wrapper.eq("uid", uid);
        List<Article> articles = articleMapper.selectList(wrapper);

        User user = userMapper.selectById(uid);
        user.setPassword("");
        user.setPhone("");

        return new ArticleVo(articles, user);
    }

    @Transactional
    @Override
    public int delArticle(String uid, String aid) {
        QueryWrapper<Article> wrapper = new QueryWrapper<Article>();
        wrapper.eq("id", aid).eq("uid", uid);
        articleMapper.delete(wrapper);

        QueryWrapper<Comment> wrapper2 = new QueryWrapper<Comment>();
        wrapper2.eq("uid", uid).eq("aid", aid);
        commentMapper.delete(wrapper2);

        QueryWrapper<Comment> wrapper3 = new QueryWrapper<Comment>();
        wrapper3.eq("uid", uid);
        Integer count = commentMapper.selectCount(wrapper3);
        return count;
    }

    private List<ArticleVo> assembly(QueryWrapper<Article> wrapper) {
        List<Article> articles = articleMapper.selectList(wrapper);
        List<ArticleVo> result = new ArrayList<>();
        for (Article article : articles) {
            User user = userMapper.selectById(article.getUid());
            if (user != null) {
                user.setPassword("");
                user.setPhone("");
                result.add(new ArticleVo(article, user));
            }
        }
        return result;
    }
}
