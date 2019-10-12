package com.rhast.clstrg.spring.boot.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lermontov-w
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView printStackTrace(HttpServletRequest request, Exception ex) {
        logger.info("Request: " + request.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;


//        logger.info("", ex);
//        StringWriter stringWriter = new StringWriter();
//        ex.printStackTrace(new PrintWriter(stringWriter));
//        return stringWriter.toString();
    }
}
