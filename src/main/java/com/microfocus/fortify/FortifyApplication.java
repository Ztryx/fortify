package com.microfocus.fortify;

/**
 * Created by andyx on 14/5/18.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.microfocus.fortify"})
public class FortifyApplication {
    public static void main(String[] args) {
        SpringApplication.run(FortifyApplication.class, args);
    }
}