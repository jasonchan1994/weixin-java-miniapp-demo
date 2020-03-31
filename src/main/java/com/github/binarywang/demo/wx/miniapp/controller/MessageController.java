package com.github.binarywang.demo.wx.miniapp.controller;
import com.github.binarywang.demo.wx.miniapp.core.Result;
import com.github.binarywang.demo.wx.miniapp.core.ResultGenerator;
import com.github.binarywang.demo.wx.miniapp.model.Message;
import com.github.binarywang.demo.wx.miniapp.model.MyParkingLot;
import com.github.binarywang.demo.wx.miniapp.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/03/25.
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

    @PostMapping("/read")
    public Result read(@RequestParam(defaultValue = "0") Integer id,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(Message.class);
        condition.createCriteria().andCondition("to_user_id = " + id + "");
        final List<Message> byCondition = messageService.findByCondition(condition);
        if(byCondition != null && byCondition.size() > 0){
            for (Message message:byCondition){
                message.setReaded(1);
                messageService.update(message);
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/unreaded")
    public Result unreaded(@RequestParam(defaultValue = "0") Integer userId,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(Message.class);
        condition.createCriteria().andCondition("to_user_id = " + userId + "").andCondition("readed = 0");
        List<Message> list = messageService.findByCondition(condition);
        return ResultGenerator.genSuccessResult(list.size());
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer userId,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(Message.class);
        condition.createCriteria().andCondition("to_user_id = " + userId + "");
        List<Message> list = messageService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
