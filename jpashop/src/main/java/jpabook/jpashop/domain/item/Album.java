// 상품 - 음반 엔티티

package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("A") // 싱글테이블이므로 DB에 저장할 때 구분하기 위해서
@Getter @Setter
public class Album extends Item {

    private String artist;
    private String etc;
}
