package hellojpa;

import jakarta.persistence.*;

import javax.management.relation.Role;
import java.util.List;
import java.util.Set;

import static java.nio.file.Files.find;

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

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("==========Start==========");
            Member findMember = em.find(Member.class, member.getId());

            // homeCity -> newCity 바꾸기
            // findMember.getHomeAddress().setCity("newCity"); 이렇게 하면 안됨
            Address a = findMember.getHomeAddress(); // 값 타입에 있는 인스턴스를 완전히 새로 갈아끼워야 한다.
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            // 치킨 -> 한식 바꾸기
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFoote = " + favoriteFood);
//            }

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
