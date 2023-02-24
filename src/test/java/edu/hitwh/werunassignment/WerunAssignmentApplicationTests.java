package edu.hitwh.werunassignment;

import edu.hitwh.werunassignment.mapper.SongMapper;
import edu.hitwh.werunassignment.model.domain.User;
import edu.hitwh.werunassignment.service.UserCenterService;
import edu.hitwh.werunassignment.service.impl.UserCenterServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = WerunAssignmentApplication.class)
class WerunAssignmentApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void kanjiLength() {
        String kanji = "姓名长度";
        System.out.println(kanji.length());
    }

}
