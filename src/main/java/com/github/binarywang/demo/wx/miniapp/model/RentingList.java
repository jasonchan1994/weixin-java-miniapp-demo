package com.github.binarywang.demo.wx.miniapp.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "renting_list")
public class RentingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ref_parking_lot")
    private Integer refParkingLot;

    @Column(name = "begin_time")
    private Date beginTime;

    @Column(name = "end_time")
    private Date endTime;

    private Double costs;

    /**
     * 0 free 1 renting 2 rented
     */
    @Column(name = "renting_status")
    private Integer rentingStatus;

    @Column(name = "tenant_id")
    private Integer tenantId;

    private String title;

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
     * @return ref_parking_lot
     */
    public Integer getRefParkingLot() {
        return refParkingLot;
    }

    /**
     * @param refParkingLot
     */
    public void setRefParkingLot(Integer refParkingLot) {
        this.refParkingLot = refParkingLot;
    }

    /**
     * @return begin_time
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return costs
     */
    public Double getCosts() {
        return costs;
    }

    /**
     * @param costs
     */
    public void setCosts(Double costs) {
        this.costs = costs;
    }

    /**
     * 获取0 free 1 renting 2 rented
     *
     * @return renting_status - 0 free 1 renting 2 rented
     */
    public Integer getRentingStatus() {
        return rentingStatus;
    }

    /**
     * 设置0 free 1 renting 2 rented
     *
     * @param rentingStatus 0 free 1 renting 2 rented
     */
    public void setRentingStatus(Integer rentingStatus) {
        this.rentingStatus = rentingStatus;
    }

    /**
     * @return tenant_id
     */
    public Integer getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId
     */
    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}