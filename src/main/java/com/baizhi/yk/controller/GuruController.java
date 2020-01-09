package com.baizhi.yk.controller;

import com.baizhi.yk.entity.Guru;
import com.baizhi.yk.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("guru")
public class GuruController {
    @Autowired
    GuruService guruService;
    //查所有
    @RequestMapping("showAllGuru")
    public List<Guru> selectAll(){
        List<Guru> gurus = guruService.selectAll();
        return gurus;
    }
}
