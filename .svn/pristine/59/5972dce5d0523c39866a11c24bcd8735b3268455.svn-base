package cn.dlb.cm.dao.permission;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.dlb.cm.base.dao.BaseDaoImpl;
import cn.dlb.cm.entity.Permission;

@Repository
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements PermissionDao{

	@Override
	public List<Permission> getL1() {
		// TODO Auto-generated method stub
		List<Permission> list = getSession()
				.createQuery("from Permission where permission is NULL")
				.getResultList();
		
		return list;
	}

	@Override
	public List<Permission> getSubPermissionsById(Integer id) {
		// TODO Auto-generated method stub
		List<Permission> list = getSession()
				.createQuery("from Permission where permission = " + id)
				.getResultList();
		return list;
	}

}
