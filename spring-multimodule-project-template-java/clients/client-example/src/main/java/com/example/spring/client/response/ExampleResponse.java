package com.example.spring.client.response;

import com.example.spring.client.model.ExampleModel;

public record ExampleResponse(String exampleResponseValue) {
    public ExampleModel toModel() {
        return new ExampleModel(exampleResponseValue);
    }
}
