package com.bdqn.myappinfo.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "data_dictionary")
public class DataDictionary implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 类型编码
     */
    @Column(name = "typeCode")
    private String typecode;

    /**
     * 类型名称
     */
    @Column(name = "typeName")
    private String typename;

    /**
     * 类型值ID
     */
    @Column(name = "valueId")
    private Long valueid;

    /**
     * 类型值Name
     */
    @Column(name = "valueName")
    private String valuename;

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
     * 获取类型编码
     *
     * @return typeCode - 类型编码
     */
    public String getTypecode() {
        return typecode;
    }

    /**
     * 设置类型编码
     *
     * @param typecode 类型编码
     */
    public void setTypecode(String typecode) {
        this.typecode = typecode == null ? null : typecode.trim();
    }

    /**
     * 获取类型名称
     *
     * @return typeName - 类型名称
     */
    public String getTypename() {
        return typename;
    }

    /**
     * 设置类型名称
     *
     * @param typename 类型名称
     */
    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    /**
     * 获取类型值ID
     *
     * @return valueId - 类型值ID
     */
    public Long getValueid() {
        return valueid;
    }

    /**
     * 设置类型值ID
     *
     * @param valueid 类型值ID
     */
    public void setValueid(Long valueid) {
        this.valueid = valueid;
    }

    /**
     * 获取类型值Name
     *
     * @return valueName - 类型值Name
     */
    public String getValuename() {
        return valuename;
    }

    /**
     * 设置类型值Name
     *
     * @param valuename 类型值Name
     */
    public void setValuename(String valuename) {
        this.valuename = valuename == null ? null : valuename.trim();
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