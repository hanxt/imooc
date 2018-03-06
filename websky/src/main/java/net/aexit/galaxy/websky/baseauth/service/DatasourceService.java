package net.aexit.galaxy.websky.baseauth.service;

import org.springframework.stereotype.Service;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import net.aexit.galaxy.earth.common.utils.BizCommonExceptionEnum;
import javax.annotation.Resource;
import java.util.List;
import net.aexit.galaxy.websky.common.mapper.SysDbMapper;
import net.aexit.galaxy.websky.common.model.SysDb;
import net.aexit.galaxy.websky.common.model.SysDbExample;

/**
 * 数据源服务类
 *
 * @author hanxt
 * @Date 
 */
@Service
public class DatasourceService {

    @Resource
    SysDbMapper  sysDbMapper;


    /**
     * 获取数据源列表
     */
    public List<SysDb> list(SysDbExample example) {
        return sysDbMapper.selectByExample(example);
    }


    /**
     * 添加数据源记录
     * @param sysDb 数据源实体信息
     * @throws BizCommonException
     */
    public void add(SysDb sysDb)
        throws BizCommonException{
        sysDbMapper.insert(sysDb);
    }


    /**
     * 删除已有数据源记录(要求每张表都要建立名字为id的主键)
     * @param id 数据源表主键id
     * @throws BizCommonException
     */
    public void delete(Integer id)  throws BizCommonException{
        //主键id不能为空
        if(id == null){
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }
        //删除数据源记录
        sysDbMapper.deleteByPrimaryKey(id);
    }


    /**
     * 根据表id修改数据源记录(要求每张表都要建立名字为id的主键)
     * @param  sysDb 数据源实体信息(包含id)
     * @throws BizCommonException
     */
    public void update(SysDb sysDb)
                        throws BizCommonException{

        //id不能为空
        if (sysDb.getId() == null) {
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }

        //根据id修改记录信息
        sysDbMapper.updateByPrimaryKeySelective(sysDb);
    }

    /**
     * 查看数据源记录详情
     * @param id 数据源表主键id
     * @throws BizCommonException
     */
    public SysDb detail(Integer id) {
        //主键id不能为空
        if(id == null){
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }
        return sysDbMapper.selectByPrimaryKey(id);
    }

}
