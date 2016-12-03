package cn.dlb.cm.service.contractRecord;

import java.util.List;
import java.util.Map;

import cn.dlb.cm.entity.ContractRecord;
import cn.dlb.cm.entity.PageBean;
import cn.dlb.cm.entity.Query_ContractRecord;

public interface ContractRecordService {

	PageBean findadvancedSearch(Query_ContractRecord contractRecord,PageBean pageBean,String importDateSort,String signingDateSort,String deadlineSort, String notUpload,String signingDateStart
			,String signingDateEnd,String importDateStart,String importDateEnd,String deadlineStart,String deadlineEnd);

	void deleteContractRecord(String id);

	public Map<String, Object> getRecycleBinData(String condition, String sort, int rows, int page);

	public PageBean getPageBean(PageBean pageBean);

	void deleteManyRecord(String[] id);

	ContractRecord getById(String id);

	void updateContractRecord(ContractRecord contractRecord);

	public Map<String, Object> monthContractCount(Integer userId);
	public Map<String, Object> yearContractCount(Integer userId);

	public Map<String, Object> getSubCompanyData(Integer userId, String year, String timeRule);

	List<ContractRecord> getManyById(String[] id);

	void add(ContractRecord contractRecord);

}
