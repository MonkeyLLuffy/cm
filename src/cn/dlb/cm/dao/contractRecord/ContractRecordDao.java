package cn.dlb.cm.dao.contractRecord;

import java.util.Map;

import cn.dlb.cm.base.dao.BaseDao;
import cn.dlb.cm.entity.ContractRecord;
import cn.dlb.cm.entity.PageBean;
import cn.dlb.cm.entity.Query_ContractRecord;

public interface ContractRecordDao extends BaseDao<ContractRecord> {

	public PageBean findadvancedSearch(Query_ContractRecord contractRecord,
			PageBean pageBean, java.sql.Date signingDateStartSQL,
			java.sql.Date signingDateEndSQL, java.sql.Date importDateStartSQL,
			java.sql.Date importDateEndSQL, java.sql.Date deadlineStartSQL,
			java.sql.Date deadlineEndSQL,String importDateSort,String signingDateSort
			,String deadlineSort, String notUpload);

	public void delete(String id);

	public Map<String, Object> getRecycleBinData(String condition, int rows,
			int page);

	public void deleteManyRecord(String[] id);

	public Map<String, Object> monthContractCount(int year);

	public Map<String, Object> yearContractCount(int year);

	public Map<String, Object> getSubCompanyData(Integer userId,String time, String timeRule);
}
