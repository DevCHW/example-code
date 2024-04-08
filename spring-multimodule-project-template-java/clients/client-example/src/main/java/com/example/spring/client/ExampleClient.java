package com.example.spring.client;

import com.example.spring.client.model.ExampleModel;
import com.example.spring.client.param.ExampleRequest;
import org.springframework.stereotype.Component;

@Component
public class ExampleClient {

    private final ExampleApi exampleApi;

    public ExampleClient(ExampleApi exampleApi) {
        this.exampleApi = exampleApi;
    }

    public ExampleModel example(String exampleParameter) {
        ExampleRequest request = new ExampleRequest(exampleParameter);
        return exampleApi.example(request).toModel();
    }

}
