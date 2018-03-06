package net.aexit.galaxy.websky.baseauth.api.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import net.aexit.galaxy.websky.common.model.SysConfig;

import java.util.List;

/**
 * Created by mss on 17-9-18.
 */
public interface SysConfigExtMapper {


    List<SysConfig> list(Page<SysConfig> page);

}
