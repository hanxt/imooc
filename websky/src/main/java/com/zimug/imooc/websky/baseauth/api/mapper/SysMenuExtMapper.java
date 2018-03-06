package com.zimug.imooc.websky.baseauth.api.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.zimug.imooc.websky.common.model.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hanxt on 17-8-11.
 */
public interface SysMenuExtMapper {

    List<SysMenu> listMenu(@Param("roleId") String roleId,
                           @Param("menuName") String menuName,
                           @Param("level") String level,
                           Page<SysMenu> page);



    List<SysMenu> listMenuByUserId(@Param("userId") String userId,
                                   @Param("level") String level,
                                   Page<SysMenu> page);


    List<String> getMenuURLsByRoleId(@Param("roleId") String roleId);


    List<HashMap<String,String>> getMenuURLRoleRelation();

}
