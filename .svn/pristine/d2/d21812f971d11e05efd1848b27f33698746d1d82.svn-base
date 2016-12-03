package cn.dlb.cm.service.contractRecord;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlb.cm.dao.contractRecord.ContractRecordDao;
import cn.dlb.cm.entity.ContractRecord;
import cn.dlb.cm.entity.PageBean;
import cn.dlb.cm.entity.Query_ContractRecord;
import cn.dlb.cm.tool.DateUtils;

@Service
public class ContractRecordServiceImpl implements ContractRecordService {
	@Autowired
	private ContractRecordDao contractRecordDao;

	@Override
	public PageBean findadvancedSearch(Query_ContractRecord contractRecord,
			PageBean pageBean,String importDateSort,String signingDateSort,String deadlineSort,String notUpload,String signingDateStart,String signingDateEnd,String importDateStart,String importDateEnd,String deadlineStart,String deadlineEnd) {

		PageBean newPageBean = getPageBean(pageBean);
		java.sql.Date signingDateStartSQL=null;
		java.sql.Date signingDateEndSQL =null;
		java.sql.Date importDateStartSQL = null;
		java.sql.Date importDateEndSQL = null;
		java.sql.Date deadlineStartSQL = null;
		java.sql.Date deadlineEndSQL=null;
		
		//将java.ytil.Date转换成java.sql.Date
		if (signingDateStart != null&&!"".equals(signingDateStart.trim())) {
			signingDateStartSQL = dateChange(dateShift(signingDateStart));
		}
		if (signingDateEnd != null&&!"".equals(signingDateEnd.trim())) {
			signingDateEndSQL = dateChange(dateShift(signingDateEnd));
		}
		if (importDateStart != null&&!"".equals(importDateStart.trim())) {
			importDateStartSQL = dateChange(dateShift(importDateStart));
		}
		if (importDateEnd != null&&!"".equals(importDateEnd.trim())) {
			importDateEndSQL = dateChange(dateShift(importDateEnd));
		}
		if (deadlineStart != null&&!"".equals(deadlineStart.trim())) {
			deadlineStartSQL = dateChange(dateShift(deadlineStart));
		}
		if (deadlineEnd != null&&!"".equals(deadlineEnd.trim())) {
			 deadlineEndSQL = dateChange(dateShift(deadlineEnd));
		}
		
		return contractRecordDao.findadvancedSearch(contractRecord,
				newPageBean, signingDateStartSQL, signingDateEndSQL,
				importDateStartSQL, importDateEndSQL, deadlineStartSQL,
				deadlineEndSQL,importDateSort,signingDateSort,deadlineSort,notUpload);
	}
	
	//如果分页pageBean没有初值,将给他赋初值
	public PageBean getPageBean(PageBean pageBean) {
		if (pageBean == null) {
			pageBean = new PageBean();
			pageBean.setCurrentPage(1);
			pageBean.setPageSize(25);
		}
		if (pageBean.getCurrentPage() == 0) {
			pageBean.setCurrentPage(1);
		}
		if (pageBean.getPageSize() == 0) {
			pageBean.setPageSize(25);
		}
		return pageBean;
	}
	
	//Date转换方法
	private java.sql.Date dateChange(Date date) {
		java.sql.Date newdate = new java.sql.Date(date.getTime());
		return newdate;
	}
	
	//将记录放到回收站
	@Override
	public void deleteContractRecord(String id) {
		contractRecordDao.delete(id);
	}

	@Override
	public Map<String, Object> getRecycleBinData(String condition,String sort, int rows,
			int page) {
		if (condition != null && condition.length() != 0) {
			condition = "order by "+condition+" "+sort;
		}
		System.out.println(condition);
		return contractRecordDao.getRecycleBinData(condition,rows,page);
	}

	@Override
	public void deleteManyRecord(String[] id) {
		contractRecordDao.deleteManyRecord(id);
	}

	@Override
	public ContractRecord getById(String id) {
		ContractRecord contractRecord=contractRecordDao.getById(id);
		return contractRecord;
	}

	@Override
	public void updateContractRecord(ContractRecord contractRecord) {
		contractRecordDao.update(contractRecord);
	}

	@Override
	public Map<String, Object> monthContractCount(Integer userId) {
		// TODO Auto-generated method stub
		//检查权限：略过
		//查询数据
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		Map<String, Object> resultMap = contractRecordDao.monthContractCount(year);
		return resultMap;
	}

	@Override
	public Map<String, Object> yearContractCount(Integer userId) {
		// TODO Auto-generated method stub
		//检查权限：略过
		//查询数据
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		Map<String, Object> resultMap = contractRecordDao.yearContractCount(year);
		return resultMap;
	}

	@Override
	public Map<String, Object> getSubCompanyData(Integer userId,String time, String timeRule) {
		// TODO Auto-generated method stub
		System.out.println("-------------"+time+"--------------------");
		Map<String, Object> map = contractRecordDao.getSubCompanyData(userId,time,timeRule);
		return map;
	}

	@Override
	public List<ContractRecord> getManyById(String[] id) {
		List<ContractRecord> records=contractRecordDao.getByIds(id);
		return records;
	}

	@Override
	public void add(ContractRecord contractRecord) {
		contractRecordDao.add(contractRecord);
	}
	
	private Date dateShift(String date){
		if(date.contains("-")){
			System.out.println(DateUtils.parse(date.trim()).getTime());
			return DateUtils.parse(date);
		}else{
			Date newDate=null;
			try {
				newDate = DateUtils.myDateFormat(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return newDate;
		}
	}
	@Test
	public void test(){
		java.sql.Date date=new java.sql.Date(1481126400000L);
		System.out.println(date);
	}
}
