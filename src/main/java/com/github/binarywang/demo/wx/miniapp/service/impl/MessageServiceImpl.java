package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.github.binarywang.demo.wx.miniapp.dao.MessageMapper;
import com.github.binarywang.demo.wx.miniapp.model.Message;
import com.github.binarywang.demo.wx.miniapp.service.MessageService;
import com.github.binarywang.demo.wx.miniapp.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/24.
 */
@Service
@Transactional
public class MessageServiceImpl extends AbstractService<Message> implements MessageService {
    @Resource
    private MessageMapper messageMapper;

}
