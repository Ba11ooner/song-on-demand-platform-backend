package edu.hitwh.werunassignment.service;

import edu.hitwh.werunassignment.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 18047
 * @description 针对表【user_center(用户)】的数据库操作Service
 * @createDate 2023-02-16 15:35:44
 */
public interface UserCenterService extends IService<User> {
    User register(String userAccount, String username, String password, String checkedPassword);

    User login(String userAccount, String password, HttpServletRequest request);

    void logout(HttpServletRequest request);

    boolean isAdmin(HttpServletRequest request);
}
