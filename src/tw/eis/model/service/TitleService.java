package tw.eis.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.eis.model.Title;
import tw.eis.model.dao.TitleDao;

@Service
public class TitleService implements ITitleService {

	private TitleDao titleDao;

	public TitleService() {
		
	}
	
	@Autowired
	public TitleService(TitleDao titleDao) {
		this.titleDao=titleDao;
	}

	@Override
	public Title titleData(int id) {
		return titleDao.titleData(id);
	}
	
	@Override
	public List<?> titleDataByDeptId(int deptId) {
		return titleDao.titleDataByDeptId(deptId);
	}

}
