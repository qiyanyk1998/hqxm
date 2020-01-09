package com.baizhi.yk.service;

import com.baizhi.yk.entity.Article;
import com.baizhi.yk.entity.User;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    //分页
    public Map showAllArticle(Integer page, Integer rows);

    //添加
    public void insert(Article article);

    //修改
    public void update(Article article);

    //删除
    public void delect(String[] id);

    //查所有
    public List selectAllArticle();
    //查一个
    public Article selectOne(String uid,String id);

}
