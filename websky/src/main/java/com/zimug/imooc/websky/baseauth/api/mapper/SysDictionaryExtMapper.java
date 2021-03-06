package com.zimug.imooc.websky.baseauth.api.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.zimug.imooc.websky.common.model.SysDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xinglili on 17-8-11.
 */
public interface SysDictionaryExtMapper {

    List<SysDictionary> listDictionary(@Param("dicClass") String dicClass,
                                       @Param("dicValue") String dicValue,
                                       Page<SysDictionary> page);

}
