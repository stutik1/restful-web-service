package com.microservice.megha.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping("/hi")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/hello")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean ("Hello World");
    }

    @GetMapping("/{name}")
   // @GetMapping("/path-variable/{name}")

    public HelloWorldBean helloWorldName (@PathVariable String name){
        return new HelloWorldBean("Hello World " + name );
    }
}
