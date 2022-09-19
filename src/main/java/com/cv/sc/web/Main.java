package com.cv.sc.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created By: bhushan.karmarkar12@gmail.com
 * Date: 19/09/22
 */
@ComponentScan(basePackages = "com.cv.sc")
@EnableAutoConfiguration
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
