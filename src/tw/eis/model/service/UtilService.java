package tw.eis.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.eis.model.dao.UtilDao;

@Service("utilService")
public class UtilService implements IUtilService {

	private UtilDao utilDao;

	public UtilService() {
		
	}
	@Autowired
	public UtilService(UtilDao utilDao) {
		this.utilDao=utilDao;
	}

	@Override
	public String createDeptList() {
		return utilDao.createDeptList();
	}

	@Override
	public String createTitleList() {
		return utilDao.createTitleList();
	}
	
	@Override
	public String createEmpList() {
		return utilDao.createEmpList();
	}
}
