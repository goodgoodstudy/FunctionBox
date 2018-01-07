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
    Long id = null;
    long sceneId;
    String name;
    String detail;
    @Generated(hash = 1368095886)
    public Function(Long id, long sceneId, String name, String detail) {
        this.id = id;
        this.sceneId = sceneId;
        this.name = name;
        this.detail = detail;
    }
    @Generated(hash = 133141990)
    public Function() {
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
    public String getDetail() {
        return this.detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public long getSceneId() {
        return this.sceneId;
    }
    public void setSceneId(long sceneId) {
        this.sceneId = sceneId;
    }
    public void setId(Long id) {
        this.id = id;
    }

}

