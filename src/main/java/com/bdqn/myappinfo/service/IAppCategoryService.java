package com.bdqn.myappinfo.service;

import com.bdqn.myappinfo.pojo.AppCategory;

import java.util.List;

public interface IAppCategoryService {

    public List<AppCategory> getCategory(Long parentId) throws Exception;

    public AppCategory getById(Long id) throws Exception;

}
