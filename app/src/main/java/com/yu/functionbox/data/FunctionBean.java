package com.yu.functionbox.data;

import java.io.Serializable;

/**
 * Created by yuw on 2017-12-29.
 * 方法
 */

public class FunctionBean implements Serializable{
    private long id;
    private String name;
    private String detail;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
