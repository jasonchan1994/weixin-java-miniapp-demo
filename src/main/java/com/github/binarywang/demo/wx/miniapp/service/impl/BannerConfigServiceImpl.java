package com.github.binarywang.demo.wx.miniapp.service.impl;

import com.github.binarywang.demo.wx.miniapp.dao.BannerConfigMapper;
import com.github.binarywang.demo.wx.miniapp.model.BannerConfig;
import com.github.binarywang.demo.wx.miniapp.service.BannerConfigService;
import com.github.binarywang.demo.wx.miniapp.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/25.
 */
@Service
@Transactional
public class BannerConfigServiceImpl extends AbstractService<BannerConfig> implements BannerConfigService {
    @Resource
    private BannerConfigMapper bannerConfigMapper;

}
