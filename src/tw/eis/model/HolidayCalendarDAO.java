package tw.eis.model;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import tw.eis.model.HolidayCalendar;

@Repository
public class HolidayCalendarDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public HolidayCalendarDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<HolidayCalendar> InqueryCalendar(int year) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hqlstr = "from HolidayCalendar where Date like :Year order by Date";
			Query<HolidayCalendar> query = session.createQuery(hqlstr, HolidayCalendar.class);
			query.setParameter("Year", year + "%");
			List<HolidayCalendar> calenderlist = query.list();
			return calenderlist;
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return null;
	}

	public List<HolidayCalendar> InqueryCalendarToday(String todaystr) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hqlstr = "from HolidayCalendar where Date =:Date";
		Query<HolidayCalendar> query = session.createQuery(hqlstr, HolidayCalendar.class);
		query.setParameter("Date", todaystr);
		List<HolidayCalendar> calenderlist = query.list();
		session.getTransaction().commit();
		session.close();
		return calenderlist;
	}

	public void InsertCalendar(Employee Emp, String date, String dateType, String remark) {
		try {
			Session session = sessionFactory.getCurrentSession();
			SimpleDateFormat nowdate = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date utildate = nowdate.parse(date);
			java.sql.Date Date = new java.sql.Date(utildate.getTime());
			HolidayCalendar calendar = new HolidayCalendar();
			calendar.setDate(Date);
			calendar.setDateType(dateType);
			calendar.setRemark(remark);
			calendar.setEmployee(Emp);
			session.save(calendar);
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
	}

	public void UpdateCalendar(Employee Emp, String date, String dateType, String remark) {
		try {
			Session session = sessionFactory.getCurrentSession();
			SimpleDateFormat nowdate = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date utildate = nowdate.parse(date);
			java.sql.Date Date = new java.sql.Date(utildate.getTime());
			String hqlstr = "Update HolidayCalendar SET DateType=:DateType , Remark=:Remark ,EmpId=:EmployeeID where Date=:Date";
			Query query = session.createQuery(hqlstr);
			query.setParameter("DateType", dateType);
			query.setParameter("Remark", remark);
			query.setParameter("EmployeeID", Emp);
			query.setParameter("Date", Date);
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
	}

	public void DeleteCalendar(List<String> date) {
		try {
			Session session = sessionFactory.getCurrentSession();
			for (String element : date) {
				String hqlstr = "Delete from HolidayCalendar where Date =:Date";
				Query query = session.createQuery(hqlstr);
				query.setParameter("Date", element);
				query.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
	}

	public List<HolidayCalendar> queryCalendarByDate(String date) {
		Session session = sessionFactory.getCurrentSession();
		String hqlstr = "from HolidayCalendar where Date =?0";
		Query<HolidayCalendar> query = session.createQuery(hqlstr, HolidayCalendar.class);
		query.setParameter(0, date);
		List<HolidayCalendar> list = query.list();
		return list;
	}

}
