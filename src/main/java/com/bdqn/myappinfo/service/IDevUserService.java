package com.bdqn.myappinfo.service;

import com.bdqn.myappinfo.pojo.DevUser;

public interface IDevUserService {

    public DevUser login(String userCode, String password) throws Exception;

}
