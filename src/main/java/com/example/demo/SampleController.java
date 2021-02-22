package com.example.demo;

import com.example.demo.annotations.Foo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("hello")
    public String hello() {
        return "Hello World!!";
    }

    @Foo
    @GetMapping("foo")
    public String foo() {
        return "Hello Foo!!";
    }


}
