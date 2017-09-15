package com.yonon.demo.launch;

import com.yonon.demo.domain.Student;
import com.yonon.demo.mongodb.MongoUtils;
import com.yonon.demo.redis.DBUtils;
import com.yonon.demo.redis.RedisUtils;

import java.util.ArrayList;

/**
 * Created by jr-jiangyinghan on 2017-9-14.
 */
public class StressDB {
    public static void main(String[] args) {

//        DBUtils.getConnect();
        setData();
        System.out.printf("main execute complete!");
    }

    public static void getData() {
        int count = DBUtils.queryCount("SELECT COUNT\\(1\\) FROM student");
        for (int index = 1; index <= count; index++) {
            Student student = RedisUtils.getStudentObject("stu021709151432", index);
            System.out.println("load data from redis,student:{" + student.toString() + "}");
        }

    }

    public static void setData(){
        ArrayList<Student> students = DBUtils.queryBySql("SELECT * FROM student");
        int index = 0;
        for (Student student : students) {
            index++;
           // RedisUtils.setStudentObject(student, "stu021709151432", index);
            MongoUtils.writeDocument("students","stu-2017-09-15-1504",student.toString());
        }
    }
}
