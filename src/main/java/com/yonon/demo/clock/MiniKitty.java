package com.yonon.demo.clock;

import java.util.Arrays;

/**
 * Created by jr-jiangyinghan on 2017-7-10.
 */
public class MiniKitty {
    private String id;
    private String name;
    private String dateSetting;
    private String remark;
    private boolean isRepeat;
    private String[] repeats;

    public MiniKitty(String id, String name, String dateSetting, String remark, boolean isRepeat, String[] repeats) {
        this.id = id;
        this.name = name;
        this.dateSetting = dateSetting;
        this.remark = remark;
        this.isRepeat = isRepeat;
        this.repeats = repeats;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateSetting() {
        return dateSetting;
    }

    public void setDateSetting(String dateSetting) {
        this.dateSetting = dateSetting;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public void setRepeat(boolean repeat) {
        isRepeat = repeat;
    }

    public String[] getRepeats() {
        return repeats;
    }

    public void setRepeats(String[] repeats) {
        this.repeats = repeats;
    }

    @Override
    public String toString() {
        return "MiniKitty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateSetting='" + dateSetting + '\'' +
                ", remark='" + remark + '\'' +
                ", isRepeat=" + isRepeat +
                ", repeats=" + Arrays.toString(repeats) +
                '}';
    }
}
