package com.my.column.service;

import com.my.column.entity.ArticleEntity;
import com.my.column.util.PageQueryUtil;
import com.my.column.util.PageResult;

public interface ArticleService {

    /**
     * 保存文章
     *
     * @param articleEntity
     * @return
     */
    String saveArticle(ArticleEntity articleEntity);

    /**
     * 获取详情
     *
     * @param articleId
     * @return
     */
    ArticleEntity getArticleEntityById(Long articleId);

    /**
     * 修改文章
     *
     * @param articleEntity
     * @return
     */
    String updateArticle(ArticleEntity articleEntity);

    /**
     * 文章列表
     *
     * @param pageUtil
     * @return
     */
    PageResult getArticlePageByColumnId(PageQueryUtil pageUtil);

    /**
     * 获取一个专栏下的所有文章数量
     *
     * @param columnId
     * @return
     */
    int getTotalArticleByColumnId(Long columnId);
}
