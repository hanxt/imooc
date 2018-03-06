package com.zimug.imooc.websky.baseauth.api.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.zimug.imooc.websky.common.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hanxt on 17-8-11.
 */
public interface SysRoleExtMapper {

    List<SysRole> listRole(@Param("roleId") String roleId,
                           @Param("roleName") String roleName,
                           Page<SysRole> page);

}
