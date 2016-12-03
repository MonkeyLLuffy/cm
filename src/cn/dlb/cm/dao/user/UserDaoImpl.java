package cn.dlb.cm.dao.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.relation.RoleList;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.dlb.cm.base.dao.BaseDaoImpl;
import cn.dlb.cm.dao.role.RoleDao;
import cn.dlb.cm.entity.Role;
import cn.dlb.cm.entity.User;

@Repository
@SuppressWarnings("unchecked")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Autowired
	private RoleDao roleDao;
	public User check(User user) {
		
		List <User>list=this.getSession()//
		.createQuery("from User where username=? and password=?")//
		.setParameter(0, user.getUsername())//
		.setParameter(1, user.getPassword())//
		.getResultList();
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public Map<String, Object> getUserWithPaging(String condition, int rows, int page) {
		// TODO Auto-generated method stub
		int start = (page-1)* rows ;
		List<Map<String, Object>> list=this.getSession()
				.createSQLQuery("select * from user "+condition)
				.setFirstResult(start)
				.setMaxResults(rows).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.getResultList();
		Long total = (Long)this.getSession().createQuery("select count(*) as total from User"+condition).uniqueResult();
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

	@Override
	public List<String> findAllDepart() {
		List <String>list=getSession().createQuery("from SystemCode where codeType=?").setParameter(0, "depart").getResultList();
		return list;
	}

	@Override
	public List<String> findAllContractType() {
		List <String>list=getSession().createQuery("from SystemCode where codeType=?").setParameter(0, "contractType").getResultList();
		return list;
	}

	@Override
	public int resetPassword(Integer id) {
		// TODO Auto-generated method stub

		int items = this.getSession()
		.createQuery("update User set password = ? where id = ?")
		.setParameter(0, "1234")
		.setParameter(1, id)
		.executeUpdate();
		return items;
		
	}

	@Override
	public int setRole(Integer[] roleIds,Integer userId) {
		// TODO Auto-generated method stub
		
		List<User> list = getSession()
				.createQuery("from User where id=?")
				.setParameter(0, userId)
				.getResultList();
		User user = list.get(0);
		List<Role> roleList = roleDao.getByIds(roleIds);
		user.getRoles().addAll(roleList);
		getSession().update(user);
		return 1;
	}
	
}
