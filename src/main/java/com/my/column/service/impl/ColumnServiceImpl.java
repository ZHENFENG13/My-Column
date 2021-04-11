package com.my.column.service.impl;

import com.my.column.common.ServiceResultEnum;
import com.my.column.dao.ColumnEntityMapper;
import com.my.column.entity.ColumnEntity;
import com.my.column.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnEntityMapper columnEntityMapper;

    @Override
    public List<ColumnEntity> getRandomColumnsForIndex() {
        //获取一定数量的专栏
        List<ColumnEntity> columns = columnEntityMapper.selectColumnsForIndex();

        List<ColumnEntity> resultColumns = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < columns.size(); i++) {
            //随机取出几条专栏数据
            int intRandom = random.nextInt(columns.size() - 1);
            if (!resultColumns.contains(columns.get(intRandom))) {
                resultColumns.add(columns.get(intRandom));
            }
            //首页展示6条专栏数据
            if (resultColumns.size() > 5) {
                break;
            }
        }
        return resultColumns;
    }

    @Override
    public ColumnEntity getColumnEntityById(Long columnId) {
        return columnEntityMapper.selectByPrimaryKey(columnId);
    }

    @Override
    public ColumnEntity getOneColumnByUserId(Long userId) {
        return columnEntityMapper.selectOneByUserId(userId);
    }

    @Override
    public String updateColumnInfo(ColumnEntity columnEntity) {
        ColumnEntity columnFromDB = columnEntityMapper.selectByPrimaryKey(columnEntity.getColumnId());
        if (columnFromDB == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        if (!columnFromDB.getUserId().equals(columnEntity.getUserId())) {
            //不是自己的专栏 不允许修改
            return ServiceResultEnum.OPERATE_ERROR.getResult();
        }
        columnEntity.setUpdateTime(new Date());
        if (columnEntityMapper.updateByPrimaryKeySelective(columnEntity) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }
}
