package com.zimug.imooc.websky.baseauth.controller;

import com.zimug.imooc.websky.baseauth.api.service.MenuService;
import com.zimug.imooc.websky.common.model.SysMenu;
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
public class MenuManageController {

    @Resource
    MenuService menuService;


    //TAB展现菜单管理
    @RequestMapping(value = "/menu_manage", method = RequestMethod.GET)
    public String userManage() {
        return "baseauth/menu_manage";
    }


    //展现菜单新增界面
    @RequestMapping(value = "/menu/viewadd", method = RequestMethod.GET)
    public String menuManageAdd() {
        return "baseauth/menu_manage_add";
    }

    //展现菜单修改界面
    @RequestMapping(value = "/menu/viewedit/{id}", method = RequestMethod.GET)
    public String menuManageEdit(@PathVariable Integer id,Model model) {
        model.addAttribute("menu",menuService.view(id));
        SysMenu sysMenu = null;
        if(menuService.view(id).getMenuPid() != null && menuService.view(id).getMenuPid() == 0){
            sysMenu = menuService.view(id);
            sysMenu.setName("菜单根节点");
        }else{
            sysMenu = menuService.view(menuService.view(id).getMenuPid());
        }
        model.addAttribute("menuFatherName",sysMenu);
        return "baseauth/menu_manage_edit";
    }

    //展现父菜单界面
    @RequestMapping(value = "/menu/open_menu_ztree", method = RequestMethod.GET)
    public String openMenuZtree() {
        return "baseauth/menu_manage_menuztree";
    }


}
