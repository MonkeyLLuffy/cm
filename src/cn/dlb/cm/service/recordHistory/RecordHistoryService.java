package cn.dlb.cm.service.recordHistory;

import cn.dlb.cm.entity.PageBean;

public interface RecordHistoryService {

	PageBean getDownloadHistory(PageBean bean, String id, String downUser,
			String contractName, String userType,String type);

}