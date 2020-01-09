package com.baizhi.yk.dao;

import com.baizhi.yk.entity.Article;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface ArticleDao extends Mapper<Article> , DeleteByIdListMapper<Article,String> {
}
