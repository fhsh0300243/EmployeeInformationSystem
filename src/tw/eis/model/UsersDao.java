package tw.eis.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import tw.eis.model.Department;
import tw.eis.model.Employee;
import tw.eis.model.Title;
import tw.eis.model.Users;

@Repository("usersDao")
public class UsersDao implements IUsersDao {

	private SessionFactory sessionFactory;

	public UsersDao() {

	}

	@Autowired
	public UsersDao(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int addUsers(String UserName, String UserPassword, String Title, int Level, String Department,
			Employee Manager, String Name, String Gender, Date BirthDay, String Address, String ExtensionNum,
			String PhoneNum, String Email, byte[] Photo, int Salary, Date HireDay, Date LastWorkDay, Department EmpDept,
			Title EmpTitle) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Users> query1 = session.createQuery("From Users Where UserName=:name and UserPassword=:pwd",
					Users.class);
			Query<Employee> query2 = session.createQuery("From Employee Where email=:email", Employee.class);
			query1.setParameter("name", UserName);
			query1.setParameter("pwd", UserPassword);
			query2.setParameter("email", Email);
			List<Users> list1 = query1.list();
			List<Employee> list2 = query2.list();
			if (!list1.isEmpty()) {
				return 2;
			}
			if(!list2.isEmpty()) {
				return 3;
			}
			Users user = new Users(UserName, UserPassword, Title, Department);
			Employee emp = new Employee(Name, Gender, BirthDay, Address, Title, Level, Department, Manager,
					ExtensionNum, PhoneNum, Email, Photo, Salary, HireDay, LastWorkDay, EmpDept, EmpTitle);
			user.setEmployee(emp);
			emp.setUsers(user);
			session.save(user);
			return 1;			
		} catch (Exception e) {
			System.out.println("類別UsersDao,addUsers方法發生例外：" + e);
			return 4;
		}
	}

	public Users userData(int id) {
		return sessionFactory.getCurrentSession().get(Users.class, id);
	}
	public List<Users> findUsers(String userName, String userPassword) {
		Session session = sessionFactory.getCurrentSession();
		Query<Users> query = session.createQuery("from Users where UserName = :userName and UserPassword = :userPassword", Users.class);
		query.setParameter("userName", userName);
		query.setParameter("userPassword", userPassword);
		List<Users> list = query.list();
		return list;
	}
	
	public List<Users> findUsersByID(int userID) {
		Session session = sessionFactory.getCurrentSession();
		Query<Users> query = session.createQuery("from Users where EmployeeID = :userID", Users.class);
		query.setParameter("userID", userID);
		List<Users> list = query.list();
		return list;
	}
	
	public boolean updateUsersPassword(String userName, String userPassword) {
		Session session = sessionFactory.getCurrentSession();
		Query<Users> query = session.createQuery("from Users where UserName = :userName", Users.class);
		query.setParameter("userName", userName);
		List<Users> list = query.list();
		if(list.size()<=0) {
			return false;
		}
		Query<?> query2  = session.createQuery("update Users set UserPassword=:userPassword where UserName = :userName");
		query2.setParameter("userName", userName);
		query2.setParameter("userPassword", userPassword);
		query2.executeUpdate();
		return true;
	}
	
	public List<Users> findStaff(Map<String, String> usersResultMap) {
		Session session = sessionFactory.getCurrentSession();
		String Title = usersResultMap.get("Title");
		String hqlstr = null;
		if(Title.equals("classleader")) {
			hqlstr="from Users where Department=:Department and (Title='staff' or Title='classleader')";
		}else if(Title.equals("manager")) {
			hqlstr="from Users where Department=:Department and (Title='staff' or Title='classleader'or Title='manager')";
		}else if(Title.equals("Chairman")) {
			hqlstr="from Users";
		}
		Query<Users> query = session.createQuery(hqlstr, Users.class);
		query.setParameter("Department", usersResultMap.get("Department"));
		List<Users> list = query.list();
		return list;
	}
	
}
