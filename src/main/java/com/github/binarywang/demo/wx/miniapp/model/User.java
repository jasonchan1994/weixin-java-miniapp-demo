package com.github.binarywang.demo.wx.miniapp.model;

import java.util.Date;
import javax.persistence.*;

public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "first_login_time")
    private Date firstLoginTime;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return open_id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * @return first_login_time
     */
    public Date getFirstLoginTime() {
        return firstLoginTime;
    }

    /**
     * @param firstLoginTime
     */
    public void setFirstLoginTime(Date firstLoginTime) {
        this.firstLoginTime = firstLoginTime;
    }

    /**
     * @return last_login_time
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * @param lastLoginTime
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
