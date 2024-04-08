package com.example.spring.core.api.presentation;

import com.example.spring.core.api.presentation.request.ExampleRequestDto;
import com.example.spring.core.api.presentation.response.ExampleResponseDto;
import com.example.spring.core.api.support.response.ApiResponse;
import com.example.spring.core.domain.ExampleData;
import com.example.spring.core.domain.ExampleResult;
import com.example.spring.core.domain.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping("/get/{exampleValue}")
    public ApiResponse<ExampleResponseDto> exampleGet(@PathVariable String exampleValue,
                                                      @RequestParam String exampleParam) {
        ExampleResult result = exampleService.processExample(new ExampleData(exampleValue, exampleParam));
        return ApiResponse.success(new ExampleResponseDto(result.data()));
    }

    @PostMapping("/post")
    public ApiResponse<ExampleResponseDto> examplePost(@RequestBody ExampleRequestDto request) {
        ExampleResult result = exampleService.processExample(request.toExampleData());
        return ApiResponse.success(new ExampleResponseDto(result.data()));
    }
}
