package edu.hitwh.werunassignment.controller;

import edu.hitwh.werunassignment.model.domain.Song;
import edu.hitwh.werunassignment.model.request.OneSongRequest;
import edu.hitwh.werunassignment.model.request.SongsRequest;
import edu.hitwh.werunassignment.service.SongService;
import edu.hitwh.werunassignment.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController()
@RequestMapping("/song")
public class SongController {
    @Autowired
    SongService songService;

    @Autowired
    UserCenterService userCenterService;

    @PostMapping("/addOneSong")
    public Song addOneSong(@RequestBody OneSongRequest oneSongRequest) {
        System.out.println("SongController:addOneSong");
        String songName = oneSongRequest.getSongName();
        String singerName = oneSongRequest.getSingerName();
        String platformName = oneSongRequest.getPlatformName();
        String remarks = oneSongRequest.getRemarks();
        return songService.addOneSong(songName, singerName, platformName, remarks);
    }

    @PostMapping("/findOneSongForVotes")
    public Song findOneSongForVotes(@RequestBody OneSongRequest oneSongRequest, HttpServletRequest request) {
        System.out.println("SongController:findOneSongForVotes");
        System.out.println(oneSongRequest);
        if (!userCenterService.isAdmin(request)) return null;
        return songService.findOneSongForVote(oneSongRequest.getSongName());
    }

    @GetMapping("/get/findOneSongForVotes")
    public Song findOneSongForVotesByGet(@RequestParam String songName, HttpServletRequest request) {
        System.out.println("SongController:findOneSongForVotesByGet");
        System.out.println(songName);
        if (!userCenterService.isAdmin(request)) return null;
        //System.out.println(songName);
        return songService.findOneSongForVote(songName);
    }

    @PostMapping("/examineSongs")
    public List<Song> examineSongs(@RequestBody SongsRequest songsRequest, HttpServletRequest request) {
        System.out.println("SongController:examineSongs");
        System.out.println(songsRequest);
        if (!userCenterService.isAdmin(request)) return null;
        return songService.examineSongs(songsRequest.getSongs());

    }

    @GetMapping("/getUnexaminedSongs")
    public List<Song> getUnexaminedSongs(HttpServletRequest request) {
        System.out.println("SongController:getUnexaminedSongs");
        if (!userCenterService.isAdmin(request)) return null;
        return songService.getUnexaminedSongs();
    }

    @PostMapping("/deleteSongs")
    public List<Song> deleteSongs(@RequestBody SongsRequest songsRequest, HttpServletRequest request) {
        System.out.println("SongController:deleteSongs");
        System.out.println(songsRequest);
        if (!userCenterService.isAdmin(request)) return null;
        //return songService.deleteSongs(songService.getUnDeleteSongs()); //测试用
        return songService.deleteSongs(songsRequest.getSongs());
    }
}
