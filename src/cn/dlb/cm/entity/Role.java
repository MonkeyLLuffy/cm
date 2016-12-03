package cn.dlb.cm.entity;


import java.util.HashSet;
import java.util.Set;

public class Role {
	private Integer id;
	private String roleName;
	private Set<User> users=new HashSet<>();
	private String remark;
	private Set<Permission> permission=new HashSet<>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Set<Permission> getPermission() {
		return permission;
	}
	public void setPermission(Set<Permission> permission) {
		this.permission = permission;
	}
	/*
	 * 方便之后做比较，只要ID相同则认为是同一个角色
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(((Role)obj).getId().equals(this.id)){
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", remark="
				+ remark + "]";
	}
	
}
