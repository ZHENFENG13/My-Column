package com.my.column.dao;

import com.my.column.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

public interface UserEntityMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Long userId);

    UserEntity selectByLoginName(String loginName);

    UserEntity selectByLoginNameAndPasswd(@Param("loginName") String loginName, @Param("password") String password);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);
}