package tw.eis.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.eis.model.HolidayCalendar;
import tw.eis.model.HolidayCalendarDAO;

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
	
	public List<HolidayCalendar> InqueryCalendarToday(String datestr) {
		return calendarDao.InqueryCalendarToday(datestr);
	}

	public void InsertCalendar(Employee Emp, String date, String dateType, String remark) {
		calendarDao.InsertCalendar(Emp, date, dateType, remark);
	}

	public void UpdateCalendar(Employee Emp, String date, String dateType, String remark) {
		calendarDao.UpdateCalendar(Emp, date, dateType, remark);
	}

	public void DeleteCalendar(List<String> date) {
		calendarDao.DeleteCalendar(date);
	}

}
