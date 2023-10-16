package com.microservice.megha.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
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

    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue dynamicFiltering(){
        SomeBean someBean = new SomeBean("Value1" , "Value2", "Value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/dynamic-filtering-list")
    public MappingJacksonValue dynamicFilteringList(){
        List<SomeBean> list = Arrays.asList(new SomeBean("Value1" , "Value2", "Value3"),
        new SomeBean("value4","value5","value6"),new SomeBean("value7","value8","value9"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
