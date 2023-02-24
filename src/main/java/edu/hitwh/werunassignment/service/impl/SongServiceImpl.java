package edu.hitwh.werunassignment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.werunassignment.mapper.SongMapper;
import edu.hitwh.werunassignment.model.domain.Song;
import edu.hitwh.werunassignment.model.request.OneSongRequest;
import edu.hitwh.werunassignment.service.UserCenterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.hitwh.werunassignment.service.SongService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 18047
 * @description 针对表【song(歌曲)】的数据库操作Service实现
 * @createDate 2023-02-16 15:35:06
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song>
        implements SongService {

    @Autowired
    SongMapper songMapper;

    @Autowired
    UserCenterService userCenterService;

    @Override
    public Song addOneSong(String songName, String singerName, String platformName, String remarks) {
        //1.判空
        if (StringUtils.isAnyBlank(songName, singerName, platformName, remarks)) {
            return null;
        }
        Song song = new Song();
        //TODO 2.字符串截取（前端也做一次限定，此处出于安全起见再做一次限定，也可以放到 Controller 中）
        // 限制歌名长度不超过 100
        // 限制歌手名字长度不超过 100
        // 限制平台名称长度不超过 20
        // ps：所有的前端都是可以绕过的
        // 限定备注长度不超过 500

        song.setSongname(songName);
        song.setSingername(singerName);
        song.setPlatformname(platformName);
        song.setRemarks(remarks);

        //3.插入
        int result = songMapper.insert(song);
        if (result < 0) {
            return null;
        }

        //4.返回结果
        System.out.println(song);
        return song;
    }

    // 给歌名搜票数
    @Override
    public Song findOneSongForVote(String songName, HttpServletRequest request) {
        //1.鉴权
        if (userCenterService.isAdmin(request) == false) return null;
        //2.判空
        if (StringUtils.isAnyBlank(songName))
            return null;
        //3.查询数据库
        //System.out.println("songName:" + songName);
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("songName", songName);
        //4.返回结果
        System.out.println(songMapper.selectOne(queryWrapper));
        return (Song) songMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Song> getUnexaminedSongs(HttpServletRequest request) {
        if (userCenterService.isAdmin(request) == false) return null;
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("songStatus",0);
        return songMapper.selectList(queryWrapper);
    }

    @Override
    //审核
    // TODO 业务流程有问题，不能一个函数想着又检测是否存在，有修改
    //  默认传入的都是已经存在的，后续补一个查询未审核的函数，这里只负责修改状态即可
    public List<Song> examineSongs(List<Song> songs, HttpServletRequest request) {
        //1.鉴权
        if (userCenterService.isAdmin(request) == false) return null;
        System.out.println("songs:" + songs);

        //2.改状态
        for (int i = 0; i < songs.size(); i++) {
            //格式转换，方便操作
            Song song = songs.get(i);
            UpdateWrapper<Song> updateWrapper = new UpdateWrapper<>();
            System.out.println(song.getSongname() + " " + song.getSongstatus());
            updateWrapper.eq("songName", song.getSongname());
            updateWrapper.set("songStatus", song.getSongstatus());
            songMapper.update(song,updateWrapper);
        }
        return songs;//返回修改后的结果
    }
}




