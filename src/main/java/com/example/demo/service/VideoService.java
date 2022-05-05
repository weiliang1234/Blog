package com.example.demo.service;

import com.example.demo.model.entity.Video;

import java.util.List;

public interface VideoService {

    void save(Video video);

    List<Video> findAll();

    List<Video> findAllByUserId(String uid);

    void delVideo(String uid, String vid);
}
