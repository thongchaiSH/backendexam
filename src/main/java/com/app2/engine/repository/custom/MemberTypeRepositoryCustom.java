package com.app2.engine.repository.custom;

import com.app2.engine.entity.app.MemberType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberTypeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public MemberType findByCode(String code){
        Criteria criteria = ((Session) entityManager.getDelegate()).createCriteria(MemberType.class);
        criteria.add(Restrictions.eq("code",code));
        return (MemberType)criteria.uniqueResult();
    }
}
