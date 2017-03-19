package com.linjinbin.test;

import org.litepal.crud.DataSupport;

/**
 * Created by binge on 2017/3/19.
 */

public class Coordinate extends DataSupport{
    String id;
    String x;
    String y;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
