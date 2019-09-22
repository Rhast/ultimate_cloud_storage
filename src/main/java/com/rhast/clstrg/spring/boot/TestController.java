package com.rhast.clstrg.spring.boot;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lermontov-w
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public BaseResponse test() {
        return new BaseResponse();
    }
}
