package com.yonon.demo.lambda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by jr-jiangyinghan on 2017-6-28.
 */
public class LambdaDemo {

    //用lambda表达式实现Runnable
    private static void thread() {
        // Java 8之前：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();
        //Java 8方式：
//        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
    }

    //使用lambda表达式对列表进行迭代
    private static void iter() {
        // Java 8之前：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);
        }

        // Java 8之后：
        List features8 = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features8.forEach(n -> System.out.println(n));

        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);
    }

    public static void main(String[] args) {
//        try {
//            match();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        foreachDemo();
    }

    private static void reach() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.length() > 4);
    }

    //使用lambda表达式和函数式接口Predicate
    public static void filter(List<String> names, Predicate<String> condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

    private static void match() {
        List<Usr> users = new ArrayList<>();

        int count = 5000000;
        double countP = ((double) count);
        for (int i = 0; i < count; i++) {
//            System.out.println("index: " + i);
            BigDecimal proc = new BigDecimal(i / countP).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println("process: " + (proc.doubleValue() ) + "% --- index: " + i);
            users.add(new Usr(1, "China", "XiaoMing", "Grade_one"));
            users.add(new Usr(2, "China", "XiaoHong", "Grade_one"));
            users.add(new Usr(3, "China", "WangWu", "Grade_two"));
            users.add(new Usr(4, "China", "LiSi", "Grade_two"));
            users.add(new Usr(5, "China", "LinKen", "Grade_two"));
            users.add(new Usr(6, "China", "FangKang", "Grade_three"));
            users.add(new Usr(7, "China", "JinLi", "Grade_three"));
        }
        boolean result = users.stream().anyMatch(usr -> usr.getName().equals("LiSi"));
        System.out.println("anyMatch:Result: " + result);
        result = users.stream().allMatch(usr -> (usr.getArea().equals("China")));
        System.out.println("allMatch:Result: " + result);

        Optional<Usr> usrOptional = users.stream().findAny();
        System.out.println(usrOptional.toString());


        List<String> usrName = users.stream().map(Usr::getGrade).distinct().collect(Collectors.toList());
        long before = System.currentTimeMillis();
        System.out.println();
        usrName.forEach(fname -> System.out.println(fname));
        long after = System.currentTimeMillis();
        long tResult = after - before;
        System.out.println("used one: " + tResult);


        before = System.currentTimeMillis();
        for (String fnameF : usrName) {
            System.out.println(fnameF);
        }
        after = System.currentTimeMillis();
        tResult = after - before;
        System.out.println("used two: " + tResult);
    }


    public static void foreachDemo(){
        List<String> l = new ArrayList<>();
        l.add("ClassOne");
        l.add("ClassTwo");
        l.add("ClassThree");
        l.add("ClassFour");
        l.add("ClassFive");
        l.forEach(p -> System.out.println(p));
    }
}
