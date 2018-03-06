package com.zimug.imooc.websky.baseauth.controller;

import com.zimug.imooc.websky.baseauth.api.service.OrgService;
import com.zimug.imooc.websky.common.model.SysOrg;
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
public class OrgManageController {

    @Resource
    OrgService orgService;


    //TAB展现组织管理
    @RequestMapping(value = "/org_manage", method = RequestMethod.GET)
    public String userManage() {
        return "baseauth/org_manage";
    }

    //展现组织新增界面
    @RequestMapping(value = "/org/add", method = RequestMethod.GET)
    public String orgManageAdd() {
        return "baseauth/org_manage_add";
    }

    //展现组织修改界面
    @RequestMapping(value = "/org/viewedit/{id}", method = RequestMethod.GET)
    public String orgManageEdit(@PathVariable Integer id,Model model) {
        // 根据前台的id获得本条数据
        model.addAttribute("org",orgService.view(id));
        SysOrg orgFather = null;
        if (orgService.view(id).getOrgPid() != null && orgService.view(id).getOrgPid() == 0) {
            orgFather = orgService.view(id);
        } else {
            orgFather = orgService.view(orgService.view(id).getOrgPid());
        }
        // 根据查询结果的父组织id获取父组织的信息
        model.addAttribute("orgFather",orgFather);
        return "baseauth/org_manage_edit";
    }
    //展现组织结构界面
    @RequestMapping(value = "/org/open_org_ztree", method = RequestMethod.GET)
    public String openOrgZtree() {
        return "baseauth/org_manage_orgztree";
    }

}
