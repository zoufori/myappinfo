package com.bdqn.myappinfo.service.impl;

import com.bdqn.myappinfo.dao.AppInfoMapper;
import com.bdqn.myappinfo.dao.AppVersionMapper;
import com.bdqn.myappinfo.dao.DataDictionaryMapper;
import com.bdqn.myappinfo.pojo.AppInfo;
import com.bdqn.myappinfo.pojo.AppVersion;
import com.bdqn.myappinfo.pojo.DataDictionary;
import com.bdqn.myappinfo.service.IAppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class AppVersionServiceImpl implements IAppVersionService {

    @Autowired
    private AppVersionMapper appVersionMapper;
    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;
    @Autowired
    private AppInfoMapper appInfoMapper;

    @Override
    public AppVersion getById(Long id) throws Exception {
        if(id != null){
            AppVersion appVersion = appVersionMapper.selectByPrimaryKey(id);
            return getForeignKey(appVersion);
        }else{
            return null;
        }
    }

    @Override
    public List<AppVersion> getByAppId(Long appid) throws Exception {
        AppVersion appVersion = new AppVersion();
        appVersion.setAppid(appid);
        List<AppVersion> select = appVersionMapper.select(appVersion);
        return getForeignKeyForList(select);
    }

    @Override
    public Integer update(AppVersion appVersion) throws Exception {
        return appVersionMapper.updateByPrimaryKeySelective(appVersion);
    }

    @Override
    public Integer save(AppVersion appVersion) throws Exception {
        return appVersionMapper.insertSelective(appVersion);
    }

    @Override
    public Integer delete(Long id) throws Exception {
        return appVersionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public AppVersion getNewest() throws Exception {
        Example example = new Example(AppVersion.class);
        example.createCriteria();
        example.orderBy("id").desc();
        List<AppVersion> appVersions = appVersionMapper.selectByExample(example);
        AppVersion appVersion = appVersions.get(0);
        getForeignKey(appVersion);
        return appVersion;
    }

    private List<AppVersion> getForeignKeyForList(List<AppVersion> list) throws Exception{
        if(list == null){
            return null;
        }

        return list.parallelStream().peek(this::addKey).collect(Collectors.toList());
    }

    private AppVersion getForeignKey(AppVersion appVersion) throws Exception{
        if(appVersion == null){
            return null;
        }
        addKey(appVersion);
        return appVersion;
    }

    private void addKey(AppVersion appVersion) {
        DataDictionary dataDictionary = new DataDictionary();
        dataDictionary.setValueid(appVersion.getPublishstatus());
        dataDictionary.setTypecode("PUBLISH_STATUS");
        DataDictionary dataDictionary1 = dataDictionaryMapper.selectOne(dataDictionary);
        AppInfo appInfo = appInfoMapper.selectByPrimaryKey(appVersion.getAppid());
        if(appInfo != null){
            appVersion.setAppName(appInfo.getSoftwarename());
        }
        if(dataDictionary1 != null) {
            appVersion.setPublishStatusName(dataDictionary1.getValuename());
        }
    }
}
