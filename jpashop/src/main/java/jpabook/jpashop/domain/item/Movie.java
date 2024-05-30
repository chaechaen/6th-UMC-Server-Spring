// 상품 - 영화 엔티티

package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("M") // 싱글테이블이므로 DB에 저장할 때 구분하기 위해서
@Getter
@Setter
public class Movie extends Item {

    private String director;
    private String actor;
}
