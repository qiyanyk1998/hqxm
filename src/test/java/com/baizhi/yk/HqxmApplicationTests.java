package com.baizhi.yk;


import com.baizhi.yk.dao.AdminDao;
import com.baizhi.yk.dao.BannerDao;
import com.baizhi.yk.entity.Admin;
import com.baizhi.yk.entity.Banner;
import com.baizhi.yk.entity.User;
import com.baizhi.yk.service.BannerService;
import com.baizhi.yk.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class HqxmApplicationTests {
    @Autowired
    AdminDao adminDao;
    @Autowired
    BannerDao bannerDao;
    @Autowired
    BannerService bannerService;
    @Autowired
    UserService userService;
    @Autowired
    Jedis jedis;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
   public void contextLoads() {

   }

    //查所有
    @Test
    public void selectall() {
        List<Admin> admins = adminDao.selectAll();
        System.out.println(admins);
    }
        //查一个
    @Test
    public void selectOne(){
        Admin admin = new Admin("1", "admin", "admin");
        adminDao.selectOne(admin);
        System.out.println(admin);
    }

    //查所有
    @Test
    public void selectAll() {
        List<Banner> banners = bannerDao.selectAll();
        System.out.println(banners);
    }

    //删除
    @Test
    public void delect(){
        Banner banner = new Banner();
        bannerDao.delete(banner);

    }

    @Test
    public void insert(){
        Banner banner = new Banner("1,", "哑巴", null, null, new Date(), "我可爱呀", "1");
        bannerDao.insert(banner);
    }

@Test
    public void selectOneUser(){
    User user1 = userService.selectOne("18531077092");
    System.out.println(user1);
    }

    @Test
    public void testString() {
        String set = jedis.set("aa", "bb");
        String aa = jedis.get("aa");
        System.out.println(aa);
    }

    @Test
    public void testKey(){
        stringRedisTemplate.opsForValue().set("name","yangke");

    }

    @Test
    public void testKey1(){
        stringRedisTemplate.opsForValue().get("name");

    }
}
