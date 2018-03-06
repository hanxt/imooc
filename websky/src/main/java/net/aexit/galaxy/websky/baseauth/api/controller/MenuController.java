package net.aexit.galaxy.websky.baseauth.api.controller;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aexit.galaxy.earth.common.utils.AjaxCommonObject;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import net.aexit.galaxy.websky.baseauth.api.mapper.SysMenuExtMapper;
import net.aexit.galaxy.websky.baseauth.api.service.MenuService;
import net.aexit.galaxy.websky.common.model.SysMenu;
import net.aexit.galaxy.earth.common.thirdmodule.ztree.TreeNode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Api(value = "menu", description = "菜单管理", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController()
@RequestMapping("menu")
public class MenuController {

    @Resource
    MenuService menuService;

    @Resource
    SysMenuExtMapper sysMenuExtMapper;


    /**
     * 获取菜单平面列表,当没有参数时查询所有的菜单
     * @param menuName  菜单名称
     * @param level     菜单级别
     * @return   菜单列表
     */
    @ApiOperation(value="查询菜单列表", notes="查询菜单列表" +
            "当pageNo和pageSize不为空时分页查询,否则查询所有")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<SysMenu> list(@RequestParam(required = false) String roleId,
                              @RequestParam(required = false) String menuName,
                              @RequestParam(required = false) String level,
                              @RequestParam(required = false) Integer pageNo,
                              @RequestParam(required = false) Integer pageSize) {

        Page<SysMenu> page;
        if(pageNo != null && pageSize != null){
            page = new Page<>(pageNo,pageSize);  //分页查询
        }else{
            page = new Page<>(1,Integer.MAX_VALUE);  //查询所有
        }

        return sysMenuExtMapper.listMenu(roleId,menuName,level,page);
    }


    /**
     * 根据userId获取菜单平面列表,当没有参数时查询所有的菜单
     * @return   菜单列表
     */
    @ApiOperation(value="查询菜单列表", notes="查询菜单列表" +
            "当pageNo和pageSize不为空时分页查询,否则查询所有")
    @RequestMapping(value = "/listByUserId",method = RequestMethod.GET)
    public List<SysMenu> listByUserId(@RequestParam(required = false) String userId,
                                      @RequestParam(required = false) String level,
                                      @RequestParam(required = false) Integer pageNo,
                                      @RequestParam(required = false) Integer pageSize) {

        Page<SysMenu> page;
        if(pageNo != null && pageSize != null){
            page = new Page<>(pageNo,pageSize);  //分页查询
        }else{
            page = new Page<>(1,Integer.MAX_VALUE);  //查询所有
        }

        return sysMenuExtMapper.listMenuByUserId(userId,level,page);
    }


    @ApiOperation(value="添加菜单", notes="添加菜单,默认已经存在,有且仅有一个根节点id为0")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public AjaxCommonObject add(@RequestBody SysMenu sysMenu) {

        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {

            ajaxCommonObject.setData(menuService.add(sysMenu)); //返回新增记录的表id
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    @ApiOperation(value="根据id修改菜单", notes="根据id修改菜单,id为必须输入的参数")
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public AjaxCommonObject edit(@RequestBody SysMenu sysMenu) {

        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            menuService.edit(sysMenu);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }



    @ApiOperation(value="根据id删除菜单及子菜单", notes="根据id删除菜单及子菜单")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public AjaxCommonObject delete(@PathVariable Integer id) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            menuService.delete(id);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }



    @ApiOperation(value="根据id查看菜单", notes="根据id查看菜单")
    @RequestMapping(value = "/view/{id}",method = RequestMethod.GET)
    public AjaxCommonObject view(@PathVariable Integer id) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            ajaxCommonObject.setData(menuService.view(id));
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }

    @ApiOperation(value="根据id查看所有子菜单", notes="根据id查看所有子菜单")
    @RequestMapping(value = "/list/{id}",method = RequestMethod.GET)
    public AjaxCommonObject listById(@PathVariable Integer id) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            ajaxCommonObject.setData(menuService.getListById(id));
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }

    /**
     * 获取菜单的ztree列表
     */
    @ApiOperation(value="配合ZTree使用的树形数据", notes="配合ZTree使用的树形数据")
    @RequestMapping(value = "/ztree",method = RequestMethod.GET)
    public @ResponseBody
    TreeNode tree() {
        TreeNode treeNode = menuService.getMenuNodeTree();
        return treeNode;
    }
}
