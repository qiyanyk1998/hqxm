package com.baizhi.yk.controller;

import com.baizhi.yk.entity.Album;
import com.baizhi.yk.service.AlbumService;
import com.baizhi.yk.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping("album")

public class AlbumController {
    @Autowired
    AlbumService albumService;
    @RequestMapping("showAllAlbums")
    //分页
    public Map showAllAlbums(Integer page,Integer rows){
        Map map = albumService.showAllAlbums(page, rows);
        return map;
    }

    @RequestMapping("selectAll")
    //查所有
    public List<Album> selectAll(){
        List<Album> albums = albumService.selectAll();
        return albums;
    }

    @RequestMapping("editAlbum")
    // oper 标示 album 数据 id 删除的id
    public Map editAlbum(String oper,Album album,String[] id){
        HashMap hashMap = new HashMap();
        // 添加逻辑
        if (oper.equals("add")) {
            String albumId = UUID.randomUUID().toString();
            album.setId(albumId);
            albumService.insert(album);
            hashMap.put("albumId", albumId);
            // 修改逻辑
        } else if (oper.equals("edit")) {
            albumService.update(album);
            hashMap.put("albumId", album.getId());
            // 删除
        } else {
            albumService.delect(id);
        }
        return hashMap;
    }
    @RequestMapping("uploadAlbum")
    public Map uploadAlbum(HttpSession session, MultipartFile cover, String albumId, HttpServletRequest request){
        HashMap hashMap = new HashMap();
        String realPath = session.getServletContext().getRealPath("/upload/albumImg/");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        // 网络路径
        String http = HttpUtil.getHttp(cover, request, "/upload/albumImg/");
        Album album = new Album();
        album.setId(albumId);
        album.setCover(http);
        albumService.update(album);
        hashMap.put("status",200);
        return hashMap;
    }
}
