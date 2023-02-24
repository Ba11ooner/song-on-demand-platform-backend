package edu.hitwh.werunassignment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hitwh.werunassignment.model.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 18047
* @description 针对表【user_center(用户)】的数据库操作Mapper
* @createDate 2023-02-16 15:35:44
* @Entity generator.domain.UserCenter
*/
@Mapper
public interface UserCenterMapper extends BaseMapper<User> {

}




