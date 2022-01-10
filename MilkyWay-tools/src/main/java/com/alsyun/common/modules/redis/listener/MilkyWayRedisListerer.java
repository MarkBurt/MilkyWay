package com.alsyun.common.modules.redis.listener;

import com.alsyun.common.base.BaseMap;


/**
 * 自定义消息监听
 */
public interface MilkyWayRedisListerer {


    /**
     * 消息
     * @param message
     */
    void onMessage(BaseMap message);
}
