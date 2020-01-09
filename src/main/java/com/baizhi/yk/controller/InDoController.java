package com.baizhi.yk.controller;

import com.baizhi.yk.dao.AlbumDao;
import com.baizhi.yk.dao.ArticleDao;
import com.baizhi.yk.dao.BannerDao;
import com.baizhi.yk.entity.Album;
import com.baizhi.yk.entity.Article;
import com.baizhi.yk.entity.Banner;
import com.baizhi.yk.entity.User;
import com.baizhi.yk.service.AlbumService;
import com.baizhi.yk.service.ArticleService;
import com.baizhi.yk.service.BannerService;
import com.baizhi.yk.service.UserService;
import com.baizhi.yk.util.SmsUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.RowBounds;
import org.apache.lucene.queryparser.surround.query.SrndPrefixQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("indo")
public class InDoController {
    @Autowired
    UserService userService;
    @Autowired
    Jedis jedis;
    @Autowired
    BannerService bannerService;
    @Autowired
    AlbumService albumService;
    @Autowired
    ArticleService articleService;

    //登入接口
    @RequestMapping("login")
    public Map login(String phone,String password){
        HashedMap hashMap = new HashedMap();
        User user = userService.selectOne(phone);
        System.out.println("+++++"+user);
        if (user==null) {
            hashMap.put("status", -200);
            hashMap.put("mesage", "该用户不存在");
        }else if (user.getStatus()=="0"){
            hashMap.put("status", -200);
            hashMap.put("mesage", "该用户已冻结");
        }else if (!password.equals(user.getPassword())){
            hashMap.put("status",-200);
            hashMap.put("mesage","密码错误");
        }else{
            hashMap.put("status",200);
            hashMap.put("user",user);
        }
        return hashMap;
    }

    //发送验证码
    @RequestMapping("sendCode")
    public Map sendCode(String phone){
        String s = UUID.randomUUID().toString();
        String code = s.substring(0, 3);
        SmsUtil.send(phone,code);
        // 将验证码保存值Redis  Key phone_186XXXX Value code 1分钟有效
        jedis.set("code","code");
        //jedis.set()
        HashMap hashMap = new HashMap();
        hashMap.put("status","200");
        hashMap.put("message","短信发送成功");
        return hashMap;
    }

    //注册接口
    @RequestMapping("logon")
    public Map logon(){
    return null;
    }

    //补充个人信息接口
    @RequestMapping("PersonalIn")
    public Map PersonalIn(String password, String photo, String name, String nickName, String sex,String sign,String location){
        HashedMap hashMap = new HashedMap();
        User user1 = userService.selectOne("18531077092");
        user1.setPassword(password);
        user1.setPhoto(photo);
        user1.setName(name);
        user1.setNickName(nickName);
        user1.setSex(sex);
        user1.setSign(sign);
        user1.setLocation(location);
        userService.updateUser(user1);

        hashMap.put("status","200");
        hashMap.put("user",user1 );
        return hashMap;
    }

    //一级页面展示接口

    @RequestMapping("onePage")
    public Map onePage(String uid,String type,String sub_type){
        HashMap hashMap = new HashMap();
        try {
            if (type.equals("all")){
                List<Banner> banners = bannerService.queryBannersByTime();
                List<Album> albums = albumService.selectByRowBounds();
                List<Article> articles = articleService.selectAllArticle();
                hashMap.put("status",200);
                hashMap.put("head",banners);
                hashMap.put("albums",albums);
                hashMap.put("articles",articles);
            }else if (type.equals("wen")){
                List<Album> albums = albumService.selectByRowBounds();
                hashMap.put("status",200);
                hashMap.put("albums",albums);
            }else {
                if (sub_type.equals("ssyj")){
                    List<Article> articles = articleService.selectAllArticle();
                    hashMap.put("status",200);
                    hashMap.put("articles",articles);
                }else {
                    List<Article> articles = articleService.selectAllArticle();
                    hashMap.put("status",200);
                    hashMap.put("articles",articles);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            hashMap.put("status","-200");
            hashMap.put("message","error");
        }

        return hashMap;
    }

    //6. 文章详情接口
    @RequestMapping("details")
    public Map  details(String id,String guruId){
        HashMap hashMap = new HashMap();
        Article article = articleService.selectOne(id, guruId);
        hashMap.put("status",200);
        hashMap.put("article",article);
        return hashMap;
    }

    //7. 专辑详情接口
@RequestMapping("albuma")
    public Map albuma(String id){
    HashMap hashMap = new HashMap();
    Album album = albumService.selectOne(id);
    hashMap.put("status",200);
    hashMap.put("album",album);
    return hashMap;
}
    //8. 展示功课
}

