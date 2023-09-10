package com.nareun.springboot.learnspringboot;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConfigurationController {

    @Autowired
    private CurrencyServiceConfiguration configuration;//*@Component로 Bean 설정을 하였으므로 자동 주입 */

    @RequestMapping("/currency-configuration")
    public CurrencyServiceConfiguration retrieveAllCourses() {
        return configuration;
                
    }
}
