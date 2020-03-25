package com.github.binarywang.demo.wx.miniapp.controller;
import com.github.binarywang.demo.wx.miniapp.core.Result;
import com.github.binarywang.demo.wx.miniapp.core.ResultGenerator;
import com.github.binarywang.demo.wx.miniapp.model.MyParkingLot;
import com.github.binarywang.demo.wx.miniapp.model.MyWallet;
import com.github.binarywang.demo.wx.miniapp.service.MyWalletService;
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
* Created by CodeGenerator on 2020/03/24.
*/
@RestController
@RequestMapping("/mywallet")
public class MyWalletController {
    @Resource
    private MyWalletService myWalletService;

    /*@PostMapping("/add")
    public Result add(MyWallet myWallet) {
        myWalletService.save(myWallet);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        myWalletService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(MyWallet myWallet) {
        myWalletService.update(myWallet);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        MyWallet myWallet = myWalletService.findById(id);
        return ResultGenerator.genSuccessResult(myWallet);
    }*/

    @PostMapping("/myaccount")
    public Result myaccount(@RequestParam(defaultValue = "0") Integer userId,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(MyWallet.class);
        condition.createCriteria().andCondition("user_id = " + userId + "");
        List<MyWallet> list = myWalletService.findByCondition(condition);
        final double sum = list.stream().mapToDouble(p -> p.getMyBalance()).sum();
        return ResultGenerator.genSuccessResult(sum);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer userId,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(MyWallet.class);
        condition.createCriteria().andCondition("user_id = " + userId + "");
        List<MyWallet> list = myWalletService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
