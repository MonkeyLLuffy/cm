package cn.dlb.cm.entity;

import java.util.HashSet;
import java.util.Set;

public class Department {
	private Integer id;
	private String departmentName;
	private Set<User> users =new HashSet<>();
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName
				+ ", remark=" + remark + "]";
	}
	
}