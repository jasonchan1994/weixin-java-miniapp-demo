package com.github.binarywang.demo.wx.miniapp.model;

import java.util.Date;
import javax.persistence.*;

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_time")
    private Date createTime;

    private Integer readed;

    private String context;

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
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return readed
     */
    public Integer getReaded() {
        return readed;
    }

    /**
     * @param readed
     */
    public void setReaded(Integer readed) {
        this.readed = readed;
    }

    /**
     * @return context
     */
    public String getContext() {
        return context;
    }

    /**
     * @param context
     */
    public void setContext(String context) {
        this.context = context;
    }
}