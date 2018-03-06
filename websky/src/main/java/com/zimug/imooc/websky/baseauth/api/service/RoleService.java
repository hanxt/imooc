package com.zimug.imooc.websky.baseauth.api.service;

import com.zimug.imooc.websky.common.mapper.SysMenuMapper;
import com.zimug.imooc.websky.common.mapper.SysRoleMapper;
import com.zimug.imooc.websky.common.mapper.SysRoleMenuMapper;
import com.zimug.imooc.websky.common.mapper.SysUserRoleMapper;
import com.zimug.imooc.websky.common.model.*;
import com.zimug.imooc.websky.common.utils.AuthConstants;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import net.aexit.galaxy.earth.common.utils.BizCommonExceptionEnum;
import net.aexit.galaxy.earth.common.thirdmodule.ztree.BaseStaticTreeNode;
import net.aexit.galaxy.earth.common.thirdmodule.ztree.StaticTree;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanxt on 17-8-15.
 */
@Service
public class RoleService {
    @Resource
    SysRoleMapper sysRoleMapper;

    @Resource
    SysRoleMenuMapper sysRoleMenuMapper;

    @Resource
    SysMenuMapper sysMenuMapper;

    @Resource
    SysUserRoleMapper sysUserRoleMapper;

    /**
     * 添加新角色
     * @param sysRole 角色实体信息
     * @throws BizCommonException
     */
    public int add(SysRole sysRole)  throws BizCommonException{

        // 判断角色id是否重复
        SysRole theRole = this.getByRoleId(sysRole.getRoleId());
        if (theRole != null) {
            throw new BizCommonException(BizCommonExceptionEnum.EXISTED_THE_RECORD);
        }

        //TODO 角色排序的问题,暂时由手工处理
        this.sysRoleMapper.insert(sysRole);

        return sysRole.getId();
    }


    /**
     * 根据roleId获取唯一角色信息
     * @param roleId  角色id
     * @return 角色实体
     */
    public SysRole getByRoleId(String roleId){
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andRoleIdEqualTo(roleId);

        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        if(sysRoles == null || sysRoles.size() != 1){
            return null;
        }else {
            return sysRoles.get(0);
        }
    }

    /**
     * 根据表id修改已有角色
     * @param sysRole 角色实体信息
     * @throws BizCommonException
     */
    public void edit(SysRole sysRole)  throws BizCommonException{

        // sysRole实体中id,roleId不能为空
        if (StringUtils.isEmpty(sysRole.getId()) || StringUtils.isEmpty(sysRole.getRoleId())) {
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }
        // 修改时判断角色ID是否已存在
        SysRoleExample sysRoleExample = new SysRoleExample();
        SysRoleExample.Criteria criteria = sysRoleExample.createCriteria();
        criteria.andRoleIdEqualTo(sysRole.getRoleId());
        criteria.andIdNotEqualTo(sysRole.getId());//排除修改的这条数据
        List<SysRole> theRoles = sysRoleMapper.selectByExample(sysRoleExample);
        if (theRoles.size() > 0) {
            throw new BizCommonException(BizCommonExceptionEnum.EXISTED_THE_RECORD);
        }
        //不能修改管理员角色信息
        if(sysRole.getRoleId().equals(AuthConstants.ADMIN)){
            throw new BizCommonException(BizCommonExceptionEnum.CANT_CHANGE_ADMIN);
        }

        // 完善角色信息
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }


    /**
     * 删除已有角色
     * @param id 角色表主键id
     * @throws BizCommonException
     */
    public void delete(Integer id)  throws BizCommonException{
        //主键id不能为空
        if(StringUtils.isEmpty(id)){
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        //不能删除管理员角色
        if(sysRole.getRoleId().equals(AuthConstants.ADMIN)){
            throw new BizCommonException(BizCommonExceptionEnum.CANT_DELETE_ADMIN);
        }
        //删除角色
        sysRoleMapper.deleteByPrimaryKey(id);
    }


    /**
     * 为已有角色分配菜单权限
     * @param roleId 角色id
     * @param menuIds 菜单id,逗号分隔字符串
     * @throws BizCommonException
     */
    @Transactional
    public void setMenus(String roleId,
                        String menuIds) throws BizCommonException{
        try{
            // 判断角色是否存在
            SysRole theRole = this.getByRoleId(roleId);
            if (theRole == null) {
                throw new BizCommonException(BizCommonExceptionEnum.ROLE_NOT_EXISTED);
            }

            //先删除该角色的所有菜单权限
            SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
            sysRoleMenuExample.createCriteria().andRoleIdEqualTo(roleId);
            sysRoleMenuMapper.deleteByExample(sysRoleMenuExample);

            //新增菜单权限
            String[] menuIdArray = menuIds.split(",");
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            for(String menuId : menuIdArray){
                sysRoleMenu.setRoleId(roleId);
                sysRoleMenu.setMenuId(Integer.parseInt(menuId));

                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }catch (Exception e){
            throw new BizCommonException(BizCommonExceptionEnum.SERVER_ERROR);
        }

    }

    /**
     * 分配权限已有权限进行回显
     * @param roleId 角色id
     * @throws BizCommonException
     */
    @Transactional
    public BaseStaticTreeNode roleAssignMenu(String roleId) throws BizCommonException{
        try{
            // 判断角色是否存在
            SysRole theRole = this.getByRoleId(roleId);
            if (theRole == null) {
                throw new BizCommonException(BizCommonExceptionEnum.ROLE_NOT_EXISTED);
            }

            //查询所有权限
            List<BaseStaticTreeNode> nodeList = new ArrayList<>();
            //查询对应roleId角色勾选的权限
            SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
            SysRoleMenuExample.Criteria criteria = sysRoleMenuExample.createCriteria();
            criteria.andRoleIdEqualTo(roleId);
            List<SysRoleMenu> rolemenuList = sysRoleMenuMapper.selectByExample(sysRoleMenuExample);


            BaseStaticTreeNode treeNode = new BaseStaticTreeNode(
                    "0",
                    "菜单根节点",
                    null,
                    AuthConstants.BASE);
            //菜单根节点
            if(rolemenuList.size() > 0){
                treeNode.setChecked(true);
            }else{
                treeNode.setChecked(false);
            }
            nodeList.add(treeNode);


            //ztree  子节点
            SysMenuExample sysMenuExample = new SysMenuExample();
            List<SysMenu> nodes = sysMenuMapper.selectByExample(sysMenuExample);
            if(nodes !=null && nodes.size() !=0){
                for(SysMenu node:nodes){
                    treeNode = new BaseStaticTreeNode(
                            String.valueOf(node.getId()),
                            node.getName(),
                            String.valueOf(node.getMenuPid()),
                            AuthConstants.NODES);
                    if(isMenuCheck(rolemenuList,String.valueOf(node.getId()))){
                        treeNode.setChecked(true);
                    }else {
                        treeNode.setChecked(false);
                    }
                    nodeList.add(treeNode);
                }
            }

            String id = null;
            StaticTree tree = new StaticTree(nodeList.toArray(new BaseStaticTreeNode[0]),id);
            tree.render();
            return tree.getRootNode();


        }catch (Exception e){
            throw new BizCommonException(BizCommonExceptionEnum.SERVER_ERROR);
        }

    }

    /**
     * 判断菜单是否被勾选
     * @param checkedMenus  所有菜单角色对应关系
     * @param menuId   菜单id
     * @return  是否被勾选
     */
    private boolean isMenuCheck(List<SysRoleMenu> checkedMenus,String menuId){
        for (SysRoleMenu itorMenu :  checkedMenus ){
            if(itorMenu.getMenuId().toString().equals(menuId)){
                return true;
            }
        }
        return false;
    }

    /**
     * 根据用户id获取角色id列表
     * @param userId
     * @return
     */
    public List<String> getRoleIdsByUserId(String userId){
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUserIdEqualTo(userId);

        List<SysUserRole> userRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
        List<String> roles = new ArrayList<>();
        for(SysUserRole sysUserRole : userRoles){
            roles.add(sysUserRole.getRoleId());
        }
        return roles;
    }

}
