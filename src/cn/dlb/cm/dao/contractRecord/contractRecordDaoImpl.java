package cn.dlb.cm.dao.contractRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.inject.New;

import org.hibernate.transform.Transformers;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.dlb.cm.base.dao.BaseDaoImpl;
import cn.dlb.cm.dao.role.RoleDao;
import cn.dlb.cm.dao.user.UserDao;
import cn.dlb.cm.entity.ContractRecord;
import cn.dlb.cm.entity.PageBean;
import cn.dlb.cm.entity.Query_ContractRecord;
import cn.dlb.cm.entity.Role;
import cn.dlb.cm.entity.User;
import cn.dlb.cm.tool.DateFormate;

@Repository
public class contractRecordDaoImpl extends BaseDaoImpl<ContractRecord>
		implements ContractRecordDao {

	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	/**路遥
	 * 
	 * 高级搜索部分
	 */
	@Override
	public PageBean findadvancedSearch(Query_ContractRecord contractRecord,
			PageBean pageBean, java.sql.Date signingDateStartSQL,
			java.sql.Date signingDateEndSQL, java.sql.Date importDateStartSQL,
			java.sql.Date importDateEndSQL, java.sql.Date deadlineStartSQL,
			java.sql.Date deadlineEndSQL,String importDateSort,String signingDateSort,String deadlineSort,String notUpload) {
		
		//动态拼接搜索条件
		StringBuilder HQL = new StringBuilder("FROM ContractRecord where 1=1");
		if (contractRecord.getId() != null
				&& !"".equals(contractRecord.getId())) {
			HQL.append(" and ID like '%" + contractRecord.getId() + "%'");
		}
		if (contractRecord.getContractName() != null
				&& !"".equals(contractRecord.getContractName())) {
			HQL.append(" and CONTRACTNAME like '%" + contractRecord.getContractName()
					+ "%'");
		}
		if (contractRecord.getPartyA() != null
				&& !"".equals(contractRecord.getPartyA())) {
			HQL.append(" and PARTYA like '%" + contractRecord.getPartyA()
					+ "%'");
		}
		if (contractRecord.getPartyB() != null
				&& !"".equals(contractRecord.getPartyB())) {
			HQL.append(" and PARTYB like '%" + contractRecord.getPartyB()
					+ "%'");
		}
		if (contractRecord.getContractAmount() != null
				&& !"".equals(contractRecord.getContractAmount())) {
			HQL.append(" and CONTRACTAMOUNT like '%"
					+ contractRecord.getContractAmount() + "%'");
		}
		if (contractRecord.getOperator() != null
				&& !"".equals(contractRecord.getOperator())) {
			HQL.append(" and OPERATOR like '%" + contractRecord.getOperator()
					+ "%'");
		}
		if (contractRecord.getDepart() != null
				&& !"".equals(contractRecord.getDepart())) {
			HQL.append(" and DEPART like '%" + contractRecord.getDepart() + "%'");
		}
		if (contractRecord.getContractType() != null
				&& !"".equals(contractRecord.getContractType())) {
			HQL.append(" and CONTRACTTYPE = '"
					+ contractRecord.getContractType() + "'");
		}
		if (notUpload != null
				&& !"".equals(notUpload)) {
			HQL.append(" and CONTRACTFILE = null and state=1");
		}else{
			HQL.append(" and state = 1");
		}
		if (signingDateStartSQL != null && signingDateEndSQL != null) {
			HQL.append(" and SIGNINGDATE between '" + signingDateStartSQL + "' and '"
					+ signingDateEndSQL  + "'");
		}
		if (importDateStartSQL != null && importDateEndSQL != null) {
			HQL.append(" and IMPORTDATE between '" + importDateStartSQL + "' and '"
					+ importDateEndSQL + "'");
		}
		if (deadlineStartSQL != null && deadlineEndSQL  != null) {
			HQL.append(" and DEADLINE between '" + deadlineStartSQL + "' and '"
					+ deadlineEndSQL + "'");
		}
		
		StringBuilder count = new StringBuilder("select count(*) ");
		count.append(HQL);
		//获取总记录数
		Long _recordNum = (Long) getSession().createQuery(count.toString())
				.uniqueResult();
		int recordNum = _recordNum.intValue();
		
		//排序
		if(importDateSort==null&&signingDateSort==null&&deadlineSort==null){
			HQL.append(" order by importDate desc");
		}else if(importDateSort.equals("")&&signingDateSort.equals("")&&deadlineSort.equals("")){
			HQL.append(" order by importDate desc");
		}else if(importDateSort!=null){
			HQL.append(" order by importDate "+importDateSort);
		}else if(signingDateSort!=null){
			HQL.append(" order by importDate "+signingDateSort);
		}else if(deadlineSort!=null){
			HQL.append(" order by importDate "+deadlineSort);
		}
		//获得当前页数据
		List<ContractRecord> list = getSession()
				.createQuery(HQL.toString())
				.setFirstResult(
						(pageBean.getCurrentPage() - 1)
								* pageBean.getPageSize())
				.setMaxResults(pageBean.getPageSize()).getResultList();

		PageBean newPageBean = new PageBean(pageBean.getCurrentPage(),
				pageBean.getPageSize(), recordNum, list);
		return newPageBean;
	}

	
	public void delete(String id) {
		ContractRecord contractRecord=getById(id);
		contractRecord.setState(0);
		getSession().update(contractRecord);
	}


	@Override
	public Map<String, Object> getRecycleBinData(String condition, int rows,
			int page) {
		// TODO Auto-generated method stub
		//DATE_FORMAT(file_record.UPLOADTIME,\"%Y-%m-%d\")  as UPLOADTIME
		int start = (page-1)* rows ;
		List<Map<String, Object>> list=this.getSession()
				.createSQLQuery("select ContractRecord.id as id," +
						"ContractRecord.contractNum as contractNum," +
						"ContractRecord.contractName as contractName," +
						"ContractRecord.partyA as partyA," +
						"ContractRecord.partyB as partyB," +
						"ContractRecord.contractType as contractType," +
						"ContractRecord.contractAmount as contractAmount," +
						"ContractRecord.depart as depart," +
						"ContractRecord.operator as operator," +
						"ContractRecord.remark as remark," +
						"ContractRecord.state as state," +
						"DATE_FORMAT(ContractRecord.signingDate,\"%Y/%m/%d\")as signingDate," +
						"DATE_FORMAT(ContractRecord.deadline,\"%Y/%m/%d\")as deadline," +
						"DATE_FORMAT(ContractRecord.importDate,\"%Y/%m/%d\")as importDate" +
						" from ContractRecord where state=0 "+((condition!=null&&condition.length() != 0)?condition:""))
				.setFirstResult(start)
				.setMaxResults(rows).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.getResultList();
		Long total = (Long)this.getSession().createQuery("select count(*) as total from ContractRecord where state = 1").getSingleResult();
		if(list.size()==0){
			return null;
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("data", list);
			map.put("total", total);
			map.put("totalPage", total/rows);
			return map;
		}
	}


	//批量将文件记录加入到回收站
	public void deleteManyRecord(String[] id) {
		List <ContractRecord> conList=this.getByIds(id);
		for (ContractRecord contractRecord : conList) {
			contractRecord.setState(0);
			getSession().update(contractRecord);
		}
	}


	@Override
	public Map<String, Object> monthContractCount(int year) {
		// TODO Auto-generated method stub

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT  DATE_FORMAT(ContractRecord.signingDate,'%m')as x,count(1) as y  FROM contractrecord WHERE signingDate > '");
		stringBuilder.append(year);
		stringBuilder.append("' GROUP BY signingDate ORDER BY x");
		List<Map<String, Object>> list=this.getSession()
				.createSQLQuery(stringBuilder.toString())
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.getResultList()
				;
		int[] y = new int[list.size()];
		String[] x = new String[list.size()];
		int i = 0;
		for (Map<String, Object> map : list) {
			x[i] = map.get("x")+"月";
			y[i] = Integer.parseInt(( map.get("y")+"")) ;
			i++;
		}
		Map<String, Object> result = new HashMap<>();
		result.put("x", x);
		result.put("y", y);
		return result;
	}

	@Override
	public Map<String, Object> yearContractCount(int year) {
		// TODO Auto-generated method stub

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT  DATE_FORMAT(ContractRecord.signingDate,'%Y')as x,count(1) as y  " );	
		stringBuilder.append("FROM contractrecord");
		stringBuilder.append(" GROUP BY x ORDER BY x asc limit 0,10");
		List<Map<String, Object>> list=this.getSession()
				.createSQLQuery(stringBuilder.toString())
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.getResultList()
				;
		int[] y = new int[list.size()];
		String[] x = new String[list.size()];
		int i = 0;
		for (Map<String, Object> map : list) {
			x[i] = map.get("x")+"年";
			y[i] = Integer.parseInt(( map.get("y")+"")) ;
			i++;
		}
		Map<String, Object> result = new HashMap<>();
		result.put("x", x);
		result.put("y", y);
		return result;
	}


	@Override
	public Map<String, Object> getSubCompanyData(Integer userId,String time, String timeRule) {
		// TODO Auto-generated method stub
		User user = userDao.getById(userId);
		Set<Role> roles = user.getRoles();
		//签订的合同总数
		Integer total = 0;
		String sql = "SELECT DISTINCT COUNT(DISTINCT contractrecord.ID) " +
				"as amount FROM contractrecord ";
		String departConditionString = "";
		String partyBConditionString = "";
		int i = 0;
		for (Role role : roles) {
			String searchString = null;
			String roleName = role.getRoleName();
			//上海分公司：上海配送什么的
			if (roleName.contains("上海") && roleName.contains("配送") ) {
				searchString=roleName.substring(0, 6);
			}else {
				searchString = roleName.substring(0, 4);
			}
			//首先搜索经办人能与该用户的角色匹配的
			//再次搜索乙方能与该用户的角色匹配的
			if (i == 0) {
				departConditionString += "WHERE (contractrecord.DEPART LIKE '%"+searchString+"%' ";
				partyBConditionString += "OR CONTRACTRECORD.PARTYB LIKE '%"+searchString+"%'";
				
			}else {
				departConditionString += "OR contractrecord.DEPART LIKE  '%"+searchString+"%'";
				partyBConditionString += "OR CONTRACTRECORD.PARTYB LIKE  '%"+searchString+"%'";
			}
			i++;
		}
		if (roles.size() != 0) {
			sql += departConditionString+partyBConditionString + ") " +
					"AND DATE_FORMAT(contractrecord.SIGNINGDATE,\""+timeRule+"\") = '"+time+"'";
			total=Integer.parseInt(this.getSession()
					.createSQLQuery(sql)
					.getSingleResult()+"");
		}
		//搜索其他角色的合同角色
		//首先得到他不存在的角色
		//然后对这些角色（除了总部和管理员）分别得到签订了多少合同
		//然后在这些里面进行求名次
		List<Role> totalRoles = roleDao.findAll();
		//移除用户被分配的角色
		totalRoles.removeAll(roles);//已对equals方法进行处理
		List<Integer> list = new ArrayList<Integer>();
		for (Role role : totalRoles) {
			String searchString = null;
			String roleName = role.getRoleName();
			//上海分公司：上海配送什么的
			if (roleName.contains("上海") && roleName.contains("配送") ) {
				searchString=roleName.substring(0, 6);
			}else {
				//如果比四个字长就只筛选前四个字匹配的
				if(roleName.length() > 4){
					searchString = roleName.substring(0, 4);
				}
				else {
					searchString = roleName;
				}
			}
			//首先搜索经办人能与该用户的角色匹配的
			//再次搜索乙方能与该用户的角色匹配的
			sql = "SELECT DISTINCT COUNT(DISTINCT contractrecord.ID) " +
					"as amount FROM contractrecord " +
					"WHERE (contractrecord.DEPART LIKE '%"+searchString+"%' " +
					"OR CONTRACTRECORD.PARTYB LIKE '%"+searchString+"%') " +
							"AND DATE_FORMAT(contractrecord.SIGNINGDATE,\""+timeRule+"\") = '"+time+"'";
			Integer count=Integer.parseInt(this.getSession()
					.createSQLQuery(sql)
					.getSingleResult()+"");
			list.add(count);
		}
		int ranking = 1;//默认为第一名
		for (Integer l : list) {
			if (l>total) {
				ranking += 1;
			}
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		String closingDateString = df.format(new Date());
		Map<String, Object> map = new HashMap<>();
		map.put("ranking", ranking);//名次
		map.put("total", total);//签订合同总份数
		map.put("closingDate", closingDateString);//统计截止日期
		return map;
	}

}
