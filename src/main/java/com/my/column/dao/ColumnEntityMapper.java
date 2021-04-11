package com.my.column.dao;

import com.my.column.entity.ColumnEntity;

import java.util.List;

public interface ColumnEntityMapper {
    int deleteByPrimaryKey(Long columnId);

    int insert(ColumnEntity record);

    int insertSelective(ColumnEntity record);

    List<ColumnEntity> selectColumnsForIndex();

    ColumnEntity selectByPrimaryKey(Long columnId);

    ColumnEntity selectOneByUserId(Long userId);

    List<ColumnEntity> selectByPrimaryKeys(List<Long> columnIds);

    int updateByPrimaryKeySelective(ColumnEntity record);

    int updateByPrimaryKey(ColumnEntity record);
}