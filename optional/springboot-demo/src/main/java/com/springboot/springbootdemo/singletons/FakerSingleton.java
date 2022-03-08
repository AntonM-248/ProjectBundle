package com.springboot.springbootdemo.singletons;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class FakerSingleton {
    private static Faker faker;

    public static Faker getFaker() {
        if (faker == null) {
            faker = new Faker();
        }
        return faker;
    }

    public static String getFirstName() {
        return getFaker().name().firstName();
    }

    public static String getLastName() {
        return getFaker().name().lastName();
    }

}
