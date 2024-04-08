package com.example.spring.core.api.presentation.request;

import com.example.spring.core.domain.ExampleData;

public record ExampleRequestDto(String data) {
    public ExampleData toExampleData() {
        return new ExampleData(data, data);
    }
}
