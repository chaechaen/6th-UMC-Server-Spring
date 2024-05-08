package hello.core.common;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@Scope(value = "request") //  request 스코프로 지정 -> 이제 이 빈은 HTTP 요청 당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소멸됨
public class MyLogger { // 로그를 출력하기 위한 MyLogger 클래스
    private String uuid;
    private String requestURL;
    public void setRequestURL(String requestURL) { // requestURL 은 이 빈이 생성되는 시점에는 알 수 없으므로, 외부에서 setter로 입력 받음
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct // 이 빈이 생성되는 시점에 자동으로 @PostConstruct 초기화 메서드를 사용해서 uuid를 생성해서 저장
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy // 이 빈이 소멸되는 시점에 @PreDestroy 를 사용해서 종료 메시지를 남김
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}