package com.baizhi.yk.service;

import com.baizhi.yk.dao.ChapterDao;
import com.baizhi.yk.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChapterServiceIml implements ChapterService {
    @Resource
    ChapterDao chapterDao;

    //查所有
    public List<Chapter> selecrAll(){
        List<Chapter> chapters = chapterDao.selectAll();
        return  chapters;
    }
    public Map showChapterById(Integer page,Integer rows,String albumId){
        HashMap hashMap = new HashMap();
        Chapter chapter = new Chapter();
        chapter.setAlbumId(albumId);
        int i = chapterDao.selectCount(chapter);
        Integer total = i%rows==0?i/rows:i/rows+1;
        List<Chapter> chapterDaos = chapterDao.selectByRowBounds(chapter, new RowBounds((page - 1) * rows, rows));
        hashMap.put("records",i);
        hashMap.put("page",page);
        hashMap.put("total",total);
        hashMap.put("rows",chapterDaos);
        return hashMap;
    }

    //添加
    @Override
    public void insert(Chapter chapter) {

        chapterDao.insert(chapter);


    }

    //修改
    @Override
    public void update(Chapter chapter) {

        chapterDao.updateByPrimaryKeySelective(chapter);

    }

    //删除
    @Override
    public void delect(String[] id) {
        chapterDao.deleteByIdList(Arrays.asList(id));
    }
}
