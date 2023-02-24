package edu.hitwh.werunassignment.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
/**
 * 注册请求体
 */
public class RegisterRequest implements Serializable {
    private String userAccount; //学号 工号
    private String username; //姓名
    private String password; //密码
    private String checkedPassword;//二次输入密码
}
