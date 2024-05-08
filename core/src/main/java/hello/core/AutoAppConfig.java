package hello.core;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan( // @Component가 붙은 클래스를 찾아 자동으로 스프링 빈으로 등록해줌
    basePackages = "hello.core.member", excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // 뺄 것 지정 (Configuration 애노테이션 붙은 애는 뺄 것임. 얘는 수동으로 등록하므로 자동으로 하면 X)
)
public class AutoAppConfig {

}
