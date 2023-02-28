package edu.hitwh.werunassignment.controller;

import edu.hitwh.werunassignment.model.domain.User;
import edu.hitwh.werunassignment.model.request.LoginRequest;
import edu.hitwh.werunassignment.model.request.RegisterRequest;
import edu.hitwh.werunassignment.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static edu.hitwh.werunassignment.constant.UserConstant.USER_LOGIN_STATE;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserCenterService userCenterService;

    //测试用
    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello");
        return "hello";
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest registerRequest) {
        System.out.println("UserController:register");
        if (registerRequest == null) return null;
        String userAccount = registerRequest.getUserAccount();
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();
        String checkedPassword = registerRequest.getCheckedPassword();
        userCenterService.register(userAccount, username, password, checkedPassword);
        return null;
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        System.out.println("UserController:login");
        if (loginRequest == null) return null;
        String userAccount = loginRequest.getUserAccount();
        String password = loginRequest.getPassword();
        return userCenterService.login(userAccount, password, request);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        System.out.println("UserController:logout");
        userCenterService.logout(request);
    }

    //测试用
    @GetMapping("/getUserState")
    public User getUserState(HttpServletRequest request) {
        System.out.println("UserController:getUserState");
        return (User) request.getSession().getAttribute(USER_LOGIN_STATE);
    }
}
