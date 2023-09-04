package com.ict.edu2.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.edu2.member.dao.memberDAO;
import com.ict.edu2.member.vo.DataVO;
import com.ict.edu2.member.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member")
@Slf4j
public class MyController {
    @Autowired
    private memberDAO memberdao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String Hello(){
        return "Hello World";
    }

    @PostMapping("/login")
    public Map<String, Object> logIn(MemberVO vo, HttpSession session){
        Map<String, Object> resMap = new HashMap<>();
        DataVO dataVO = new DataVO();

        // 입력받은 아이디가 존재하는지 검사
        int cnt = memberdao.getIdCnt(vo.getM_id());
        if(cnt <= 0){
            dataVO.setSuccess(false);
            dataVO.setMessage("아이디가 존재하지 않습니다.");
            
            resMap.put("data", dataVO);
            return resMap;
        }else{
            // 입력받은 아이디를 이용해 db 패스워드 구하기
            MemberVO mvo = memberdao.getMemberOne(vo.getM_id());
  
            // 가지고 온 패스워드와 입력된 패스워드가 같은지 검사
            if(!passwordEncoder.matches(vo.getM_pw(), mvo.getM_pw())){
               dataVO.setSuccess(false);
               dataVO.setMessage("비밀번호가 틀립니다."); 
            }else{
                // 로그인 정보 저장(Session)
                session.setAttribute("mvo", mvo);
                
                // 로그인 성공
                dataVO.setSuccess(true);
                dataVO.setMessage("로그인 성공.");
            }
            resMap.put("data", dataVO);
            return resMap;
        }
    }

    @PostMapping("/join")
    public Map<String, Object> Join(MemberVO vo){
        Map<String, Object> resMap = new HashMap<>();
        DataVO dataVO = new DataVO();
        vo.setM_pw(passwordEncoder.encode(vo.getM_pw()));

        int result = memberdao.getMemberJoin(vo);
        if(result > 0){
            dataVO.setSuccess(true);
            dataVO.setMessage("회원가입 성공");
            resMap.put("data", dataVO);
        }else{
            dataVO.setSuccess(false);
            dataVO.setMessage("회원가입 실패");
            resMap.put("data", dataVO);  
        }
        return resMap;
    }
   
    @GetMapping("/list")
    public Map<String, Object> getList(){
        List<MemberVO> list = memberdao.getList();
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("list", list);
        return resMap;
    }
}
