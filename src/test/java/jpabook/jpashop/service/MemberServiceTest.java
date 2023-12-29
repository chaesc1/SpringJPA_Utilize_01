package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("chae");

        //when
        Long join = memberService.join(member);
        //then
//        entityManager.flush(); //db에 쿼리를 강제로 날리고
        Assert.assertEquals(member, memberRepository.findOne(join));
    }

    @Test
    void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("chae");

        Member member2 = new Member();
        member2.setName("chae");

        //when
        memberService.join(member1);

        //then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertEquals("이미 존재하는 회원입니다", thrown.getMessage());
    }

}