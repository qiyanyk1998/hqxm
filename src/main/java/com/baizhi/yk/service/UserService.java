package com.baizhi.yk.service;

import com.baizhi.yk.entity.BannerPageDto;
import com.baizhi.yk.entity.MapDto;
import com.baizhi.yk.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    //根据性别查询
   public Integer selectSex(String sex,Integer day);
   //添加
    public void add(User user);
    //地图
    public List<MapDto> selectMap(String sex);
    //查一个
    public User selectOne(String phone);
//    //添加
//    public void insertUser(User user);
    //修改
    public void updateUser(User user);
    //删除
    public void delect(String[] id);
    //分页
    public Map showAllUser(Integer page, Integer rows);
}
