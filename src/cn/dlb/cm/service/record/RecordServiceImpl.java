package cn.dlb.cm.service.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlb.cm.dao.record.RecordDao;
import cn.dlb.cm.entity.Record;

@Service
public class RecordServiceImpl implements RecordService{
	@Autowired
	private RecordDao dao;

	@Override
	public void add(Record record) {
		dao.add(record);
	}
	
	
}
