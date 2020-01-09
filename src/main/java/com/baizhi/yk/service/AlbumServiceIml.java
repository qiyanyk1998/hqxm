package com.baizhi.yk.service;

import com.baizhi.yk.dao.AlbumDao;
import com.baizhi.yk.entity.Admin;
import com.baizhi.yk.entity.Album;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlbumServiceIml implements AlbumService {
    @Resource
    AlbumDao albumDao;
    //查所有
    public List<Album> selectAll(){
        List<Album> albums = albumDao.selectAll();
        return  albums;
    }

//分页
    public Map showAllAlbums(Integer page, Integer rows){
        HashMap hashMap = new HashMap();
        int i = albumDao.selectCount(null);
        Integer total = i%rows==0?i/rows:i/rows+1;
        List<Album> albums = albumDao.selectByRowBounds(null, new RowBounds((page - 1) * rows,rows));
        hashMap.put("records",i);
        hashMap.put("total",total);
        hashMap.put("rows",albums);
        hashMap.put("page",page);
        return hashMap;
    }

    //添加
    @Override
    public void insert(Album album) {

        albumDao.insert(album);


    }

    //修改
    @Override
    public void update(Album album) {

        albumDao.updateByPrimaryKeySelective(album);

    }

    //删除
    @Override
    public void delect(String[] id) {
        albumDao.deleteByIdList(Arrays.asList(id));
    }

    @Override
    public List selectByRowBounds() {
        List<Album> albums = albumDao.selectByRowBounds(null, new RowBounds(0, 5));
       return albums;
    }

    @Override
    public Album selectOne(String id) {
        //构建查询条件
        Album album=new Album();
        album.setId(id);
        //调用dao查询
        albumDao.selectOne(album);
        return album;
    }

}
