package com.casvolley.Models;

import java.io.Serializable;

/**
 * Created by sotra on 3/2/2017.
 */
public class UserModel implements Serializable {
    String user_id , user_name ;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
