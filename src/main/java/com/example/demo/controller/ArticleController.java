package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.constant.ArticleType;
import com.example.demo.model.entity.Article;
import com.example.demo.model.support.ResponseResult;
import com.example.demo.model.vo.ArticleVo;
import com.example.demo.service.ArticleService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/type/all")
    public String[] findAllType() {
        return ArticleType.list;
    }

    /**
     * 根据文章id点赞,如果当前用户没有对当前文章点过赞,则点赞时点赞数加1,否则点赞数减1
     * @param id 文章id
     * @param userId 用户id
     */
    @GetMapping("/agree/{id}/{userId}")
    public void agree(@PathVariable String id,@PathVariable String userId) {
        articleService.agree(id,userId);
    }

    @GetMapping("/search/{value}")
    public List<ArticleVo> search(@PathVariable String value) {
        return articleService.search(value);
    }

    @PostMapping("")
    public Article save(@RequestBody Article article) {
        return articleService.save(article);
    }

    @GetMapping("")
    public List<ArticleVo> findAll(String type) {
        return articleService.findAll(type);
    }

    /**
     * 根据文章id查询文章详情,再根据当前用户id判断是否对查看用户点赞
     * @param id 文章id
     * @param userId 用户id
     * @return
     */
    @GetMapping("/{id}/{userId}")
    public ArticleVo findById(@PathVariable String id,@PathVariable String userId) {
        return articleService.findById(id,userId);
    }

    // 通过用户id查询他所有的文章
    @GetMapping("/uid/{uid}")
    public ArticleVo findAllByUserId(@PathVariable String uid) {
        return articleService.findAllByUserId(uid);
    }

    /**
     * 删除文章
     * @param uid
     * @param aid
     */
    @GetMapping("/del/{uid}/{aid}")
    public int delArticle(@PathVariable String uid, @PathVariable String aid) {
        return articleService.delArticle(uid,aid);
    }
}
