package tw.eis.model;

import java.util.List;

import tw.eis.model.Department;

public interface IDepartmentService {
	public Department deptData(int id);
	public List<?> allDeptData();
	public String deptIdByDeptAbb(int deptid);
}
