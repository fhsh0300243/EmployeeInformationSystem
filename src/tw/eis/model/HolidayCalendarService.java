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

	public boolean InsertCalendar(Map<String, String> usersResultMap, String date, String dateType, String remark) {
		return calendarDao.InsertCalendar(usersResultMap, date, dateType, remark);
	}

	public boolean UpdateCalendar(Map<String, String> usersResultMap, String date, String dateType, String remark) {
		return calendarDao.UpdateCalendar(usersResultMap, date, dateType, remark);
	}

	public boolean DeleteCalendar(List<String> date) {
		return calendarDao.DeleteCalendar(date);
	}

}
