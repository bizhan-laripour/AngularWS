package com.dpco.business.service;

import com.dpco.business.dao.MemberDao;
import com.dpco.business.entity.Member;
import com.dpco.business.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService extends GenericService<Member , Integer>{

    @Autowired
    public MemberService(MemberDao genericDao) {
        super(genericDao);
    }

    public Member findByUsernameAndPassword(Member member){
        try {
            MemberDao memberDao = (MemberDao) genericDao;
            return memberDao.findByUsernameAndPassword(member);
        }catch (CustomException ex){
            throw new CustomException(ex.getMessage() , ex.getStatus());
        }
    }


}
