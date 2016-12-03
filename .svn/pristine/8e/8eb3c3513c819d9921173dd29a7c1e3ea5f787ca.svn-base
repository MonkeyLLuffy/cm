package cn.dlb.cm.service.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.inject.New;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cn.dlb.cm.dao.permission.PermissionDao;
import cn.dlb.cm.dao.role.RoleDao;
import cn.dlb.cm.entity.Permission;
import cn.dlb.cm.entity.Role;
import cn.dlb.cm.entity.User;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	PermissionDao dao;
	@Autowired
	RoleDao roleDao;
	/**
	 * 获取全部的权限，并以三级菜单存储
	 * 将全部实体转为Map是为了减少Gson转换产生异常
	 * l1，l2,l3前缀的表示第一层，第二层，第三层
	 */
	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		ArrayList<Map<String, Object>> result = new ArrayList<>();
		
		List<Permission> L1Permissions = dao.getL1();
		
		for (int i = 0; i < L1Permissions.size(); i++) {
			
			List<Permission> L2Permissions = dao.getSubPermissionsById(L1Permissions.get(i).getId());
			List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
			
			for (int j = 0; j < L2Permissions.size(); j++) {
				
				List<Permission> L3Permissions = dao.getSubPermissionsById(L2Permissions.get(j).getId());
				List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
				
				for (Permission permission : L3Permissions) {
					Map<String, Object> l3Map = new HashMap<>();
					l3Map.put("id", permission.getId());
					l3Map.put("permissionName", permission.getPermissionName());
					l3Map.put("remark", permission.getRemark());
					list2.add(l3Map);
				}
				//构建这一项的值
				Map<String, Object> l2Map = new HashMap<>();
				l2Map.put("id", L2Permissions.get(j).getId());
				l2Map.put("permissionName", L2Permissions.get(j).getPermissionName());
				l2Map.put("remark", L2Permissions.get(j).getRemark());
				
				//将当前项以及子项目的都加入父项目
				Map<String, Object> l1map = new HashMap<>();
				l1map.put("data", l2Map);
				if (list2.size() > 0) {
					l1map.put("list", list2);
				}
				list1.add(l1map);
			}
			//构建这一项的值
			Map<String, Object> l1Map = new HashMap<>();
			l1Map.put("id", L1Permissions.get(i).getId());
			l1Map.put("permissionName", L1Permissions.get(i).getPermissionName());
			l1Map.put("remark", L1Permissions.get(i).getRemark());
			//将当前项以及子项目的都加入最终结果列表
			Map<String, Object> map = new HashMap<>();
			map.put("data", l1Map);
			if (list1.size() > 0) {
				map.put("list", list1);				
			}
			result.add(map);
		}
		
		String resultString = new Gson().toJson(result);
		//Permission对应一个List
		System.out.println(resultString);
		return resultString;
		
	}

	@Override
	public String getRolePermission(Integer roleId) {
		// TODO Auto-generated method stub
		Role role = roleDao.getRoleById(roleId);
		Set<Map<String, Object>> result = new HashSet<>();
		Set<Permission> permissions = role.getPermission();
		if (permissions != null) {
			for (Permission permission : permissions ) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", permission.getId());
				map.put("permissionName", permission.getPermissionName());
				map.put("remark", role.getRemark());
				result.add(map);
			}
			String rolesString = new Gson().toJson(result);
			return rolesString;
		}
		return null;
	}

}
