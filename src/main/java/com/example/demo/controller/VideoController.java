package com.example.demo.controller;

import com.example.demo.mapper.VideoMapper;
import com.example.demo.model.entity.Video;
import com.example.demo.model.support.ResponseResult;
import com.example.demo.model.vo.ArticleVo;
import com.example.demo.service.VideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Resource
    private VideoService videoService;

    @GetMapping("")
    public List<Video> findAll() {
        return videoService.findAll();
    }

    @PostMapping("")
    public void save(@RequestBody Video video) {
        videoService.save(video);
    }

    // 通过用户id查询他所有的视频
    @GetMapping("/uid/{uid}")
    public List<Video> findAllByUserId(@PathVariable String uid) {
        return videoService.findAllByUserId(uid);
    }

    /**
     * 删除视频
     * @param uid
     * @param vid
     */
    @GetMapping("/{uid}/{vid}")
    public void delVideo(@PathVariable String uid, @PathVariable String vid) {
        videoService.delVideo(uid,vid);
    }
}
