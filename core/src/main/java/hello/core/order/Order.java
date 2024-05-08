// 주문 Entity

package hello.core.order;
public class Order {

    // 주문시 주문서의 결과
    private Long memberId;
    private String itemName;
    private int itemPrice; // 원가
    private int discountPrice; // 할인
    public Order(Long memberId, String itemName, int itemPrice, int
            discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    // 계산 로직
    public int calculatePrice() { // 최종 할인된 가격 계산
        return itemPrice - discountPrice;
    }

    // getter setter
    public Long getMemberId() {
        return memberId;
    }
    public String getItemName() {
        return itemName;
    }
    public int getItemPrice() {
        return itemPrice;
    }
    public int getDiscountPrice() {
        return discountPrice;
    }
    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}