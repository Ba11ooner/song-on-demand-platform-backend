package edu.hitwh.werunassignment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.werunassignment.mapper.UserCenterMapper;
import edu.hitwh.werunassignment.model.domain.User;
import edu.hitwh.werunassignment.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.hitwh.werunassignment.constant.UserConstant.ADMIN_ROLE;
import static edu.hitwh.werunassignment.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 18047
 * @description 针对表【user_center(用户)】的数据库操作Service实现
 * @createDate 2023-02-16 15:35:44
 */
@Service
public class UserCenterServiceImpl extends ServiceImpl<UserCenterMapper, User>
        implements UserCenterService {
    //密码盐值，用于混淆密码
    private static final String SALT = "salt";

    @Autowired
    private UserCenterMapper userMapper;

    /**
     * 注册
     *
     * @param userAccount     学号、工号
     * @param username        姓名
     * @param password        密码
     * @param checkedPassword 验证密码
     * @return 注册成功，返回脱敏用户对象，否则返回 null
     */
    @Override
    public User register(String userAccount, String username, String password, String checkedPassword) {
        //1.判空
        if (StringUtils.isAnyBlank(userAccount, username, password, checkedPassword)) {
            return null;
        }
        //2.格式判断
        if (userAccount.length() != 10) {// 学号不是 10 位
            return null;
        }
        if (password.length() < 8 || checkedPassword.length() < 8 || password.length() > 20 || checkedPassword.length() > 20) { // 密码小于 8 位或大于 20 位
            return null;
        }
        if (username.length() < 2 || username.length() > 16) { // 姓名小于 2 个字 大于 16 个字
            return null;
        }
        //特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }
        // 密码和校验密码相同
        if (!password.equals(checkedPassword)) {
            return null;
        }

        //3.账户不重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        System.out.println(userAccount);
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);

        if (count > 0) {// 账号重复
            System.out.println("账号重复");
            return null;
        }

        //4.加密
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        System.out.println(encryptedPassword);

        //5.插入数据
        User user = new User();
        user.setUseraccount(userAccount);
        user.setUsername(username);
        user.setUserpassword(encryptedPassword);
        boolean saveResult = this.save(user);//此处有一种看似违背架构实为封装的简化做法，直接调用 this.save(user)

        //6.用户脱敏
        User safetyUser = getSafetyUser(user);

        System.out.println("register:" + safetyUser);
        //7.返回结果
        if (!saveResult) {
            return null;
        } else
            return safetyUser;
    }

    /**
     * 登录
     *
     * @param userAccount 学号、工号
     * @param password    密码
     * @param request     请求，用于在 Session 中存储登录态
     * @return 登录成功，返回用户对象，否则返回 null
     */
    @Override
    public User login(String userAccount, String password, HttpServletRequest request) {
        //1.判空
        if (StringUtils.isAnyBlank(userAccount, password)) {
            return null;
        }

        //2.判格式
        if (userAccount.length() != 10) {// 学号不是 10 位
            return null;
        }
        if (password.length() < 8 || password.length() > 20) { // 密码小于 8 位或大于 20 位
            return null;
        }
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";// 非法字符
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }

        //3.密码加密
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        //System.out.println(encryptedPassword);

        //4.数据库比对
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptedPassword);
        // queryWrapper 就相当于 SQL 语句
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return null;
        }

        //5.用户脱敏
        User safetyUser = getSafetyUser(user);
        //System.out.println("User:"+user);
        //System.out.println("safetyUser:"+safetyUser);
        //6.登录态记录
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);

        //7.返回结果
        //System.out.println("login:" + safetyUser);
        return safetyUser;
    }

    /**
     * 注销（登出）
     *
     * @param request 清除 Session 中的登录态
     */
    @Override
    public void logout(HttpServletRequest request) {
        //登录态清除
        request.getSession().removeAttribute(USER_LOGIN_STATE);
    }


    @Override
    public boolean isAdmin(HttpServletRequest request) {
        //1. 获取用户登录态
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        //System.out.println("isAdmin:"+user);
        //2.权限判断
        return user != null && user.getUserrole() == ADMIN_ROLE;
    }

    private User getSafetyUser(User user) {
        if (user == null) return null;
        User safetyUser = new User();
        safetyUser.setUserpassword(null);
        safetyUser.setUseraccount(user.getUseraccount());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setUserrole(user.getUserrole());
        return safetyUser;
    }
}




