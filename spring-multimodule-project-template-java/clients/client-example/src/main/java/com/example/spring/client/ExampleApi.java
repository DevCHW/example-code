package com.example.spring.client;

import com.example.spring.client.param.ExampleRequest;
import com.example.spring.client.response.ExampleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "${example.api.value}", url = "${example.api.url}", configuration = ExampleApiHeaderConfig.class)
interface ExampleApi {

    @GetMapping("/example/example-api")
    ExampleResponse example(@RequestBody ExampleRequest request);

}
