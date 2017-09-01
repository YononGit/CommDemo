package com.yonon.demo.launch;

import com.yonon.demo.db.MySqlDriver;
import com.yonon.demo.util.SequenceIDUtils;

import java.sql.ResultSet;

/**
 * Created by jr-jiangyinghan on 2017-6-22.
 */
public class CentosDB implements Runnable {


    public static void main(String[] args) {
//        exc("Select * from tb_user t");
//        exc("Select * from tb_user t where id = 1");

//        insert("D", 20000);
//        System.out.println(getCount("SELECT COUNT(*) as rowCount from tb_user t"));
        CentosDB centosDB = new CentosDB();
        Thread t1 = new Thread(centosDB, "t1");
        Thread t2 = new Thread(centosDB, "t2");
        Thread t3 = new Thread(centosDB, "t3");
        Thread t4 = new Thread(centosDB, "t4");
        Thread t5 = new Thread(centosDB, "t5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }

    private static void exc(String sql) {
        try {
            ResultSet resultSet = MySqlDriver.getRS(sql);
            if (resultSet == null) {
                System.out.println("not data..");
            }
            while (resultSet.next()) {
                System.out.println("输出数据..");
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String sex = resultSet.getString(3);
                System.out.println("id:" + id + "----name:" + name + "----sex:" + sex);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            SequenceIDUtils.getID("D", 20000);
        } catch (Exception ex) {

        }
    }
}
