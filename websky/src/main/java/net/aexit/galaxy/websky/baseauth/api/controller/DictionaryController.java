package net.aexit.galaxy.websky.baseauth.api.controller;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aexit.galaxy.earth.common.utils.AjaxCommonObject;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import net.aexit.galaxy.websky.baseauth.api.service.DictionaryService;
import net.aexit.galaxy.websky.baseauth.api.mapper.SysDictionaryExtMapper;
import net.aexit.galaxy.websky.common.model.SysDictionary;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xinglili on 17-9-15.
 *
 */
@Api(value = "dictionary", description = "字典管理", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController()
@RequestMapping("dictionary")
public class DictionaryController {
    @Resource
    SysDictionaryExtMapper sysDictionaryExtMapper;

    @Resource
    DictionaryService dictionaryService;

    @ApiOperation(value="查询字典列表", notes="查询字典列表" +
            "当pageNo和pageSize不为空时分页查询,否则查询所有")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<SysDictionary> list( @RequestParam(required = false) Integer pageNo,
                                     @RequestParam(required = false) Integer pageSize,
                                     @RequestParam(required = false) String dicClass,
                                     @RequestParam(required = false) String dicValue) {

        Page<SysDictionary> page = null;
        if(pageNo != null && pageSize != null){
            page = new Page<>(pageNo,pageSize);       // 分页查询
        }else{
            page = new Page<>(1,Integer.MAX_VALUE);  // 查询所有
        }
        return sysDictionaryExtMapper.listDictionary(dicClass,dicValue,page);

    }

    @ApiOperation(value="字典新增", notes="字典新增")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public AjaxCommonObject add(@RequestBody SysDictionary sysDictionary) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {

            ajaxCommonObject.setData(dictionaryService.add(sysDictionary)); // 返回新增字典的表id

        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }

    @ApiOperation(value="根据表id修改已有字典", notes="根据表id修改已有字典")
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public AjaxCommonObject edit(@RequestBody SysDictionary sysDictionary) {

        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            dictionaryService.edit(sysDictionary);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    @ApiOperation(value="根据角色表主键id删除字典", notes="根据角色表主键id删除字典")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public AjaxCommonObject delete(@PathVariable Integer id) {

        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            dictionaryService.delete(id);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    @ApiOperation(value="根据字典ID查看字典", notes="根据字典ID查看字典")
    @RequestMapping(value = "/view/{id}",method = RequestMethod.GET)
    public AjaxCommonObject view(@PathVariable Integer id) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            ajaxCommonObject.setData(dictionaryService.getSysDictionaryById(id));
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


}
