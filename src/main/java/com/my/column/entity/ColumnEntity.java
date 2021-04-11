package com.my.column.entity;

import java.util.Date;

public class ColumnEntity {
    private Long columnId;

    private Long userId;

    private String columnName;

    private String columnCover;

    private String columnIntroduce;

    private Date createTime;

    private Date updateTime;

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getColumnCover() {
        return columnCover;
    }

    public void setColumnCover(String columnCover) {
        this.columnCover = columnCover == null ? null : columnCover.trim();
    }

    public String getColumnIntroduce() {
        return columnIntroduce;
    }

    public void setColumnIntroduce(String columnIntroduce) {
        this.columnIntroduce = columnIntroduce == null ? null : columnIntroduce.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", columnId=").append(columnId);
        sb.append(", userId=").append(userId);
        sb.append(", columnName=").append(columnName);
        sb.append(", columnCover=").append(columnCover);
        sb.append(", columnIntroduce=").append(columnIntroduce);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}