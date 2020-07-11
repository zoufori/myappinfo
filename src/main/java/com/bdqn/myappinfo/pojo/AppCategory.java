package com.bdqn.myappinfo.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "app_category")
public class AppCategory implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 分类编码
     */
    @Column(name = "categoryCode")
    private String categorycode;

    /**
     * 分类名称
     */
    @Column(name = "categoryName")
    private String categoryname;

    /**
     * 父级节点id
     */
    @Column(name = "parentId")
    private Long parentid;

    /**
     * 创建者（来源于backend_user用户表的用户id）
     */
    @Column(name = "createdBy")
    private Long createdby;

    /**
     * 创建时间
     */
    @Column(name = "creationTime")
    private Date creationtime;

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
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取分类编码
     *
     * @return categoryCode - 分类编码
     */
    public String getCategorycode() {
        return categorycode;
    }

    /**
     * 设置分类编码
     *
     * @param categorycode 分类编码
     */
    public void setCategorycode(String categorycode) {
        this.categorycode = categorycode == null ? null : categorycode.trim();
    }

    /**
     * 获取分类名称
     *
     * @return categoryName - 分类名称
     */
    public String getCategoryname() {
        return categoryname;
    }

    /**
     * 设置分类名称
     *
     * @param categoryname 分类名称
     */
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

    /**
     * 获取父级节点id
     *
     * @return parentId - 父级节点id
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * 设置父级节点id
     *
     * @param parentid 父级节点id
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
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
     * @return creationTime - 创建时间
     */
    public Date getCreationtime() {
        return creationtime;
    }

    /**
     * 设置创建时间
     *
     * @param creationtime 创建时间
     */
    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
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