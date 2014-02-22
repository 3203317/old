package net.newcapec.sca.role;

/**
 * 角色UI菜单权限
 *
 * @author huangxin
 *
 */
public class RoleUIPermission {

	private Integer roleId;
	private Integer resourceId;
	private String resourceName;
	private Integer resourcePId;
	private String uiPermission;
	private String type = "roleUiPer";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Integer getResourcePId() {
		return resourcePId;
	}

	public void setResourcePId(Integer resourcePId) {
		this.resourcePId = resourcePId;
	}

	public String getUiPermission() {
		return uiPermission;
	}

	public void setUiPermission(String uiPermission) {
		this.uiPermission = uiPermission;
	}
}
