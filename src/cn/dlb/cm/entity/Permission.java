package cn.dlb.cm.entity;

import java.util.HashSet;
import java.util.Set;

public class Permission {
	private Integer id;
	private String permissionName;
	private String url;
	private Permission parent;
	private Set<Permission> children=new HashSet<>();
	private Set<Role> roles=new HashSet<>();
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Permission getParent() {
		return parent;
	}
	public void setParent(Permission parent) {
		this.parent = parent;
	}
	public Set<Permission> getChildren() {
		return children;
	}
	public void setChildren(Set<Permission> children) {
		this.children = children;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", permissionName=" + permissionName
				+ ", url=" + url + ", remark=" + remark + "]";
	}
	
}