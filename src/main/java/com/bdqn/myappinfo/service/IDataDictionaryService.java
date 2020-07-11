package com.bdqn.myappinfo.service;

import com.bdqn.myappinfo.pojo.DataDictionary;

import java.util.List;

public interface IDataDictionaryService {

    public DataDictionary getById(Long id) throws Exception;

    public List<DataDictionary> getByTypeCode(String tcode) throws Exception;

}
