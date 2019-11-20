package com.young.mapper;

import com.young.model.SysRole;
import com.young.model.SysUser;

import java.util.List;

/**
 * @author YangKun
 * @date 2019/11/19 6:59 PM
 */
public interface UserMapper {
	SysUser selectById(Long id);

	List<SysUser> selectAll();

	List<SysRole> selectRolesByUserId(Long userId);
}
