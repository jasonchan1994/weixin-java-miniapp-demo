package com.github.binarywang.demo.wx.miniapp.dao;

import com.github.binarywang.demo.wx.miniapp.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jason
 * @title: UserDao
 * @description: TODO
 * @date 2020/3/5  18:54
 */
@Mapper
public interface UserDao {
    User getUserById(Integer id);
}
