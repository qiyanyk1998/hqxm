package com.baizhi.yk.dao;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import com.baizhi.yk.entity.Banner;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BannerDao extends Mapper<Banner>, DeleteByIdListMapper<Banner,String>,InsertListMapper<Banner> {
    public List<Banner> queryBannersByTime();

}
