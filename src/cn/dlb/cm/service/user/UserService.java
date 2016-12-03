package cn.dlb.cm.service.user;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.dlb.cm.entity.Role;
import cn.dlb.cm.entity.User;

public interface UserService {

	User check(User user);

	public Map<String, Object> getUserWithPaging(String condition, int rows, int page);


	public int update(User user);
	
	public int add(User user);

	public int delete(Integer id);
	List<String> findAllDepart();

	List<String> findAllContractType();

	public int deleteByIds(Integer[] ids);

	public int resetPassword(Integer id);

	public int setRole(Integer[] roleIds,Integer userId);
	
	public Set<Map<String, Object>> getRoleById(Integer id);

}