package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.github.binarywang.demo.wx.miniapp.dao.MyWalletMapper;
import com.github.binarywang.demo.wx.miniapp.model.MyWallet;
import com.github.binarywang.demo.wx.miniapp.service.MyWalletService;
import com.github.binarywang.demo.wx.miniapp.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/24.
 */
@Service
@Transactional
public class MyWalletServiceImpl extends AbstractService<MyWallet> implements MyWalletService {
    @Resource
    private MyWalletMapper myWalletMapper;

}
