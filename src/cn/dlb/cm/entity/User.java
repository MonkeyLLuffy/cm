package cn.dlb.cm.entity;

import java.util.HashSet;
import java.util.Set;

public class User{
	private Integer id;
	private String username;
	private String trueName;
	private String password;
	private Department department;
	private boolean isAdmin;
	private String email;
	private String telephone;
	private String remark;
	private Set<Role> roles=new HashSet<>();
	private Set<ContractRecord> contractRecords=new HashSet<>();
	private Set<ContractFile> contractFiles=new HashSet<>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<ContractRecord> getContractRecords() {
		return contractRecords;
	}
	public void setContractRecords(Set<ContractRecord> contractRecords) {
		this.contractRecords = contractRecords;
	}
	public Set<ContractFile> getContractFiles() {
		return contractFiles;
	}
	public void setContractFiles(Set<ContractFile> contractFiles) {
		this.contractFiles = contractFiles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", trueName="
				+ trueName + ", password=" + password + ", isAdmin=" + isAdmin
				+ ", email=" + email + ", telephone=" + telephone + ", remark="
				+ remark + "]";
	}
	
	
}
