package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.VideoMapper;
import com.example.demo.model.entity.Article;
import com.example.demo.model.entity.Video;
import com.example.demo.service.VideoService;
import com.example.demo.utils.DataTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoMapper videoMapper;

    @Override
    public void save(Video video) {
        video.setCreateAt(DataTimeUtil.getNowTimeString());
        videoMapper.insert(video);
    }

    @Override
    public List<Video> findAll() {
        return videoMapper.selectList(null);
    }

    @Override
    public List<Video> findAllByUserId(String uid) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        return videoMapper.selectList(wrapper);
    }

    @Override
    public void delVideo(String uid, String vid) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid).eq("id", vid);
        videoMapper.delete(wrapper);
    }
}
