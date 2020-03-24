package com.github.binarywang.demo.wx.miniapp.controller;
import com.github.binarywang.demo.wx.miniapp.core.Result;
import com.github.binarywang.demo.wx.miniapp.core.ResultGenerator;
import com.github.binarywang.demo.wx.miniapp.model.Message;
import com.github.binarywang.demo.wx.miniapp.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/03/24.
*/
@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    private MessageService messageService;

    @PostMapping("/add")
    public Result add(Message message) {
        messageService.save(message);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        messageService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Message message) {
        messageService.update(message);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Message message = messageService.findById(id);
        return ResultGenerator.genSuccessResult(message);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Message> list = messageService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
