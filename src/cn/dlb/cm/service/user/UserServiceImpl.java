package cn.dlb.cm.service.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Array;

import cn.dlb.cm.dao.user.UserDao;
import cn.dlb.cm.entity.Role;
import cn.dlb.cm.entity.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public User check(User user) {
		return userDao.check(user);
	}

	@Override
	public Map<String, Object> getUserWithPaging(String condition, int rows, int page) {
		if (condition!=null && condition.trim().length() != 0) {
			condition = " where username like '%"+condition+"%' or trueName like '%"+condition+"%'";
		}else {
			condition = "";
		}
		return userDao.getUserWithPaging(condition, rows, page);
	}


	@Override
	public int update(User user) {
		userDao.update(user);
		return 1;
	}

	@Override
	public int add(User user) {
		userDao.add(user);
		return 1;
	}
	@Override
	public int delete(Integer id) {
		userDao.delete(id);
		return 1;
	}

	@Override
	public int deleteByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		for (int i = 0; i < ids.length; i++) {
			userDao.delete(ids[i]);
		}
		return ids.length;
	}

	@Override
	public List<String> findAllDepart() {
		List<String> list=userDao.findAllDepart();
		return list;
	}

	@Override
	public List<String> findAllContractType() {
		List<String> list=userDao.findAllContractType();
		return list;
	}

	@Override
	public int resetPassword(Integer id) {
		// TODO Auto-generated method stub
		
		return userDao.resetPassword(id);
	}

	@Override
	public int setRole(Integer[] roleIds,Integer userId) {
		// TODO Auto-generated method stub
		return userDao.setRole(roleIds,userId);
	}

	@Override
	public Set<Map<String, Object>> getRoleById(Integer id) {
		// TODO Auto-generated method stub
		User user = userDao.getById(id);
		Set<Map<String, Object>> result = new HashSet<>();
		for (Role role : user.getRoles()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", role.getId());
			map.put("roleName", role.getRoleName());
			map.put("remark", role.getRemark());
			result.add(map);
		}
		
		return result;
	}
}
