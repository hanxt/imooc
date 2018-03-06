package com.zimug.imooc.websky.baseauth.api.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.zimug.imooc.websky.common.model.SysOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hanxt on 17-8-11.
 */
public interface SysOrgExtMapper {

    List<SysOrg> listOrg(@Param("orgName") String orgName,
                         @Param("level") String level,
                         Page<SysOrg> page);

}
