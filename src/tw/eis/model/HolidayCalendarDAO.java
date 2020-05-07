package tw.eis.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class HolidayCalendarDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public HolidayCalendarDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	// 查詢行事曆(年)
	public List<HolidayCalendar> InqueryCalendar(int year) {
		String hqlstr = "from HolidayCalendar where Date like :Year order by Date";	
		Query<HolidayCalendar> query = getSession().createQuery(hqlstr, HolidayCalendar.class);
		query.setParameter("Year", year + "%");	//模糊搜尋
		List<HolidayCalendar> calenderlist = query.list();
		return calenderlist;
	}

	public List<HolidayCalendar> InqueryCalendarToday(String todaystr) {	//帶入日期字串，回傳List<HolidayCalendar>
		Session session = sessionFactory.openSession();	//使用不同資料表所以手動openSession
		session.beginTransaction();
		String hqlstr = "from HolidayCalendar where Date =:Date";
		Query<HolidayCalendar> query = getSession().createQuery(hqlstr, HolidayCalendar.class);
		query.setParameter("Date", todaystr);
		List<HolidayCalendar> calenderlist = query.list();
		session.getTransaction().commit();
		session.close();	//使用不同資料表所以手動close
		return calenderlist;
	}

	//新增行事曆
	public void InsertCalendar(Employee Emp, java.sql.Date Date, String dateType, String remark) {
		HolidayCalendar calendar = new HolidayCalendar();
		calendar.setDate(Date);
		calendar.setDateType(dateType);
		calendar.setRemark(remark);
		calendar.setEmployee(Emp);
		getSession().save(calendar);
	}

	//修改行事曆
	public void UpdateCalendar(Employee Emp, java.sql.Date Date, String dateType, String remark) {
		String hqlstr = "Update HolidayCalendar SET DateType=:DateType , Remark=:Remark ,EmpId=:EmployeeID where Date=:Date";
		Query query = getSession().createQuery(hqlstr);
		query.setParameter("DateType", dateType);
		query.setParameter("Remark", remark);
		query.setParameter("EmployeeID", Emp);
		query.setParameter("Date", Date);
		query.executeUpdate();
	}
	
	//刪除行事曆
	public void DeleteCalendar(List<String> date) {
		for (String element : date) {
			String hqlstr = "Delete from HolidayCalendar where Date =:Date";
			Query query = getSession().createQuery(hqlstr);
			query.setParameter("Date", element);
			query.executeUpdate();
		}
	}

	
	public List<HolidayCalendar> queryCalendarByDate(String date) {
		String hqlstr = "from HolidayCalendar where Date =?0";
		Query<HolidayCalendar> query = getSession().createQuery(hqlstr, HolidayCalendar.class);
		query.setParameter(0, date);
		List<HolidayCalendar> list = query.list();
		return list;
	}

}
