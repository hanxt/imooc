package net.aexit.galaxy.websky.baseauth.config;

import net.aexit.galaxy.websky.common.model.SysMenu;
import net.aexit.galaxy.websky.common.utils.AuthConstants;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

/**
 * 自定义shiro的URL拦截器
 * 拦截目标为菜单url,根据AexitShrioRealm配置的权限决定用户有没有权限访问菜单
 * 注意:这里不做数据层面的请求限制
 */
public class URLPermissionsFilter extends PermissionsAuthorizationFilter {

    /** 
     *  @param mappedValue 指的是在声明url时指定的权限字符串，
     *  如/User/create.do=perms[User:create].我们要动态产生这个权限字符串，所以这个配置对我们没用
     */
    @SuppressWarnings("unchecked")
    public boolean isAccessAllowed(ServletRequest request,
                                   ServletResponse response, Object mappedValue) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getServletPath();
        //用户在登陆时把系统所有的菜单项目保存到list里面
        List<SysMenu> sysMenus = (List<SysMenu>)((HttpServletRequest) request).getSession().getAttribute(AuthConstants.MENUS);

        if(sysMenus != null){
            for(SysMenu menu : sysMenus){
                if(path.equals(menu.getUrl())){
                    //根据AexitShrioRealm配置的权限决定用户有没有权限访问菜单
                    return super.isAccessAllowed(request, response, buildPermissions(request));
                }
            }
        }

        //不是菜单的,不做数据层面的请求限制
        return true;
    }


    /** 
     * 根据请求URL产生权限字符串(如:/menu)，这里只产生，而比对的事交给Realm
     */  
    protected String[] buildPermissions(ServletRequest request) {  
        String[] perms = new String[1];  
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getServletPath();  
        perms[0] = path;//path直接作为权限字符串
        return perms;  
    }  
}