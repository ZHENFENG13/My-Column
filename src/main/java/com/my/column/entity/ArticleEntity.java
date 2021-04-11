package com.my.column.entity;

import java.util.Date;

public class ArticleEntity {
    private Long articleId;

    private Long columnId;

    private Long userId;

    private String articleTitle;

    private String articleCoverImage;

    private Date createTime;

    private Date updateTime;

    private String articleContent;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

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

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleCoverImage() {
        return articleCoverImage;
    }

    public void setArticleCoverImage(String articleCoverImage) {
        this.articleCoverImage = articleCoverImage == null ? null : articleCoverImage.trim();
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

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", columnId=").append(columnId);
        sb.append(", userId=").append(userId);
        sb.append(", articleTitle=").append(articleTitle);
        sb.append(", articleCoverImage=").append(articleCoverImage);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", articleContent=").append(articleContent);
        sb.append("]");
        return sb.toString();
    }
}