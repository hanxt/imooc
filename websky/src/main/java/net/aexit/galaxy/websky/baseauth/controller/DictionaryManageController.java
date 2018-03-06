package net.aexit.galaxy.websky.baseauth.controller;

import net.aexit.galaxy.websky.baseauth.api.service.DictionaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * Created by xinglili on 17-8-24.
 *
 */
@Controller
@ApiIgnore
public class DictionaryManageController {

    @Resource
    DictionaryService dictionaryService;


    //TAB展现字典管理
    @RequestMapping(value = "/dictionary_manage", method = RequestMethod.GET)
    public String dictionaryManage() {
        return "baseauth/dictionary_manage";
    }


    //展现字典新增界面
    @RequestMapping(value = "/dictionary/viewadd", method = RequestMethod.GET)
    public String dictionaryManageAdd() {
        return "baseauth/dictionary_manage_add";
    }


    //展现字典修改界面
    @RequestMapping(value = "/dictionary/viewedit/{id}", method = RequestMethod.GET)
    public String dictionaryManageEdit(@PathVariable Integer id,Model model) {

        model.addAttribute("dictionary",dictionaryService.getSysDictionaryById(id));
        return "baseauth/dictionary_manage_edit";
    }

}
