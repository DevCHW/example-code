package com.devchw.timetrace;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @TimeTrace
    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        Thread.sleep(1000);
        innerMethod();
        return "hello";
    }

    @GetMapping("/v2/hello")
    public String helloV2() throws InterruptedException {
        Thread.sleep(1000);
        innerMethod();
        return "hello";
    }

    @TimeTrace
    public void innerMethod() {
        System.out.println("innerMethod 호출입니다요");
    }

}
