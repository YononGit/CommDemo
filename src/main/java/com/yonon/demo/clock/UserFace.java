package com.yonon.demo.clock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * Created by jr-jiangyinghan on 2017-7-10.
 */
public class UserFace {
    private Map<String, MiniKitty> stringMiniKittyMap;

    public void setClock(String name, String dateSetting, String remark, boolean isRepeat, String[] repeats) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String id = sdf.format(new Date()) + new Random().ints(1, 500);
//        String dateSetting, String remark, boolean isRepeat, String[] repeats
        MiniKitty miniKitty = new MiniKitty(id, name, dateSetting, remark, isRepeat, repeats);
    }

    public void deleteClock() {
    }
}
