package com.yonon.demo.design.command;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-03-19.
 */
public class CommandMain {
    public static void main(String args[]) {
        Document doc = new Document(); //文档实体对象
        AddCommand addCmd = new AddCommand(doc); //具体命令实体对象
        UndoCommand undoCmd = new UndoCommand(doc); //具体命令实体对象
        RedoCommand redoCmd = new RedoCommand(doc); //具体命令实体对象
        Invoker invoker = new Invoker(); //调用者对象
        invoker.setCommand(addCmd);
        invoker.execute();
        invoker.setCommand(addCmd);
        invoker.execute();
        invoker.setCommand(undoCmd);
        invoker.execute();
        invoker.setCommand(redoCmd);
        invoker.execute();
    }
}