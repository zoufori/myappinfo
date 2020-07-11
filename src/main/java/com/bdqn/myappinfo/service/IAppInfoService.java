package com.bdqn.myappinfo.service;

import com.bdqn.myappinfo.pojo.AppInfo;

import java.util.List;

public interface IAppInfoService {

    public List<AppInfo> getByPage(Integer pageNum, Integer page) throws Exception;

    public List<AppInfo> getByPageAndText(String text, Long categoryLevel1, Long categoryLevel2, Long categoryLevel3, Long flatForm, Long status, Integer pageNum, Integer page) throws Exception;

    public AppInfo getById(Long id) throws Exception;

    public Integer delete(Long id) throws Exception;

    public Integer update(AppInfo appInfo) throws Exception;

    public Integer insert(AppInfo appInfo) throws Exception;

}
