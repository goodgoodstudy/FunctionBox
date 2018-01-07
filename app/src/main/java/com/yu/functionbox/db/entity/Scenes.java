package com.yu.functionbox.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

@Entity
public class Scenes {
    @Id(autoincrement = true)
    Long id = null;
    long sortId;
    String name;
    @Generated(hash = 1715213794)
    public Scenes(Long id, long sortId, String name) {
        this.id = id;
        this.sortId = sortId;
        this.name = name;
    }
    @Generated(hash = 1003349671)
    public Scenes() {
    }
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getSortId() {
        return this.sortId;
    }
    public void setSortId(long sortId) {
        this.sortId = sortId;
    }
    public void setId(Long id) {
        this.id = id;
    }

}

