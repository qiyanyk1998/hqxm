package com.baizhi.yk.service;

import com.baizhi.yk.dao.AdminDao;
import com.baizhi.yk.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceIml implements AdminService {
    @Resource
    AdminDao adminDao;
    @Override
    public Admin selectOne() {
    //构建查询条件
        Admin admin=new Admin();
        admin.setUsername(admin.getUsername());
        //调用dao查询
        Admin admin1 = adminDao.selectOne(admin);
        return admin1;
    }
}
