package com.linjinbin.test;

import org.litepal.crud.DataSupport;

/**
 * Created by binge on 2017/3/22.
 */

public class CreateTime extends DataSupport{
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
