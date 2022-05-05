package com.example.demo.test;

import com.example.demo.controller.ArticleController;
import com.example.demo.model.vo.ArticleVo;
import com.example.demo.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wl
 * @Date 2021/11/11 21:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {
    @Autowired
    ArticleServiceImpl articleService;

    @Test
    public void myTest() {
        final ArticleVo allByUserId = articleService.findAllByUserId("1");
        System.out.println(allByUserId);
    }
}
