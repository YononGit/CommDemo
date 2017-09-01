package com.yonon.demo.lambda;

/**
 * Created by jr-jiangyinghan on 2017-6-28.
 */
public class Usr {
    private int no;
    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    private String name;
    private String grade;

    public Usr(int no, String area, String name, String grade) {
        this.no = no;
        this.area = area;
        this.name = name;
        this.grade = grade;
    }

    public Usr() {
    }

    @Override
    public String toString() {
        return "Usr{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
