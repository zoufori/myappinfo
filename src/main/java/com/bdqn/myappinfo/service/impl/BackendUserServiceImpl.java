package com.bdqn.myappinfo.service.impl;

import com.bdqn.myappinfo.dao.BackendUserMapper;
import com.bdqn.myappinfo.dao.DataDictionaryMapper;
import com.bdqn.myappinfo.pojo.BackendUser;
import com.bdqn.myappinfo.pojo.DataDictionary;
import com.bdqn.myappinfo.service.IBackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BackendUserServiceImpl implements IBackendUserService {

    @Autowired
    private BackendUserMapper backendUserMapper;
    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public BackendUser login(String userCode, String password) throws Exception {
        BackendUser backendUser = new BackendUser();
        backendUser.setUsercode(userCode);
        backendUser.setUserpassword(password);

        BackendUser b = backendUserMapper.selectOne(backendUser);
        if(b != null) {
            DataDictionary byId = dataDictionaryMapper.selectByPrimaryKey(b.getUsertype());
            b.setDataDictionary(byId);
        }
        return b;
    }
}
