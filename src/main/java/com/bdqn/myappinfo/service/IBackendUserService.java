package com.bdqn.myappinfo.service;

import com.bdqn.myappinfo.pojo.BackendUser;

public interface IBackendUserService {

    public BackendUser login(String userCode, String password) throws Exception;

}
