package cn.dlb.cm.dao.recordHistory;

import cn.dlb.cm.base.dao.BaseDao;
import cn.dlb.cm.entity.PageBean;
import cn.dlb.cm.entity.Record;

public interface RecordHistoryDao extends BaseDao<Record>{

	PageBean getDownloadHistory(PageBean bean, String id, String downUser,
			String contractName, String userType,String type);

}
