// 회원 엔티티

package jpabook.jpashop.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue // 시퀀스 값을 쓸 것
    @Column(name = "member_id") // member_id가 기본키이므로
    private Long id;

    private String name;

    @Embedded // 내장타입을 포함했다는 어노테이션으로 매핑해줌
    private Address address; // Address 클래스 만들기

    // Member와 Order는 일대다 관계
    @OneToMany(mappedBy = "member") // 연관 관계의 주인X, 거울에 mappedBy 적어줌. Member 필드에 의해 매핑된 것!
    private List<Order> orders = new ArrayList<>(); // 리스트로 Order를 가짐, Order 클래스 만들기
}