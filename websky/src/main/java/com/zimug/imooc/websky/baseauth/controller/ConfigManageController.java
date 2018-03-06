package com.zimug.imooc.websky.baseauth.controller;

import com.zimug.imooc.websky.baseauth.api.service.ConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * Created by mss on 17-9-15.
 *
 */
@Controller
@ApiIgnore
public class ConfigManageController {

    @Resource
    ConfigService configService;


    //系统配置菜单管理
    @RequestMapping(value = "/config_manage", method = RequestMethod.GET)
    public String configManage() {
        return "baseauth/config_manage";
    }


    //系统配置新增界面
    @RequestMapping(value = "/config/viewadd", method = RequestMethod.GET)
    public String configManageAdd() {
        return "baseauth/config_manage_add";
    }



    //系统配置修改界面
   @RequestMapping(value = "/config/viewedit/{id}", method = RequestMethod.GET)
    public String configManageEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("config",configService.view(id));
        return "baseauth/config_manage_edit";
    }




}
