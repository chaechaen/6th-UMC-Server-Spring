package hello.core.discount;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    // VIP인 경우
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")

    void vip_o() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP); // 임의의 VIP 멤버 만듦
        //when
        int discount = discountPolicy.discount(member, 10000); // 멤버와 price를 넘겨줌
        //then
        assertThat(discount).isEqualTo(1000); // 검증 (discount 금액이 1000원이 적용되어야 한다)
    }

    // VIP가 아닌 경우
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")

    void vip_x() {
        //given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC); // 등급은 BASIC
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(0); // 검증 (할인 금액이 1000원이 되어야 한다)
    }
}