package net.aexit.galaxy.websky.baseauth.api.service;

import net.aexit.galaxy.websky.baseauth.api.mapper.SysUserExtMapper;
import net.aexit.galaxy.websky.baseauth.api.model.SysUserRoleOrg;
import net.aexit.galaxy.websky.baseauth.api.model.UserStatus;
import net.aexit.galaxy.websky.common.mapper.SysUserMapper;
import net.aexit.galaxy.websky.common.mapper.SysUserRoleMapper;
import net.aexit.galaxy.websky.common.model.*;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import net.aexit.galaxy.earth.common.utils.BizCommonExceptionEnum;
import net.aexit.galaxy.websky.common.utils.ShiroKit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by hanxt on 17-8-14.
 */
@Service
public class UserService {

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    SysUserExtMapper sysUserExtMapper;

    @Resource
    SysUserRoleMapper sysUserRoleMapper;

    /**
     * 添加新用户
     * @param sysUser 用户实体信息
     * @throws BizCommonException
     */
    public int add(SysUser sysUser)  throws BizCommonException{

        // 判断账号是否重复
        SysUser theUser = this.getByUserId(sysUser.getUserId());
        if (theUser != null) {
            throw new BizCommonException(BizCommonExceptionEnum.USER_ALREADY_REG);
        }

        try{
            // 完善账号信息
            sysUser.setSalt(ShiroKit.getRandomSalt(5));
            sysUser.setPassword(ShiroKit.md5(sysUser.getPassword(), sysUser.getSalt()));
            sysUser.setStatus(UserStatus.OK.getCode());
            sysUser.setCreateDate(new Date());

            this.sysUserMapper.insert(sysUser);
        }catch (Exception e){
            throw new BizCommonException(BizCommonExceptionEnum.SAVE_DATA_ERROR);
        }

        return sysUser.getId();
    }

    /**
     * 修改已有用户
     * @param sysUser 用户实体信息
     * @throws BizCommonException
     */
    public void edit(SysUser sysUser)  throws BizCommonException{

        // 判断账号是否存在
        SysUser theUser = this.getByUserId(sysUser.getUserId());
        if (theUser == null) {
            throw new BizCommonException(BizCommonExceptionEnum.USER_NOT_EXISTED);
        }

        try{
            // 完善账号信息
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUserIdEqualTo(sysUser.getUserId());
            sysUserMapper.updateByExampleSelective(sysUser,sysUserExample);
        }catch (Exception e){
            throw new BizCommonException(BizCommonExceptionEnum.SAVE_DATA_ERROR);
        }

    }

    /**
     * 根据表id修改已有用户
     * @param sysUser 用户实体信息
     * @throws BizCommonException
     */
    public void editByTableId(SysUser sysUser)  throws BizCommonException{

        // sysUser实体中id不能为空
        if (StringUtils.isEmpty(sysUser.getId())) {
            throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
        }
        try{
            // 完善账号信息
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizCommonException(BizCommonExceptionEnum.SAVE_DATA_ERROR);
        }

    }



    /**
     * 删除已有用户
     * @param userId 用户登陆id
     * @throws BizCommonException
     */
    public void delete(String userId)  throws BizCommonException{

        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserIdEqualTo(userId);
        sysUserMapper.deleteByExample(sysUserExample);
    }

    /**
     * 修改用户密码
     * @param userId 用户登录id
     * @param oldPwd 旧的密码
     * @param newPwd 新密码
     * @param rePwd 再次输入的新密码
     * @throws BizCommonException
     */
    public void changePwd(String userId,
                          String oldPwd,
                          String newPwd,
                          String rePwd) throws BizCommonException{
        if (!newPwd.equals(rePwd)) {
            throw new BizCommonException(BizCommonExceptionEnum.TWO_PWD_NOT_MATCH);
        }

        SysUser sysUser = this.getByUserId(userId);
        String oldMd5 = ShiroKit.md5(oldPwd, sysUser.getSalt());
        if (sysUser.getPassword().equals(oldMd5)) {
            String newMd5 = ShiroKit.md5(newPwd, sysUser.getSalt());
            sysUser.setPassword(newMd5);

            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUserIdEqualTo(userId);
            sysUserMapper.updateByExampleSelective(sysUser,sysUserExample);
        } else {
            throw new BizCommonException(BizCommonExceptionEnum.OLD_PWD_NOT_RIGHT);
        }
    }


    /**
     * 为用户分配角色
     * @param userId 用户登陆id
     * @param roleIds 角色id逗号分隔字符串
     * @throws BizCommonException
     */
    @Transactional
    public void setRole(String userId,
                        String roleIds) throws BizCommonException{
        try{
            // 判断账号是否存在
            SysUser theUser = this.getByUserId(userId);
            if (theUser == null) {
                throw new BizCommonException(BizCommonExceptionEnum.USER_NOT_EXISTED);
            }

            //先删除该用户所有的角色
            SysUserRoleExample  sysUserRoleExample = new SysUserRoleExample();
            sysUserRoleExample.createCriteria().andUserIdEqualTo(userId);
            sysUserRoleMapper.deleteByExample(sysUserRoleExample);

            //该用户新增角色
            String[] roleIdArray = roleIds.split(",");
            SysUserRole sysUserRole = new SysUserRole();
            for(String roleId : roleIdArray){
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(userId);

                sysUserRoleMapper.insert(sysUserRole);
            }
        }catch (Exception e){
            throw new BizCommonException(BizCommonExceptionEnum.SERVER_ERROR);
        }

    }





    /**
     * 用户名密码是否匹配
     * @param userId  用户名
     * @param passWord 密码
     * @return 如果成功返回用户信息,不含密码和密码盐
     */
    public SysUser isUserValid(String userId,String passWord)  throws BizCommonException{

        //判断账号是否存在
        SysUser theUser = this.getByUserId(userId);
        if (theUser == null) {
            throw new BizCommonException(BizCommonExceptionEnum.USER_NOT_EXISTED);
        }

        SysUserExample sysUserExample  = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andPasswordEqualTo(ShiroKit.md5(passWord, theUser.getSalt()));
        int count = sysUserMapper.countByExample(sysUserExample);

        if (count != 1) {
            throw new BizCommonException(400,"请输入正确的用户名和密码!");
        }
        //返回用户信息,不含密码和密码盐
        theUser.setSalt("");
        theUser.setPassword("");

        return theUser;
    }


    /**
     * 根据userId获取唯一用户信息
     * @param userId  用户登录id
     * @return 用户实体
     */
    public SysUser getByUserId(String userId){
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserIdEqualTo(userId);

        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if(sysUsers == null || sysUsers.size() != 1){
            return null;
        }else {
            return sysUsers.get(0);
        }
    }


    /**
     * 根据表id获取唯一用户信息
     * @return 用户实体
     */
    public SysUserRoleOrg getByTableId(Integer id){
        return sysUserExtMapper.getSysUserRoleById(id);
    }
}
