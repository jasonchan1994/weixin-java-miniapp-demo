package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.github.binarywang.demo.wx.miniapp.dao.MyParkingLotMapper;
import com.github.binarywang.demo.wx.miniapp.model.MyParkingLot;
import com.github.binarywang.demo.wx.miniapp.service.MyParkingLotService;
import com.github.binarywang.demo.wx.miniapp.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/24.
 */
@Service
@Transactional
public class MyParkingLotServiceImpl extends AbstractService<MyParkingLot> implements MyParkingLotService {
    @Resource
    private MyParkingLotMapper myParkingLotMapper;

}
