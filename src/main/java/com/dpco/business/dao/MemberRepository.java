package com.dpco.business.dao;


import com.dpco.business.entity.Member;

public interface MemberRepository extends GenericRepository<Member, Integer>{

    Member findByUsernameAndPassword(String username, String password);
}
