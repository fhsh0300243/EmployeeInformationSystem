package tw.eis.model.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.eis.model.Department;
import tw.eis.model.Employee;
import tw.eis.model.Title;
import tw.eis.model.dao.EmployeeDao;

@Service("employeeService")
public class EmployeeService implements IEmployeeService {

	private EmployeeDao employeeDao;

	public EmployeeService() {

	}

	@Autowired
	public EmployeeService(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public List<?> allEmpData() {
		return employeeDao.allEmpData();
	}

	@Override
	public List<?> queryEmp(int id, String Name, String Department) {
		return employeeDao.queryEmp(id, Name, Department);
	}

	@Override
	public Employee empData(int id) {
		return employeeDao.empData(id);
	}
	
	@Override
	public boolean editEmp(int id, String Title,int Level, String Department, Employee Manager, String Name, String Gender,
			Date BirthDay, String Address, String ExtensionNum, String PhoneNum, String Email, byte[] Photo, int Salary,
			Date HireDay, Date LastWorkDay, Department EmpDept, Title EmpTitle) {

		return employeeDao.editEmp(id, Title,Level, Department, Manager, Name, Gender, BirthDay, Address, ExtensionNum,
				PhoneNum, Email, Photo, Salary, HireDay, LastWorkDay, EmpDept, EmpTitle);
	}

	@Override
	public List<?> managerDataByDeptId(int deptId) {
		return employeeDao.managerDataByDeptId(deptId);
	}

	public List<Employee> findEmployeeByEmail(String email){
		return employeeDao.findEmployeeByEmail(email);
	}
	
	@Override
	public List<?> empDataByManagerId(int managerId){
		return employeeDao.empDataByManagerId(managerId);
	}
	
	@Override
	public List<?> empDataByDeptId(int deptId){
		return employeeDao.empDataByDeptId(deptId);
	}
	
	@Override
	public List<?> empDataByTitleId(int titleId){
		return employeeDao.empDataByTitleId(titleId);
	}
	
	public void test() {
		employeeDao.test();
	}

}
