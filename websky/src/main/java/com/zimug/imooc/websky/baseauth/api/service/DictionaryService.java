package com.zimug.imooc.websky.baseauth.api.service;

import com.zimug.imooc.websky.common.mapper.SysDictionaryMapper;
import com.zimug.imooc.websky.common.model.SysDictionary;
import com.zimug.imooc.websky.common.model.SysDictionaryExample;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import net.aexit.galaxy.earth.common.utils.BizCommonExceptionEnum;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by hanxt on 17-8-15.
 */
@Service
public class DictionaryService {

    @Resource
    SysDictionaryMapper sysDictionaryMapper;

    /**
     * 添加新字典
     * @param sysDictionary 字典实体
     * @throws BizCommonException
     */
    public int add(SysDictionary sysDictionary)  throws BizCommonException{
        int countDictionary = getDictionaryByProperty(sysDictionary);
        if (countDictionary > 0) {
            throw new BizCommonException(BizCommonExceptionEnum.DICT_EXISTED);
        }
        // 向数据库中插入数据
        this.sysDictionaryMapper.insert(sysDictionary);

        return sysDictionary.getId();
    }

    /**
     * 按照属性查询字典
     * @param sysDictionary 字典实体
     * @throws BizCommonException
     */
    public int getDictionaryByProperty(SysDictionary sysDictionary)  throws BizCommonException{
        SysDictionaryExample sysDictionaryExample = new SysDictionaryExample();
        SysDictionaryExample.Criteria criteria = sysDictionaryExample.createCriteria();
        criteria.andDicClassEqualTo(sysDictionary.getDicClass());
        criteria.andDicPropertyEqualTo(sysDictionary.getDicProperty());
        criteria.andDicValueEqualTo(sysDictionary.getDicValue());
        return this.sysDictionaryMapper.countByExample(sysDictionaryExample);
    }

    /**
     *  根据字典ID查看字典
     * @param id
     * @return
     */
    public SysDictionary getSysDictionaryById(int id){

        //主键id不能为空
        if(StringUtils.isEmpty(id)){
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }

        return sysDictionaryMapper.selectByPrimaryKey(id);

    }

    /**
     * 根据表id修改已有字典
     * @param sysDictionary 字典实体
     * @throws BizCommonException
     */
    public void edit(SysDictionary sysDictionary)  throws BizCommonException{

        // sysDictionary实体中id不能为空
        if (StringUtils.isEmpty(sysDictionary.getId())) {
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }

        int countDictionary = getDictionaryByProperty(sysDictionary);
        if (countDictionary > 0) {
            throw new BizCommonException(BizCommonExceptionEnum.DICT_EXISTED);
        }

        // 完善字典信息
        sysDictionaryMapper.updateByPrimaryKeySelective(sysDictionary);
    }


    /**
     * 删除已有字典信息
     * @param id 字典表主键id
     * @throws BizCommonException
     */
    public void delete(Integer id) throws BizCommonException{

        //主键id不能为空
        if(StringUtils.isEmpty(id)){
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }
        SysDictionary sysDictionary = sysDictionaryMapper.selectByPrimaryKey(id);
        if (!sysDictionary.equals(null)) {
            //删除字典信息
            sysDictionaryMapper.deleteByPrimaryKey(id);
        } else {
            throw new BizCommonException(BizCommonExceptionEnum.DICT_NOT_EXISTED);
        }

    }

}
