package cn.dlb.cm.dao.role;

import java.util.Map;

import cn.dlb.cm.base.dao.BaseDao;
import cn.dlb.cm.entity.Role;

public interface RoleDao extends BaseDao<Role>{

	public Map<String, Object> getRoleWithPaging(String condition, int rows, int page);
	public Role getRoleById(Integer id);
	public int setPermission(Integer[] permissionIds, Integer roleId);
}
