package cn.dlb.cm.service.contractFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlb.cm.dao.contractFile.ContractFileDao;
import cn.dlb.cm.entity.ContractFile;

@Service
public class ContractFileServiceImpl implements ContractFileService{
	@Autowired
	private ContractFileDao dao;

	@Override
	public void add(ContractFile file) {
		dao.add(file);
	}
}
