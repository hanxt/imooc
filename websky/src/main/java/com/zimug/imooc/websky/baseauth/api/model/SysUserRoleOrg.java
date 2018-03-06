package com.zimug.imooc.websky.baseauth.api.model;

import com.zimug.imooc.websky.common.model.SysUser;

/**
 * Created by hanxt on 17-8-17.
 */
public class SysUserRoleOrg extends SysUser {

    //用户对应的多个角色id(逗号分隔)
    private String roleIds;
    //用户对应的多个角色(逗号分隔)
    private String roleNames;
    //用户对应的组织名称
    private String orgName;


    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
