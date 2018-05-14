package com.yonon.demo.design.command;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-03-19.
 */
public class AddCommand implements Command{
    /**
     * 命令接受者对象
     */
    public Document doucment;

    /**
     * 构造方法
     * @param document
     */
    public AddCommand(Document document){
        this.doucment = document;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        this.doucment.add();
    }
}
