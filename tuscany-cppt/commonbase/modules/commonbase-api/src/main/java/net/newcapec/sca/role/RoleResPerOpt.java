package net.newcapec.sca.role;

/**
 * 角色资源权限操作
 *
 * @author huangxin
 *
 */
public class RoleResPerOpt {
	private Integer perId;
	private String perName;
	private Integer resId;
	private String resName;
	private Boolean optFlag;

	public Integer getPerId() {
		return perId;
	}

	public void setPerId(Integer perId) {
		this.perId = perId;
	}

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public Boolean getOptFlag() {
		return optFlag;
	}

	public void setOptFlag(Boolean optFlag) {
		this.optFlag = optFlag;
	}

}
