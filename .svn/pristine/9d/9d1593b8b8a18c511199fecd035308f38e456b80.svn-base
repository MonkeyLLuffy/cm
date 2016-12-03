package cn.dlb.cm.dao.recordHistory;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.dlb.cm.base.dao.BaseDaoImpl;
import cn.dlb.cm.entity.PageBean;
import cn.dlb.cm.entity.Record;
import cn.dlb.cm.entity.RecordHistory;

@Repository
public class RecordHistoryDaoImpl extends BaseDaoImpl<Record> implements RecordHistoryDao {

	//查询得到各种历史记录
	public PageBean getDownloadHistory(PageBean bean, String id,
			String downUser, String contractName, String userType,String type) {

		StringBuilder HQL = new StringBuilder(
				"SELECT new cn.dlb.cm.entity.RecordHistory(c.id ,c.contractName,c.partyA"+
				",c.partyB,c.contractType,u.username"+
				",u.trueName,u.isAdmin,r.time,r.operate) "+
				"FROM ContractRecord AS c,User AS u,Record AS r "+
				"WHERE c.id=r.contractRecord AND u.id=r.user AND r.type='"+type+"'");
		
		StringBuilder count = new StringBuilder("SELECT count(*) from ContractRecord AS c,User AS u,Record AS r " +
				" WHERE c.id=r.contractRecord AND u.id=r.user AND r.type='"+type+"'");
		
		if(id!=null&&!"".equals(id)){
			HQL.append(" AND c.id LIKE '%"+id+"%'");
			count.append(" AND c.id LIKE '%"+id+"%'");
		}
		if(downUser!=null&&!"".equals(downUser)){
			HQL.append(" AND u.trueName LIKE '%"+downUser+"%'");
			count.append(" AND u.trueName LIKE '%"+downUser+"%'");
		}
		if(contractName!=null&&!"".equals(contractName)){
			HQL.append(" AND c.contractName LIKE '%"+contractName+"%'");
			count.append(" AND c.contractName LIKE '%"+contractName+"%'");
		}
		if(userType!=null&&!"".equals(userType)){
			if(userType.equals("管理员")){
				HQL.append(" AND u.isAdmin = true");
				count.append(" AND u.isAdmin = true");
			}else{
				HQL.append(" AND u.isAdmin = false");
				count.append(" AND u.isAdmin =false");
			}
		}
		Long _count=(Long) getSession().createQuery(count.toString()).getSingleResult();
		int recordNum=_count.intValue();
		
		List<RecordHistory> list=getSession().createQuery(HQL.toString()).setFirstResult((bean.getCurrentPage()-1)*bean.getPageSize()).setMaxResults(bean.getPageSize()).getResultList();
		
		PageBean pageBean=new PageBean(bean.getCurrentPage(), bean.getPageSize(), recordNum, list);
		return pageBean;
	}

}
