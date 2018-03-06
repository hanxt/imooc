package net.aexit.galaxy.websky.baseauth.api.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import net.aexit.galaxy.websky.common.model.SysRole;
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
