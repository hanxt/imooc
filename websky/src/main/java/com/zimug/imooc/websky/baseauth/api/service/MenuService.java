package com.zimug.imooc.websky.baseauth.api.service;

import com.zimug.imooc.websky.common.mapper.SysRoleMenuMapper;
import com.zimug.imooc.websky.common.model.SysMenu;
import com.zimug.imooc.websky.common.model.SysMenuExample;
import com.zimug.imooc.websky.common.model.SysRoleMenuExample;
import com.zimug.imooc.websky.common.utils.AuthConstants;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import net.aexit.galaxy.earth.common.utils.BizCommonExceptionEnum;
import com.zimug.imooc.websky.common.mapper.SysMenuMapper;
import com.zimug.imooc.websky.common.model.SysRoleMenu;
import net.aexit.galaxy.earth.common.thirdmodule.ztree.BaseStaticTreeNode;
import net.aexit.galaxy.earth.common.thirdmodule.ztree.StaticTree;
import net.aexit.galaxy.earth.common.thirdmodule.ztree.TreeNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SunBo
 * on 2017/8/16.
 */
@Service
public class MenuService {

    private static final String LEFT_PIDS_WAPPER = "[";  //pids用左右大括号包起来,便于后续操作
    private static final String RIGHT_PIDS_WAPPER = "]";

    @Resource
    SysMenuMapper sysMenuMapper;

    @Resource
    SysRoleMenuMapper sysRoleMenuMapper;
    /**
     * 添加菜单
     * @param sysMenu 菜单实体信息
     * @throws BizCommonException
     */
    @Transactional
    public int add(SysMenu sysMenu)  throws BizCommonException {

        //父菜单id，菜单名称，菜单路径不能为空
        if(StringUtils.isEmpty(sysMenu.getMenuPid())
                || StringUtils.isEmpty(sysMenu.getName())
                || StringUtils.isEmpty(sysMenu.getUrl())){

            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }

        // 菜单名称是否已经存在
        if (this.isExistTheMenuName(sysMenu.getName(),null)) {
            throw new BizCommonException(BizCommonExceptionEnum.EXISTED_THE_MENUNAME);
        }
        sysMenu.setIsLeaf((byte)1);//新增的节点都是叶子节点
        sysMenu.setStatus((byte)0);//默认状态为启用
        setMenuPids(sysMenu);//设置菜单pids及菜单层级
        sysMenuMapper.insert(sysMenu);


        //父节点不再是叶子节点
        SysMenu parentSysMenu = new SysMenu();
        parentSysMenu.setId(sysMenu.getMenuPid());
        parentSysMenu.setIsLeaf((byte)0);
        sysMenuMapper.updateByPrimaryKeySelective(parentSysMenu);

        return sysMenu.getId();

    }

    /**
     * 修改菜单
     * @param sysMenu 菜单实体信息
     * @throws BizCommonException
     */
    public void edit(SysMenu sysMenu)  throws BizCommonException{

        //主键,菜单id，父菜单id，菜单名称，菜单路径不能为空
        if(StringUtils.isEmpty(sysMenu.getId())
                || StringUtils.isEmpty(sysMenu.getMenuPid())
                || StringUtils.isEmpty(sysMenu.getName())
                || StringUtils.isEmpty(sysMenu.getUrl())){

            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }

        // 菜单名称是否已经存在
        if (isExistTheMenuName(sysMenu.getName(),sysMenu.getId())) {
            throw new BizCommonException(BizCommonExceptionEnum.EXISTED_THE_MENUNAME);
        }

        setMenuPids(sysMenu);//设置菜单pids及菜单层级

        // 完善菜单信息
        sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
    }

    /**
     * 根据id删除菜单及子菜单
     * @param id   菜单id即表id
     * @throws BizCommonException
     */
    @Transactional
    public void delete(Integer id) throws BizCommonException{

        //id不能为空
        if(StringUtils.isEmpty(id)){
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }
        //根据id删除当前菜单
        sysMenuMapper.deleteByPrimaryKey(id);

        //删除当前菜单的子菜单
        SysMenuExample chidMenuExample = new SysMenuExample();
        chidMenuExample.createCriteria().andMenuPidsLike("%" + LEFT_PIDS_WAPPER + id + RIGHT_PIDS_WAPPER +"%");
        sysMenuMapper.deleteByExample(chidMenuExample);

    }

    /**
     * 根据id查看菜单信息
     * @param id  主键id
     * @throws BizCommonException
     */
    public SysMenu view(Integer id) throws BizCommonException{
        //menuId不能为空
        if(StringUtils.isEmpty(id)){
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }

        return sysMenuMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id查询菜单的子菜单
     * @param id   菜单id即表查询的Pid
     * @throws BizCommonException
     */
    @Transactional
    public List<SysMenu> getListById(Integer id) throws BizCommonException{

        // id不能为空
        if (StringUtils.isEmpty(id)) {
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }
        // 查询根当前菜单的子菜单
        SysMenuExample childMenuExample = new SysMenuExample();
        SysMenuExample.Criteria criteria = childMenuExample.createCriteria();
        criteria.andMenuPidEqualTo(id);
        return sysMenuMapper.selectByExample(childMenuExample);
    }




    /**
     *  判断菜单名称是否重复
     * @param menuName  菜单名称
     * @param id  菜单主键
     * @return boolean
     */
    private Boolean isExistTheMenuName(String menuName,Integer id){

        SysMenuExample sysMenuExample = new SysMenuExample();
        SysMenuExample.Criteria criteria = sysMenuExample.createCriteria();
        criteria.andNameEqualTo(menuName);
        if(id != null){//修改时排除自己
            criteria.andIdNotEqualTo(id);
        }
        List<SysMenu> sysMenuList = sysMenuMapper.selectByExample(sysMenuExample);
        if(sysMenuList == null || sysMenuList.size() == 0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 根据请求的父级菜单编号设置pids和层级
     * @param sysMenu 菜单实体
     */
    private void setMenuPids(SysMenu sysMenu){

        //设置所有父菜单id等值
        if(sysMenu.getMenuPid() == 0){//当前添加的菜单无父菜单
            sysMenu.setMenuPid(0);
            sysMenu.setMenuPids("[0]");
            sysMenu.setLevel((byte)1);
        }else {
            //查询父菜单对应的数据
            SysMenuExample sysMenuExample = new SysMenuExample();
            SysMenuExample.Criteria criteria = sysMenuExample.createCriteria();
            criteria.andIdEqualTo(sysMenu.getMenuPid());
            SysMenu pmenu = sysMenuMapper.selectByExample(sysMenuExample).get(0);

            if(pmenu.getMenuPids().equals("[0]")){//当前添加的菜单父菜单是一级菜单menu_pids为0
                sysMenu.setMenuPids(LEFT_PIDS_WAPPER + sysMenu.getMenuPid().toString() + RIGHT_PIDS_WAPPER);
            }else {
                sysMenu.setMenuPids(pmenu.getMenuPids() + ","
                        + LEFT_PIDS_WAPPER + sysMenu.getMenuPid().toString() + RIGHT_PIDS_WAPPER);
            }
            //如果编号和父编号一致会导致无限递归
            if (!StringUtils.isEmpty(sysMenu.getId()) && (sysMenu.getId().equals(sysMenu.getMenuPid()))) {
                throw new BizCommonException(BizCommonExceptionEnum.MENU_PCODE_COINCIDENCE);
            }
            sysMenu.setLevel((byte) (pmenu.getLevel() + 1));
        }
    }

    /**
     * 请求菜单ztree
     * @return ztree数据
     */
    public TreeNode getMenuNodeTree(){

        List<BaseStaticTreeNode> nodeList = new ArrayList<>();

        nodeList.add(new BaseStaticTreeNode(
                "0",
                "菜单根节点",
                null,
                AuthConstants.BASE));


        //ztree  子节点
        SysMenuExample sysMenuExample = new SysMenuExample();
        List<SysMenu> nodes = sysMenuMapper.selectByExample(sysMenuExample);

        if(nodes !=null && nodes.size() !=0){
            for(SysMenu node:nodes){
                BaseStaticTreeNode treeNode = new BaseStaticTreeNode(String.valueOf(node.getId()), node.getName(), String.valueOf(node.getMenuPid()), AuthConstants.NODES);
                nodeList.add(treeNode);
            }
        }
        String id = null;
        StaticTree tree = new StaticTree(nodeList.toArray(new BaseStaticTreeNode[0]),id);
        tree.render();
        return tree.getRootNode();
    }

    /**
     * 根据roleId获取menuids
     * @param roleId 角色id
     * @return menuis列表
     */
    public List<String> getMenuidsByRoleId(String roleId){
        SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
        sysRoleMenuExample.createCriteria().andRoleIdEqualTo(roleId);

        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectByExample(sysRoleMenuExample);
        List<String> menuURLs = new ArrayList<>();
        for(SysRoleMenu sysRoleMenu : sysRoleMenus){
            menuURLs.add(sysRoleMenu.getMenuId().toString());
        }

        return menuURLs;
    }
}
