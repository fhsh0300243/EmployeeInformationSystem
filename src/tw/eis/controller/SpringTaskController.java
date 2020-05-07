package tw.eis.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.ApplyForLeave;
import tw.eis.model.ApplyForLeaveService;
import tw.eis.model.Attendance;
import tw.eis.model.AttendanceService;
import tw.eis.model.Employee;
import tw.eis.model.EmployeeService;
import tw.eis.model.HolidayCalendar;
import tw.eis.model.HolidayCalendarService;

@Controller
@SessionAttributes(names = { "LoginOK", "usersResultMap", "errorMsgMap" })
public class SpringTaskController {

	private AttendanceService AttService;
	private HolidayCalendarService HCService;
	private EmployeeService EmService;
	private ApplyForLeaveService LeaveService;

	@Autowired
	public SpringTaskController(AttendanceService AttService, HolidayCalendarService HCService,
			EmployeeService EmService, ApplyForLeaveService LeaveService) {
		this.AttService = AttService;
		this.HCService = HCService;
		this.EmService = EmService;
		this.LeaveService = LeaveService;
	}

	// 新建當天出勤資料表
	@RequestMapping(path = "/CreateTable0300", method = RequestMethod.GET)
	public String CreateTable0300() {
		try {
			SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");	//取得日期
			nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String todaystr = nowdate.format(new Date());
			Date utilDate = nowdate.parse(todaystr);
			java.sql.Date Date = new java.sql.Date(utilDate.getTime());
			Calendar cal = Calendar.getInstance();	//使用 java.util.Calendar 類別
			String DayType = null;	//DayType(上班日/休假)

			List<HolidayCalendar> TodayCalendar = HCService.InqueryCalendarToday(todaystr);	//查詢今天行事曆
			if (TodayCalendar == null || TodayCalendar.size() < 1) {
				if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY	//判斷是否為六日
						|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					DayType = "休假";
				} else {
					DayType = "上班日";
				}
			} else {
				if (TodayCalendar.get(0).getDateType().equals("補班")) {	//若今日為補班
					DayType = "上班日";
				} else {
					DayType = "休假";
				}
			}
			if (DayType.equals("上班日")) {
				List<Employee> allEmpId = EmService.allEmpIdforTask();	//查詢所有在職員工
				for (Employee element : allEmpId) {
					AttService.NewAttendance(element, Date);	//建立當日空白出勤欄位
				}
			}
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return "redirect:/InquiryToday";
	}

	// 判斷上班刷卡有無異常(排程)
	@RequestMapping(path = "/CheckStatus0830", method = RequestMethod.GET)
	public String CheckStatus0830() {
		try {
			SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");	//日期格式
			nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String todaystr = nowdate.format(new Date());
			Date utilDate = nowdate.parse(todaystr);
			java.sql.Date Date = new java.sql.Date(utilDate.getTime());	//取得日期

			Calendar cal08 = Calendar.getInstance();	//使用 java.util.Calendar 類別設定8:00:01
			cal08.set(Calendar.HOUR_OF_DAY, 8);
			cal08.set(Calendar.MINUTE, 0);
			cal08.set(Calendar.SECOND, 1);
			Date Time08 = cal08.getTime();

			List<Attendance> AllToday = AttService.InquiryAllToday(todaystr);	//查詢今日所有出勤紀錄
			if (AllToday != null || AllToday.size() > 0) {
				for (Attendance element : AllToday) {	
					int Id = element.getEmployee().getEmpID();	//取得員工ID
					if (element.getStartTime() != null) {	//若有上班打卡資料
						String str = element.getStartTime().toString();	//將時間轉為String字串
						String[] timeArray = str.split(":");	//切割
						int HH = Integer.parseInt(timeArray[0]);
						int mm = Integer.parseInt(timeArray[1]);
						int ss = Integer.parseInt(timeArray[2]);

						Calendar sTime = Calendar.getInstance();	//設定為Calendar格式
						sTime.set(Calendar.HOUR_OF_DAY, HH);
						sTime.set(Calendar.MINUTE, mm);
						sTime.set(Calendar.SECOND, ss);
						Date StartTime = sTime.getTime();

						if (StartTime.before(Time08)) {	//若上班時間在8:00:01前
							AttService.UpdateAttendanceStatus(Date, Id, "正常", "");
						} else {
							AttService.UpdateAttendanceStatus(Date, Id, "異常", "");
						}
					} else {
						AttService.UpdateAttendanceStatus(Date, Id, "異常", "");
					}
				}
			}
			List<ApplyForLeave> TodayLeaveStart = LeaveService.getTodayLeaveforTask(Time08);	//搜尋請假資料表是否有當日8:00:01介於起始結束之間
			for (ApplyForLeave element : TodayLeaveStart) {
				AttService.UpdateAttendanceStatus(Date, element.getEmployeeId().getEmpID(), "請假",	//將狀態更改為請假
						element.getLeaveType());
			}
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return "redirect:/InquiryToday";
	}

	// 判斷全天刷卡有無異常(排程)
	@RequestMapping(path = "/CheckStatus2330", method = RequestMethod.GET)
	public String CheckStatus2330() {
		try {
			SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");	//日期格式
			nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String todaystr = nowdate.format(new Date());
			Date utilDate = nowdate.parse(todaystr);
			java.sql.Date Date = new java.sql.Date(utilDate.getTime());	//取得日期

			Calendar cal17 = Calendar.getInstance();	//使用 java.util.Calendar 類別設定16:59:59
			cal17.set(Calendar.HOUR_OF_DAY, 16);
			cal17.set(Calendar.MINUTE, 59);
			cal17.set(Calendar.SECOND, 59);
			Date Time17 = cal17.getTime();

			List<Attendance> AllToday = AttService.InquiryAllToday(todaystr);	//查詢今日所有出勤紀錄

			if (AllToday != null || AllToday.size() > 0) {
				for (Attendance element : AllToday) {
					if (element.getStatus().equals("正常")) {	//若出勤狀態為正常(為異常則跳過不動作)
						int Id = element.getEmployee().getEmpID();	//取得員工ID
						if (element.getEndTime() != null) {	//若有下班打卡資料
							String str = element.getEndTime().toString();	//將時間轉為String字串
							String[] timeArray = str.split(":");	//切割
							int HH = Integer.parseInt(timeArray[0]);
							int mm = Integer.parseInt(timeArray[1]);
							int ss = Integer.parseInt(timeArray[2]);

							Calendar eTime = Calendar.getInstance();	//設定為Calendar格式
							eTime.set(Calendar.HOUR_OF_DAY, HH);
							eTime.set(Calendar.MINUTE, mm);
							eTime.set(Calendar.SECOND, ss);
							Date EndTime = eTime.getTime();

							if (EndTime.after(Time17)) {	//若下班時間在16:59:59後
								AttService.UpdateAttendanceStatus(Date, Id, "正常", "");
							} else {
								AttService.UpdateAttendanceStatus(Date, Id, "異常", "");
							}
						} else {
							AttService.UpdateAttendanceStatus(Date, Id, "異常", "");
						}
					}
				}
			}
			List<ApplyForLeave> TodayLeaveStart = LeaveService.getTodayLeaveforTask(Time17);	//搜尋請假資料表是否有當日16:59:59介於起始結束之間
			for (ApplyForLeave element : TodayLeaveStart) {
				AttService.UpdateAttendanceStatus(Date, element.getEmployeeId().getEmpID(), "請假",	//將狀態更改為請假
						element.getLeaveType());	
			}
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return "redirect:/InquiryToday";
	}

	// DEMO判斷全天刷卡有無異常
	@RequestMapping(path = "/CheckStatusALL", method = RequestMethod.GET)
	public String CheckStatusALL() {
		try {
			SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
			nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String todaystr = nowdate.format(new Date());
			Date utilDate = nowdate.parse(todaystr);
			java.sql.Date Date = new java.sql.Date(utilDate.getTime());

			Calendar cal08 = Calendar.getInstance();
			cal08.set(Calendar.HOUR_OF_DAY, 8);
			cal08.set(Calendar.MINUTE, 0);
			cal08.set(Calendar.SECOND, 1);
			Date Time08 = cal08.getTime();

			Calendar cal17 = Calendar.getInstance();
			cal17.set(Calendar.HOUR_OF_DAY, 17);
			cal17.set(Calendar.MINUTE, 0);
			cal17.set(Calendar.SECOND, 0);
			Date Time17 = cal17.getTime();

			List<Attendance> AllToday = AttService.InquiryAllToday(todaystr);

			if (AllToday != null || AllToday.size() > 0) {
				for (Attendance element : AllToday) {
					String Status = "異常";
					int Id = element.getEmployee().getEmpID();
					if (element.getStartTime() != null) {
						String str = element.getStartTime().toString();
						String[] timeArray = str.split(":");
						int HH = Integer.parseInt(timeArray[0]);
						int mm = Integer.parseInt(timeArray[1]);
						int ss = Integer.parseInt(timeArray[2]);

						Calendar sTime = Calendar.getInstance();
						sTime.set(Calendar.HOUR_OF_DAY, HH);
						sTime.set(Calendar.MINUTE, mm);
						sTime.set(Calendar.SECOND, ss);
						Date StartTime = sTime.getTime();

						if (StartTime.before(Time08)) {
							Status = "正常";
						} else {
							Status = "異常";
						}
					} else {
						Status = "異常";
					}
					if (Status.equals("正常")) {
						if (element.getEndTime() != null) {
							String str = element.getEndTime().toString();
							String[] timeArray = str.split(":");
							int HH = Integer.parseInt(timeArray[0]);
							int mm = Integer.parseInt(timeArray[1]);
							int ss = Integer.parseInt(timeArray[2]);

							Calendar eTime = Calendar.getInstance();
							eTime.set(Calendar.HOUR_OF_DAY, HH);
							eTime.set(Calendar.MINUTE, mm);
							eTime.set(Calendar.SECOND, ss);
							Date EndTime = eTime.getTime();

							if (EndTime.after(Time17)) {
								Status = "正常";
							} else {
								Status = "異常";
							}
						} else {
							Status = "異常";
						}
					}
					AttService.UpdateAttendanceStatus(Date, Id, Status, "");
				}
			}
			List<ApplyForLeave> TodayLeaveStart = LeaveService.getTodayLeaveforTask(Time08);
			for (ApplyForLeave element : TodayLeaveStart) {
				AttService.UpdateAttendanceStatus(Date, element.getEmployeeId().getEmpID(), "請假",
						element.getLeaveType());
			}
			List<ApplyForLeave> TodayLeaveEnd = LeaveService.getTodayLeaveforTask(Time17);
			for (ApplyForLeave element : TodayLeaveEnd) {
				AttService.UpdateAttendanceStatus(Date, element.getEmployeeId().getEmpID(), "請假",
						element.getLeaveType());
			}
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return "redirect:/InquiryToday";
	}
}
