package com.github.binarywang.demo.wx.miniapp.model;

import javax.persistence.*;

@Table(name = "banner_config")
public class BannerConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "base_url")
    private String baseUrl;

    private String url;

    private Integer order;

    private String direct;

    private String remark;

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
     * @return base_url
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @param baseUrl
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return order
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * @param order
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * @return direct
     */
    public String getDirect() {
        return direct;
    }

    /**
     * @param direct
     */
    public void setDirect(String direct) {
        this.direct = direct;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}