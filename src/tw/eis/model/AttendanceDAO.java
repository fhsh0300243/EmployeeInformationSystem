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

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	public List<Attendance> InquiryToday(int EmpId) {	//帶入員工ID，回傳List<Attendance>
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");	//取得今天日期
		nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String today = nowdate.format(new Date());
		String hqlstr = "from Attendance where EmpId=:EmployeeID and Date =:Date order by Date";	//Hibernate hql 查詢
		Query<Attendance> query = getSession().createQuery(hqlstr, Attendance.class);
		query.setParameter("EmployeeID", EmpId);
		query.setParameter("Date", today);
		List<Attendance> myPunch = query.list();
		return myPunch;
	}

	//個人出勤查詢(月份)
	public List<Attendance> InquiryAttendance(int EmpId, String month) {	//帶入員工ID，月份
		String hqlstr = "from Attendance where EmpId=:id and Date like :Month order by Date";	//依日期排序
		Query<Attendance> query = getSession().createQuery(hqlstr, Attendance.class);
		query.setParameter("id", EmpId);
		query.setParameter("Month", month + "%");	//利用模糊搜尋
		List<Attendance> attlist = query.list();
		return attlist;
	}
	
	//新增上班時間
	public void InsertStartTime(Employee Emp, java.sql.Date Date, java.sql.Time Time) {	//帶入員工，日期，時間
		Attendance attendance = new Attendance();
		attendance.setEmployee(Emp);
		attendance.setDate(Date);
		attendance.setStartTime(Time);
		getSession().save(attendance);
	}

	//新增下班時間
	public void InsertEndTime(Employee Emp, java.sql.Date Date, java.sql.Time Time) {	//帶入員工，日期，時間
		Attendance attendance = new Attendance();
		attendance.setEmployee(Emp);
		attendance.setDate(Date);
		attendance.setEndTime(Time);
		getSession().save(attendance);
	}

	//更新下班時間
	public void UpdateEndTime(Employee Emp, java.sql.Date Date, java.sql.Time Time) {	//帶入員工，日期，時間
		String hqlstr = "Update Attendance SET EndTime=:Time where Date=:Date and EmpId=:Employee";
		Query<?> query = getSession().createQuery(hqlstr);
		query.setParameter("Time", Time);
		query.setParameter("Date", Date);
		query.setParameter("Employee", Emp);
		query.executeUpdate();
	}
	
	//更新上班時間
	public void UpdateStartTime(Employee Emp, java.sql.Date Date, java.sql.Time Time) {	//帶入員工，日期，時間
		String hqlstr = "Update Attendance SET StartTime=:Time where Date=:Date and EmpId=:Employee";
		Query<?> query = getSession().createQuery(hqlstr);
		query.setParameter("Time", Time);
		query.setParameter("Date", Date);
		query.setParameter("Employee", Emp);
		query.executeUpdate();
	}

	
	public void UpdateStatus(Map<String, String> usersResultMap, java.sql.Date Date, String Status) {
		String hqlstr = "Update Attendance SET Status=:Status where Date=:Date and EmpId=:EmployeeID";
		Query<?> query = getSession().createQuery(hqlstr);
		query.setParameter("Status", Status);
		query.setParameter("Date", Date);
		query.setParameter("EmployeeID", usersResultMap.get("EmployeeID"));
		query.executeUpdate();
	}

	//查詢當日所有出勤資料
	public List<Attendance> InquiryAllToday(String todaystr) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hqlstr = "from Attendance where Date like:today order by Date";
		Query<Attendance> query = getSession().createQuery(hqlstr, Attendance.class);
		query.setParameter("today", todaystr);
		List<Attendance> AllToday = query.list();
		session.getTransaction().commit();
		session.close();
		return AllToday;
	}

	//更新出勤狀態欄位
	public void UpdateAttendanceStatus(java.sql.Date Date, int Id, String Status, String LeaveType) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hqlstr = "Update Attendance SET Status=:Status ,LeaveType=:LeaveType where Date=:Date and EmpId=:EmployeeID";
		Query<?> query = session.createQuery(hqlstr);
		query.setParameter("Status", Status);
		query.setParameter("LeaveType", LeaveType);
		query.setParameter("Date", Date);
		query.setParameter("EmployeeID", Id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	//建立空白出勤欄位
	public void NewAttendance(Employee Emp, java.sql.Date Date) {
		Session session = sessionFactory.openSession();
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

	//計算月份出勤異常次數
	public int CountError(Employee Emp, String month) {
		getSession().beginTransaction();
		String hqlstr = "Select Count(Status) from Attendance where EmpId=:Emp and Date like :month and Status='異常'";
		Query<?> query = getSession().createQuery(hqlstr);
		query.setParameter("Emp", Emp);
		query.setParameter("month", month + "%");
		Object result = query.uniqueResult();
		int countError = Integer.parseInt(result.toString());
		getSession().getTransaction().commit();
		getSession().close();
		return countError;
	}

	public void DeleteTodayAttendance(java.sql.Date Date) {
		String hqlstr = "Delete from Attendance where Date=:Date";
		Query<?> query = getSession().createQuery(hqlstr);
		query.setParameter("Date", Date);
		query.executeUpdate();
	}

	public void UpdateOKAttemdance(java.sql.Date Date) {
		String hqlstr = "Update Attendance SET StartTime='08:00:00' ,EndTime='17:00:00' where Date=:Date";
		Query<?> query = getSession().createQuery(hqlstr);
		query.setParameter("Date", Date);
		query.executeUpdate();
	}

	public void UpdateStartNGAttemdance(java.sql.Date Date) {
		String hqlstr = "Update Attendance SET StartTime='08:00:01' ,EndTime='17:00:00' where Date=:Date";
		Query<?> query = getSession().createQuery(hqlstr);
		query.setParameter("Date", Date);
		query.executeUpdate();
	}

}
