package cn.dlb.cm.entity;

import java.util.Date;

public class ContractFile {
	private Integer id;
	private String fileName;
	private String fileAddress;
	private Date uploadTime;
	private String remark;
	private User uploader;
	private ContractRecord contractRecord;
	public Integer getId() {
		return id;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileAddress() {
		return fileAddress;
	}
	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public User getUploader() {
		return uploader;
	}
	public void setUploader(User uploader) {
		this.uploader = uploader;
	}
	public ContractRecord getContractRecord() {
		return contractRecord;
	}
	public void setContractRecord(ContractRecord contractRecord) {
		this.contractRecord = contractRecord;
	}
	@Override
	public String toString() {
		return "ContractFile [id=" + id + ", fileName=" + fileName
				+ ", fileAddress=" + fileAddress + ", uploadTime=" + uploadTime
				+ ", remark=" + remark + "]";
	}
	
}