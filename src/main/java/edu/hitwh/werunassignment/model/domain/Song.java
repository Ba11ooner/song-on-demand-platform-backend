package edu.hitwh.werunassignment.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 歌曲
 *
 * @TableName song
 */
@TableName(value = "song")
@Data
public class Song implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 歌曲名称
     */
    private String songname;

    /**
     * 歌手姓名
     */
    private String singername;

    /**
     * 平台名称
     */
    private String platformname;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 票数
     */
    private Integer votes;

    /**
     * 状态 0-未审核 1-审核通过 2-审核未通过 3-假删除
     */
    private Integer songstatus;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 是否删除
     */
    private Integer isdelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Song other = (Song) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getSongname() == null ? other.getSongname() == null : this.getSongname().equals(other.getSongname()))
                && (this.getSingername() == null ? other.getSingername() == null : this.getSingername().equals(other.getSingername()))
                && (this.getPlatformname() == null ? other.getPlatformname() == null : this.getPlatformname().equals(other.getPlatformname()))
                && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
                && (this.getVotes() == null ? other.getVotes() == null : this.getVotes().equals(other.getVotes()))
                && (this.getSongstatus() == null ? other.getSongstatus() == null : this.getSongstatus().equals(other.getSongstatus()))
                && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
                && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
                && (this.getIsdelete() == null ? other.getIsdelete() == null : this.getIsdelete().equals(other.getIsdelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSongname() == null) ? 0 : getSongname().hashCode());
        result = prime * result + ((getSingername() == null) ? 0 : getSingername().hashCode());
        result = prime * result + ((getPlatformname() == null) ? 0 : getPlatformname().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getVotes() == null) ? 0 : getVotes().hashCode());
        result = prime * result + ((getSongstatus() == null) ? 0 : getSongstatus().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getIsdelete() == null) ? 0 : getIsdelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", songname=").append(songname);
        sb.append(", singername=").append(singername);
        sb.append(", platformname=").append(platformname);
        sb.append(", remarks=").append(remarks);
        sb.append(", votes=").append(votes);
        sb.append(", songstatus=").append(songstatus);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", isdelete=").append(isdelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}