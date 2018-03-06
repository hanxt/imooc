package com.zimug.imooc.websky.baseauth.api.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.zimug.imooc.websky.common.model.SysConfig;

import java.util.List;

/**
 * Created by mss on 17-9-18.
 */
public interface SysConfigExtMapper {


    List<SysConfig> list(Page<SysConfig> page);

}
