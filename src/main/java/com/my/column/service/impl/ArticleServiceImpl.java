package com.my.column.service.impl;

import com.my.column.common.ServiceResultEnum;
import com.my.column.dao.ArticleEntityMapper;
import com.my.column.dao.ColumnEntityMapper;
import com.my.column.entity.ArticleEntity;
import com.my.column.entity.ColumnEntity;
import com.my.column.service.ArticleService;
import com.my.column.util.PageQueryUtil;
import com.my.column.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ColumnEntityMapper columnEntityMapper;
    @Autowired
    private ArticleEntityMapper articleEntityMapper;

    @Override
    public String saveArticle(ArticleEntity articleEntity) {
        ColumnEntity columnEntity = columnEntityMapper.selectOneByUserId(articleEntity.getUserId());
        if (columnEntity == null) {
            return ServiceResultEnum.COLUMN_NOT_EXIST.getResult();
        }
        articleEntity.setColumnId(columnEntity.getColumnId());
        if (articleEntityMapper.insertSelective(articleEntity) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public ArticleEntity getArticleEntityById(Long articleId) {
        return articleEntityMapper.selectByPrimaryKey(articleId);
    }

    @Override
    public String updateArticle(ArticleEntity articleEntity) {
        ArticleEntity temp = articleEntityMapper.selectByPrimaryKey(articleEntity.getArticleId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        if (!articleEntity.getUserId().equals(temp.getUserId())) {
            //不是自己的文章 不允许修改
            return ServiceResultEnum.OPERATE_ERROR.getResult();
        }
        articleEntity.setColumnId(temp.getColumnId());
        articleEntity.setUpdateTime(new Date());
        if (articleEntityMapper.updateByPrimaryKeySelective(articleEntity) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public PageResult getArticlePageByColumnId(PageQueryUtil pageUtil) {
        int total = articleEntityMapper.getTotalArticles(pageUtil);
        List<ArticleEntity> articleList = articleEntityMapper.findArticleList(pageUtil);
        PageResult pageResult = new PageResult(articleList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;    }

    @Override
    public int getTotalArticleByColumnId(Long columnId) {
        return articleEntityMapper.selectTotalArticleByColumnId(columnId);
    }
}
