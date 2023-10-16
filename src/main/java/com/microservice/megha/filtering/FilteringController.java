package com.microservice.megha.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean Filtering(){
        return new SomeBean("Value1" , "Value2", "Value3");
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> FilteringList(){
        return Arrays.asList(new SomeBean("Value1" , "Value2", "Value3"),
                new SomeBean("Value4" , "Value5", "Value6"));
    }
}
