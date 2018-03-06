package com.zimug.imooc.websky.baseauth.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.zimug.imooc.websky.baseauth.api.mapper.SysRoleExtMapper;
import com.zimug.imooc.websky.baseauth.api.service.UserService;
import com.zimug.imooc.websky.baseauth.api.model.SysUserRoleOrg;
import com.zimug.imooc.websky.common.model.SysRole;
import net.aexit.galaxy.earth.common.thirdmodule.picklist.PickListData;
import net.aexit.galaxy.earth.common.thirdmodule.picklist.PickNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hanxt on 17-8-24.
 *
 */
@Controller
@ApiIgnore
public class UserManageController {

    @Resource
    UserService userService;

    @Resource
    SysRoleExtMapper sysRoleExtMapper;


    //TAB展现用户管理
    @RequestMapping(value = "/user_manage", method = RequestMethod.GET)
    public String userManage() {
        return "baseauth/user_manage";
    }


    //展现用户新增界面
    @RequestMapping(value = "/user/viewadd", method = RequestMethod.GET)
    public String userManageAdd() {
        return "baseauth/user_manage_add";
    }


    //展现用户修改界面
    @RequestMapping(value = "/user/viewedit/{tableId}", method = RequestMethod.GET)
    public String userManageEdit(@PathVariable Integer tableId,Model model) {

        model.addAttribute("user",userService.getByTableId(tableId));
        return "baseauth/user_manage_edit";
    }


    //展现角色分配界面
    @RequestMapping(value = "/user/role_assign/{tableId}", method = RequestMethod.GET)
    public String userRoleAssign(@PathVariable Integer tableId,Model model) {
        //查询该用户所有的角色
        SysUserRoleOrg userRoleOrg = userService.getByTableId(tableId);
        String[] myroles = null;
        if(!StringUtils.isEmpty(userRoleOrg.getRoleIds())){
            myroles = userRoleOrg.getRoleIds().split(",");
        }

        //查询系统所有的角色
        List<SysRole> roles = sysRoleExtMapper.listRole(null,null,new Page<>());

        //PickList的JSON数据结构
        PickListData pickListData = new PickListData();
        pickListData.setSelectedIds(myroles);
        PickNode pickNode = null;
        for(SysRole sysRole:roles){
            pickNode = new PickNode();
            pickNode.setId(sysRole.getRoleId());
            pickNode.setText(sysRole.getRoleName());
            pickListData.addNode(pickNode);
        }

        model.addAttribute("pickListData",pickListData.getJSON());
        model.addAttribute("user",userRoleOrg);
        return "baseauth/user_manage_role";
    }

    //展现组织结构界面
    @RequestMapping(value = "/user/open_org_ztree", method = RequestMethod.GET)
    public String openOrgZtree() {
        return "baseauth/user_manage_orgztree";
    }

}
