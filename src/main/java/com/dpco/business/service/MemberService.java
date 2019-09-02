package com.dpco.business.service;

import com.dpco.business.entity.Member;
import com.dpco.business.dao.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService extends GenericService<Member, Integer> {

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        super(memberRepository);
    }
    public Member findByUsernameAndPassword(String username , String password){
        MemberRepository memberRepository = (MemberRepository) genericRepository;
        return memberRepository.findByUsernameAndPassword(username , password);
    }
}
