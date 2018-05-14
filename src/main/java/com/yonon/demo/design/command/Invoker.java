package com.yonon.demo.design.command;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-03-19.
 */
public class Invoker {
    /**
     * 命令对象
     */
    public Command command;

    /**
     * 命令设置方法
     * @param command
     */
    public void setCommand(Command cmd){
        this.command = cmd;
    }

    /**
     * 命令执行方法
     */
    public void execute(){
        this.command.execute();
    }
}
