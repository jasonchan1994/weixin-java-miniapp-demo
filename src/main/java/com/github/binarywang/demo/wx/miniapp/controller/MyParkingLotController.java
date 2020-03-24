package com.github.binarywang.demo.wx.miniapp.controller;
import com.github.binarywang.demo.wx.miniapp.core.Result;
import com.github.binarywang.demo.wx.miniapp.core.ResultGenerator;
import com.github.binarywang.demo.wx.miniapp.model.MyParkingLot;
import com.github.binarywang.demo.wx.miniapp.service.MyParkingLotService;
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
@RequestMapping("/myparkinglot")
public class MyParkingLotController {
    @Resource
    private MyParkingLotService myParkingLotService;

    @PostMapping("/add")
    public Result add(MyParkingLot myParkingLot) {
        myParkingLotService.save(myParkingLot);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        myParkingLotService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(MyParkingLot myParkingLot) {
        myParkingLotService.update(myParkingLot);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        MyParkingLot myParkingLot = myParkingLotService.findById(id);
        return ResultGenerator.genSuccessResult(myParkingLot);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<MyParkingLot> list = myParkingLotService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
