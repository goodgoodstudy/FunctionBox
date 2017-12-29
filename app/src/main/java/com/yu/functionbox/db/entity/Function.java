package com.yu.functionbox.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

@Entity
public class Function {
    @Id(autoincrement = true)
    long id;
    String name;
    String detail;
    @Generated(hash = 445291440)
    public Function(long id, String name, String detail) {
        this.id = id;
        this.name = name;
        this.detail = detail;
    }
    @Generated(hash = 133141990)
    public Function() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDetail() {
        return this.detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

}

