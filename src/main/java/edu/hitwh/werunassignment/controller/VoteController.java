package edu.hitwh.werunassignment.controller;

import edu.hitwh.werunassignment.common.BaseResponse;
import edu.hitwh.werunassignment.common.ResultUtils;
import edu.hitwh.werunassignment.model.domain.Song;
import edu.hitwh.werunassignment.model.request.SongsRequest;
import edu.hitwh.werunassignment.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzh
 */
@RestController()
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    SongService songService;

    /**
     *
     * @return 返回可投票的歌曲列表
     */
    @GetMapping("/showVotes")
    public BaseResponse<List<Song>> showVotes() {
        System.out.println("VoteController:showVotes");
        return ResultUtils.success(songService.showVotes());
    }

    /**
     *
     * @param songsRequest 歌曲列表请求
     * @return 投票后的歌曲列表
     */
    @PostMapping("/vote")
    public BaseResponse<List<Song>> vote(@RequestBody SongsRequest songsRequest) {
        System.out.println("VoteController:vote");
        System.out.println(songsRequest);
        return ResultUtils.success(songService.vote(songsRequest.getSongs()));
        //return ResultUtils.success(songService.vote(songService.showVotes())); //测试用
    }
}
