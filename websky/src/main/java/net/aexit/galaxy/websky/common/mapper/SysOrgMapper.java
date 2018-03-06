package net.aexit.galaxy.websky.common.mapper;

import java.util.List;
import net.aexit.galaxy.websky.common.model.SysOrg;
import net.aexit.galaxy.websky.common.model.SysOrgExample;
import org.apache.ibatis.annotations.Param;

public interface SysOrgMapper {
    int countByExample(SysOrgExample example);

    int deleteByExample(SysOrgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysOrg record);

    int insertSelective(SysOrg record);

    List<SysOrg> selectByExample(SysOrgExample example);

    SysOrg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByExample(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKey(SysOrg record);
}