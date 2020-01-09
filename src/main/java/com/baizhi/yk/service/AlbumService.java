package com.baizhi.yk.service;

import com.baizhi.yk.entity.Admin;
import com.baizhi.yk.entity.Album;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    public Map showAllAlbums(Integer page, Integer rows);
    public List<Album> selectAll();
    //添加
    public void insert(Album album);
    //修改
    public void update(Album album);
    //删除
    public void delect(String[] id);
    public List selectByRowBounds();
    //查一个
    public Album selectOne(String id);

}
