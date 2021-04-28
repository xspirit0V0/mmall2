package com.xspirit.mmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xspirit.mmall.entity.User;
import com.xspirit.mmall.mapper.UserMapper;
import com.xspirit.mmall.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
