package edu.hitwh.werunassignment.service;

import edu.hitwh.werunassignment.model.domain.Song;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.hitwh.werunassignment.model.request.OneSongRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 18047
* @description 针对表【song(歌曲)】的数据库操作Service
* @createDate 2023-02-16 15:35:06
*/
public interface SongService extends IService<Song> {

    Song addOneSong(String songName, String singerName, String platformName, String remarks);


    // 给歌名搜票数
    Song findOneSongForVote(String songName, HttpServletRequest request);


    List<Song> getUnexaminedSongs(HttpServletRequest request);

    //审核
    List<Song> examineSongs(List<Song> songs, HttpServletRequest request);
}
