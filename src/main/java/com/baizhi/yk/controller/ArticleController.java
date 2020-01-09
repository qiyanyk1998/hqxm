package com.baizhi.yk.controller;

import com.baizhi.yk.entity.Article;
import com.baizhi.yk.service.ArticleService;
import com.baizhi.yk.util.HttpUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    //分页
    @RequestMapping("showAllArticle")
    public Map showAllArticle(Integer page,Integer rows){
        Map map = articleService.showAllArticle(page, rows);
        return map;
    }

    //上传
    @RequestMapping("uploadImg")
    public Map uploadImg(MultipartFile imgFile, HttpSession session, HttpServletRequest request){
        HashMap hashMap = new HashMap();
        String realPath = session.getServletContext().getRealPath("/upload/uploadImg");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        //网络路径
        try {
            String http = HttpUtil.getHttp(imgFile, request, "/upload/articleImg");
            hashMap.put("error", 0);
            hashMap.put("url", http);
        }catch(Exception e) {
            hashMap.put("error", 1);
            e.printStackTrace();
        }
        return hashMap;
    }

    @RequestMapping("showAllImg")
    public Map showAllImg(HttpServletRequest request,HttpSession session){
        HashMap hashMap = new HashMap();
        hashMap.put("current_url",request.getContextPath()+"/upload/articleImg/");
        String realPath = session.getServletContext().getRealPath("/upload/articleImg/");
        File file = new File(realPath);
        File[] files = file.listFiles();
        hashMap.put("total_count",files.length);
        ArrayList arrayList = new ArrayList();
        for (File file1 : files) {
            HashMap fileMap = new HashMap();
            fileMap.put("is_dir",false);
            fileMap.put("has_file",false);
            fileMap.put("filesize",file1.length());
            fileMap.put("is_photo",true);
            String name = file1.getName();
            String extension = FilenameUtils.getExtension(name);
            fileMap.put("filetype",extension);
            fileMap.put("filename",name);
            // 通过字符串拆分获取时间戳
            String time = name.split("_")[0];
            // 创建SimpleDateFormat对象 指定yyyy-MM-dd hh:mm:ss 样式
            //  simpleDateFormat.format() 获取指定样式的字符串(yyyy-MM-dd hh:mm:ss)
            // format(参数)  参数:时间类型   new Date(long类型指定时间)long类型  现有数据:字符串类型时间戳
            // 需要将String类型 转换为Long类型 Long.valueOf(str);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(new Date(Long.valueOf(time)));
            fileMap.put("datetime",format);
            arrayList.add(fileMap);
        }
        hashMap.put("file_list",arrayList);
        return hashMap;
    }

    @RequestMapping("delect")
    public void  delect(String[]id){
        articleService.delect(id);
    }

    @RequestMapping("insertArticle")
    public Map insertArticle(Article article, MultipartFile inputfile, HttpServletRequest request){
        HashMap hashMap=new HashMap();
        if (article.getId()==null ||"".equals(article.getId())){
            System.out.println(article.getId());
            //添加
            String http = HttpUtil.getHttp(inputfile, request, "/upload/articleImg/");
            String s = UUID.randomUUID().toString();
            article.setId(s);
            article.setImg(http);
            article.setCreateDate(new Date());
            article.setPublishDate(new Date());
            articleService.insert(article);
        }else{
            // 修改
            String http = HttpUtil.getHttp(inputfile, request, "/upload/articleImg/");
            article.setImg(http);
            articleService.update(article);
        }
        System.out.println(article);
        System.out.println(inputfile);
        return hashMap;
    }

}
