package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.github.binarywang.demo.wx.miniapp.dao.RentingListMapper;
import com.github.binarywang.demo.wx.miniapp.model.RentingList;
import com.github.binarywang.demo.wx.miniapp.service.RentingListService;
import com.github.binarywang.demo.wx.miniapp.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/25.
 */
@Service
@Transactional
public class RentingListServiceImpl extends AbstractService<RentingList> implements RentingListService {
    @Resource
    private RentingListMapper rentingListMapper;

}
