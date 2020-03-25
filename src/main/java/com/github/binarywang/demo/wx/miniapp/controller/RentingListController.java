package com.github.binarywang.demo.wx.miniapp.controller;
import com.github.binarywang.demo.wx.miniapp.core.Result;
import com.github.binarywang.demo.wx.miniapp.core.ResultGenerator;
import com.github.binarywang.demo.wx.miniapp.model.Message;
import com.github.binarywang.demo.wx.miniapp.model.MyParkingLot;
import com.github.binarywang.demo.wx.miniapp.model.MyWallet;
import com.github.binarywang.demo.wx.miniapp.model.RentingList;
import com.github.binarywang.demo.wx.miniapp.service.MessageService;
import com.github.binarywang.demo.wx.miniapp.service.MyParkingLotService;
import com.github.binarywang.demo.wx.miniapp.service.MyWalletService;
import com.github.binarywang.demo.wx.miniapp.service.RentingListService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* Created by CodeGenerator on 2020/03/25.
*/
@RestController
@RequestMapping("/rentinglist")
public class RentingListController {
    @Resource
    private RentingListService rentingListService;

    @Resource
    private MyParkingLotService myParkingLotService;

    @Resource
    private MyWalletService myWalletService;

    @Resource
    private MessageService messageService;


    @PostMapping("/add")
    public Result add(RentingList rentingList) {
        rentingListService.save(rentingList);
        MyParkingLot byId = myParkingLotService.findById(rentingList.getRefParkingLot());
        if(byId != null){
            byId.setStatus(1);
            myParkingLotService.update(byId);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        final RentingList byId = rentingListService.findById(id);
        if(byId != null){
            byId.getRefParkingLot();
        }
        rentingListService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(RentingList rentingList) {
        rentingListService.update(rentingList);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        RentingList rentingList = rentingListService.findById(id);
        return ResultGenerator.genSuccessResult(rentingList);
    }

    @PostMapping("/rent")
    @Transactional
    public Result rent(@RequestParam(defaultValue = "0") Integer avalibleId,@RequestParam(defaultValue = "0") Integer userId,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        RentingList byCondition = rentingListService.findById(avalibleId);
        if(byCondition != null){
            byCondition.setTenantId(userId);
            byCondition.setRentingStatus(2);
            //出租记录修改
            rentingListService.update(byCondition);
            MyParkingLot parkingLot = myParkingLotService.findById(byCondition.getRefParkingLot());
            if(parkingLot != null){
                //车位状态修改
                parkingLot.setStatus(2);
                myParkingLotService.update(parkingLot);
                //增减积分
                MyWallet wallet = new MyWallet();
                wallet.setCreateTime(new Date());
                wallet.setMyBalance(byCondition.getCosts());
                wallet.setRamark("租金收入");
                wallet.setUserId(parkingLot.getUserId());
                myWalletService.save(wallet);
                wallet.setMyBalance(0-byCondition.getCosts());
                wallet.setUserId(userId);
                wallet.setRamark("租金支出");
                wallet.setId(null);
                myWalletService.save(wallet);
                //发送消息
                Message message = new Message();
                message.setContext("您的车位已成功租出，请查看！");
                message.setCreateTime(new Date());
                message.setToUserId(parkingLot.getUserId());
                messageService.save(message);
            }

        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/unrent")
    @Transactional
    public Result unrent(@RequestParam(defaultValue = "0") Integer avalibleId,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        RentingList byCondition = rentingListService.findById(avalibleId);
        if(byCondition != null){
            byCondition.setRentingStatus(0);
            //出租记录修改
            rentingListService.update(byCondition);
            MyParkingLot parkingLot = myParkingLotService.findById(byCondition.getRefParkingLot());
            if(parkingLot != null){
                //车位状态修改
                parkingLot.setStatus(0);
                myParkingLotService.update(parkingLot);
                //发送消息
                Message message = new Message();
                message.setContext("您的车位已被归还，请查看！");
                message.setCreateTime(new Date());
                message.setToUserId(parkingLot.getUserId());
                messageService.save(message);
            }

        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/myrent")
    public Result myrent(@RequestParam(defaultValue = "0") Integer userId,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(RentingList.class);
        condition.createCriteria().andCondition("tenant_id = " + userId + "");
        List<RentingList> list = rentingListService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/mypublishs")
    public Result mypublishs(@RequestParam(defaultValue = "0") Integer userId,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(MyParkingLot.class);
        condition.createCriteria().andCondition("user_id = " + userId + "");
        List<MyParkingLot> list = myParkingLotService.findByCondition(condition);
        Condition condition2 = new Condition(RentingList.class);
        final String collect = list.stream().map(e -> e.getId().toString()).distinct().collect(Collectors.joining(","));
        condition2.createCriteria().andCondition("ref_parking_lot in (" + collect + ")");
        final List<RentingList> byCondition = rentingListService.findByCondition(condition2);
        PageInfo pageInfo = new PageInfo(byCondition);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/getavalibleparkinglots")
    public Result getavalibleparkinglots(@RequestParam(defaultValue = "0") Integer userId,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition2 = new Condition(RentingList.class);
        condition2.createCriteria().andCondition("renting_status = " + 1 + "");
        final List<RentingList> byCondition = rentingListService.findByCondition(condition2);
        PageInfo pageInfo = new PageInfo(byCondition);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<RentingList> list = rentingListService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
