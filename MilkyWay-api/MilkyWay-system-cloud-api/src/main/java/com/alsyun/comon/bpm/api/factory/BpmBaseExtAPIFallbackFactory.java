package com.alsyun.comon.bpm.api.factory;

import com.alsyun.comon.bpm.api.IBpmBaseExtAPI;
import com.alsyun.comon.bpm.api.fallback.BpmBaseExtAPIFallback;
import feign.hystrix.FallbackFactory;

import org.springframework.stereotype.Component;

/**
 * @author Markburt
 */
@Component
public class BpmBaseExtAPIFallbackFactory implements FallbackFactory<IBpmBaseExtAPI> {

    @Override
    public IBpmBaseExtAPI create(Throwable throwable) {
        BpmBaseExtAPIFallback fallback = new BpmBaseExtAPIFallback();
        fallback.setCause(throwable);
        return fallback;
    }
}
