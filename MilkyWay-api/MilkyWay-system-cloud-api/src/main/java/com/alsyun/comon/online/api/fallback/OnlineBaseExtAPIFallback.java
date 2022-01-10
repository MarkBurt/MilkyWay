package com.alsyun.comon.online.api.fallback;

import com.alibaba.fastjson.JSONObject;
import com.alsyun.common.system.vo.DictModel;
import com.alsyun.comon.online.api.IOnlineBaseExtAPI;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


import java.util.List;
import java.util.Map;

/**
 * 进入fallback的方法 检查是否token未设置
 * @author Markburt
 */
@Slf4j
public class OnlineBaseExtAPIFallback implements IOnlineBaseExtAPI {

    @Setter
    private Throwable cause;

    @Override
    public String cgformPostCrazyForm(String tableName, JSONObject jsonObject) {
        return null;
    }

    @Override
    public String cgformPutCrazyForm(String tableName, JSONObject jsonObject) {
        return null;
    }

    @Override
    public JSONObject cgformQueryAllDataByTableName(String tableName, String dataIds) {
        return null;
    }

    @Override
    public String cgformDeleteDataByCode(String cgformCode, String dataIds) {
        return null;
    }

    @Override
    public Map<String, Object> cgreportGetData(String code, String forceKey, String dataList) {
        return null;
    }

    @Override
    public List<DictModel> cgreportGetDataPackage(String code, String dictText, String dictCode, String dataList) {
        return null;
    }

}
