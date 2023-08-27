package com.yu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 宿舍楼栋
 * @TableName d_building
 */
@TableName(value ="d_building")
@Data
public class Building implements Serializable {
    /**
     * 楼栋id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 楼名字,10字以内
     */
    @TableField(value = "build_name")
    private String buildName;

    /**
     * 经度
     */
    @TableField(value = "latitude")
    private Double latitude;

    /**
     * 纬度
     */
    @TableField(value = "longitude")
    private Double longitude;

    /**
     * 一层的最大房间号
     */
    @TableField(value = "maxroom")
    private Integer maxroom;

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
        Building other = (Building) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBuildName() == null ? other.getBuildName() == null : this.getBuildName().equals(other.getBuildName()))
            && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
            && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
            && (this.getMaxroom() == null ? other.getMaxroom() == null : this.getMaxroom().equals(other.getMaxroom()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBuildName() == null) ? 0 : getBuildName().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getMaxroom() == null) ? 0 : getMaxroom().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", buildName=").append(buildName);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", maxroom=").append(maxroom);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}