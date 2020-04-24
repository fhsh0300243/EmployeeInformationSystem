package tw.eis.model;

import java.sql.Date;
import java.util.List;

import tw.eis.model.Department;
import tw.eis.model.Employee;
import tw.eis.model.Title;

public interface IEmployeeDao {
	public List<?> allEmpData();

	public List<Integer> allEmpIdforTask();
	
	public List<?> queryEmp(int id, String Name, String Department, String Resigned);

	public Employee empData(int id);

	public boolean editEmp(int id, String Title, int Level, String Department, Employee Manager, String Name,
			String Gender, Date BirthDay, String Address, String ExtensionNum, String PhoneNum, String Email,
			byte[] Photo, int Salary, Date HireDay, Date LastWorkDay, Department EmpDept, Title EmpTitle);

	public List<?> managerDataByDeptId(int deptId);

	public List<Employee> findEmployeeByEmail(String email);

	public List<?> empDataByManagerId(int managerId);

	public List<?> empDataByDeptId(int deptId);

	public List<?> empDataByTitleId(int titleId);

	public void test();

}
