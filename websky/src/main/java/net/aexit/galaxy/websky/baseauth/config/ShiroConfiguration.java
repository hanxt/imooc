package net.aexit.galaxy.websky.baseauth.config;

import net.aexit.galaxy.websky.baseauth.api.mapper.SysMenuExtMapper;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro生命周期配置项
 * Created by hanxt on 2017/12/18.
 */
@Configuration
public class ShiroConfiguration {

    @Resource
    SysMenuExtMapper sysMenuExtMapper;

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean(name = "shiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public AexitShrioRealm shiroRealm() {
        AexitShrioRealm realm = new AexitShrioRealm();
        return realm;
    }

    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setCacheManager(ehCacheManager());//用户授权/认证信息Cache, 采用EhCache 缓存
        return securityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<>();
        filterChainDefinitionManager.put("/druid/**", "anon");
        filterChainDefinitionManager.put("/static/**", "anon");//静态资源不拦截
        filterChainDefinitionManager.put("/login", "anon");//anon 可以理解为不拦截
        filterChainDefinitionManager.put("/logout", "anon");//anon 可以理解为不拦截
        filterChainDefinitionManager.put("/kaptcha", "anon");//anon 可以理解为不拦截

        filterChainDefinitionManager.put("/", "anon");
        filterChainDefinitionManager.put("/**", "authc,myperm");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);


        shiroFilterFactoryBean.setLoginUrl("/");
        //shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/");


        URLPermissionsFilter urlPermissionsFilter = new URLPermissionsFilter();
        Map<String,Filter> filtermap = new HashMap<>();
        filtermap.put("myperm",urlPermissionsFilter);shiroFilterFactoryBean.setFilters(filtermap);



        return shiroFilterFactoryBean;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    //thymeleaf模板引擎和shiro整合时使用
    /*@Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }*/
}