/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2021 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package com.my.column.service;

import com.my.column.entity.UserEntity;

import javax.servlet.http.HttpSession;

public interface UserService {

    /**
     * 用户注册
     *
     * @param loginName
     * @param password
     * @return
     */
    String register(String loginName, String password);

    /**
     * 登录
     *
     * @param loginName
     * @param passwordMD5
     * @param httpSession
     * @return
     */
    String login(String loginName, String passwordMD5, HttpSession httpSession);

    /**
     * 修改用户信息
     *
     * @param userEntity
     * @param httpSession
     * @return
     */
    UserEntity updateUserInfo(UserEntity userEntity, HttpSession httpSession);

    /**
     * 获取用户详情
     *
     * @param userId
     * @return
     */
    UserEntity getUserById(Long userId);
}
