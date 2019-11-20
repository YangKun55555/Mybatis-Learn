package com.young.model;

/**
 * @author YangKun
 * @date 2019/11/19 2:57 PM
 */
public class SysRolePrivilege {
	private Long roleId;
	private Long privilegeId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Long privilegeId) {
		this.privilegeId = privilegeId;
	}
}
