package tw.eis.model.service;

import java.util.List;

import tw.eis.model.Department;

public interface IDepartmentService {
	public Department deptData(int id);
	public List<?> allDeptData();
}
