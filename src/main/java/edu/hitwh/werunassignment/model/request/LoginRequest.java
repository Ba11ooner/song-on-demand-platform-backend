package edu.hitwh.werunassignment.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
/**
 * 登录请求体
 */
public class LoginRequest implements Serializable {
    private String userAccount; //学号 工号
    private String password; //密码
}