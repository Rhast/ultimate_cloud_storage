package com.rhast.clstrg.spring.boot;

/**
 * @author lermontov-w
 */
public class BaseResponse {
    private final String hello;
    private final String world;

    public BaseResponse() {
        this.hello = "hello";
        this.world = "world";
    }

    public String getHello() {
        return hello;
    }

    public String getWorld() {
        return world;
    }
}
