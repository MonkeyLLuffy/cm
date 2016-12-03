package cn.dlb.cm.service.department;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlb.cm.dao.department.DepartmentDao;
import cn.dlb.cm.entity.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	private DepartmentDao dao;
	@Override
	public String addDepartment(Department department) {
		// TODO Auto-generated method stub
		dao.add(department);
		return "1";
	}
	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		dao.delete(id);
		return 1;
	}
	
	@Override
	public int deleteByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		for (int i = 0; i < ids.length; i++) {
			dao.delete(ids[i]);
		}
		return ids.length;
	}
	@Override
	public int update(Department department) {
		// TODO Auto-generated method stub
		dao.update(department);
		return 1;
	}
	@Override
	public int add(Department department) {
		// TODO Auto-generated method stub
		dao.add(department);
		return 1;
	}
	@Override
	public Map<String, Object> getDepartmentWithPaging(String condition,
			int rows, int page) {
		// TODO Auto-generated method stub
		if (condition!=null && condition.trim().length() != 0) {
			condition = " where departmentName like '%"+condition+"%' or remark like '%"+condition+"%'";
		}else {
			condition = "";
		}
		return dao.getDepartmentWithPaging(condition,
				 rows, page);
	}
	
}
