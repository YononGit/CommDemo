package com.yonon.demo.lambda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by jr-jiangyinghan on 2017-8-10.
 */
public class Reduce {
    public static void main(String[] args) {
        ArrayList<BigDecimal> types = new ArrayList<BigDecimal>();
        types.add(new BigDecimal(1));
        types.add(new BigDecimal(2));
        types.add(new BigDecimal(3));
        types.add(new BigDecimal(4));
        types.add(new BigDecimal(5));
        BigDecimal count = types.stream().reduce(BigDecimal::multiply).get();
        System.out.println(count);
    }
//        int accResult = Stream.of(1, 2, 3, 4).reduce(0, (acc, item) -> {
//                    System.out.println("acc : "  + acc);
//                    acc += item;
//                    System.out.println("item: " + item);
//                    System.out.println("acc+ : "  + acc);
//                    System.out.println("--------");
//                    return acc;
//                });
//        System.out.println("accResult: " + accResult);
//        System.out.println("--------");

}
