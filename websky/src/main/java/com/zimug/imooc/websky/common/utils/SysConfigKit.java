package com.zimug.imooc.websky.common.utils;

import com.zimug.imooc.websky.common.model.SysConfig;
import com.zimug.imooc.websky.common.model.SysConfigExample;
import com.zimug.imooc.websky.common.mapper.SysConfigMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanxt on 17-9-5.
 */
@Component
public class SysConfigKit implements CommandLineRunner {

    @Resource
    private SysConfigMapper  sysConfigMapperAutoWired;

    private static List<SysConfig> sysConfigs;

    private static SysConfigMapper  sysConfigMapper;

    /**
     * 解决静态资源无法注入的问题
     */
    @PostConstruct
    public void init() {
        sysConfigMapper = this.sysConfigMapperAutoWired;
    }

    @Override
    public void run(String... strings) throws Exception {
        SysConfigExample sysConfigExample = new SysConfigExample();
        sysConfigs = sysConfigMapper.selectByExample(sysConfigExample);
    }


    /**
     * 根据分组获取一组key和value
     * @param group
     * @return
     */
    public static Map<String,String> getSysConfigByGroup(String group){
        Map<String,String> ret = new HashMap<>();
        for(SysConfig sysConfig : sysConfigs){
            if(sysConfig.getConfigGroup().equals(group)){
                ret.put(sysConfig.getConfigKey(),sysConfig.getConfigValue());
            }
        }
        return ret;
    }


    /**
     * 根据分组获取一组key和value,实时获取,数据库发生更改即变化
     * @param group
     * @return
     */
    public static Map<String,String> getSysConfigByGroupRealTime(String group){
        Map<String,String> ret = new HashMap<>();

        SysConfigExample sysConfigExample = new SysConfigExample();
        sysConfigExample.createCriteria().andConfigGroupEqualTo(group);
        List<SysConfig> tmpList = sysConfigMapper.selectByExample(sysConfigExample);

        for(SysConfig sysConfig : tmpList){
                ret.put(sysConfig.getConfigKey(),sysConfig.getConfigValue());
        }
        return ret;
    }



    /**
     * 系统配置根据key获取value
     * @param key
     * @return
     */
    public static String getSysConfigByKey(String key){
        for(SysConfig sysConfig : sysConfigs){
            if(sysConfig.getConfigKey().equals(key)){
                return sysConfig.getConfigValue();
            }
        }
        return null;
    }


    /**
     * 系统配置根据key获取value,实时获取,数据库发生更改即变化
     * @param key
     * @return
     */
    public static String getSysConfigByKeyRealTime(String key){

        SysConfigExample sysConfigExample = new SysConfigExample();
        sysConfigExample.createCriteria().andConfigKeyEqualTo(key);
        List<SysConfig> tmpList = sysConfigMapper.selectByExample(sysConfigExample);

        if(tmpList != null && tmpList.size() > 0){
            return tmpList.get(0).getConfigValue();
        }
        return null;
    }
}
