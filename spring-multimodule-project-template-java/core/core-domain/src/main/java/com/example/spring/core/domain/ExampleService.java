package com.example.spring.core.domain;

import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    public ExampleResult processExample(ExampleData exampleData) {
        return new ExampleResult(exampleData.value());
    }

}
