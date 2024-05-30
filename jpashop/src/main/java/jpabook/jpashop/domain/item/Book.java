// 상품 - 도서 엔티티

package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B") // 싱글테이블이므로 DB에 저장할 때 구분하기 위해서
@Getter
@Setter
public class Book extends Item { // 상속이니까 extends

    private String author;
    private String isbn;
}
