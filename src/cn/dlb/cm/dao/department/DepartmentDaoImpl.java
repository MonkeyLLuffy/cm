package cn.dlb.cm.dao.department;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.dlb.cm.base.dao.BaseDaoImpl;
import cn.dlb.cm.entity.Department;

@Repository
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao{

	@Override
	public Map<String, Object> getDepartmentWithPaging(String condition, int rows, int page) {
		// TODO Auto-generated method stub
		int start = (page-1)* rows ;
		List<Map<String, Object>> list=this.getSession()
				.createQuery("from Department "+condition)
				.setFirstResult(start)
				.setMaxResults(rows)
				.getResultList();
		Long total = (Long)this.getSession().createQuery("select count(*) as total from Department "+condition).getSingleResult();
		if(list.size()==0){
			return null;
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("data", list);
			map.put("total", total);
			int totalPage = (int) (total/rows);
			if (total%rows != 0) {
				totalPage+=1;
			}
			map.put("totalPage", totalPage);
			return map;
		}
	}
}