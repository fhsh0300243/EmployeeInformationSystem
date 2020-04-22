package tw.eis.model.dao;

import java.util.List;

import tw.eis.model.Department;

public interface IDepartmentDao {
	public Department deptData(int id);
	public List<?> allDeptData();
}
