package cn.dlb.cm.dao.role;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.dlb.cm.base.dao.BaseDaoImpl;
import cn.dlb.cm.dao.permission.PermissionDao;
import cn.dlb.cm.entity.Permission;
import cn.dlb.cm.entity.Role;
import cn.dlb.cm.entity.User;
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

	@Autowired
	PermissionDao permissionDao;
	@Override
	public Map<String, Object> getRoleWithPaging(String condition, int rows,
			int page) {
		// TODO Auto-generated method stub
		int start = (page-1)* rows ;
		List<Role> list=this.getSession()
				.createQuery("from Role "+condition)
				.setFirstResult(start)
				.setMaxResults(rows)
				.getResultList();
		Long total = (Long)this.getSession().createQuery("select count(*) as total from Role"+condition).getSingleResult();
		if(list.size()==0){
			return null;
		}else{
			Set<Map<String, Object>> resultRole = new HashSet<>();
			for (Role role : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", role.getId());
				map.put("roleName", role.getRoleName());
				map.put("remark", role.getRemark());
				resultRole.add(map);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(list);
			map.put("data", resultRole);
			map.put("total", total);
			int totalPage = (int) (total/rows);
			if (total%rows != 0) {
				totalPage+=1;
			}
			map.put("totalPage", totalPage);
			return map;
		}
	}

	@Override
	public Role getRoleById(Integer id) {
		// TODO Auto-generated method stub
		List<Role> list=this.getSession()
				.createQuery("from Role where id="+id)
				.getResultList();
		if(list.size()==0){
			return null;
		}else{

			return list.get(0);
		}
	}

	@Override
	public int setPermission(Integer[] permissionIds, Integer roleId) {
		// TODO Auto-generated method stub

		List<Role> list = getSession()
				.createQuery("from Role where id=?")
				.setParameter(0, roleId)
				.getResultList();
		Role role = list.get(0);
		List<Permission> permissionList = permissionDao.getByIds(permissionIds);
		role.getPermission().addAll(permissionList);
		getSession().update(role);
		return 1;
	}

}
