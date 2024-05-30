// 배송 엔티티

package jpabook.jpashop.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) // 연관 관계 거울
    private Order order;

    @Embedded // 내장 타입
    private Address address;

    @Enumerated(EnumType.STRING) // Enum 타입은 이렇게. 반드시 default인 ORDINAL이 아닌 STRING으로!
    private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]
}