package tw.eis.model;

import java.sql.Date;
import java.util.List;

import tw.eis.model.Department;
import tw.eis.model.Employee;
import tw.eis.model.Title;
import tw.eis.model.Users;

public interface IUsersDao {
	public int addUsers(String UserName, String UserPassword, String Title,int Level, String Department,Employee Manager,String Name,
			String Gender,Date BirthDay,String Address,String ExtensionNum,String PhoneNum,String Email,byte[] Photo,int Salary,
			Date HireDay,Date LastWorkDay,Department EmpDept, Title EmpTitle);
	public Users userData(int id);
	public List<Users> findUsers(String userName, String userPassword);
	public List<Users> findUsersByID(int userID);
	public boolean updateUsersPassword(String userName, String userPassword);
}
