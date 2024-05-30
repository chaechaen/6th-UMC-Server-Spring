// 상품 리포지토리 코드

package jpabook.jpashop.repository;
import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { // item은 처음에는 id가 없음 -> 새로 생성하는 객체
            em.persist(item); // 신규로 등록
        } else { // 이미 item 값이 있으며 디비에서 가져옴
            em.merge(item); // merge는 업데이트 같은 것
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }
}