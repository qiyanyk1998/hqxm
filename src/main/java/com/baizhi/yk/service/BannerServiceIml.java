package com.baizhi.yk.service;

import com.baizhi.yk.annotation.LogAnnotation;
import com.baizhi.yk.dao.BannerDao;
import com.baizhi.yk.entity.Banner;
import com.baizhi.yk.entity.BannerPageDto;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BannerServiceIml implements BannerService {
    @Resource
    BannerDao bannerDao;
    @Override
    public List<Banner> querrAll() {
        List<Banner> banners = bannerDao.selectAll();
        return banners;
    }
    //添加
    @LogAnnotation(value = "")
    @Override
    public void insert(Banner banner) {

        bannerDao.insert(banner);


    }

    //修改
    @LogAnnotation(value = "")
    @Override
    public void update(Banner banner) {

        bannerDao.updateByPrimaryKeySelective(banner);

    }

    //删除
    @LogAnnotation(value = "")
    @Override
    public void delect(String[] id) {
         bannerDao.deleteByIdList(Arrays.asList(id));
    }

    @Override
    public BannerPageDto queryByPage(int curPage, int pageSize) {

            //创建dto对象
        BannerPageDto bannerDto = new BannerPageDto();
            //设置当前页
            bannerDto.setPage(curPage);
            //设置总行数
            int count = bannerDao.selectCount(null);
            bannerDto.setRecords(count);
            //设置总页数
            bannerDto.setTotal(count%pageSize==0?count/pageSize:count/pageSize+1);
            //计算下标
            int index = (curPage-1)*pageSize;
            //调用dao层的分页查方法 获取当前页数据
            List<Banner> banners = bannerDao.selectByRowBounds(null, new RowBounds(index, pageSize));
            //设置当前页的所有数据
            bannerDto.setRows(banners);
            return bannerDto;
        }
    public List<Banner> queryBannersByTime() {
        List<Banner> banners = bannerDao.queryBannersByTime();
        return banners;
    }
}
