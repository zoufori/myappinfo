package com.bdqn.myappinfo.service.impl;

import com.bdqn.myappinfo.dao.*;
import com.bdqn.myappinfo.pojo.*;
import com.bdqn.myappinfo.service.IAppInfoService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class AppInfoServiceImpl implements IAppInfoService {

    @Autowired
    private AppInfoMapper appInfoMapper;
    @Autowired
    private DevUserMapper devUserMapper;
    @Autowired
    private AppCategoryMapper appCategoryMapper;
    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;
    @Autowired
    private AppVersionMapper appVersionMapper;

    @Override
    public List<AppInfo> getByPage(Integer pageNum, Integer page) throws Exception {
        if (page == null)
            page = 8;

        RowBounds r = new RowBounds((pageNum - 1) * page, page);
        List<AppInfo> list = appInfoMapper.selectByRowBounds(new AppInfo(), r);
        list = addForeignKey(list);
        return list;
    }

    @Override
    public List<AppInfo> getByPageAndText(String text, Long categoryLevel1, Long categoryLevel2, Long categoryLevel3, Long flatForm, Long status, Integer pageNum, Integer page) throws Exception {
        if (page == null)
            page = 8;
        List<AppInfo> list = null;
        if (pageNum != null) {
            list = appInfoMapper.getByPageAndText(text, categoryLevel1, categoryLevel2, categoryLevel3, flatForm, status, (pageNum - 1) * page, page);
        } else {
            list = appInfoMapper.getByPageAndText(text, categoryLevel1, categoryLevel2, categoryLevel3, flatForm, status, null, null);
        }
        list = addForeignKey(list);
        return list;
    }

    @Override
    public AppInfo getById(Long id) throws Exception {
        if (id != null) {
            AppInfo appInfo = appInfoMapper.selectByPrimaryKey(id);
            return addForeignKeyForAppInfo(appInfo);

        } else {
            return null;
        }
    }

    @Override
    public Integer delete(Long id) throws Exception {
        if (id != null) {
            return appInfoMapper.deleteByPrimaryKey(id);
        } else {
            return -1;
        }
    }

    @Override
    public Integer update(AppInfo appInfo) throws Exception {
        if (appInfo.getId() != null) {
            return appInfoMapper.updateByPrimaryKeySelective(appInfo);
        } else {
            return -1;
        }
    }

    @Override
    public Integer insert(AppInfo appInfo) throws Exception {
        return appInfoMapper.insertSelective(appInfo);
    }

    private AppInfo addForeignKeyForAppInfo(AppInfo appInfo) {
        DataDictionary dataDictionary = new DataDictionary();

        AppCategory level1 = appCategoryMapper.selectByPrimaryKey(appInfo.getCategorylevel1());
        AppCategory level2 = appCategoryMapper.selectByPrimaryKey(appInfo.getCategorylevel2());
        AppCategory level3 = appCategoryMapper.selectByPrimaryKey(appInfo.getCategorylevel3());
        AppVersion appVersion = appVersionMapper.selectByPrimaryKey(appInfo.getVersionid());
        dataDictionary.setTypecode("APP_STATUS");
        dataDictionary.setValueid(appInfo.getStatus());
        DataDictionary status = dataDictionaryMapper.selectOne(dataDictionary);
        dataDictionary.setTypecode("APP_FLATFORM");
        dataDictionary.setValueid(appInfo.getFlatformid());
        DataDictionary flatform = dataDictionaryMapper.selectOne(dataDictionary);

        judge(appInfo, level1, level2, level3, status, flatform, appVersion);

        return appInfo;
    }

    private List<AppInfo> addForeignKey(List<AppInfo> list) {
        List<AppInfo> appInfoList = list.parallelStream().peek(x -> {
            Long devId = x.getDevid();
            Long categorylevel1 = x.getCategorylevel1();
            Long categorylevel2 = x.getCategorylevel2();
            Long categorylevel3 = x.getCategorylevel3();
            Long statusid = x.getStatus();
            Long flatformid = x.getFlatformid();
            Long versionid = x.getVersionid();

            DevUser byId = null;
            AppCategory appCategory1 = null;
            AppCategory appCategory2 = null;
            AppCategory appCategory3 = null;
            DataDictionary status = null;
            DataDictionary flatform = null;
            AppVersion version = null;

            try {
                byId = devUserMapper.selectByPrimaryKey(devId);
                appCategory1 = appCategoryMapper.selectByPrimaryKey(categorylevel1);
                appCategory2 = appCategoryMapper.selectByPrimaryKey(categorylevel2);
                appCategory3 = appCategoryMapper.selectByPrimaryKey(categorylevel3);
                DataDictionary s = new DataDictionary();
                s.setValueid(statusid);
                s.setTypecode("APP_STATUS");
                status = dataDictionaryMapper.selectOne(s);
                s.setValueid(flatformid);
                s.setTypecode("APP_FLATFORM");
                flatform = dataDictionaryMapper.selectOne(s);
                version = appVersionMapper.selectByPrimaryKey(versionid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            x.setDevUser(byId);
            judge(x, appCategory1, appCategory2, appCategory3, status, flatform, version);

        }).collect(Collectors.toList());
        return appInfoList;
    }

    private void judge(AppInfo x, AppCategory appCategory1, AppCategory appCategory2, AppCategory appCategory3, DataDictionary status, DataDictionary flatform, AppVersion version) {
        if (appCategory1 != null) {
            x.setCategoryLevel1Name(appCategory1.getCategoryname());
        }
        if (appCategory2 != null) {
            x.setCategoryLevel2Name(appCategory2.getCategoryname());
        }
        if (appCategory3 != null) {
            x.setCategoryLevel3Name(appCategory3.getCategoryname());
        }
        if (status != null) {
            x.setStatusName(status.getValuename());
        }
        if (flatform != null) {
            x.setFlatformName(flatform.getValuename());
        }
        if (version != null) {
            x.setVersionNo(version.getVersionno());
        }
    }
}
