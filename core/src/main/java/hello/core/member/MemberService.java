// 회원 서비스 인터페이스

package hello.core.member;

public interface MemberService {

    // 회원 가입
    void join(Member member);

    // 회원 조회
    Member findMember(Long memberId);
}
