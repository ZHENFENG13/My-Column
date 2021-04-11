/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2021 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package com.my.column.service.impl;

import com.my.column.common.Constants;
import com.my.column.common.ServiceResultEnum;
import com.my.column.dao.ColumnEntityMapper;
import com.my.column.dao.UserEntityMapper;
import com.my.column.entity.ColumnEntity;
import com.my.column.entity.UserEntity;
import com.my.column.service.UserService;
import com.my.column.util.MD5Util;
import com.my.column.util.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityMapper userEntityMapper;
    @Autowired
    private ColumnEntityMapper columnEntityMapper;

    @Override
    @Transactional
    public String register(String loginName, String password) {
        if (userEntityMapper.selectByLoginName(loginName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        //注册用户
        UserEntity registerUser = new UserEntity();
        registerUser.setLoginName(loginName);
        //默认头像
        registerUser.setHeadImgUrl("/images/default-user-img.png");
        //默认介绍
        registerUser.setIntroduce("随心写作，自由表达");
        //行业
        registerUser.setProfession("未知");
        //居住地
        registerUser.setAddress("未知");
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        registerUser.setPasswordMd5(passwordMD5);

        //新增用户数据及用户的专栏数据
        if (userEntityMapper.insertSelective(registerUser) > 0) {
            //专栏数据
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setUserId(registerUser.getUserId());
            //专栏名称
            columnEntity.setColumnName(loginName + "的专栏");
            //默认专栏封面
            columnEntity.setColumnCover("/images/default-column-img.jpg");
            //默认专栏介绍
            columnEntity.setColumnIntroduce("这是" + loginName + "的专栏，赶快来阅读吧");

            if (columnEntityMapper.insertSelective(columnEntity) > 0) {
                return ServiceResultEnum.SUCCESS.getResult();
            }

        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String login(String loginName, String passwordMD5, HttpSession httpSession) {
        UserEntity user = userEntityMapper.selectByLoginNameAndPasswd(loginName, passwordMD5);
        if (user != null && httpSession != null) {
            httpSession.setAttribute(Constants.USER_SESSION_KEY, user);
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    @Override
    public UserEntity updateUserInfo(UserEntity userEntity, HttpSession httpSession) {
        UserEntity userTemp = (UserEntity) httpSession.getAttribute(Constants.USER_SESSION_KEY);
        UserEntity userFromDB = userEntityMapper.selectByPrimaryKey(userTemp.getUserId());
        if (userFromDB != null) {
            userFromDB.setProfession(SystemUtil.cleanString(userEntity.getProfession()));
            userFromDB.setAddress(SystemUtil.cleanString(userEntity.getAddress()));
            userFromDB.setIntroduce(SystemUtil.cleanString(userEntity.getIntroduce()));
            userFromDB.setHeadImgUrl(SystemUtil.cleanString(userEntity.getHeadImgUrl()));
            if (userEntityMapper.updateByPrimaryKeySelective(userFromDB) > 0) {
                httpSession.setAttribute(Constants.USER_SESSION_KEY, userFromDB);
                return userFromDB;
            }
        }
        return null;
    }

    @Override
    public UserEntity getUserById(Long userId) {
        return userEntityMapper.selectByPrimaryKey(userId);
    }
}
