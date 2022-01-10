package com.alsyun.comon.system.api.factory;

import com.alsyun.comon.system.api.ISysBaseAPI;
import com.alsyun.comon.system.api.fallback.SysBaseAPIFallback;
import feign.hystrix.FallbackFactory;

import org.springframework.stereotype.Component;

/**
 * @author Markburt
 */
@Component
public class SysBaseAPIFallbackFactory implements FallbackFactory<ISysBaseAPI> {

    @Override
    public ISysBaseAPI create(Throwable throwable) {
        SysBaseAPIFallback fallback = new SysBaseAPIFallback();
        fallback.setCause(throwable);
        return fallback;
    }
}
