package edu.hitwh.werunassignment.model.request;

import lombok.Data;

/**
 * 单首歌曲信息请求
 */
@Data
public class OneSongRequest {
    private String songName;//歌名
    private String singerName;//歌手名称
    private String platformName;//平台名称
    private String remarks;//备注
    private String songStatus;//状态
}
