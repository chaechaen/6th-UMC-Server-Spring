// 회원 리포지토리

package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    @PersistenceContext
    private final EntityManager em; // 스프링이 엔티티 매니저를 만들어서 걔를 주입해줌

    public void save(Member member) { // 저장 로직
        em.persist(member);
    }

    public Member findOne(Long id) { // 단건 조회
        return em.find(Member.class, id); // JPA가 제공하는 find 메서드 -> 멤버를 찾아 반환
    }

    public List<Member> findAll() { // 리스트 조회
        // 멤버를 리스트로 만들어주고, 첫 번째에 jpql, 두 번째에 반환 타입 넣어주면 됨
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class) // 조회 타입은 Member.class
                .setParameter("name", name)
                .getResultList();
    }
}