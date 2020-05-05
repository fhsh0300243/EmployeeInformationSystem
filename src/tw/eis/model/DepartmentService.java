package tw.eis.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.eis.model.Department;
import tw.eis.model.DepartmentDao;

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
	
	@Override
	public String deptIdByDeptAbb(int deptid) {
		return departmentDao.deptIdByDeptAbb(deptid);
	}

}
