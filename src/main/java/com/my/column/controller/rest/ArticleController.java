package com.my.column.controller.rest;

import com.my.column.common.Constants;
import com.my.column.common.ServiceResultEnum;
import com.my.column.entity.ArticleEntity;
import com.my.column.entity.ColumnEntity;
import com.my.column.entity.UserEntity;
import com.my.column.service.ArticleService;
import com.my.column.service.ColumnService;
import com.my.column.service.UserService;
import com.my.column.util.Result;
import com.my.column.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ArticleController {

    @Resource
    private ArticleService articleService;
    @Resource
    private ColumnService columnService;
    @Resource
    private UserService userService;

    @GetMapping("/articleEdit")
    public String articleEditPage() {
        return "article-edit";
    }

    @GetMapping("/articleEdit/{articleId}")
    public String articleEditPageById(HttpServletRequest request, @PathVariable("articleId") Long articleId, HttpSession httpSession) {
        ArticleEntity articleEntityById = articleService.getArticleEntityById(articleId);
        if (articleEntityById == null) {
            return "error/error_404";
        }
        UserEntity loginUser = (UserEntity) httpSession.getAttribute(Constants.USER_SESSION_KEY);
        if (!loginUser.getUserId().equals(articleEntityById.getUserId())) {
            //如果不是自己的文章 不允许编辑
            return "error/error_5xx";
        }
        request.setAttribute("article", articleEntityById);
        return "article-edit";
    }

    @GetMapping("/article/{articleId}")
    public String articleDetailPage(HttpServletRequest request, @PathVariable("articleId") Long articleId) {
        //文章信息
        ArticleEntity articleEntityById = articleService.getArticleEntityById(articleId);
        if (articleEntityById == null) {
            return "error/error_404";
        }
        //文章作者信息
        UserEntity articleAuthor = userService.getUserById(articleEntityById.getUserId());
        if (articleAuthor == null) {
            return "error/error_404";
        }
        //专栏信息
        ColumnEntity columnEntityById = columnService.getColumnEntityById(articleEntityById.getColumnId());
        if (columnEntityById == null) {
            return "error/error_404";
        }
        request.setAttribute("article", articleEntityById);
        request.setAttribute("articleAuthor", articleAuthor);
        request.setAttribute("column", columnEntityById);
        return "article-detail";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/article/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody ArticleEntity articleEntity, HttpSession httpSession) {
        if (!StringUtils.hasLength(articleEntity.getArticleContent())
                || !StringUtils.hasLength(articleEntity.getArticleCoverImage())
                || !StringUtils.hasLength(articleEntity.getArticleTitle())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        UserEntity loginUser = (UserEntity) httpSession.getAttribute(Constants.USER_SESSION_KEY);
        articleEntity.setUserId(loginUser.getUserId());
        String result = articleService.saveArticle(articleEntity);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/article/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody ArticleEntity updateArticleEntity, HttpSession httpSession) {
        if (ObjectUtils.isEmpty(updateArticleEntity.getArticleId())
                || !StringUtils.hasLength(updateArticleEntity.getArticleContent())
                || !StringUtils.hasLength(updateArticleEntity.getArticleCoverImage())
                || !StringUtils.hasLength(updateArticleEntity.getArticleTitle())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        UserEntity loginUser = (UserEntity) httpSession.getAttribute(Constants.USER_SESSION_KEY);
        updateArticleEntity.setUserId(loginUser.getUserId());
        String result = articleService.updateArticle(updateArticleEntity);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

}
