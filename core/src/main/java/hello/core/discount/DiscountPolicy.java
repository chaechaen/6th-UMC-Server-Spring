// 할인 정책 인터페이스

package hello.core.discount;

import hello.core.member.Member;

// 이걸 호출하면 그 결과로 얼마가 할인되었는지 알려줌
public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);

}
