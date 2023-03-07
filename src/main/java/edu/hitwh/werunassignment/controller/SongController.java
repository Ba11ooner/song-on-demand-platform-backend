package edu.hitwh.werunassignment.controller;

import edu.hitwh.werunassignment.common.BaseResponse;
import edu.hitwh.werunassignment.common.ResultUtils;
import edu.hitwh.werunassignment.model.domain.Song;
import edu.hitwh.werunassignment.model.request.OneSongRequest;
import edu.hitwh.werunassignment.model.request.SongsRequest;
import edu.hitwh.werunassignment.service.SongService;
import edu.hitwh.werunassignment.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
@RequestMapping("/song")
public class SongController {
    @Autowired
    SongService songService;
    @Autowired
    UserCenterService userCenterService;

    @PostMapping("/addOneSong")
    public BaseResponse<Song> addOneSong(@RequestBody OneSongRequest oneSongRequest) {
        System.out.println("SongController:addOneSong");
        String songName = oneSongRequest.getSongName();
        String singerName = oneSongRequest.getSingerName();
        String platformName = oneSongRequest.getPlatformName();
        String remarks = oneSongRequest.getRemarks();
        return ResultUtils.success(songService.addOneSong(songName, singerName, platformName, remarks));
    }

    @PostMapping("/findOneSongForVotes")
    public BaseResponse<Song> findOneSongForVotes(@RequestBody OneSongRequest oneSongRequest, HttpServletRequest request) {
        System.out.println("SongController:findOneSongForVotes");
        System.out.println(oneSongRequest);
        //TODO 鉴权问题暂时放一放
        //if (!userCenterService.isAdmin(request)) throw new BusinessException(ErrorCode.NO_AUTH);
        return ResultUtils.success(songService.findOneSongForVote(oneSongRequest.getSongName()));
    }

    @GetMapping("/get/findOneSongForVotes")
    public BaseResponse<Song> findOneSongForVotesByGet(@RequestParam String songName, HttpServletRequest request) {
        System.out.println("SongController:findOneSongForVotesByGet");
        System.out.println(songName);
        //TODO 鉴权问题暂时放一放
        //if (!userCenterService.isAdmin(request)) throw new BusinessException(ErrorCode.NO_AUTH);
        return ResultUtils.success(songService.findOneSongForVote(songName));
    }

    @PostMapping("/examineSongs")
    public BaseResponse<List<Song>> examineSongs(@RequestBody SongsRequest songsRequest, HttpServletRequest request) {
        System.out.println("SongController:examineSongs");
        System.out.println(songsRequest);
        //TODO 鉴权问题暂时放一放
        //if (!userCenterService.isAdmin(request)) throw new BusinessException(ErrorCode.NO_AUTH);
        return ResultUtils.success(songService.examineSongs(songsRequest.getSongs()));
    }

    @GetMapping("/getUnexaminedSongs")
    public BaseResponse<List<Song>> getUnexaminedSongs(HttpServletRequest request) {
        System.out.println("SongController:getUnexaminedSongs");
        //TODO 鉴权问题暂时放一放
        //if (!userCenterService.isAdmin(request)) throw new BusinessException(ErrorCode.NO_AUTH);
        return ResultUtils.success(songService.getUnexaminedSongs());
    }

    @PostMapping("/deleteSongs")
    public BaseResponse<List<Song>> deleteSongs(@RequestBody SongsRequest songsRequest, HttpServletRequest request) {
        System.out.println("SongController:deleteSongs");
        System.out.println(songsRequest);
        //TODO 鉴权问题暂时放一放
        //if (!userCenterService.isAdmin(request)) throw new BusinessException(ErrorCode.NO_AUTH);
        return ResultUtils.success(songService.deleteSongs(songsRequest.getSongs()));
        //return songService.deleteSongs(songService.getUnDeleteSongs()); //测试用
    }
}
