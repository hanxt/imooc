package com.zimug.imooc.websky.config;

import com.zimug.imooc.websky.common.utils.ShiroKit;
import net.aexit.galaxy.earth.common.utils.ToolUtil;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by hanxt on 17-8-24.
 */
@Configuration
public class BeetlTplConfig extends BeetlGroupUtilConfiguration {


    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {

        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
        beetlGroupUtilConfiguration.setResourceLoader(classpathResourceLoader);

        Properties beetlConfigProperties = new Properties();
        //是否检测文件变化,开发用true合适，但线上要改为false
        beetlConfigProperties.setProperty("RESOURCE.autoCheck","true");
        //自定义标签文件Root目录和后缀
        beetlConfigProperties.setProperty("RESOURCE.tagRoot","templates/tags");
        beetlConfigProperties.setProperty("RESOURCE.tagSuffix","tag");

        beetlGroupUtilConfiguration.setConfigProperties(beetlConfigProperties);
        return beetlGroupUtilConfiguration;
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setPrefix("/templates/");
        beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }


    @Override
    public void initOther() {

        groupTemplate.registerFunctionPackage("shiro", new ShiroKit());
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());


    }
}
