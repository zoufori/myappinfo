package com.bdqn.myappinfo.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "dev_user")
public class DevUser implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 开发者帐号
     */
    @Column(name = "devCode")
    private String devcode;

    /**
     * 开发者名称
     */
    @Column(name = "devName")
    private String devname;

    /**
     * 开发者密码
     */
    @Column(name = "devPassword")
    private String devpassword;

    /**
     * 开发者电子邮箱
     */
    @Column(name = "devEmail")
    private String devemail;

    /**
     * 开发者简介
     */
    @Column(name = "devInfo")
    private String devinfo;

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
     * 获取开发者帐号
     *
     * @return devCode - 开发者帐号
     */
    public String getDevcode() {
        return devcode;
    }

    /**
     * 设置开发者帐号
     *
     * @param devcode 开发者帐号
     */
    public void setDevcode(String devcode) {
        this.devcode = devcode == null ? null : devcode.trim();
    }

    /**
     * 获取开发者名称
     *
     * @return devName - 开发者名称
     */
    public String getDevname() {
        return devname;
    }

    /**
     * 设置开发者名称
     *
     * @param devname 开发者名称
     */
    public void setDevname(String devname) {
        this.devname = devname == null ? null : devname.trim();
    }

    /**
     * 获取开发者密码
     *
     * @return devPassword - 开发者密码
     */
    public String getDevpassword() {
        return devpassword;
    }

    /**
     * 设置开发者密码
     *
     * @param devpassword 开发者密码
     */
    public void setDevpassword(String devpassword) {
        this.devpassword = devpassword == null ? null : devpassword.trim();
    }

    /**
     * 获取开发者电子邮箱
     *
     * @return devEmail - 开发者电子邮箱
     */
    public String getDevemail() {
        return devemail;
    }

    /**
     * 设置开发者电子邮箱
     *
     * @param devemail 开发者电子邮箱
     */
    public void setDevemail(String devemail) {
        this.devemail = devemail == null ? null : devemail.trim();
    }

    /**
     * 获取开发者简介
     *
     * @return devInfo - 开发者简介
     */
    public String getDevinfo() {
        return devinfo;
    }

    /**
     * 设置开发者简介
     *
     * @param devinfo 开发者简介
     */
    public void setDevinfo(String devinfo) {
        this.devinfo = devinfo == null ? null : devinfo.trim();
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