package com.alsyun.modules.system.controller;

import java.util.*;

import javax.annotation.Resource;

import com.alsyun.modules.base.service.BaseCommonService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import com.alsyun.common.api.vo.Result;
import com.alsyun.common.constant.CacheConstant;
import com.alsyun.common.constant.CommonConstant;
import com.alsyun.common.system.api.ISysBaseAPI;
import com.alsyun.common.system.util.JwtUtil;
import com.alsyun.common.system.vo.LoginUser;
import com.alsyun.common.util.RedisUtil;
import com.alsyun.common.util.oConvertUtils;
import com.alsyun.modules.system.service.ISysUserService;
import com.alsyun.modules.system.vo.SysUserOnlineVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 在线用户
 * @Author: chenli
 * @Date: 2020-06-07
 * @Version: V1.0
 */
@RestController
@RequestMapping("/sys/online")
@Slf4j
public class SysUserOnlineController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    public RedisTemplate redisTemplate;

    @Autowired
    public ISysUserService userService;

    @Autowired
    private ISysBaseAPI sysBaseAPI;

    @Resource
    private BaseCommonService baseCommonService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<Page<SysUserOnlineVO>> list(@RequestParam(name="username", required=false) String username,
                                              @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,@RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
        Collection<String> keys = redisTemplate.keys(CommonConstant.PREFIX_USER_TOKEN + "*");
        List<SysUserOnlineVO> onlineList = new ArrayList<SysUserOnlineVO>();
        for (String key : keys) {
            String token = (String)redisUtil.get(key);
            if (StringUtils.isNotEmpty(token)) {
                SysUserOnlineVO online = new SysUserOnlineVO();
                online.setToken(token);
                //TODO 改成一次性查询
                LoginUser loginUser = sysBaseAPI.getUserByName(JwtUtil.getUsername(token));
                BeanUtils.copyProperties(loginUser, online);
                onlineList.add(online);
            }
        }
        Collections.reverse(onlineList);

        Page<SysUserOnlineVO> page = new Page<SysUserOnlineVO>(pageNo, pageSize);
        int count = onlineList.size();
        List<SysUserOnlineVO> pages = new ArrayList<>();
        // 计算当前页第一条数据的下标
        int currId = pageNo > 1 ? (pageNo - 1) * pageSize : 0;
        for (int i = 0; i < pageSize && i < count - currId; i++) {
            pages.add(onlineList.get(currId + i));
        }
        page.setSize(pageSize);
        page.setCurrent(pageNo);
        page.setTotal(count);
        // 计算分页总页数
        page.setPages(count % 10 == 0 ? count / 10 : count / 10 + 1);
        page.setRecords(pages);

        Result<Page<SysUserOnlineVO>> result = new Result<Page<SysUserOnlineVO>>();
        result.setSuccess(true);
        result.setResult(page);
        return result;
    }

    /**
     * 强退用户
     */
    @RequestMapping(value = "/forceLogout",method = RequestMethod.POST)
    public Result<Object> forceLogout(@RequestBody SysUserOnlineVO online) {
        //用户退出逻辑
        if(oConvertUtils.isEmpty(online.getToken())) {
            return Result.error("退出登录失败！");
        }
        String username = JwtUtil.getUsername(online.getToken());
        LoginUser sysUser = sysBaseAPI.getUserByName(username);
        if(sysUser!=null) {
            baseCommonService.addLog("强制: "+sysUser.getRealname()+"退出成功！", CommonConstant.LOG_TYPE_1, null,sysUser);
            log.info(" 强制  "+sysUser.getRealname()+"退出成功！ ");
            //清空用户登录Token缓存
            redisUtil.del(CommonConstant.PREFIX_USER_TOKEN + online.getToken());
            //清空用户登录Shiro权限缓存
            redisUtil.del(CommonConstant.PREFIX_USER_SHIRO_CACHE + sysUser.getId());
            //清空用户的缓存信息（包括部门信息），例如sys:cache:user::<username>
            redisUtil.del(String.format("%s::%s", CacheConstant.SYS_USERS_CACHE, sysUser.getUsername()));
            //调用shiro的logout
            SecurityUtils.getSubject().logout();
            return Result.ok("退出登录成功！");
        }else {
            return Result.error("Token无效!");
        }
    }
}