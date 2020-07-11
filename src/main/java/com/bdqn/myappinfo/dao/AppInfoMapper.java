package com.bdqn.myappinfo.dao;

import com.bdqn.myappinfo.pojo.AppInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AppInfoMapper extends Mapper<AppInfo> {

    public List<AppInfo> getByPageAndText(@Param("text") String text,
                                          @Param("categoryLevel1") Long categoryLevel1,
                                          @Param("categoryLevel2") Long categoryLevel2,
                                          @Param("categoryLevel3") Long categoryLevel3,
                                          @Param("flatForm") Long flatformId,
                                          @Param("status") Long status,
                                          @Param("pageNum") Integer pageNum,
                                          @Param("page") Integer page) throws Exception;

}