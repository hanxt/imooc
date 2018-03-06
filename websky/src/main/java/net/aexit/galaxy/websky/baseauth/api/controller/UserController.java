package net.aexit.galaxy.websky.baseauth.api.controller;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.aexit.galaxy.earth.common.utils.AjaxCommonObject;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import net.aexit.galaxy.earth.common.utils.BizCommonExceptionEnum;
import net.aexit.galaxy.websky.baseauth.api.mapper.SysUserExtMapper;
import net.aexit.galaxy.websky.baseauth.api.model.SysUserRoleOrg;
import net.aexit.galaxy.websky.baseauth.api.model.UserStatus;
import net.aexit.galaxy.websky.baseauth.api.service.UserService;
import net.aexit.galaxy.websky.common.mapper.SysUserMapper;
import net.aexit.galaxy.websky.common.model.SysUser;
import net.aexit.galaxy.websky.common.model.SysUserExample;
import net.aexit.galaxy.websky.common.utils.*;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hanxt on 17-8-10.
 *
 */
@Api(value = "user", description = "用户管理", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController()
@RequestMapping("user")
public class UserController {

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    SysUserExtMapper sysUserExtMapper;

    @Resource
    UserService userService;

    /**
     * 查询用户列表
     * @param userId 用户登录id
     * @param userName 用户姓名
     * @param orgId 用户组织id
     * @param phone 电话号
     * @param sex 性别
     * @param email  电话号码
     * @return 用户列表
     */
    @ApiOperation(value="查询用户列表", notes="查询用户列表." +
            "当pageNo和pageSize不为空时分页查询,否则查询所有")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<SysUserRoleOrg> list(
                            @RequestParam(required = false) Integer pageNo,
                            @RequestParam(required = false) Integer pageSize,
                            @RequestParam(required = false) String userId,
                            @RequestParam(required = false) String userName,
                            @RequestParam(required = false) String orgId,
                            @RequestParam(required = false) String phone,
                            @RequestParam(required = false) Byte sex,
                            @RequestParam(required = false) String email) {

        Page<SysUserRoleOrg> page = null;
        if(pageNo != null && pageSize != null){
            page = new Page<>(pageNo,pageSize);  //分页查询
        }else{
            page = new Page<>(1,Integer.MAX_VALUE);  //查询所有
        }

        return sysUserExtMapper.listUserWithRoleOrg(userId,userName,orgId,phone,sex,email,page);
    }



    /**
     * 添加用户
     * @param sysUser 用户实体信息
     * @return ajax通用响应
     */
    @ApiOperation(value="添加用户", notes="添加用户,程序userId将校验唯一性")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public AjaxCommonObject add(@RequestBody SysUser sysUser) {

        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {

            ajaxCommonObject.setData(userService.add(sysUser)); //返回新增记录的表id

        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }

    /**
     * 修改用户
     * 注意:1.sysUser的属性userId为必传参数
     * 注意:2.此方法之修改用户基本信息,不要用来修改用户密码.如修改密码使用/change_pwd
     * @param sysUser 用户实体信息
     * @return ajax通用响应
     */
    @ApiOperation(value="按用户登录id修改用户", notes="按用户userId修改用户,此方法之修改用户基本信息,不要用来修改用户密码.如修改密码使用/change_pwd")
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public AjaxCommonObject edit(@RequestBody SysUser sysUser) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            userService.edit(sysUser);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    /**
     * 根据表id修改用户信息
     * 注意:1.sysUser的属性id为必传参数
     * @param sysUser 用户实体信息
     * @return ajax通用响应
     */
    @ApiOperation(value="根据表主键id修改用户", notes="根据表主键id修改用户")
    @RequestMapping(value = "/edit_by_tableid",method = RequestMethod.PUT)
    public AjaxCommonObject editByTableId(@RequestBody SysUser sysUser) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            userService.editByTableId(sysUser);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    /**
     * 删除用户（物理删除）
     * @param userId 用户登录id
     * @return ajax通用响应
     */
    @ApiOperation(value="删除用户", notes="根据userId删除用户")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public AjaxCommonObject delete(@RequestParam("userId") String userId) {

        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            if(StringUtils.isEmpty(userId)){
                return new AjaxCommonObject(BizCommonExceptionEnum.REQUEST_NULL);
            }
            userService.delete(userId);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


    /**
     * 查看用户详情
     * @param userId 用户登录id
     * @return ajax通用响应
     */
    @ApiOperation(value="查看用户详情", notes="查看用户详情")
    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public AjaxCommonObject view(@RequestParam("userId") String userId) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            if(StringUtils.isEmpty(userId)){
                return new AjaxCommonObject(BizCommonExceptionEnum.REQUEST_NULL);
            }
            ajaxCommonObject.setData(sysUserExtMapper.getUser(userId));
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }



    /**
     * 重置用户的密码
     * @param userId 用户登录id
     * @return ajax通用响应
     */
    @ApiOperation(value="重置用户的密码", notes="根据userId重置用户的密码")
    @RequestMapping(value = "/reset",method = RequestMethod.PUT)
    public AjaxCommonObject reset(@RequestParam("userId") String userId) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            if(StringUtils.isEmpty(userId)){
                return new AjaxCommonObject(BizCommonExceptionEnum.REQUEST_NULL);
            }
            SysUser sysUser = new SysUser();
            sysUser.setSalt(ShiroKit.getRandomSalt(5));
            sysUser.setPassword(ShiroKit.md5(AuthConstants.DEFAULT_PASSWORD, sysUser.getSalt()));

            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUserIdEqualTo(userId);
            sysUserMapper.updateByExampleSelective(sysUser,sysUserExample);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;

    }


    /**
     * 冻结用户
     * @param userId 用户登录id
     * @return ajax通用响应
     */
    @ApiOperation(value="冻结用户", notes="冻结用户")
    @RequestMapping(value = "/freeze",method = RequestMethod.PUT)
    public AjaxCommonObject freeze(@RequestParam("userId") String userId) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            if(StringUtils.isEmpty(userId)){
                return new AjaxCommonObject(BizCommonExceptionEnum.REQUEST_NULL);
            }
            SysUser sysUser = new SysUser();
            sysUser.setStatus(UserStatus.FREEZED.getCode());

            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUserIdEqualTo(userId);
            sysUserMapper.updateByExampleSelective(sysUser,sysUserExample);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }

    /**
     * 解除冻结用户
     * @param userId 用户登录id
     * @return ajax通用响应
     */
    @ApiOperation(value="解除冻结用户", notes="解除冻结用户")
    @RequestMapping(value = "/unfreeze",method = RequestMethod.PUT)
    public AjaxCommonObject unfreeze(@RequestParam("userId") String userId) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            if(StringUtils.isEmpty(userId)){
                return new AjaxCommonObject(BizCommonExceptionEnum.REQUEST_NULL);
            }
            SysUser sysUser = new SysUser();
            sysUser.setStatus(UserStatus.OK.getCode());

            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUserIdEqualTo(userId);
            sysUserMapper.updateByExampleSelective(sysUser,sysUserExample);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }




    @ApiOperation(value="修改用户密码", notes="修改用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户登陆ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "oldPwd", value = "用户旧的密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "newPwd", value = "用户新密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "rePwd", value = "再次输入用户新密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "/change_pwd",method = RequestMethod.PUT)
    public AjaxCommonObject changePwd(@RequestParam(value="userId",required = true) String userId,
                                      @RequestParam(value="oldPwd",required = true) String oldPwd,
                                      @RequestParam(value="newPwd",required = true) String newPwd,
                                      @RequestParam(value="rePwd",required = true) String rePwd) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            userService.changePwd(userId,oldPwd,newPwd,rePwd);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }



    /**
     * 分配角色
     * @param userId 用户登录id
     * @param roleIds 角色id逗号分隔
     * @return ajax通用响应
     */
    @ApiOperation(value="分配角色", notes="分配角色")
    @RequestMapping(value = "/set_roles",method = RequestMethod.POST)
    public AjaxCommonObject setRole(@RequestParam("userId") String userId,
                                    @RequestParam("roleIds") String roleIds) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(roleIds)) {
                throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
            }
            userService.setRole(userId,roleIds);
        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }



    @ApiOperation(value="判断用户是否合法", notes="用户名密码是否正确!")
    @RequestMapping(value = "/is_user_valid",method = RequestMethod.GET)
    public AjaxCommonObject isUserValid(@RequestParam(value="userId",required = true) String userId,
                                        @RequestParam(value="passWord",required = true) String passWord) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {
            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(passWord)) {
                throw new BizCommonException(BizCommonExceptionEnum.REQUEST_NULL);
            }

            ajaxCommonObject.setData(userService.isUserValid(userId,passWord));

        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;
    }


}
