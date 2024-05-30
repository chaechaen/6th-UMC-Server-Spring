package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // 여기 .html이 자동으로 붙음
        // 스프링부트 thymeleaf viewName 매핑 -> resources:templates/ + {ViewName}+.html
    }
}