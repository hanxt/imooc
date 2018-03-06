package net.aexit.galaxy.websky.baseauth.api.controller;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aexit.galaxy.websky.baseauth.api.mapper.SysRoleExtMapper;
import net.aexit.galaxy.websky.baseauth.api.service.RoleService;
import net.aexit.galaxy.websky.common.model.SysRole;
import net.aexit.galaxy.earth.common.utils.AjaxCommonObject;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import net.aexit.galaxy.earth.common.utils.BizCommonExceptionEnum;
import net.aexit.galaxy.earth.common.thirdmodule.ztree.TreeNode;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hanxt on 17-8-15.
 *
 */
@Api(value = "role", description = "角色管理", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController()
@RequestMapping("role")
public class RoleController {
    @Resource
    SysRoleExtMapper sysRoleExtMapper;

    @Resource
    RoleService roleService;

    @ApiOperation(value="查询角色列表", notes="查询角色列表" +
            "当pageNo和pageSize不为空时分页查询,否则查询所有")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<SysRole> list( @RequestParam(required = false) Integer pageNo,
                                @RequestParam(required = false) Integer pageSize,
                                @RequestParam(required = false) String roleId,
                                @RequestParam(required = false) String roleName) {

        Page<SysRole> page = null;
        if(pageNo != null && pageSize != null){
            page = new Page<>(pageNo,pageSize);  //分页查询
        }else{
            page = new Page<>(1,Integer.MAX_VALUE);  //查询所有
        }
        return sysRoleExtMapper.listRole(roleId,roleName,page);
    }

    @ApiOperation(value="角色新增", notes="角色新增")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public AjaxCommonObject add(@RequestBody SysRole sysRole) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {

            ajaxCommonObject.setData(roleService.add(sysRole)); //返回新增记录的表id

        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    @ApiOperation(value="根据表id修改已有角色", notes="根据表id修改已有角色")
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public AjaxCommonObject edit(@RequestBody SysRole sysRole) {

        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
           roleService.edit(sysRole);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    @ApiOperation(value="根据角色表主键id删除角色", notes="根据角色表主键id删除角色")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public AjaxCommonObject delete(@PathVariable Integer id) {

        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            roleService.delete(id);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    @ApiOperation(value="根据角色ID查看角色", notes="根据角色ID查看角色")
    @RequestMapping(value = "/view/{roleId}",method = RequestMethod.GET)
    public AjaxCommonObject view(@PathVariable String roleId) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            ajaxCommonObject.setData(roleService.getByRoleId(roleId));
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    /**
     * 配置权限
     */
    @ApiOperation(value = "为角色分配菜单权限", notes = "为角色分配菜单权限")
    @RequestMapping(value = "/set_menus",method = RequestMethod.POST)
    public AjaxCommonObject setMenus(@RequestParam("roleId") String roleId,
                                    @RequestParam("menuIds") String menuIds) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            if (StringUtils.isEmpty(roleId) || StringUtils.isEmpty(menuIds)) {
                throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
            }
            roleService.setMenus(roleId,menuIds);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }

    /**
     * 权限分配，回显角色权限
     * @param roleId
     * @return
     */
    @ApiOperation(value = "为角色分配菜单权限", notes = "为角色分配菜单权限")
    @RequestMapping(value = "/role_assign_menu",method = RequestMethod.POST)
    public @ResponseBody
    TreeNode roleAssignMenu(@RequestParam("roleId") String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }
        TreeNode treeNode = roleService.roleAssignMenu(roleId);
        return treeNode;
    }





    /**
     * 获取角色列表
     *//*
    @RequestMapping(value = "/roleTreeList")
    @ResponseBody
    public List<ZTreeNode> roleTreeList() {
        List<ZTreeNode> roleTreeList = this.roleDao.roleTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }

    *//**
     * 获取角色列表
     *//*
    @RequestMapping(value = "/roleTreeListByUserId/{userId}")
    @ResponseBody
    public List<ZTreeNode> roleTreeListByUserId(@PathVariable Integer userId) {
        User theUser = this.userMapper.selectById(userId);
        String roleid = theUser.getRoleid();
        if (ToolUtil.isEmpty(roleid)) {
            List<ZTreeNode> roleTreeList = this.roleDao.roleTreeList();
            return roleTreeList;
        } else {
            String[] strArray = Convert.toStrArray(",", roleid);
            List<ZTreeNode> roleTreeListByUserId = this.roleDao.roleTreeListByRoleId(strArray);
            return roleTreeListByUserId;
        }
    }*/

}
