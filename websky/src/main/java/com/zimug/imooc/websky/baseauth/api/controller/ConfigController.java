package com.zimug.imooc.websky.baseauth.api.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.zimug.imooc.websky.common.model.SysConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aexit.galaxy.earth.common.utils.AjaxCommonObject;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import com.zimug.imooc.websky.baseauth.api.service.ConfigService;
import com.zimug.imooc.websky.baseauth.api.mapper.SysConfigExtMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mss on 17-9-18.
 *
 */
@Api(value = "config", description = "系统配置", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController()
@RequestMapping("config")
public class ConfigController {


    @Resource
    SysConfigExtMapper sysConfigExtMapper;

    @Resource
    ConfigService configService;

    /**
     * 查询系统配置
     * @return 系统配置
     */
    @ApiOperation(value="查询系统配置", notes="查询系统配置." +
            "当pageNo和pageSize不为空时分页查询,否则查询所有")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<SysConfig> list(
                            @RequestParam(required = false) Integer pageNo,
                            @RequestParam(required = false) Integer pageSize) {

        Page<SysConfig> page = null;
        if(pageNo != null && pageSize != null){
            page = new Page<>(pageNo,pageSize);  //分页查询
        }else{
            page = new Page<>(1,Integer.MAX_VALUE);  //查询所有
        }

        return sysConfigExtMapper.list(page);
    }

    @ApiOperation(value="系统配置新增", notes="系统配置新增")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public AjaxCommonObject add(@RequestBody SysConfig sysConfig) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {

            ajaxCommonObject.setData(configService.add(sysConfig)); //返回新增记录的表id

        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }

    @ApiOperation(value="根据表id修改系统配置", notes="根据表id修改系统配置")
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public AjaxCommonObject edit(@RequestBody SysConfig sysConfig) {

        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            configService.edit(sysConfig);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    @ApiOperation(value="根据系统配置表主键id删除角色", notes="根据系统配置表主键id删除角色")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public AjaxCommonObject delete(@PathVariable Integer id) {

        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            configService.delete(id);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }

}
