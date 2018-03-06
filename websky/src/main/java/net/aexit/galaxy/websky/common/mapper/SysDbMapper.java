package net.aexit.galaxy.websky.common.mapper;

import net.aexit.galaxy.websky.common.model.SysDb;
import net.aexit.galaxy.websky.common.model.SysDbExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDbMapper {
    int countByExample(SysDbExample example);

    int deleteByExample(SysDbExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysDb record);

    int insertSelective(SysDb record);

    List<SysDb> selectByExample(SysDbExample example);

    SysDb selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysDb record, @Param("example") SysDbExample example);

    int updateByExample(@Param("record") SysDb record, @Param("example") SysDbExample example);

    int updateByPrimaryKeySelective(SysDb record);

    int updateByPrimaryKey(SysDb record);
}