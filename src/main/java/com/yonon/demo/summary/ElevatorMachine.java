package com.yonon.demo.summary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jr-jiangyinghan on 2017-7-7.
 */
public class ElevatorMachine {
    private Elevator[] elevators;

    public void run(int count) {
        if (null == elevators) {
            System.out.println("no power...");
            power(count);
        }
        System.out.println(count + " elevators runs...");
        System.out.println("elevator status: ");
        getElevatorFloor();
    }

    private void power(int count) {
        elevators = new Elevator[2];
        for (int i = 0; i < count; i++) {
            elevators[i] = new Elevator(i, 20, 1, Math.abs(i - 5), 1, 0);
        }
    }

    private void getElevatorFloor() {
        for (Elevator elevator : elevators) {
            System.out.println("id:" + elevator.getId() + " currentFloor is: " + elevator.getCurrent() + " direction: " + elevator.getDirection());
        }
    }

    public Elevator press(Customer customer) {
        ArrayList<Elevator> elevatorUpList = new ArrayList();
        ArrayList<Elevator> elevatorDownList = new ArrayList();
        for (Elevator elevator : elevators) {
            int custDir = customer.getCurrentFloor() - customer.getTargetFloor();
            if (custDir > 0) {
                System.out.println("customer-id : " + customer.getId() + " is up");
            } else {
                System.out.println("customer-id : " + customer.getId() + " is down");
            }
            int direction = elevator.getDirection();
            if (direction == custDir) {
                if (direction > 0) {
                    elevatorUpList.add(elevator);
                } else {
                    elevatorDownList.add(elevator);
                }
            }
        }
        int index = -1;
        if (elevatorDownList.size() != 0) {
            index = compare(elevatorDownList, customer, 999, true);
            return elevatorDownList.get(index);
        } else if (elevatorUpList.size() != 0) {
            index = compare(elevatorUpList, customer, -1, false);
            return elevatorUpList.get(index);
        }
        return null;
    }

    private int compare(List<Elevator> content, Customer customer, int compare, boolean isMin) {
        int index = -1;
        int select = -1;
        for (Elevator elevator : content) {
            int beyong = elevator.getCurrent() - customer.getCurrentFloor();

            if (beyong < compare && isMin) {
                beyong = compare;
                select = index;
            } else if (compare > beyong && !isMin) {
                beyong = compare;
                select = index;
            }
            index++;
            if (index == content.size()) {
                return select;
            }
        }
        return -1;
    }
}