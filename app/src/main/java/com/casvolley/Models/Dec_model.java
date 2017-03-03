package com.casvolley.Models;

import java.io.Serializable;

/**
 * Created by sotra on 3/2/2017.
 */
public class Dec_model implements Serializable {
    String title , desc ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
