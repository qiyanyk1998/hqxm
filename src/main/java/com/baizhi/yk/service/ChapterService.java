package com.baizhi.yk.service;

import com.baizhi.yk.entity.Chapter;

import java.util.List;
import java.util.Map;

public interface ChapterService {
    public List<Chapter> selecrAll();
    public Map showChapterById(Integer page,Integer rows,String albumId);
    //添加
    public void insert(Chapter chapter);
    //修改
    public void update(Chapter chapter);
    //删除
    public void delect(String[] id);
}
