package com.bdqn.myappinfo.service.impl;

import com.bdqn.myappinfo.dao.DevUserMapper;
import com.bdqn.myappinfo.pojo.DevUser;
import com.bdqn.myappinfo.service.IDevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DevUserServiceImpl implements IDevUserService {

    @Autowired
    private DevUserMapper devUserMapper;

    @Override
    public DevUser login(String userCode, String password) throws Exception {
        DevUser devUser = new DevUser();
        devUser.setDevcode(userCode);
        devUser.setDevpassword(password);
        return devUserMapper.selectOne(devUser);
    }
}
