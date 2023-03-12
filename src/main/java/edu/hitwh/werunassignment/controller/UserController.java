package edu.hitwh.werunassignment.controller;

import edu.hitwh.werunassignment.common.BaseResponse;
import edu.hitwh.werunassignment.common.ErrorCode;
import edu.hitwh.werunassignment.common.ResultUtils;
import edu.hitwh.werunassignment.exception.BusinessException;
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

    @PostMapping(path = "/register")
    public BaseResponse<User> register(@RequestBody RegisterRequest registerRequest) {
        System.out.println("UserController:register");
        if (registerRequest == null) throw new BusinessException(ErrorCode.NULL_ERROR);
        String userAccount = registerRequest.getUserAccount();
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();
        String checkedPassword = registerRequest.getCheckedPassword();
        return ResultUtils.success(userCenterService.register(userAccount, username, password, checkedPassword));
    }

    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        System.out.println("UserController:login");
        if (loginRequest == null) throw new BusinessException(ErrorCode.NULL_ERROR);
        String userAccount = loginRequest.getUserAccount();
        String password = loginRequest.getPassword();
        return ResultUtils.success(userCenterService.login(userAccount, password, request));
    }

    @GetMapping("/logout")
    public BaseResponse logout(HttpServletRequest request) {
        System.out.println("UserController:logout");
        if (request == null) throw new BusinessException(ErrorCode.NULL_ERROR);
        userCenterService.logout(request);
        return ResultUtils.success(null);
    }

    //测试用
    @GetMapping("/getUserState")
    public User getUserState(HttpServletRequest request) {
        System.out.println("UserController:getUserState");
        return (User) request.getSession().getAttribute(USER_LOGIN_STATE);
    }

    //测试用
    @GetMapping("/getUserState01")
    public BaseResponse<User> getUserState01(HttpServletRequest request) {
        System.out.println("UserController:getUserState");
        return ResultUtils.success((User) request.getSession().getAttribute(USER_LOGIN_STATE));
    }
}
