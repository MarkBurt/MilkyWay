package com.alsyun.modules.system.service.impl;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import com.alsyun.modules.system.entity.SysRole;
import com.alsyun.modules.system.entity.SysUser;
import com.alsyun.modules.system.entity.SysUserRole;
import com.alsyun.modules.system.mapper.SysUserRoleMapper;
import com.alsyun.modules.system.service.ISysRoleService;
import com.alsyun.modules.system.service.ISysUserRoleService;
import com.alsyun.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @Author scott
 * @since 2018-12-21
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

}
