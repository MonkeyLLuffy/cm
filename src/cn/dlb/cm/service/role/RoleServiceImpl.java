package cn.dlb.cm.service.role;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlb.cm.dao.role.RoleDao;
import cn.dlb.cm.entity.Role;
import cn.dlb.cm.entity.User;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleDao dao;
	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		dao.delete(id);
		return 1;
	}

	@Override
	public int update(Role role) {
		// TODO Auto-generated method stub
		dao.update(role);
		return 1;
	}

	@Override
	public int add(Role role) {
		// TODO Auto-generated method stub
		dao.add(role);
		return 1;
	}

	@Override
	public Map<String, Object> getRoleWithPaging(String condition, int rows,
			int page) {
		// TODO Auto-generated method stub
		if (condition!=null && condition.trim().length() != 0) {
			condition = " where roleName like '%"+condition+"%' or remark like '%"+condition+"%'";
		}else {
			condition = "";
		}
		return dao.getRoleWithPaging(condition,rows,page);
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
	public int setPermission(Integer[] permissionIds, Integer roleId) {
		// TODO Auto-generated method stub
		return dao.setPermission(permissionIds,roleId);
	}
}
