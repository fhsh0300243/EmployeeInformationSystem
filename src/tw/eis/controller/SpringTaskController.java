package tw.eis.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tw.eis.model.Attendance;
import tw.eis.model.AttendanceService;
import tw.eis.model.Employee;
import tw.eis.model.EmployeeService;
import tw.eis.model.HolidayCalendar;
import tw.eis.model.HolidayCalendarService;
import tw.eis.model.UsersService;

@Controller
public class SpringTaskController {

	private AttendanceService AttService;
	private UsersService UService;
	private HolidayCalendarService HCService;
	private EmployeeService EmService;

	@Autowired
	public SpringTaskController(AttendanceService AttService, UsersService UService, HolidayCalendarService HCService,
			EmployeeService EmService) {
		this.AttService = AttService;
		this.UService = UService;
		this.HCService = HCService;
		this.EmService = EmService;
	}

	public void CreateTable0300() {
		try {
			SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat nowtime = new SimpleDateFormat("HH:mm:ss");
			nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			nowtime.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String datestr = nowdate.format(new Date());
			String timestr = nowtime.format(new Date());
			Date utilDate = nowdate.parse(datestr);
			Date utilTime = nowtime.parse(timestr);
			java.sql.Date Date = new java.sql.Date(utilDate.getTime());
			java.sql.Time Time = new java.sql.Time(utilTime.getTime());

			Calendar cal = Calendar.getInstance();
			Date now = new Date();
			String DayType = null;
			String Status = null;

			Calendar cal08 = Calendar.getInstance();
			cal08.set(Calendar.HOUR_OF_DAY, 8);
			cal08.set(Calendar.MINUTE, 0);
			cal08.set(Calendar.SECOND, 1);
			Date Time08 = cal08.getTime();
			List<HolidayCalendar> TodayCalendar = HCService.InqueryCalendarToday(datestr);
			if (TodayCalendar == null || TodayCalendar.size() < 1) {
				if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
						|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					DayType = "休假";
				} else {
					DayType = "上班日";
				}
			} else {
				if (TodayCalendar.get(0).getDateType().equals("補班")) {
					DayType = "上班日";
				} else {
					DayType = "休假";
				}
			}
			List<?> allEmp = EmService.allEmpData();
			System.out.println("allEmp:"+allEmp);
			for (Object element : allEmp) {
				Employee Emp = ((Employee) element);
				boolean New = AttService.NewAttendance(Emp,Date);
			}
			
			
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
	}

	public void CheckStatus0830() {
		try {
			SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat nowtime = new SimpleDateFormat("HH:mm:ss");
			nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			nowtime.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String datestr = nowdate.format(new Date());
			String timestr = nowtime.format(new Date());
			Date utilDate = nowdate.parse(datestr);
			Date utilTime = nowtime.parse(timestr);
			java.sql.Date Date = new java.sql.Date(utilDate.getTime());
			java.sql.Time Time = new java.sql.Time(utilTime.getTime());

			Calendar cal = Calendar.getInstance();
			Date now = new Date();
			String DayType = null;
			String Status = null;

			Calendar cal08 = Calendar.getInstance();
			cal08.set(Calendar.HOUR_OF_DAY, 8);
			cal08.set(Calendar.MINUTE, 0);
			cal08.set(Calendar.SECOND, 1);
			Date Time08 = cal08.getTime();

			List<HolidayCalendar> TodayCalendar = HCService.InqueryCalendarToday(datestr);
			if (TodayCalendar == null || TodayCalendar.size() < 1) {
				if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
						|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					DayType = "休假";
				} else {
					DayType = "上班日";
				}
			} else {
				if (TodayCalendar.get(0).getDateType().equals("補班")) {
					DayType = "上班日";
				} else {
					DayType = "休假";
				}
			}

			List<Attendance> AllToday = AttService.InquiryAllToday();

			if (AllToday != null || AllToday.size() > 0) {
				if (DayType.equals("上班日")) {
					for (Object element : AllToday) {
						String str = ((Attendance) element).getStartTime().toString();
						String[] timeArray = str.split(":");
						int HH = Integer.parseInt(timeArray[0]);
						int mm = Integer.parseInt(timeArray[1]);
						int ss = Integer.parseInt(timeArray[2]);

						Calendar sTime = Calendar.getInstance();
						sTime.set(Calendar.HOUR_OF_DAY, HH);
						sTime.set(Calendar.MINUTE, mm);
						sTime.set(Calendar.SECOND, ss);
						Date StartTime = sTime.getTime();
						int Id = ((Attendance) element).getEmployee().getEmpID();
						if (StartTime.before(Time08)) {
							boolean Update = AttService.UpdateAttendanceStatus(Date, Id, "正常");
						} else {
							boolean Update = AttService.UpdateAttendanceStatus(Date, Id, "異常");
						}
					}
				} else {
					for (Object element : AllToday) {
						int Id = ((Attendance) element).getEmployee().getEmpID();
						boolean Update = AttService.UpdateAttendanceStatus(Date, Id, "加班");
					}
				}
			}
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
	}

}
