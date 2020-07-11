package com.bdqn.myappinfo.service;

import com.bdqn.myappinfo.pojo.AppVersion;

import java.util.List;

public interface IAppVersionService {

    public AppVersion getById(Long id) throws Exception;

    public List<AppVersion> getByAppId(Long appid) throws Exception;

    public Integer update(AppVersion appVersion) throws Exception;

    public Integer save(AppVersion appVersion) throws Exception;

    public Integer delete(Long id) throws Exception;

    public AppVersion getNewest() throws Exception;

}
