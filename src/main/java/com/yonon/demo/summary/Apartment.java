package com.yonon.demo.summary;

/**
 * Created by jr-jiangyinghan on 2017-7-7.
 */
public class Apartment {

    public static void main(String[] args) {
        ElevatorMachine machine = new ElevatorMachine();
        machine.run(2);
        Customer customer1 = new Customer(1,"c1",1,6);
        Customer customer2 = new Customer(2,"c2",1,5);
        machine.press(customer1);
        machine.press(customer2);
    }
}
