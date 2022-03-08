package com.springboot.springbootdemo;

import com.springboot.springbootdemo.models.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootDemoApplication.class, args);

        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Cena");
        employee.setId(100);
        //System.out.println(employee.toString());
		
    }

}
