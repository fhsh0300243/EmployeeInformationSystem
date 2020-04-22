package tw.eis.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.eis.model.Department;
import tw.eis.model.dao.DepartmentDao;

@Service("departmentService")
public class DepartmentService implements IDepartmentService {

	private DepartmentDao departmentDao;

	public DepartmentService() {
		
	}
	
	@Autowired
	public DepartmentService(DepartmentDao departmentDao) {
		this.departmentDao=departmentDao;
	}

	@Override
	public Department deptData(int id) {
		return departmentDao.deptData(id);
	}
	
	@Override
	public List<?> allDeptData() {
		return departmentDao.allDeptData();
	}

}
