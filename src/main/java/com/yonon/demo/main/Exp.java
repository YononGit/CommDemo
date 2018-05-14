package com.yonon.demo.main;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2017-11-29.
 */
public class Exp extends  Exception {
    public Exp(Exception ex){
        super(ex);
        ex.printStackTrace();
    }
}
