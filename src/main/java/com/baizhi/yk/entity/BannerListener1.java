package com.baizhi.yk.entity;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baizhi.yk.dao.BannerDao;
import com.baizhi.yk.util.MyWebAware;

import java.util.ArrayList;

public class BannerListener1 extends AnalysisEventListener<Banner> {

    ArrayList<Banner> banners = new ArrayList<>();

    @Override
    public void invoke(Banner banner, AnalysisContext analysisContext) {
        banners.add(banner);
        System.out.println(banner);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        BannerDao bannerDao = (BannerDao) MyWebAware.getBeanByClass(BannerDao.class);
        bannerDao.insertList(banners);
    }

}
