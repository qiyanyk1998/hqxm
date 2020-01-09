package com.baizhi.yk.service;

import com.baizhi.yk.dao.ArticleDao;
import com.baizhi.yk.entity.Album;
import com.baizhi.yk.entity.Article;
import com.baizhi.yk.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ArticleServiceIml implements ArticleService {
    @Resource
    ArticleDao articleDao;
    //分页
    public Map showAllArticle(Integer page, Integer rows){
        HashMap hashMap = new HashMap();
        int i = articleDao.selectCount(null);
        Integer total = i%rows==0?i/rows:i/rows+1;
        List<Article> articles = articleDao.selectByRowBounds(null, new RowBounds((page - 1) * rows,rows));
        hashMap.put("records",i);
        hashMap.put("total",total);
        hashMap.put("rows",articles);
        hashMap.put("page",page);
        return hashMap;
    }

    @Override
    public void insert(Article article) {

        articleDao.insert(article);
    }

    @Override
    public void update(Article article) {
        articleDao.updateByPrimaryKeySelective(article);
    }

    @Override
    public void delect(String[] id) {
        articleDao.deleteByIdList(Arrays.asList(id));
    }

    @Override
    public List selectAllArticle() {
        List<Article> articles = articleDao.selectAll();
        return articles;
    }

    @Override
    public Article selectOne(String uid, String id) {
        Article article = new Article();
        article.setId(uid);
        article.setGuruId(id);
        Article article1 = articleDao.selectOne(article);
        return article1;
    }

}
