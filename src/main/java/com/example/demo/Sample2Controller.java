package com.example.demo;

import com.example.demo.annotations.Bar;
import com.example.demo.annotations.Default;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Bar
@RestController
public class Sample2Controller {
    @GetMapping("bar")
    public String bar() {
        return "Hello Bar!!";
    }

    @Default
    @GetMapping("default")
    public String defaultMethod() {
        return "Hello Default!!";
    }
}
