package com.bdqn.myappinfo.service.impl;

import com.bdqn.myappinfo.dao.DataDictionaryMapper;
import com.bdqn.myappinfo.pojo.DataDictionary;
import com.bdqn.myappinfo.service.IDataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DataDictionaryServiceImpl implements IDataDictionaryService {

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public DataDictionary getById(Long id) throws Exception {
        DataDictionary dataDictionary = new DataDictionary();
        dataDictionary.setId(id);
        return dataDictionaryMapper.selectOne(dataDictionary);
    }

    @Override
    public List<DataDictionary> getByTypeCode(String tcode) throws Exception {
        DataDictionary dataDictionary = new DataDictionary();
        dataDictionary.setTypecode(tcode);
        return dataDictionaryMapper.select(dataDictionary);
    }
}
