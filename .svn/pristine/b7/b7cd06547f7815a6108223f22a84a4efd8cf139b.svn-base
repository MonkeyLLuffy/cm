package cn.dlb.cm.service.recordHistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlb.cm.dao.recordHistory.RecordHistoryDao;
import cn.dlb.cm.entity.PageBean;
import cn.dlb.cm.service.contractRecord.ContractRecordService;

@Service
public class RecordHistoryServiceImpl implements RecordHistoryService{
	
	@Autowired
	private RecordHistoryDao recordHistoryDao;
	@Autowired
	private ContractRecordService contracRecordService;
	
	@Override
	public PageBean getDownloadHistory(PageBean bean, String id,
			String downUser, String contractName, String userType,String type) {
		PageBean newBean=contracRecordService.getPageBean(bean);
		PageBean pageBean=recordHistoryDao.getDownloadHistory(newBean, id,
				downUser, contractName,  userType,type);
		return pageBean;
	}
	
	
}
