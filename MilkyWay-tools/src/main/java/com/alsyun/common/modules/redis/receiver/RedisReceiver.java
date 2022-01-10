package com.alsyun.common.modules.redis.receiver;


import cn.hutool.core.util.ObjectUtil;
import com.alsyun.common.base.BaseMap;
import com.alsyun.common.constant.GlobalConstants;

import com.alsyun.common.modules.redis.listener.MilkyWayRedisListerer;
import com.alsyun.common.util.SpringContextHolder;
import lombok.Data;
import org.springframework.stereotype.Component;


/**
 * @author Markburt
 */
@Component
@Data
public class RedisReceiver {


    /**
     * 接受消息并调用业务逻辑处理器
     *
     * @param params
     */
    public void onMessage(BaseMap params) {
        Object handlerName = params.get(GlobalConstants.HANDLER_NAME);
        MilkyWayRedisListerer messageListener = SpringContextHolder.getHandler(handlerName.toString(), MilkyWayRedisListerer.class);
        if (ObjectUtil.isNotEmpty(messageListener)) {
            messageListener.onMessage(params);
        }
    }

}
