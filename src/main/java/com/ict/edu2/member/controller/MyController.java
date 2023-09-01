package com.ict.edu2.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.edu2.member.dao.memberDAO;
import com.ict.edu2.member.vo.VO;

import lombok.extern.slf4j.Slf4j;

// @CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
@RequestMapping("/member")
@Slf4j
public class MyController {
    @Autowired
    private memberDAO memberdao;

    @GetMapping("/")
    public String Hello(){
        return "Hello World";
    }

    @PostMapping("/login")
    public Map<String, Object> logIn(VO vo){
        log.info("\nlogin 서버\n");
        log.info("\n" + vo.getM_id() + "\n");
        log.info("\n" + vo.getM_pw() + "\n");

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("chk", 0);

        return resMap;
    }

    @GetMapping("/list")
    public Map<String, Object> getList(){
        List<VO> list = memberdao.getList();
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("list", list);
        return resMap;
    }
}
