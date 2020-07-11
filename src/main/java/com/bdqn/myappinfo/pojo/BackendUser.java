package com.bdqn.myappinfo.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "backend_user")
public class BackendUser implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 用户编码
     */
    @Column(name = "userCode")
    private String usercode;

    /**
     * 用户名称
     */
    @Column(name = "userName")
    private String username;

    /**
     * 用户角色类型（来源于数据字典表，分为：超管、财务、市场、运营、销售）
     */
    @Column(name = "userType")
    private Long usertype;

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

    /**
     * 用户密码
     */
    @Column(name = "userPassword")
    private String userpassword;

    private static final long serialVersionUID = 1L;

    private DataDictionary dataDictionary;

    public DataDictionary getDataDictionary() {
        return dataDictionary;
    }

    public void setDataDictionary(DataDictionary dataDictionary) {
        this.dataDictionary = dataDictionary;
    }

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
     * 获取用户编码
     *
     * @return userCode - 用户编码
     */
    public String getUsercode() {
        return usercode;
    }

    /**
     * 设置用户编码
     *
     * @param usercode 用户编码
     */
    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    /**
     * 获取用户名称
     *
     * @return userName - 用户名称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名称
     *
     * @param username 用户名称
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取用户角色类型（来源于数据字典表，分为：超管、财务、市场、运营、销售）
     *
     * @return userType - 用户角色类型（来源于数据字典表，分为：超管、财务、市场、运营、销售）
     */
    public Long getUsertype() {
        return usertype;
    }

    /**
     * 设置用户角色类型（来源于数据字典表，分为：超管、财务、市场、运营、销售）
     *
     * @param usertype 用户角色类型（来源于数据字典表，分为：超管、财务、市场、运营、销售）
     */
    public void setUsertype(Long usertype) {
        this.usertype = usertype;
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

    /**
     * 获取用户密码
     *
     * @return userPassword - 用户密码
     */
    public String getUserpassword() {
        return userpassword;
    }

    /**
     * 设置用户密码
     *
     * @param userpassword 用户密码
     */
    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }
}