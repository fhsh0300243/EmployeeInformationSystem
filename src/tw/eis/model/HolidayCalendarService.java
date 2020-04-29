package tw.eis.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolidayCalendarService {

	private HolidayCalendarDAO calendarDao;

	@Autowired
	public HolidayCalendarService(HolidayCalendarDAO calendarDao) {
		this.calendarDao = calendarDao;
	}

	public List<HolidayCalendar> InqueryCalendar(int year) {
		return calendarDao.InqueryCalendar(year);
	}

	public List<HolidayCalendar> InqueryCalendarToday(String todaystr) {
		return calendarDao.InqueryCalendarToday(todaystr);
	}

	public void InsertCalendar(Employee Emp, java.sql.Date Date, String dateType, String remark) {
		calendarDao.InsertCalendar(Emp, Date, dateType, remark);
	}

	public void UpdateCalendar(Employee Emp, java.sql.Date Date, String dateType, String remark)  {
		calendarDao.UpdateCalendar(Emp, Date, dateType, remark);
	}

	public void DeleteCalendar(List<String> date) {
		calendarDao.DeleteCalendar(date);
	}

	public List<HolidayCalendar> queryCalendarByDate(String date) {
		return calendarDao.queryCalendarByDate(date);
	}

}
