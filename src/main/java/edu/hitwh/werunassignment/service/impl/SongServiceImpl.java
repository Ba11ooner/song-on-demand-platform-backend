package edu.hitwh.werunassignment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.werunassignment.common.ErrorCode;
import edu.hitwh.werunassignment.exception.BusinessException;
import edu.hitwh.werunassignment.mapper.SongMapper;
import edu.hitwh.werunassignment.model.domain.Song;
import edu.hitwh.werunassignment.service.UserCenterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.hitwh.werunassignment.service.SongService;

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
        System.out.println("SongService: addOneSong");
        //1.判空
        if (StringUtils.isAnyBlank(songName, singerName, platformName, remarks)) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }

        // 2.字符串截取（前端也做一次限定，此处出于安全起见再做一次限定，也可以放到 Controller 中）
        // ps：所有的前端都是可以绕过的
        // 限制歌名长度不超过 100
        if (songName.length() > 100) {
            songName = songName.substring(0, 100); // 左闭右开 [,)
        }
        // 限制歌手名字长度不超过 100
        if (singerName.length() > 100) {
            singerName = songName.substring(0, 100); // 左闭右开 [,)
        }
        // 限制平台名称长度不超过 20
        if (platformName.length() > 20) {
            platformName = songName.substring(0, 20); // 左闭右开 [,)
        }
        // 限定备注长度不超过 500
        if (remarks.length() > 500) {
            remarks = songName.substring(0, 500); // 左闭右开 [,)
        }

        //3.插入
        Song song = new Song();
        song.setSongname(songName);
        song.setSingername(singerName);
        song.setPlatformname(platformName);
        song.setRemarks(remarks);
        int result = songMapper.insert(song);
        if (result < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"歌曲已存在");
        }

        //4.返回结果
        System.out.println(song);
        return song;
    }

    // 给歌名搜票数
    @Override
    public Song findOneSongForVote(String songName) {
        System.out.println("SongService: findOneSongForVote");
        //1.判空
        if (StringUtils.isAnyBlank(songName))
            throw new BusinessException(ErrorCode.NULL_ERROR);
        //2.查询数据库
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("songName", songName);
        //3.返回结果
        System.out.println(songMapper.selectOne(queryWrapper));
        return (Song) songMapper.selectOne(queryWrapper);
    }

    // 获取查询未审核歌曲的函数
    @Override
    public List<Song> getUnexaminedSongs() {
        System.out.println("SongService: getUnexaminedSongs");
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("songStatus", 0);
        return songMapper.selectList(queryWrapper);
    }

    @Override
    // 审核
    public List<Song> examineSongs(List<Song> songs) {
        System.out.println("SongService: examineSongs");
        List<Song> songList = new ArrayList<>();
        //1.改状态
        for (Song song : songs) {
            // 放内部保证更新
            UpdateWrapper<Song> updateWrapper = new UpdateWrapper<>();
            QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
            updateWrapper.eq("songName", song.getSongname());
            updateWrapper.set("songStatus", song.getSongstatus());
            songMapper.update(song, updateWrapper);
            //获取修改后结果
            queryWrapper.eq("songName", song.getSongname());
            songList.add(songMapper.selectOne(queryWrapper));
        }
        //2.返回修改后的结果
        return songList;
    }


    @Override // 获取没被删除的歌曲
    public List<Song> getUnDeleteSongs() {
        System.out.println("SongService: getUnDeleteSongs");
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isDelete", 0);
        return songMapper.selectList(queryWrapper);
    }

    @Override // 从没被删除的歌曲里面选要删除的歌曲
    public List<Song> deleteSongs(List<Song> songs) {
        System.out.println("SongService: deleteSongs");
        List<Song> songList = new ArrayList<>();
        for (Song song : songs) {
            songMapper.deleteById(song.getId()); // 希望实现逻辑删除而非直接删除? 记得给对应属性加上 @TableLogic 注解
            QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", song.getId());
            songList.add(songMapper.selectOne(queryWrapper));
        }
        System.out.println(songList);
        return songList;
    }

    // 展示投票结果
    @Override
    public List<Song> showVotes() {
        System.out.println("SongService: showVotes");
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("songStatus", 1);
        return songMapper.selectList(queryWrapper);
    }

    //投票
    @Override
    public List<Song> vote(List<Song> songs) {
        System.out.println("SongService: vote");
        List<Song> songList = new ArrayList<>();
        for (Song song : songs) {
            // 获取投票前数据
            QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("songName", song.getSongname());
            Song songTMP = songMapper.selectOne(queryWrapper);
            // 更改投票数据
            UpdateWrapper<Song> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("songName", song.getSongname());
            updateWrapper.set("votes", songTMP.getVotes() + 1);
            songMapper.update(songTMP, updateWrapper);
            // 获取投票后数据
            songList.add(songMapper.selectOne(queryWrapper));
        }
        return songList;
    }
}




