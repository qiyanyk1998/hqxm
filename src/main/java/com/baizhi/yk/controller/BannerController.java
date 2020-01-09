package com.baizhi.yk.controller;

import com.alibaba.excel.EasyExcel;
import com.baizhi.yk.entity.Banner;
import com.baizhi.yk.entity.BannerListener1;
import com.baizhi.yk.entity.BannerPageDto;
import com.baizhi.yk.service.BannerService;
import com.baizhi.yk.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    BannerService bannerService;
    @ResponseBody
    @RequestMapping("querryAll")
public List<Banner> querryAll(){
    List<Banner> banners = bannerService.querrAll();
    return banners;
}

    //jqgrid的增删改查处理
    @RequestMapping("save")
    @ResponseBody
    public Map save(Banner banner,String oper,String[] id){
        HashMap hashMap = new HashMap();
        if("add".equals(oper)){
            String bannerId = UUID.randomUUID().toString();
            banner.setId(bannerId);
            bannerService.insert(banner);
            hashMap.put("bannerId", bannerId);
        }else if("edit".equals(oper)) {
            banner.setUrl(null);
            bannerService.update(banner);
            hashMap.put("bannerId", banner.getId());
        }else {
            bannerService.delect(id);
        }
        return hashMap;
    }

    @RequestMapping("loadBanner")
    public Map uploadBanner(MultipartFile url, String bannerId, HttpSession session, HttpServletRequest request){
        HashMap hashMap = new HashMap();
        System.out.println("_______________");
        String http = HttpUtil.getHttp(url, request, "/upload/img/");
        // 文件上传 工具类完成
        // 更新数据库信息
        Banner banner = new Banner();
            banner.setId(bannerId);
            banner.setUrl(http);
            bannerService.update(banner);
        hashMap.put("status",200);
        return hashMap;
    }

    //第一个参数 当前页 第二个参数 当前页有多少行
    @RequestMapping("queryByPage")
    @ResponseBody
    public BannerPageDto queryByPage(int page, int rows){
        BannerPageDto bannerDto = bannerService.queryByPage(page, rows);
        return  bannerDto;
    }

    //导出
    @RequestMapping("dc")
    public void dc(){
        List<Banner> banners = bannerService.querrAll();
        String fileName="E://easyexcel.xls";
        EasyExcel.write(fileName,Banner.class
        ).sheet("测试")
                .doWrite(banners);
    }

    //EasyExcel导入轮播图信息
    @RequestMapping("/importBanner")
    @ResponseBody
    public Map importBanner(MultipartFile inputBanner,HttpServletRequest request){
        String http = HttpUtil.getHttp(inputBanner, request, "/upload/importBanner/");
        String s = http.split("/")[http.split("/").length - 1];
        String realPath = request.getSession().getServletContext().getRealPath("/upload/importBanner/");
        String path = realPath+s;
        EasyExcel.read(path,Banner.class,new BannerListener1()).sheet().doRead();
        HashMap hashMap = new HashMap();
        hashMap.put("status",200);
        return hashMap;
    }
    @RequestMapping("downloadBanner")
    public void downloadBanner(){
        Banner banner = new Banner();
        String fileName = "D:\\idea\\xaingmu\\hqxm\\src\\main\\webapp\\upload\\img\\"+new Date().getTime()+".xls";
        EasyExcel.write(fileName,Banner.class).sheet("banner").doWrite(Arrays.asList(banner));
    }
}
