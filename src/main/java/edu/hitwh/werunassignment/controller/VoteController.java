package edu.hitwh.werunassignment.controller;

import edu.hitwh.werunassignment.model.domain.Song;
import edu.hitwh.werunassignment.model.request.SongsRequest;
import edu.hitwh.werunassignment.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    SongService songService;

    @GetMapping("/showVotes")
    public List<Song> showVotes() {
        System.out.println("VoteController:showVotes");
        return songService.showVotes();
    }

    @PostMapping("/vote")
    public List<Song> vote(SongsRequest songsRequest) {
        System.out.println("VoteController:vote");
        return songService.vote(songService.showVotes());// 测试用
        //return songService.vote(songsRequest.getSongs());

    }
}
