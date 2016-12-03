package cn.dlb.cm.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Record {
	private Integer id;
	private User user;
	private ContractRecord contractRecord;
	private Date time;
	private String operate;
	private String type;
	private String remark;
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public ContractRecord getContractRecord() {
		return contractRecord;
	}
	public void setContractRecord(ContractRecord contractRecord) {
		this.contractRecord = contractRecord;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Record [id=" + id + ", time=" + time + ", operate=" + operate
				+ ", type=" + type + ", remark=" + remark + "]";
	}
	
}
