package com.baizhi.yk.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.baizhi.yk.entity.MapDto;
import com.baizhi.yk.entity.User;
import com.baizhi.yk.service.UserService;
import com.baizhi.yk.util.HttpUtil;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("selectSex")
    public Map selectSex(){
        HashMap hashMap = new HashMap();
        ArrayList manList = new ArrayList();
        manList.add(userService.selectSex("0",1));
        manList.add(userService.selectSex("0",7));
        manList.add(userService.selectSex("0",30));
        manList.add(userService.selectSex("0",365));
        ArrayList womenList = new ArrayList();
        womenList.add(userService.selectSex("1",1));
        womenList.add(userService.selectSex("1",7));
        womenList.add(userService.selectSex("1",30));
        womenList.add(userService.selectSex("1",365));
        hashMap.put("man",manList);
        hashMap.put("women",womenList);
        System.out.println(hashMap);
        return hashMap;
    }
    //添加
    @RequestMapping("addUser")
    public void addUser(){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setSex("0");
        user.setLocation("北京");
        user.setRigestDate(new Date());
        userService.add(user);
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-1fc6b6af4acd4505bc6f7cc97f74525e");
        Map map = selectSex();
        String s = JSONUtils.toJSONString(map);
        System.out.println(s);
        goEasy.publish("cmfz", s);
    }

    //地图
    @RequestMapping("selectSexMap")
    public Map selectSexDu(){
        System.out.println("+++++++++++++");
        HashMap hashMap = new HashMap();
        List manList = userService.selectMap("0");
        List womenList = userService.selectMap("1");
        hashMap.put("man",manList);
        hashMap.put("women",womenList);
        System.out.println(hashMap);
        return hashMap;
    }

    @RequestMapping("editUser")
    public Map editUser(String[] id, User user, HttpServletRequest request, String oper) {
        HashMap hashMap = new HashMap();
        if (oper.equals("add")) {
            String userId = UUID.randomUUID().toString();
            user.setId(userId);
            hashMap.put("status", 200);
            hashMap.put("userId", userId);
            userService.add(user);
        } else if (oper.equals("edit")) {
            user.setPhoto(null);
            hashMap.put("userId", user.getId());
            userService.updateUser(user);
        } else {
            userService.delect(id);
        }
        return hashMap;
    }

    @RequestMapping("showAllAlbums")
    //分页
    public Map showAllAlbums(Integer page,Integer rows){
        Map map =userService.showAllUser(page, rows);
        return map;
    }

    @RequestMapping("/upload")
    public Map upload(MultipartFile photo, String userId, HttpServletRequest request){
//        调用工具类获取上传图片 并返回uri
        String uri = HttpUtil.getHttp(photo, request,"/upload/userImg/");
        User user = new User();
        user.setId(userId);
        user.setPhoto(uri);
        userService.updateUser(user);
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        return hashMap;
    }
}
