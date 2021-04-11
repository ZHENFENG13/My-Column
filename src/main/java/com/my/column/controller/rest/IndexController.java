package com.my.column.controller.rest;

import com.my.column.service.ColumnService;
import com.my.column.util.Result;
import com.my.column.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    private ColumnService columnService;

    @GetMapping({"", "/", "/index", "/index.html"})
    public String indexPage() {
        return "index";
    }

    @GetMapping("/randomColumns")
    @ResponseBody
    public Result randomColumns() {
        Result result = ResultGenerator.genSuccessResult();
        result.setData(columnService.getRandomColumnsForIndex());
        return result;
    }
}
