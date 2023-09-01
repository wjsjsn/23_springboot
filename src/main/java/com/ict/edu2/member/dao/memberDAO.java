package com.ict.edu2.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu2.member.vo.VO;

@Service
public class memberDAO {
    @Autowired
    private SqlSessionTemplate ss;

    public List<VO> getList(){
        return ss.selectList("members.list");
    }
}
