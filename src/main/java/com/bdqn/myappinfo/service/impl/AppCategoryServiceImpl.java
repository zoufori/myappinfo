package com.bdqn.myappinfo.service.impl;

import com.bdqn.myappinfo.dao.AppCategoryMapper;
import com.bdqn.myappinfo.pojo.AppCategory;
import com.bdqn.myappinfo.service.IAppCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Transactional
@Service
public class AppCategoryServiceImpl implements IAppCategoryService {

    @Autowired
    private AppCategoryMapper appCategoryMapper;

    @Override
    public List<AppCategory> getCategory(Long parentId) throws Exception {
        Example example = new Example(AppCategory.class);
        Example.Criteria criteria = example.createCriteria();
        if(parentId != null) {
            criteria.andEqualTo("parentid", parentId);
        } else {
            criteria.andIsNull("parentid");
        }
        return appCategoryMapper.selectByExample(example);
    }

    @Override
    public AppCategory getById(Long id) throws Exception {
        AppCategory appCategory = new AppCategory();
        appCategory.setId(id);
        return appCategoryMapper.selectOne(appCategory);
    }
}
