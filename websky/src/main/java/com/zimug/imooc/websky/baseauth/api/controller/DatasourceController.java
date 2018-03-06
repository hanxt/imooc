package com.zimug.imooc.websky.baseauth.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aexit.galaxy.earth.common.utils.AjaxCommonObject;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import com.zimug.imooc.websky.baseauth.service.DatasourceService;
import com.zimug.imooc.websky.common.mapper.SysDbMapper;
import com.zimug.imooc.websky.common.model.SysDb;
import com.zimug.imooc.websky.common.model.SysDbExample;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */
@Api(value = "datasource", description = "数据源配置", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping("datasource")
public class DatasourceController {

    @Resource
    DatasourceService bizService;
    @Resource
    SysDbMapper sysDbMapper;

    /**
     * 获取数据源列表
     */
    @ApiOperation(value="查询数据源", notes="查询系统配置.当pageNo和pageSize不为空时分页查询,否则查询所有")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<SysDb> list(@RequestParam(required = false) String dbName,
                            @RequestParam(required = false) String dbType,
                            @RequestParam(required = false) Integer pageNo,
                            @RequestParam(required = false) Integer pageSize) {


        if(pageNo == null || pageSize == null){
            pageNo = 1;
            pageSize = Integer.MAX_VALUE;
        }

        SysDbExample sysDbExample = new SysDbExample();
        sysDbExample.setOffset((pageNo - 1) * pageSize + 1);
        sysDbExample.setLimit(pageSize);
        SysDbExample.Criteria criteria = sysDbExample.createCriteria();
        if(!StringUtils.isEmpty(dbName)){
            criteria .andDbNameLike(dbName);
        }
        if(!StringUtils.isEmpty(dbType)){
            criteria.andDbTypeLike(dbType);
        }
        return sysDbMapper.selectByExample(sysDbExample);
    }


    /**
     * 新增数据源数据记录
     */
    @ApiOperation(value="数据源新增", notes="数据源新增")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public AjaxCommonObject add(@RequestBody SysDb dto) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {

            bizService.add(dto); //返回新增记录的表id

        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    /**
     * 删除数据源数据记录
     */
    @ApiOperation(value="数据源删除", notes="数据源删除")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxCommonObject delete(@PathVariable Integer id) {

        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            bizService.delete(id);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    /**
     * 修改数据源数据记录
     */
    @ApiOperation(value="数据源修改", notes="数据源修改")
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public AjaxCommonObject update(@RequestBody SysDb dto) {

        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            bizService.update(dto);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }

    /**
     * 数据源详情
     */
    @ApiOperation(value="数据源详情", notes="数据源详情")
    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    @ResponseBody
    public AjaxCommonObject detail(@PathVariable Integer id) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            ajaxCommonObject.setData(bizService.detail(id));
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }
}
