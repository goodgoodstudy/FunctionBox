package com.yu.functionbox.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

@Entity
public class Sort {
    @Id(autoincrement = true)
    Long id = null;
    String name;
    @Generated(hash = 492124304)
    public Sort(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1984197757)
    public Sort() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }


}

