package com.baizhi.yk.service;

import com.baizhi.yk.dao.GuruDao;
import com.baizhi.yk.entity.Guru;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class GuruServiceIml implements GuruService {
@Resource
    GuruDao guruDao;
    @Override
    public List<Guru> selectAll() {
        List<Guru> gurus = guruDao.selectAll();
        return gurus;
    }
}
