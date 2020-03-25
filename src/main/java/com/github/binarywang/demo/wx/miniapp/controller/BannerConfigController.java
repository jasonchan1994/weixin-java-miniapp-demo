package com.github.binarywang.demo.wx.miniapp.controller;
import com.github.binarywang.demo.wx.miniapp.core.Result;
import com.github.binarywang.demo.wx.miniapp.core.ResultGenerator;
import com.github.binarywang.demo.wx.miniapp.model.BannerConfig;
import com.github.binarywang.demo.wx.miniapp.service.BannerConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/03/25.
*/
@RestController
@RequestMapping("/bannerconfig")
public class BannerConfigController {
    @Resource
    private BannerConfigService bannerConfigService;

    @PostMapping("/add")
    public Result add(BannerConfig bannerConfig) {
        bannerConfigService.save(bannerConfig);
        return ResultGenerator.genSuccessResult();
    }


    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BannerConfig> list = bannerConfigService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
