package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    // @GenteratedValue는 JPA에서 Entity의 Primary Key를 생성하여 주는 기능
    // PK로 사용될 Entity의 프로퍼티에 @Id를 선언하여 준다음 @GeneratedValue를 선언
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne // Order와 Member는 다대일 관계
    @JoinColumn(name = "member_id") // 매핑을 member_id로 할 것 (Foreign Key)
    private Member member;

    @OneToMany(mappedBy = "order") // 일대다
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 일대일
    @JoinColumn(name = "delivery_id") // 연관 관계 주인
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [ORDER, CANCEL]

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) { // OrderItem은 리스트로 넘기기

        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        order.setStatus(OrderStatus.ORDER);

        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel() {

        if (delivery.getStatus() == DeliveryStatus.COMP) { // 이미 배송이 되어버렸으면 (COMP) 예외 발생
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);

        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //==조회 로직==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {

        int totalPrice = 0;

        for (OrderItem orderItem : orderItems) { // orderItems 루프를 돌면서 totalPrice를 가져옴
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }
}