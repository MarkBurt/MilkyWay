package com.alsyun.modules.message.service;

import java.util.List;

import com.alsyun.common.system.base.service.MilkyWayService;
import com.alsyun.modules.message.entity.SysMessageTemplate;

/**
 * @Description: 消息模板
 * @Author: jeecg-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
public interface ISysMessageTemplateService extends MilkyWayService<SysMessageTemplate> {
    List<SysMessageTemplate> selectByCode(String code);
}
