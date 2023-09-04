package com.ict.edu2.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu2.member.vo.MemberVO;

@Service
public class memberDAO {
    @Autowired
    private SqlSessionTemplate ss;

    public List<MemberVO> getList(){
        return ss.selectList("members.list");
    }

    public int getIdCnt(String m_id){
        return ss.selectOne("members.idcnt", m_id);
    }

    public MemberVO getMemberOne(String m_id){
        return ss.selectOne("members.memberone", m_id);
    }
    public int getMemberJoin(MemberVO vo){
        return ss.insert("members.join", vo);
    }
}
