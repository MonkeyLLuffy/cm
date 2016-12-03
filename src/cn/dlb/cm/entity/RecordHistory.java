package cn.dlb.cm.entity;

import java.util.Date;

public class RecordHistory {
	private String id;
	private String contractName;
	private String partyA;
	private String PartyB;
	private String contractType;
	private String username;
	private String trueName;
	private boolean isAdmin;
	private Date time;
	private String operate;
	
	public RecordHistory(String id, String contractName, String partyA,
			String partyB, String contractType, String username,
			String trueName, boolean isAdmin, Date time, String operate) {
		super();
		this.id = id;
		this.contractName = contractName;
		this.partyA = partyA;
		PartyB = partyB;
		this.contractType = contractType;
		this.username = username;
		this.trueName = trueName;
		this.isAdmin = isAdmin;
		this.time = time;
		this.operate = operate;
	}
	public RecordHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getPartyA() {
		return partyA;
	}
	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}
	public String getPartyB() {
		return PartyB;
	}
	public void setPartyB(String partyB) {
		PartyB = partyB;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
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
	
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "DownloadHistory [id=" + id + ", contractName=" + contractName
				+ ", partyA=" + partyA + ", PartyB=" + PartyB
				+ ", contractType=" + contractType + ", username=" + username
				+ ", trueName=" + trueName + ", isAdmin=" + isAdmin + ", time="
				+ time + ", operate=" + operate + "]";
	}
	
}
