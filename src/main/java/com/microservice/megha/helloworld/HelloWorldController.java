package com.microservice.megha.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
//@RequestMapping("/hello")
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource =messageSource;
    }

    @GetMapping("/hi")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/hello")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean ("Hello World");
    }

    @GetMapping("/hello-world/{name}")
   // @GetMapping("/path-variable/{name}")

    public HelloWorldBean helloWorldName (@PathVariable String name){
        return new HelloWorldBean("Hello World " + name );
    }

    @GetMapping( path = "/hello-world-internationalized")
    public String helloWorldInternationalized(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message" , null,"Default Message",locale);
    }

}
