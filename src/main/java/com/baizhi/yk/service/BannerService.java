package com.baizhi.yk.service;

import com.baizhi.yk.entity.Admin;
import com.baizhi.yk.entity.Banner;
import com.baizhi.yk.entity.BannerPageDto;

import java.util.List;
import java.util.Map;

public interface BannerService {
    //查所有
    public List<Banner> querrAll();

    //添加
    public void insert(Banner banner);
    //修改
    public void update(Banner banner);
    //删除
    public void delect(String[] id);
    //分页
    public BannerPageDto queryByPage(int curPage, int pageSize);
    public List<Banner> queryBannersByTime();
}
