package net.aexit.galaxy.websky.baseauth.api.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import net.aexit.galaxy.websky.baseauth.api.model.SysUserRoleOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hanxt on 17-8-11.
 */
public interface SysUserExtMapper {

    SysUserRoleOrg getUser(@Param("userId") String userId);

    SysUserRoleOrg getSysUserRoleById(@Param("id") Integer id);


    List<SysUserRoleOrg> listUserWithRoleOrg(@Param("userId") String userId,
                                             @Param("userName") String userName,
                                             @Param("orgId") String orgId,
                                             @Param("phone") String phone,
                                             @Param("sex") Byte sex,
                                             @Param("email") String email,
                                             Page<SysUserRoleOrg> page);

}
