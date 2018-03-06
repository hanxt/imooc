package net.aexit.galaxy.websky.baseauth.api.service;

import net.aexit.galaxy.websky.baseauth.api.mapper.SysOrgExtMapper;
import net.aexit.galaxy.websky.common.mapper.SysOrgMapper;
import net.aexit.galaxy.websky.common.model.SysOrg;
import net.aexit.galaxy.websky.common.model.SysOrgExample;
import net.aexit.galaxy.websky.common.utils.AuthConstants;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import net.aexit.galaxy.earth.common.utils.BizCommonExceptionEnum;
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
 * Created by hanxt on 17-8-21.
 *
 */
@Service
public class OrgService {

    private static final String LEFT_PIDS_WAPPER = "[";  //pids用左右大括号包起来,便于后续操作
    private static final String RIGHT_PIDS_WAPPER = "]";

    @Resource
    SysOrgMapper sysOrgMapper;
    @Resource
    SysOrgExtMapper sysOrgExtMapper;

    /**
     * 添加菜单
     * @param sysOrg 菜单实体信息
     * @throws BizCommonException
     */
    @Transactional
    public int add(SysOrg sysOrg)  throws BizCommonException {

        //父组织id，组织名称不能为空
        if ( StringUtils.isEmpty(sysOrg.getOrgPid())
                || StringUtils.isEmpty(sysOrg.getOrgName()) ) {
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }

        // 组织机构名称是否已经存在
        if (this.isExistTheOrgName(sysOrg.getOrgName(),null)) {
            throw new BizCommonException(BizCommonExceptionEnum.EXISTED_THE_ORGNAME);
        }
        sysOrg.setIsLeaf((byte)1);//新增的节点都是叶子节点
        sysOrg.setStatus((byte)0);//默认状态为启用
        setOrgPids(sysOrg);//设置组织机构pids及组织层级
        sysOrgMapper.insert(sysOrg);

        //父节点不再是叶子节点
        SysOrg parentSysOrg = new SysOrg();
        parentSysOrg.setId(sysOrg.getOrgPid());
        parentSysOrg.setIsLeaf((byte)0);
        sysOrgMapper.updateByPrimaryKeySelective(parentSysOrg);

        return sysOrg.getId();

    }

    /**
     * 修改组织机构
     * @param sysOrg 组织机构实体信息
     * @throws BizCommonException
     */
    public void edit(SysOrg sysOrg)  throws BizCommonException{

        //主键,组织id，父组织id，组织名称不能为空
        if ( StringUtils.isEmpty(sysOrg.getId())
                ||StringUtils.isEmpty(sysOrg.getOrgPid())
                || StringUtils.isEmpty(sysOrg.getOrgName()) ) {
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }

        // 组织机构名称是否已经存在
        if (this.isExistTheOrgName(sysOrg.getOrgName(),sysOrg.getId())) {
            throw new BizCommonException(BizCommonExceptionEnum.EXISTED_THE_ORGNAME);
        }

        setOrgPids(sysOrg);//设置组织机构pids及组织层级

        // 完善菜单信息
        sysOrgMapper.updateByPrimaryKeySelective(sysOrg);
    }

    /**
     * 根据id删除组织机构及子机构
     * @param id   组织机构id即表id
     * @throws BizCommonException
     */
    @Transactional
    public void delete(Integer id) throws BizCommonException{

        //id不能为空
        if (StringUtils.isEmpty(id)) {
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }
        //根据id删除当前组织机构
        sysOrgMapper.deleteByPrimaryKey(id);

        //删除当前组织机构的子机构
        SysOrgExample childOrgExample = new SysOrgExample();
        childOrgExample.createCriteria().andOrgPidsLike("%" + LEFT_PIDS_WAPPER + id + RIGHT_PIDS_WAPPER +"%");
        sysOrgMapper.deleteByExample(childOrgExample);

    }

    /**
     * 根据id查询组织机构的子机构
     * @param id   组织机构id即表查询的Pid
     * @throws BizCommonException
     */
    @Transactional
    public List<SysOrg> getListById(Integer id) throws BizCommonException{

        // id不能为空
        if (StringUtils.isEmpty(id)) {
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }
        // 查询当前组织机构的子机构
        SysOrgExample childOrgExample = new SysOrgExample();
        SysOrgExample.Criteria criteria = childOrgExample.createCriteria();
        criteria.andOrgPidEqualTo(id);
        return sysOrgMapper.selectByExample(childOrgExample);
    }

    /**
     * 根据id查看组织机构信息
     * @param id  主键id
     * @throws BizCommonException
     */
    public SysOrg view(Integer id) throws BizCommonException {
        // OrgId不能为空
        if (StringUtils.isEmpty(id)) {
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }

        return sysOrgMapper.selectByPrimaryKey(id);
    }

    /**
     *  判断组织机构名称是否重复
     * @param orgName  组织机构名称
     * @param id  组织机构主键
     * @return boolean
     */
    private Boolean isExistTheOrgName(String orgName,Integer id){

        SysOrgExample sysOrgExample = new SysOrgExample();
        SysOrgExample.Criteria criteria = sysOrgExample.createCriteria();
        criteria.andOrgNameEqualTo(orgName);
        if(id != null){//修改时排除自己
            criteria.andIdNotEqualTo(id);
        }
        List<SysOrg> sysOrgList = sysOrgMapper.selectByExample(sysOrgExample);
        if(sysOrgList == null || sysOrgList.size() == 0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 根据请求的父级组织机构编号设置pids和层级
     * @param sysOrg 组织机构实体
     */
    private void setOrgPids(SysOrg sysOrg){

        //设置所有父组织机构id等值
        if(sysOrg.getOrgPid() == 0){//当前添加的菜单无父菜单
            sysOrg.setOrgPid(0);
            sysOrg.setOrgPids("[0]");
            sysOrg.setLevel((byte)1);
        }else {
            //查询父组织机构对应的数据
            SysOrgExample sysOrgExample = new SysOrgExample();
            SysOrgExample.Criteria criteria = sysOrgExample.createCriteria();
            criteria.andIdEqualTo(sysOrg.getOrgPid());
            SysOrg porg = sysOrgMapper.selectByExample(sysOrgExample).get(0);

            if(porg.getOrgPids().equals("[0]")){//当前添加的菜单父菜单是一级菜单menu_pids为0
                sysOrg.setOrgPids(LEFT_PIDS_WAPPER + sysOrg.getOrgPid().toString() + RIGHT_PIDS_WAPPER);
            }else {
                sysOrg.setOrgPids(porg.getOrgPids() + ","
                        + LEFT_PIDS_WAPPER + sysOrg.getOrgPid().toString() + RIGHT_PIDS_WAPPER);
            }
            //如果编号和父编号一致会导致无限递归
            if (!StringUtils.isEmpty(sysOrg.getId()) && (sysOrg.getId().equals(sysOrg.getOrgPid()))) {
                throw new BizCommonException(BizCommonExceptionEnum.MENU_PCODE_COINCIDENCE);
            }
            sysOrg.setLevel((byte) (porg.getLevel() + 1));
        }
    }

    /**
     * 请求组织机构ztree
     * @return ztree数据
     */
    public TreeNode getOrgNodeTree(){

        List<BaseStaticTreeNode> nodeList = new ArrayList<>();
        //ztree根节点
        SysOrgExample orgExample = new SysOrgExample();
        SysOrgExample.Criteria org = orgExample.createCriteria();
        org.andOrgPidEqualTo(0);
        List<SysOrg> sysOrg = sysOrgMapper.selectByExample(orgExample);
        //创建根节点
        nodeList.add(new BaseStaticTreeNode(
                String.valueOf(sysOrg.get(0).getId()),
                sysOrg.get(0).getOrgName(),
                null,
                AuthConstants.BASE));
        //ztree  子节点
        SysOrgExample sysOrgExample = new SysOrgExample();
        SysOrgExample.Criteria criteria = sysOrgExample.createCriteria();
        criteria.andOrgPidNotEqualTo(0);
        List<SysOrg> nodes = sysOrgMapper.selectByExample(sysOrgExample);

        if(nodes !=null && nodes.size() !=0){
            for(SysOrg node:nodes){
                BaseStaticTreeNode treeNode = new BaseStaticTreeNode(String.valueOf(node.getId()), node.getOrgName(), String.valueOf(node.getOrgPid()), AuthConstants.NODES);
                nodeList.add(treeNode);
            }
        }
        String id = null;
        StaticTree tree = new StaticTree(nodeList.toArray(new BaseStaticTreeNode[0]),id);
        tree.render();
        return tree.getRootNode();
    }

}
