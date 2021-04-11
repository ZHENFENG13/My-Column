package com.my.column.dao;

import com.my.column.entity.ArticleEntity;
import com.my.column.util.PageQueryUtil;

import java.util.List;

public interface ArticleEntityMapper {
    int deleteByPrimaryKey(Long articleId);

    int insert(ArticleEntity record);

    int insertSelective(ArticleEntity record);

    ArticleEntity selectByPrimaryKey(Long articleId);

    int updateByPrimaryKeySelective(ArticleEntity record);

    int updateByPrimaryKeyWithBLOBs(ArticleEntity record);

    int updateByPrimaryKey(ArticleEntity record);

    int getTotalArticles(PageQueryUtil pageUtil);

    List<ArticleEntity> findArticleList(PageQueryUtil pageUtil);

    int selectTotalArticleByColumnId(Long columnId);
}