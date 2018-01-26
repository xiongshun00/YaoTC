package com.stylefeng.guns.modular.system.controller;

import com.google.code.kaptcha.Constants;
import com.stylefeng.guns.common.exception.InvalidKaptchaException;
import com.stylefeng.guns.common.persistence.dao.UserMapper;
import com.stylefeng.guns.common.persistence.model.User;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogManager;
import com.stylefeng.guns.core.log.factory.LogTaskFactory;
import com.stylefeng.guns.core.node.MenuNode;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.ApiMenuFilter;
import com.stylefeng.guns.core.util.KaptchaUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.dao.MenuDao;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import static com.stylefeng.guns.core.support.HttpKit.getIp;

/**
 * 登录控制器
 *
 * @author fengshuonan
 * @Date 2017年1月10日 下午8:25:24
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    MenuDao menuDao;

    @Autowired
    UserMapper userMapper;

    /**
     * 跳转到主页
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        //获取菜单列表
    	
    	//获取登录用户的角色身份
        List<Integer> roleList = ShiroKit.getUser().getRoleList();
        if (roleList == null || roleList.size() == 0) {
            ShiroKit.getSubject().logout();
            model.addAttribute("tips", "该用户没有角色，无法登陆");
            return "/login.html";
        }
        //通过角色获取到所有的菜单
        List<MenuNode> menus = menuDao.getMenusByRoleIds(roleList);
        //对菜单进行排序，得到新的菜单
        List<MenuNode> titles = MenuNode.buildTitle(menus);
        //判断菜单手否关闭
        titles = ApiMenuFilter.build(titles);
        //将菜单存入送到页面
        model.addAttribute("titles", titles);

        //获取用户头像
        Integer id = ShiroKit.getUser().getId();//找到用户id
        User user = userMapper.selectById(id);//通过id获取相对应的用户
        String avatar = user.getAvatar();//获取用户的头像地址
        model.addAttribute("avatar", avatar);//送入页面
        //返回页面
        return "/index.html";
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
            return "/login.html";
        }
    }

    /**
     * 点击登录执行的动作
     */
    //@ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginVali() {
    	//获取用户名，密码和记住我
        String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();
        String remember = super.getPara("remember");

        //验证验证码是否正确
        if (KaptchaUtil.getKaptchaOnOff()) {
            String kaptcha = super.getPara("kaptcha").trim();
            String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (ToolUtil.isEmpty(kaptcha) || !kaptcha.equalsIgnoreCase(code)) {
                throw new InvalidKaptchaException();
            }
        }
        //获取Subject接口，ShiroKit工具类
        Subject currentUser = ShiroKit.getSubject();
        //获取令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
        
        if ("on".equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }
        //shiro登陆方法
        currentUser.login(token);
        //获取验证成功后得到的用户信息
        ShiroUser shiroUser = ShiroKit.getUser();
        //将shiro获取的用户和用户名存入
        super.getSession().setAttribute("shiroUser", shiroUser);
        super.getSession().setAttribute("username", shiroUser.getAccount());
        //写入日志
        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));
        //请求通过
        ShiroKit.getSession().setAttribute("sessionFlag", true);
        //跳转请求到44行
        return REDIRECT + "/";
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
    	//记录退出的日志
        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), getIp()));
        ShiroKit.getSubject().logout();
        //返回登录页面
        return REDIRECT + "/login";
    }
}
