package com.yonon.demo.main;

import com.yonon.demo.domain.RefDemo;

import java.sql.Ref;
import java.util.HashMap;

/**
 * Created by jr-jiangyinghan on 2017-9-26.
 */
public class RefMain {
    static HashMap<String,RefDemo> map = new HashMap<>();

    public static void main(String[] args) {
        RefDemo ref1 = new RefDemo(1,"XiaoMing");
        RefDemo ref2 = new RefDemo(1,"Lisi");
        map.put("obj1",ref1);
        map.put("obj2",ref2);
        RefDemo refDemo = map.get("obj1");
        refDemo.setName("Wangwu");
        System.out.printf("");
    }
}
