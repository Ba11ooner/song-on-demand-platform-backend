package edu.hitwh.werunassignment;

import edu.hitwh.werunassignment.model.domain.User;
import edu.hitwh.werunassignment.service.impl.UserCenterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WerunAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(WerunAssignmentApplication.class, args);
    }

}
