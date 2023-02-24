package edu.hitwh.werunassignment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hitwh.werunassignment.model.domain.Song;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 18047
* @description 针对表【song(歌曲)】的数据库操作Mapper
* @createDate 2023-02-16 15:35:06
* @Entity edu.hitwh.werunassignment.model.domain.Song
*/
@Mapper
public interface SongMapper extends BaseMapper<Song> {

}




