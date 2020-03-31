package com.github.binarywang.demo.wx.miniapp.config;

import com.github.binarywang.demo.wx.miniapp.model.Message;
import com.github.binarywang.demo.wx.miniapp.model.MyParkingLot;
import com.github.binarywang.demo.wx.miniapp.model.RentingList;
import com.github.binarywang.demo.wx.miniapp.service.MessageService;
import com.github.binarywang.demo.wx.miniapp.service.MyParkingLotService;
import com.github.binarywang.demo.wx.miniapp.service.MyWalletService;
import com.github.binarywang.demo.wx.miniapp.service.RentingListService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Jason
 * @title: SaticScheduleTask
 * @description: 自动归还车位定时任务
 * @date 2020/3/30  18:58
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {

    @Resource
    private RentingListService rentingListService;

    @Resource
    private MyParkingLotService myParkingLotService;

    @Resource
    private MyWalletService myWalletService;

    @Resource
    private MessageService messageService;
    //3.添加定时任务
    @Scheduled(fixedRate=60000)
    private void configureTasks() {
        Date date = new Date();
        List<RentingList> byCondition = rentingListService.findAll();
        if(byCondition != null && byCondition.size() > 0){
            for (RentingList rent:byCondition){
                if(rent.getRentingStatus() == 2 && date.before(rent.getEndTime())){
                    rent.setRentingStatus(0);
                    //出租记录修改
                    rentingListService.update(rent);
                    MyParkingLot parkingLot = myParkingLotService.findById(rent.getRefParkingLot());
                    if(parkingLot != null){
                        //车位状态修改
                        parkingLot.setStatus(0);
                        myParkingLotService.update(parkingLot);
                        //发送消息
                        Message message = new Message();
                        message.setRefParkingLot(parkingLot.getId());
                        message.setRefRentId(rent.getId());
                        message.setContext("您的车位已被归还，请查看！");
                        message.setCreateTime(new Date());
                        message.setToUserId(parkingLot.getUserId());
                        messageService.save(message);

                        Message message1 = new Message();
                        message1.setRefParkingLot(parkingLot.getId());
                        message1.setRefParkingLot(rent.getId());
                        message1.setContext("您的车位已到期自动归还，请查看！");
                        message1.setCreateTime(new Date());
                        message1.setToUserId(rent.getTenantId());
                        messageService.save(message1);
                    }
                }
            }
        }
    }
}
