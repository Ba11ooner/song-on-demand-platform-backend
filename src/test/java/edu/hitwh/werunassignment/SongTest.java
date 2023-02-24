package edu.hitwh.werunassignment;

import edu.hitwh.werunassignment.model.domain.Song;
import edu.hitwh.werunassignment.service.SongService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SongTest {

    @Autowired
    SongService songService;

    @Test
    public void addOneSongTest() {

        //歌名重复
        String songName = "song01";
        String singerName = "singer01";
        String platformName = "网易云音乐";
        String remarks = "remark01";
        Song result = songService.addOneSong(songName, singerName, platformName, remarks);
        Assertions.assertNull(result);
    }
}
