package com.dpco.business.dao;

import com.dpco.business.entity.Member;
import com.dpco.business.exception.CustomException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDao extends GenericDao<Member , Integer> {

    public Member findByUsernameAndPassword(Member member){
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Member as mem where mem.username=:username and mem.password=:password");
            query.setParameter("username", member.getUsername());
            query.setParameter("password", member.getPassword());
            List<Member> members = query.list();
            transaction.commit();
            session.close();
            Member mem =  members.get(0);
            if(mem == null){
                throw new CustomException("there is no member with this identities" , HttpStatus.ACCEPTED);
            }
            return mem;
        }catch (Exception ex){
            throw new CustomException("there is some problem in finding member by username and password", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}
