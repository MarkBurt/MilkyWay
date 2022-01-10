package com.alsyun.modules.message.service.impl;

import com.alsyun.common.system.base.service.impl.MilkyWayServiceImpl;
import com.alsyun.modules.message.entity.SysMessage;
import com.alsyun.modules.message.mapper.SysMessageMapper;
import com.alsyun.modules.message.service.ISysMessageService;
import org.springframework.stereotype.Service;

/**
 * @Description: 消息
 * @Author: jeecg-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
@Service
public class SysMessageServiceImpl extends MilkyWayServiceImpl<SysMessageMapper, SysMessage> implements ISysMessageService {

}
