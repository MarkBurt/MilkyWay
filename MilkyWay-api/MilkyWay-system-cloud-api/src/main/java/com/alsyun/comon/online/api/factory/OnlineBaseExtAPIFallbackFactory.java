package com.alsyun.comon.online.api.factory;

import com.alsyun.comon.online.api.IOnlineBaseExtAPI;
import com.alsyun.comon.online.api.fallback.OnlineBaseExtAPIFallback;
import feign.hystrix.FallbackFactory;

import org.springframework.stereotype.Component;

/**
 * @author Markburt
 */
@Component
public class OnlineBaseExtAPIFallbackFactory implements FallbackFactory<IOnlineBaseExtAPI> {

    @Override
    public IOnlineBaseExtAPI create(Throwable throwable) {
        OnlineBaseExtAPIFallback fallback = new OnlineBaseExtAPIFallback();
        fallback.setCause(throwable);
        return fallback;
    }
}
