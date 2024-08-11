package com.devchw;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final ParentService parentService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        parentService.parentMethod();
        return ResponseEntity.ok("hello");
    }
}
