package com.bdqn.myappinfo.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "app_version")
public class AppVersion implements Serializable {
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
     * 版本号
     */
    @Column(name = "versionNo")
    private String versionno;

    /**
     * 版本介绍
     */
    @Column(name = "versionInfo")
    private String versioninfo;

    /**
     * 发布状态（来源于：data_dictionary，1 不发布 2 已发布 3 预发布）
     */
    @Column(name = "publishStatus")
    private Long publishstatus;

    /**
     * 下载链接
     */
    @Column(name = "downloadLink")
    private String downloadlink;

    /**
     * 版本大小（单位：M）
     */
    @Column(name = "versionSize")
    private BigDecimal versionsize;

    /**
     * 创建者（来源于dev_user开发者信息表的用户id）
     */
    @Column(name = "createdBy")
    private Long createdby;

    /**
     * 创建时间
     */
    @Column(name = "creationDate")
    private Date creationdate;

    /**
     * 更新者（来源于dev_user开发者信息表的用户id）
     */
    @Column(name = "modifyBy")
    private Long modifyby;

    /**
     * 最新更新时间
     */
    @Column(name = "modifyDate")
    private Date modifydate;

    /**
     * apk文件的服务器存储路径
     */
    @Column(name = "apkLocPath")
    private String apklocpath;

    /**
     * 上传的apk文件名称
     */
    @Column(name = "apkFileName")
    private String apkfilename;

    private static final long serialVersionUID = 1L;

    @Transient private String publishStatusName;
    @Transient private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPublishStatusName() {
        return publishStatusName;
    }

    public void setPublishStatusName(String publishStatusName) {
        this.publishStatusName = publishStatusName;
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
     * 获取版本号
     *
     * @return versionNo - 版本号
     */
    public String getVersionno() {
        return versionno;
    }

    /**
     * 设置版本号
     *
     * @param versionno 版本号
     */
    public void setVersionno(String versionno) {
        this.versionno = versionno == null ? null : versionno.trim();
    }

    /**
     * 获取版本介绍
     *
     * @return versionInfo - 版本介绍
     */
    public String getVersioninfo() {
        return versioninfo;
    }

    /**
     * 设置版本介绍
     *
     * @param versioninfo 版本介绍
     */
    public void setVersioninfo(String versioninfo) {
        this.versioninfo = versioninfo == null ? null : versioninfo.trim();
    }

    /**
     * 获取发布状态（来源于：data_dictionary，1 不发布 2 已发布 3 预发布）
     *
     * @return publishStatus - 发布状态（来源于：data_dictionary，1 不发布 2 已发布 3 预发布）
     */
    public Long getPublishstatus() {
        return publishstatus;
    }

    /**
     * 设置发布状态（来源于：data_dictionary，1 不发布 2 已发布 3 预发布）
     *
     * @param publishstatus 发布状态（来源于：data_dictionary，1 不发布 2 已发布 3 预发布）
     */
    public void setPublishstatus(Long publishstatus) {
        this.publishstatus = publishstatus;
    }

    /**
     * 获取下载链接
     *
     * @return downloadLink - 下载链接
     */
    public String getDownloadlink() {
        return downloadlink;
    }

    /**
     * 设置下载链接
     *
     * @param downloadlink 下载链接
     */
    public void setDownloadlink(String downloadlink) {
        this.downloadlink = downloadlink == null ? null : downloadlink.trim();
    }

    /**
     * 获取版本大小（单位：M）
     *
     * @return versionSize - 版本大小（单位：M）
     */
    public BigDecimal getVersionsize() {
        return versionsize;
    }

    /**
     * 设置版本大小（单位：M）
     *
     * @param versionsize 版本大小（单位：M）
     */
    public void setVersionsize(BigDecimal versionsize) {
        this.versionsize = versionsize;
    }

    /**
     * 获取创建者（来源于dev_user开发者信息表的用户id）
     *
     * @return createdBy - 创建者（来源于dev_user开发者信息表的用户id）
     */
    public Long getCreatedby() {
        return createdby;
    }

    /**
     * 设置创建者（来源于dev_user开发者信息表的用户id）
     *
     * @param createdby 创建者（来源于dev_user开发者信息表的用户id）
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
     * 获取更新者（来源于dev_user开发者信息表的用户id）
     *
     * @return modifyBy - 更新者（来源于dev_user开发者信息表的用户id）
     */
    public Long getModifyby() {
        return modifyby;
    }

    /**
     * 设置更新者（来源于dev_user开发者信息表的用户id）
     *
     * @param modifyby 更新者（来源于dev_user开发者信息表的用户id）
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
     * 获取apk文件的服务器存储路径
     *
     * @return apkLocPath - apk文件的服务器存储路径
     */
    public String getApklocpath() {
        return apklocpath;
    }

    /**
     * 设置apk文件的服务器存储路径
     *
     * @param apklocpath apk文件的服务器存储路径
     */
    public void setApklocpath(String apklocpath) {
        this.apklocpath = apklocpath == null ? null : apklocpath.trim();
    }

    /**
     * 获取上传的apk文件名称
     *
     * @return apkFileName - 上传的apk文件名称
     */
    public String getApkfilename() {
        return apkfilename;
    }

    /**
     * 设置上传的apk文件名称
     *
     * @param apkfilename 上传的apk文件名称
     */
    public void setApkfilename(String apkfilename) {
        this.apkfilename = apkfilename == null ? null : apkfilename.trim();
    }

    @Override
    public String toString() {
        return "AppVersion{" +
                "id=" + id +
                ", appid=" + appid +
                ", versionno='" + versionno + '\'' +
                ", versioninfo='" + versioninfo + '\'' +
                ", publishstatus=" + publishstatus +
                ", downloadlink='" + downloadlink + '\'' +
                ", versionsize=" + versionsize +
                ", createdby=" + createdby +
                ", creationdate=" + creationdate +
                ", modifyby=" + modifyby +
                ", modifydate=" + modifydate +
                ", apklocpath='" + apklocpath + '\'' +
                ", apkfilename='" + apkfilename + '\'' +
                ", publishStatusName='" + publishStatusName + '\'' +
                ", appName='" + appName + '\'' +
                '}';
    }
}