package com.my.column.controller.rest;

import com.my.column.common.Constants;
import com.my.column.common.ServiceResultEnum;
import com.my.column.entity.ColumnEntity;
import com.my.column.entity.UserEntity;
import com.my.column.service.ArticleService;
import com.my.column.service.ColumnService;
import com.my.column.service.UserService;
import com.my.column.util.PageQueryUtil;
import com.my.column.util.Result;
import com.my.column.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ColumnController {

    @Resource
    private ColumnService columnService;
    @Resource
    private UserService userService;
    @Resource
    private ArticleService articleService;

    @PostMapping("/updateColumnInfo")
    @ResponseBody
    public Result updateColumnInfo(@RequestBody ColumnEntity columnEntity, HttpSession httpSession) {
        UserEntity loginUser = (UserEntity) httpSession.getAttribute(Constants.USER_SESSION_KEY);
        columnEntity.setUserId(loginUser.getUserId());
        String result = columnService.updateColumnInfo(columnEntity);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    @GetMapping("/columnPage")
    public String columnPage(HttpServletRequest request, @RequestParam Map<String, Object> params) {
        if (ObjectUtils.isEmpty(params.get("columnId"))) {
            //参数异常
            return "error/error_5xx";
        }
        if (ObjectUtils.isEmpty(params.get("page"))) {
            params.put("page", 1);
        }
        Long columnId = Long.valueOf(params.get("columnId") + "");
        //专栏信息
        ColumnEntity columnInfo = columnService.getColumnEntityById(columnId);
        if (columnInfo == null) {
            return "error/error_404";
        }
        //专栏作者信息
        UserEntity columnAuthor = userService.getUserById(columnInfo.getUserId());
        if (columnAuthor == null) {
            return "error/error_404";
        }
        params.put("limit", Constants.Column_PAGE_LIMIT);

        //封装数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        request.setAttribute("articlePageResult", articleService.getArticlePageByColumnId(pageUtil));
        request.setAttribute("columnAuthor", columnAuthor);
        request.setAttribute("column", columnInfo);
        request.setAttribute("columnArticlesCount", articleService.getTotalArticleByColumnId(columnId));
        return "column-page";
    }


    @GetMapping("/myColumnPage")
    public String myColumnPage(HttpServletRequest request, HttpSession httpSession) {
        UserEntity loginUser = (UserEntity) httpSession.getAttribute(Constants.USER_SESSION_KEY);
        //专栏信息
        ColumnEntity columnInfo = columnService.getOneColumnByUserId(loginUser.getUserId());
        if (columnInfo == null) {
            return "error/error_404";
        }
        //专栏作者信息
        UserEntity columnAuthor = userService.getUserById(columnInfo.getUserId());
        if (columnAuthor == null) {
            return "error/error_404";
        }
        Map<String, Object> params = new HashMap<>();
        params.put("page", 1);//默认第一页
        params.put("columnId", columnInfo.getColumnId());
        params.put("limit", Constants.Column_PAGE_LIMIT);

        //封装数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);

        request.setAttribute("articlePageResult", articleService.getArticlePageByColumnId(pageUtil));
        request.setAttribute("columnAuthor", columnAuthor);
        request.setAttribute("column", columnInfo);
        request.setAttribute("columnArticlesCount", articleService.getTotalArticleByColumnId(columnInfo.getColumnId()));
        return "column-page";
    }
}
