package cn.dlb.cm.entity;

public class SystemCode {
	private Integer id;
	private String codeName;
	private String codeType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	@Override
	public String toString() {
		return "SystemCode [id=" + id + ", codeName=" + codeName
				+ ", codeType=" + codeType + "]";
	}
	
}
