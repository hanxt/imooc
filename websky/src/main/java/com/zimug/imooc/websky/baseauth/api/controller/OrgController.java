package com.zimug.imooc.websky.baseauth.api.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.zimug.imooc.websky.baseauth.api.service.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.zimug.imooc.websky.baseauth.api.mapper.SysOrgExtMapper;
import com.zimug.imooc.websky.common.model.SysOrg;
import net.aexit.galaxy.earth.common.utils.AjaxCommonObject;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import net.aexit.galaxy.earth.common.thirdmodule.ztree.TreeNode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hanxt on 17-8-17.
 *
 */
@Api(value = "org", description = "组织机构管理", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController()
@RequestMapping("org")
public class OrgController {

    @Resource
    OrgService orgService;

    @Resource
    SysOrgExtMapper sysOrgExtMapper;


    @ApiOperation(value="查询组织机构列表", notes="查询组织机构列表" +
            "当pageNo和pageSize不为空时分页查询,否则查询所有")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<SysOrg> list(@RequestParam(required = false) String orgName,
                             @RequestParam(required = false) String level,
                             @RequestParam(required = false) Integer pageNo,
                             @RequestParam(required = false) Integer pageSize) {

        Page<SysOrg> page;
        if(pageNo != null && pageSize != null){
            page = new Page<>(pageNo,pageSize);  //分页查询
        }else{
            page = new Page<>(1,Integer.MAX_VALUE);  //查询所有
        }

        return sysOrgExtMapper.listOrg(orgName,level,page);
    }


    @ApiOperation(value="添加组织机构", notes="添加组织机构,默认已经存在,有且仅有一个根节点id为0")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public AjaxCommonObject add(@RequestBody SysOrg sysOrg) {

        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {

            ajaxCommonObject.setData(orgService.add(sysOrg)); //返回新增记录的表id

        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    @ApiOperation(value="根据id修改组织机构", notes="根据id修改组织机构,id为必须输入的参数")
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public AjaxCommonObject edit(@RequestBody SysOrg sysOrg) {

        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            orgService.edit(sysOrg);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    @ApiOperation(value="根据id删除组织机构及子机构", notes="根据id删除组织机构及子机构")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public AjaxCommonObject delete(@PathVariable Integer id) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            orgService.delete(id);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    @ApiOperation(value="根据id查看组织机构", notes="根据id查看组织机构")
    @RequestMapping(value = "/view/{id}",method = RequestMethod.GET)
    public AjaxCommonObject view(@PathVariable Integer id) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            ajaxCommonObject.setData(orgService.view(id));
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }

    @ApiOperation(value="根据id查看所有子机构", notes="根据id查看所有子机构")
    @RequestMapping(value = "/list/{id}",method = RequestMethod.GET)
    public AjaxCommonObject listById(@PathVariable Integer id) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            ajaxCommonObject.setData(orgService.getListById(id));
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }

    /**
     * 获取部门的ztree列表
     */
    @ApiOperation(value="配合ZTree使用的树形数据", notes="配合ZTree使用的树形数据")
    @RequestMapping(value = "/ztree",method = RequestMethod.GET)
    public @ResponseBody TreeNode tree() {
        TreeNode treeNode = orgService.getOrgNodeTree();
        return treeNode;
    }
}
