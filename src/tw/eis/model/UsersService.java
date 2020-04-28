package tw.eis.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.eis.model.Department;
import tw.eis.model.Employee;
import tw.eis.model.Title;
import tw.eis.model.Users;
import tw.eis.model.UsersDao;

@Service("usersService")
public class UsersService implements IUsersService {
	
	private UsersDao usersDao;

	public UsersService() {
		
	}
	
	@Autowired
	public UsersService(UsersDao usersDao) {
		this.usersDao=usersDao;
	}
	

	@Override
	public int addUsers(String UserName, String UserPassword, String Title,int Level, String Department,Employee Manager,String Name,
			String Gender,Date BirthDay,String Address,String ExtensionNum,String PhoneNum,String Email,byte[] Photo,int Salary,
			Date HireDay,Date LastWorkDay,Department EmpDept, Title EmpTitle) {
		return usersDao.addUsers(UserName, UserPassword, Title,Level,Department,Manager,Name,Gender,BirthDay,
								Address,ExtensionNum,PhoneNum,Email,Photo,Salary,HireDay,LastWorkDay,EmpDept,EmpTitle);
	}

	public Users userData(int id) {
		return usersDao.userData(id);
	}
	
	
	public List<Users> findUsers(String userName, String userPassword) {
		return usersDao.findUsers(userName, userPassword);
	}
	
	public List<Users> findUsersByID(int userID){
		return usersDao.findUsersByID(userID);
	}
	
	public boolean updateUsersPassword(String userName, String userPassword) {
		return usersDao.updateUsersPassword(userName, userPassword);
	}
	
	public List<Users> findStaff(Map<String, String> usersResultMap){
		return usersDao.findStaff(usersResultMap);
	}
	
	public Timestamp queryLoginTime(int empyleeID){
		return usersDao.queryLoginTime(empyleeID);
	}
	public void updateLoginTime(Users Users) {
		usersDao.updateLoginTime(Users);
	}
}
