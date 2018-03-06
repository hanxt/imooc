package net.aexit.galaxy.websky.baseauth.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import net.aexit.galaxy.earth.common.utils.AjaxCommonObject;
import net.aexit.galaxy.earth.common.utils.BizCommonException;
import net.aexit.galaxy.websky.baseauth.api.service.UserService;
import net.aexit.galaxy.websky.common.mapper.SysDictionaryMapper;
import net.aexit.galaxy.websky.common.mapper.SysMenuMapper;
import net.aexit.galaxy.websky.common.model.SysDictionaryExample;
import net.aexit.galaxy.websky.common.model.SysMenuExample;
import net.aexit.galaxy.websky.common.model.SysUser;
import net.aexit.galaxy.websky.common.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created by hanxt on 17-8-24.
 *
 */
@Controller
@ApiIgnore
public class LoginController {

    @Resource
    DefaultKaptcha captchaProducer;

    @Resource
    UserService userService;

    @Resource
    SysDictionaryMapper sysDictionaryMapper;

    @Resource
    SysMenuMapper sysMenuMapper;

    //跳转到登录页面
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewLogin(Model model, HttpServletRequest request) {
        setLoginSkin(model,request.getContextPath());
        return "login";
    }

    /**
     * 用户登出
     * @return 登陆界面
     */
    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(HttpSession session,Model model,HttpServletRequest request) {
        setLoginSkin(model,request.getContextPath());
        session.removeAttribute(AuthConstants.CURRENT_USER);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();

        return "login";
    }

    //跳转到系统布局页面
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model,HttpServletRequest request) {
        setLoginSkin(model,request.getContextPath());
        return "layout/index";
    }

    //TAB展现首页信息
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "layout/home_page";
    }

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/kaptcha", method = RequestMethod.GET)
    public void kaptcha(HttpSession session,HttpServletResponse response) throws Exception {

        try (ServletOutputStream out = response.getOutputStream()) {
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");

            String capText = captchaProducer.createText();

            //TODO 如果是前后台分离或分布式部署,这里需要使用SpringSession放到redis里面
            //将验证码存到session
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

            BufferedImage bi = captchaProducer.createImage(capText);
            ImageIO.write(bi, "jpg", out);

            out.flush();
        }
    }

    /**
     * 登陆验证
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public AjaxCommonObject login(@RequestParam(value="user_id",required = false) String userId,
                                  @RequestParam(value="password",required = false) String password,
                                  @RequestParam(value="user_validate_code",required = false) String userValidateCode,
                                  HttpSession session) {
        AjaxCommonObject ajaxCommonObject = new AjaxCommonObject();
        try {

            if (!userValidateCode.equals(session.getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
                throw new BizCommonException(400,"请输入正确的验证码!");
            }

            SysUser user = userService.getByUserId(userId.trim());
            try {
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(
                        userId,
                        ShiroKit.md5(password, user.getSalt()));
                subject.login(token);
            } catch (Exception e){
                throw new BizCommonException(400,"请输入正确的用户名密码!");
            }

            //登陆成功之后
            session.setAttribute(AuthConstants.CURRENT_USER, user);
            session.setAttribute(AuthConstants.DICTIONRYS,
                    sysDictionaryMapper.selectByExample(new SysDictionaryExample()));//数据字典
            session.setAttribute(AuthConstants.MENUS,
                    sysMenuMapper.selectByExample(new SysMenuExample()));//数据字典

        } catch (BizCommonException e) {
            return new AjaxCommonObject(e);
        }
        return ajaxCommonObject;

    }


    /**
     * 设置登陆页皮肤
     */
    private void setLoginSkin(Model model,String contextPath){
        String skin = SysConfigKit.getSysConfigByKeyRealTime(SysConfigKeys.SKIN_SELECT);
        Map<String,String> skinConfig = SysConfigKit.getSysConfigByGroupRealTime(skin);
        model.addAttribute("skin_css",skinConfig.get(SysConfigKeys.SKIN_CSS));
        model.addAttribute("skin_select",skin);

        String bodyImg = skinConfig.get(SysConfigKeys.SKIN_BG_IMG);
        if(bodyImg.contains("http")){//网络图片
            model.addAttribute("skin_body_img",bodyImg);
        }else{//本地图片
            model.addAttribute("skin_body_img",contextPath + bodyImg);
        }

        String logoImg = skinConfig.get(SysConfigKeys.SKIN_LOGO_IMG);
        if(bodyImg.contains("http")){
            model.addAttribute("skin_logo_img",logoImg);
        }else{
            model.addAttribute("skin_logo_img",contextPath + logoImg);
        }
    }
}
