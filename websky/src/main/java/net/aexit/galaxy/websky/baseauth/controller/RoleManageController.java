package net.aexit.galaxy.websky.baseauth.controller;

import net.aexit.galaxy.websky.baseauth.api.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * Created by hanxt on 17-8-24.
 *
 */
@Controller
@ApiIgnore
public class RoleManageController {

    @Resource
    RoleService roleService;


    //TAB展现角色管理
    @RequestMapping(value = "/role_manage", method = RequestMethod.GET)
    public String roleManage() {
        return "baseauth/role_manage";
    }


    //展现角色新增界面
    @RequestMapping(value = "/role/viewadd", method = RequestMethod.GET)
    public String rolerManageAdd() {
        return "baseauth/role_manage_add";
    }


    //展现角色修改界面
    @RequestMapping(value = "/role/viewedit/{roleId}", method = RequestMethod.GET)
    public String roleManageEdit(@PathVariable String roleId,Model model) {

        model.addAttribute("role",roleService.getByRoleId(roleId));
        return "baseauth/role_manage_edit";
    }

    //展现分配权限菜单界面
    @RequestMapping(value = "/role/open_menu_ztree/{roleId}", method = RequestMethod.GET)
    public String openMenuZtree(@PathVariable String roleId, Model model) {
        model.addAttribute("assignRoleId",roleId);
        return "baseauth/role_manage_menuztree";
    }

}
