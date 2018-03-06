package com.zimug.imooc.websky.baseauth.controller;

import com.zimug.imooc.websky.baseauth.service.DatasourceService;
import com.zimug.imooc.websky.common.model.SysDb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * 数据源控制器
 *
 * @author hanxt
 * @Date 
 */
@ApiIgnore
@Controller
@RequestMapping("/datasource")
public class DatasourceManageController {

    @Resource
    DatasourceService bizService;

    private String PREFIX = "baseauth/datasource/";

    /**
     * 跳转到数据源首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "datasource";
    }

    /**
     * 跳转到添加数据源
     */
    @RequestMapping("/datasource_add")
    public String datasourceAdd() {
        return PREFIX + "datasource_add";
    }

    /**
     * 跳转到修改数据源
     */
    @RequestMapping("/datasource_update/{id}")
    public String datasourceUpdate(@PathVariable Integer id, Model model) {
        model.addAttribute("id",id);
        SysDb dto = bizService.detail(id);
        model.addAttribute("sysDb",dto);
        return PREFIX + "datasource_edit";
    }

}
