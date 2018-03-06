package com.zimug.imooc.websky.baseauth.api.service;

import com.zimug.imooc.websky.common.mapper.SysConfigMapper;
import com.zimug.imooc.websky.common.model.SysConfig;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import net.aexit.galaxy.earth.common.utils.BizCommonExceptionEnum;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by mss on 17-9-18.
 */
@Service
public class ConfigService {
    @Resource
    SysConfigMapper sysConfigMapper;


    /**
     * 添加系统配置
     * @param sysConfig 系统配置实体信息
     * @throws BizCommonException
     */
    public int add(SysConfig sysConfig)  throws BizCommonException {

        this.sysConfigMapper.insert(sysConfig);

        return sysConfig.getId();

    }

    /**
     * 根据id查看系统配置信息
     * @param id  主键id
     * @throws BizCommonException
     */
    public SysConfig view(Integer id) throws BizCommonException{
        //Id不能为空
        if(StringUtils.isEmpty(id)){
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }

        return sysConfigMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改系统配置
     * @param sysConfig 系统配置实体信息
     * @throws BizCommonException
     */
    public void edit(SysConfig sysConfig)  throws BizCommonException{

        // 更新系统配置信息
        sysConfigMapper.updateByPrimaryKeySelective(sysConfig);
    }


    /**
     * 根据id删除系统配置信息
     * @param id   系统配置id即表id
     * @throws BizCommonException
     */
    @Transactional
    public void delete(Integer id) throws BizCommonException{

        //id不能为空
        if(StringUtils.isEmpty(id)){
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }
        //根据id删除当前系统配置信息
        sysConfigMapper.deleteByPrimaryKey(id);

    }
}
