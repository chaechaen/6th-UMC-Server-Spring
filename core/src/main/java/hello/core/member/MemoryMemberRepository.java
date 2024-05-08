// Member Repository의 Memory 버전 구현체

package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements  MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); // 키가 Long 타입이고 값이 Member 객체인 Map (Long 형식의 키로 Member 객체를 저장하고 검색 가능)

    // @Override로 상위 클래스/인터페이스에서 이 메서드가 상속되었음을 나타냄
    @Override
    public void save(Member member) {
        // Member 객체의 ID를 가져와 store에 해당 객체를 값으로 저장 (ID를 key로)
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        // 주어진 memberId를 키로 사용하여 맵에서 해당하는 Member 객체를 찾아 반환
        return store.get(memberId);
    }
}
