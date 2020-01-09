package com.baizhi.yk.dao;

import com.baizhi.yk.entity.Banner;
import com.baizhi.yk.entity.MapDto;
import com.baizhi.yk.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User> ,DeleteByIdListMapper<Banner,String>{
    //根据性别查询
    Integer selectSex(@Param("sex") String sex, @Param("day") Integer day);
    //地图
    public List<MapDto> selectMap(String sex);
}
