package tw.eis.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import tw.eis.model.Attendance;
import tw.eis.util.GlobalService;

@Repository
public class AttendanceDAO {

	private SessionFactory sessionFactory;
	// private UsersService uService;

	@Autowired
	public AttendanceDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//	@Autowired
//	public AttendanceDAO(UsersService uService) {
//		this.uService = uService;
//	}

	public List<Attendance> InquiryToday(int EmpId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
			nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String today = nowdate.format(new Date());
			String hqlstr = "from Attendance where EmpId=:EmployeeID and Date =:Date";
			Query<Attendance> query = session.createQuery(hqlstr, Attendance.class);
			query.setParameter("EmployeeID", EmpId);
			query.setParameter("Date", today);
			List<Attendance> myPunch = query.list();
			return myPunch;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return null;
	}

	public List<Attendance> InquiryAttendance(int EmpId, String month) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hqlstr = "from Attendance where EmpId=:id and Date like :Month";
			Query<Attendance> query = session.createQuery(hqlstr, Attendance.class);
			query.setParameter("id", EmpId);
			query.setParameter("Month", month + "%");
			List<Attendance> attlist = query.list();
			return attlist;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return null;
	}

	public void InsertStartTime(Employee Emp, java.sql.Date Date, java.sql.Time Time) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Attendance attendance = new Attendance();
			attendance.setEmployee(Emp);
			attendance.setDate(Date);
			attendance.setStartTime(Time);
			session.save(attendance);
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
	}

	public void InsertEndTime(Employee Emp, java.sql.Date Date, java.sql.Time Time) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Attendance attendance = new Attendance();
			attendance.setEmployee(Emp);
			attendance.setDate(Date);
			attendance.setEndTime(Time);
			session.save(attendance);
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
	}

	public void UpdateEndTime(Employee Emp, java.sql.Date Date, java.sql.Time Time) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hqlstr = "Update Attendance SET EndTime=:Time where Date=:Date and EmpId=:Employee";
			Query query = session.createQuery(hqlstr);
			query.setParameter("Time", Time);
			query.setParameter("Date", Date);
			query.setParameter("Employee", Emp);
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
	}

	public void UpdateStatus(Map<String, String> usersResultMap, java.sql.Date Date, String Status) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hqlstr = "Update Attendance SET Status=:Status where Date=:Date and EmpId=:EmployeeID";
			Query query = session.createQuery(hqlstr);
			query.setParameter("Status", Status);
			query.setParameter("Date", Date);
			query.setParameter("EmployeeID", usersResultMap.get("EmployeeID"));
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
	}

	public List<Attendance> InquiryAllToday(String todaystr) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hqlstr = "from Attendance where Date like:today";
		Query<Attendance> query = session.createQuery(hqlstr, Attendance.class);
		query.setParameter("today", todaystr);
		List<Attendance> AllToday = query.list();
		session.getTransaction().commit();
		session.close();
		return AllToday;
	}

	public void UpdateAttendanceStatus(java.sql.Date Date, int Id, String Status, String LeaveType) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hqlstr = "Update Attendance SET Status=:Status ,LeaveType=:LeaveType where Date=:Date and EmpId=:EmployeeID";
		Query query = session.createQuery(hqlstr);
		query.setParameter("Status", Status);
		query.setParameter("LeaveType", LeaveType);
		query.setParameter("Date", Date);
		query.setParameter("EmployeeID", Id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	public void NewAttendance(Employee Emp, java.sql.Date Date) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Attendance attendance = new Attendance();
		attendance.setEmployee(Emp);
		attendance.setDate(Date);
		session.save(attendance);
		session.getTransaction().commit();
		session.close();
	}

	public List<?> queryEmpAttendanceData(int empId, String Name, String Department, java.sql.Date StartDate,
			java.sql.Date EndDate) {
		DetachedCriteria mainQuery = DetachedCriteria.forClass(Attendance.class);
		DetachedCriteria subQuery = DetachedCriteria.forClass(Employee.class);
		subQuery.add(Restrictions.or(Restrictions.gt("lastWorkDay", GlobalService.dateOfToday()),
				Restrictions.isNull("lastWorkDay")));
		subQuery.setProjection(Property.forName("empID"));
		if (StartDate != null && EndDate != null) {
			mainQuery.add(Restrictions.between("date", StartDate, EndDate));
		}

		if (StartDate != null) {
			mainQuery.add(Restrictions.ge("date", StartDate));
		}

		if (EndDate != null) {
			mainQuery.add(Restrictions.le("date", EndDate));
		}

		if (empId != 0) {
			subQuery.add(Restrictions.eq("empID", empId));
		}
		if (!Name.equals("na")) {
			subQuery.add(Restrictions.eq("name", Name));
		}
		if (!Department.equals("na")) {
			subQuery.add(Restrictions.eq("department", Department));
		}
		List<?> list = mainQuery.add(Property.forName("employee").in(subQuery))
				.getExecutableCriteria(sessionFactory.getCurrentSession()).list();
		return list;
	}

	public int CountError(Employee Emp, String month) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hqlstr = "Select Count(Status) from Attendance where EmpId=:Emp and Date like :month and Status='異常'";
		Query query = session.createQuery(hqlstr);
		query.setParameter("Emp", Emp);
		query.setParameter("month", month + "%");
		Object result = query.uniqueResult();
		int countError = Integer.parseInt(result.toString());
		session.getTransaction().commit();
		session.close();
		return countError;
	}
}
