package com.github.binarywang.demo.wx.miniapp.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "my_wallet")
public class MyWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "my_balance")
    private Double myBalance;

    private String ramark;

    @Column(name = "create_time")
    private Date createTime;

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
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return my_balance
     */
    public Double getMyBalance() {
        return myBalance;
    }

    /**
     * @param myBalance
     */
    public void setMyBalance(Double myBalance) {
        this.myBalance = myBalance;
    }

    /**
     * @return ramark
     */
    public String getRamark() {
        return ramark;
    }

    /**
     * @param ramark
     */
    public void setRamark(String ramark) {
        this.ramark = ramark;
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
}