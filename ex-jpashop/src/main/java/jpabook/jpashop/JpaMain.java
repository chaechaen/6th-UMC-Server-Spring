package jpabook.jpashop;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;


public class JpaMain {

    public static void main(String[] args) {

        // emf는 entity manager factory, em은 entity manager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // emf에서 entity manager를 꺼냄

        // tx는 transaction
        EntityTransaction tx = em.getTransaction(); // 트랜잭션을 얻고

        // 트랜잭션을 시작
        tx.begin();

        /**
         * 이 안에서 실제 code를 작성하게 됨
         * */

        try {

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");

            em.persist(book);

            // 정상적일 때는 커밋하기
            tx.commit();

        } catch (Exception e) {

            // 문제가 생기면 롤백해주기
            tx.rollback();
        } finally {

            // 작업이 끝나면 엔티티 매니저를 닫아주기
            em.close();
        }

        // 전체 애플리케이션이 끝나면 엔티티 매니저 팩토리까지 닫아주기
        emf.close();
    }
}
