package com.yonon.demo.design.command;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-03-19.
 */
public class Document {
    public static StringBuffer sb = new StringBuffer();

    public static int count = 0;

    /**
     * 撤销实现方法
     */
    public void undo(){
        System.out.println("调用撤销实现方法，字符串递减");
        sb.deleteCharAt(sb.length()-1);
        count --;
        System.out.println("当前文本为：" + sb.toString());
    }

    /**
     * 恢复实现方法
     */
    public void redo(){
        System.out.println("调用恢复实现方法，字符串递加");
        this.sb.append(count);
        count ++;
        System.out.println("当前文本为：" + sb.toString());
    }

    /**
     * 执行实现方法
     */
    public void add(){
        System.out.println("调用执行实现方法，字符串递加");
        this.sb.append(count);
        count ++;
        System.out.println("当前文本为：" + sb.toString());
    }
}
