package com.yonon.demo.cache;

import org.springframework.cache.annotation.Cacheable;

/**
 * Created by jr-jiangyinghan on 2017-8-31.
 */
@Cacheable(value = "employee")
public class EmployeeDao {

    @Cacheable(value = "employee", condition = "#age < 25")
    public Person findEmployee(String name, int age) {
        try{
            Thread.sleep(8000);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new Person(name, age);
    }

    public Person findAnotherEmployee(String name, int age) {
        return new Person(name, age);
    }
}
