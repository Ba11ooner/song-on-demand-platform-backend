package edu.hitwh.werunassignment;

import edu.hitwh.werunassignment.model.domain.User;
import edu.hitwh.werunassignment.service.UserCenterService;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    UserCenterService userCenterService;
    @Test
    public void registerTest() {

        String userAccount, username, password, checkedPassword;
        User result;

        // 空
        username = "";
        userAccount = "";
        password = "";
        checkedPassword = "";
        result = userCenterService.register(userAccount, username, password, checkedPassword);
        Assertions.assertEquals(null,result);

        // 格式错误
        username = "张三";
        userAccount = "2201110666";
        password = "123456";
        checkedPassword = "123456";
        result = userCenterService.register(userAccount, username, password, checkedPassword);
        Assertions.assertEquals(null,result);

        // 格式正确
        username = "张三01";
        userAccount = "2201110503";
        password = "12345678";
        checkedPassword = "12345678";
        result = userCenterService.register(userAccount, username, password, checkedPassword);
        Assertions.assertNotEquals(null,result);
    }




}
