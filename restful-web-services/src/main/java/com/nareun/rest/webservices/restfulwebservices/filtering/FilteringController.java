package com.nareun.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

    @GetMapping("filtering")
    public MappingJacksonValue filtering() {// field1, field3만 반환

        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        MappingJacksonValue mappingJacksonValue = filteredMappingJacksonValue(someBean, "field1", "field3");

        return mappingJacksonValue;
    }

    @GetMapping("filtering-list")
    public MappingJacksonValue filteringList() {// field2, field3만 반환
        List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));

        MappingJacksonValue mappingJacksonValue = filteredMappingJacksonValue(list, "field2", "field3");

        return mappingJacksonValue;
    }

    // * MappingJackson객체에 동적 필터링을 달아주는 메서드
    private static MappingJacksonValue filteredMappingJacksonValue(Object value, String... field) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(value);
        // 동적 필터 구현
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(field);
        // 필터 제공자에 필터를 넣어준다.
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        // 필터 설정
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
