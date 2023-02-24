package edu.hitwh.werunassignment.model.request;

import edu.hitwh.werunassignment.model.domain.Song;
import lombok.Data;
import java.util.List;

@Data
public class SongsRequest {

    private List<Song> songs;
}
