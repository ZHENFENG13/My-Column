package com.my.column.service;

import com.my.column.entity.ColumnEntity;

import java.util.List;

public interface ColumnService {

    /**
     * 获取首页专栏数据列表
     *
     * @return
     */
    List<ColumnEntity> getRandomColumnsForIndex();

    /**
     * 获取专栏详情
     *
     * @param columnId
     * @return
     */
    ColumnEntity getColumnEntityById(Long columnId);

    /**
     * 获取专栏
     *
     * @param userId
     * @return
     */
    ColumnEntity getOneColumnByUserId(Long userId);

    /**
     * 修改专栏信息
     *
     * @param columnEntity
     * @return
     */
    String updateColumnInfo(ColumnEntity columnEntity);
}
