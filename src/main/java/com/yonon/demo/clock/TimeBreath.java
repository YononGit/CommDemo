package com.yonon.demo.clock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by jr-jiangyinghan on 2017-7-10.
 */
public class TimeBreath implements Runnable {
    private Date memDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private HashMap<String, MiniKitty> miniKittyHashMap;

    public HashMap<String, MiniKitty> getMiniKittyHashMap() {
        return miniKittyHashMap;
    }

    public void setMiniKittyHashMap(HashMap<String, MiniKitty> miniKittyHashMap) {
        this.miniKittyHashMap = miniKittyHashMap;
    }

    public Date getMemDate() {
        return memDate;
    }

    public void setMemDate(Date memDate) {
        this.memDate = memDate;
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
//    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(sdf.format(memDate) + " now is: " + sdf.format(new Date()));
                if (sdf.format(memDate).equals(sdf.format(new Date())))
                    System.out.println("bilibili...");
                String dateStr = sdf.format(new Date());
                if (miniKittyHashMap.containsKey(sdf.format(new Date()))) {
                    System.out.println("miniKittyHashMap call...");
                    MiniKitty miniKitty = miniKittyHashMap.get(dateStr);
                    System.out.println(miniKitty.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
