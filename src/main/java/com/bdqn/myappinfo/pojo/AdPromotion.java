package com.bdqn.myappinfo.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "ad_promotion")
public class AdPromotion implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * appId（来源于：app_info表的主键id）
     */
    @Column(name = "appId")
    private Long appid;

    /**
     * 广告图片存储路径
     */
    @Column(name = "adPicPath")
    private String adpicpath;

    /**
     * 广告点击量
     */
    @Column(name = "adPV")
    private Long adpv;

    /**
     * 轮播位（1-n）
     */
    @Column(name = "carouselPosition")
    private Integer carouselposition;

    /**
     * 起效时间
     */
    @Column(name = "startTime")
    private Date starttime;

    /**
     * 失效时间
     */
    @Column(name = "endTime")
    private Date endtime;

    /**
     * 创建者（来源于backend_user用户表的用户id）
     */
    @Column(name = "createdBy")
    private Long createdby;

    /**
     * 创建时间
     */
    @Column(name = "creationDate")
    private Date creationdate;

    /**
     * 更新者（来源于backend_user用户表的用户id）
     */
    @Column(name = "modifyBy")
    private Long modifyby;

    /**
     * 最新更新时间
     */
    @Column(name = "modifyDate")
    private Date modifydate;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取appId（来源于：app_info表的主键id）
     *
     * @return appId - appId（来源于：app_info表的主键id）
     */
    public Long getAppid() {
        return appid;
    }

    /**
     * 设置appId（来源于：app_info表的主键id）
     *
     * @param appid appId（来源于：app_info表的主键id）
     */
    public void setAppid(Long appid) {
        this.appid = appid;
    }

    /**
     * 获取广告图片存储路径
     *
     * @return adPicPath - 广告图片存储路径
     */
    public String getAdpicpath() {
        return adpicpath;
    }

    /**
     * 设置广告图片存储路径
     *
     * @param adpicpath 广告图片存储路径
     */
    public void setAdpicpath(String adpicpath) {
        this.adpicpath = adpicpath == null ? null : adpicpath.trim();
    }

    /**
     * 获取广告点击量
     *
     * @return adPV - 广告点击量
     */
    public Long getAdpv() {
        return adpv;
    }

    /**
     * 设置广告点击量
     *
     * @param adpv 广告点击量
     */
    public void setAdpv(Long adpv) {
        this.adpv = adpv;
    }

    /**
     * 获取轮播位（1-n）
     *
     * @return carouselPosition - 轮播位（1-n）
     */
    public Integer getCarouselposition() {
        return carouselposition;
    }

    /**
     * 设置轮播位（1-n）
     *
     * @param carouselposition 轮播位（1-n）
     */
    public void setCarouselposition(Integer carouselposition) {
        this.carouselposition = carouselposition;
    }

    /**
     * 获取起效时间
     *
     * @return startTime - 起效时间
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * 设置起效时间
     *
     * @param starttime 起效时间
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * 获取失效时间
     *
     * @return endTime - 失效时间
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * 设置失效时间
     *
     * @param endtime 失效时间
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * 获取创建者（来源于backend_user用户表的用户id）
     *
     * @return createdBy - 创建者（来源于backend_user用户表的用户id）
     */
    public Long getCreatedby() {
        return createdby;
    }

    /**
     * 设置创建者（来源于backend_user用户表的用户id）
     *
     * @param createdby 创建者（来源于backend_user用户表的用户id）
     */
    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    /**
     * 获取创建时间
     *
     * @return creationDate - 创建时间
     */
    public Date getCreationdate() {
        return creationdate;
    }

    /**
     * 设置创建时间
     *
     * @param creationdate 创建时间
     */
    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    /**
     * 获取更新者（来源于backend_user用户表的用户id）
     *
     * @return modifyBy - 更新者（来源于backend_user用户表的用户id）
     */
    public Long getModifyby() {
        return modifyby;
    }

    /**
     * 设置更新者（来源于backend_user用户表的用户id）
     *
     * @param modifyby 更新者（来源于backend_user用户表的用户id）
     */
    public void setModifyby(Long modifyby) {
        this.modifyby = modifyby;
    }

    /**
     * 获取最新更新时间
     *
     * @return modifyDate - 最新更新时间
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * 设置最新更新时间
     *
     * @param modifydate 最新更新时间
     */
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}