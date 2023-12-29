package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id) { //단건 조회
        return em.find(Member.class, id);
    }

    public List<Member> findAll() { //jpql 이용해서 전체 조회
       return em.createQuery("select m from Member m", Member.class)
               .getResultList();
    }

    public List<Member> findByName(String name) { //파라미터 이용한 조회 jpql
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
