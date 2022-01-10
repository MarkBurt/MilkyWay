package com.alsyun.common.system.base.service.impl;

import com.alsyun.common.system.base.entity.MilkyWayEntity;
import com.alsyun.common.system.base.service.MilkyWayService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class MilkyWayServiceImpl <M extends BaseMapper<T>, T extends MilkyWayEntity> extends ServiceImpl<M, T> implements MilkyWayService<T> {
}
