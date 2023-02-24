package edu.hitwh.werunassignment.controller;

import edu.hitwh.werunassignment.model.domain.Song;
import edu.hitwh.werunassignment.model.request.OneSongRequest;
import edu.hitwh.werunassignment.model.request.SongsRequest;
import edu.hitwh.werunassignment.service.SongService;
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

    @PostMapping("/addOneSong")
    public Song addOneSong(@RequestBody OneSongRequest oneSongRequest) {
        String songName = oneSongRequest.getSongName();
        String singerName = oneSongRequest.getSingerName();
        String platformName = oneSongRequest.getPlatformName();
        String remarks = oneSongRequest.getRemarks();
        return songService.addOneSong(songName, singerName, platformName, remarks);
    }

    @PostMapping("/findOneSongForVotes")
    public Song findOneSongForVotes(@RequestBody OneSongRequest oneSongRequest, HttpServletRequest request) {
        return songService.findOneSongForVote(oneSongRequest.getSongName(), request);
    }

    @GetMapping("/get/findOneSongForVotes")
    public Song findOneSongForVotesByGet(@RequestParam String songName, HttpServletRequest request) {
        //System.out.println(songName);
        return songService.findOneSongForVote(songName, request);
    }

    @PostMapping("/examineSongs")
    public List<Song> examineSongs(@RequestBody SongsRequest songsRequest, HttpServletRequest request) {
        System.out.println("Controller:" + songsRequest.getSongs());
        return songService.examineSongs(songsRequest.getSongs(), request);

    }

    @GetMapping("/getUnexaminedSongs")
    public List<Song> getUnexaminedSongs(HttpServletRequest request){
        return songService.getUnexaminedSongs(request);
    }
}
