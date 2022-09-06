package com.managementboot.service.impl;

import com.managementboot.entity.GlobalStat;
import com.managementboot.mapper.BookMapper;
import com.managementboot.mapper.UserMapper;
import com.managementboot.service.StatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StatServiceImpl implements StatService {
    @Resource
    BookMapper bookMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public GlobalStat getGlobalStat() {

        return new GlobalStat(userMapper.getStudnetCount(),
                bookMapper.getBorrowCount(),
                bookMapper.getBookCount());

    }

}
