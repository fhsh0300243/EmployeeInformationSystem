package tw.eis.controller;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.Attendance;
import tw.eis.model.AttendanceService;
import tw.eis.model.Employee;
import tw.eis.model.EmployeeService;
import tw.eis.model.HolidayCalendar;
import tw.eis.model.HolidayCalendarService;
import tw.eis.model.Users;

@Controller
@SessionAttributes(value = { "empID", "EmployeeID", "LoginOK" })
public class AttendanceController {

	private AttendanceService AttService;
	private EmployeeService EmService;
	private HolidayCalendarService HCService;

	@Autowired
	public AttendanceController(AttendanceService AttService, EmployeeService EmService,
			HolidayCalendarService HCService) {
		this.AttService = AttService;
		this.EmService = EmService;
		this.HCService = HCService;
	}

	// 轉至個人查詢頁面
	@RequestMapping(path = "/gotoAttendanceOwnPage", method = RequestMethod.GET)
	public String gotoAttendanceOwnPage() {
		return "AttendanceOwnPage";
	}

	// 轉至部門查詢頁面
	@RequestMapping(path = "/gotoAttendanceDepartmentPage", method = RequestMethod.GET)
	public String goToInquiryDepartmentPage() {
		return "AttendanceDepartmentPage";
	}

	// 執行個人出勤查詢(月份)
	@RequestMapping(path = "/InquiryAttendance", method = RequestMethod.POST)
	public String InquiryAttendance(@ModelAttribute("LoginOK") Users userBean, @RequestParam("month") String month,
			HttpServletRequest request) throws Exception {
		List<Attendance> attlist = AttService.InquiryAttendance(userBean.getEmployeeID(), month);
		request.setAttribute("attlist", attlist);
		request.setAttribute("month", month);
		return "AttendanceOwnPage";
	}

	// 主管執行員工出勤查詢(月份)
	@RequestMapping(path = "/InquiryAttendanceByBoss", method = RequestMethod.GET)
	public String InquiryAttendanceByBoss(@RequestParam("EmpId") String EmpId, @RequestParam("month") String month,
			HttpServletRequest request) throws Exception {
		int id = Integer.parseInt(EmpId);
		List<Attendance> attlist = AttService.InquiryAttendance(id, month);
		request.setAttribute("attlist", attlist);
		request.setAttribute("month", month);
		return "AttendanceStaffPage";
	}

	// 主管執行部門出勤查詢
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/InquiryAttendanceDepartment", method = RequestMethod.POST)
	public String InquiryAttendanceDepartment(@ModelAttribute("LoginOK") Users userBean,
			@RequestParam("month") String month, HttpServletRequest request) throws Exception {
		Map<Employee, Integer> map = new HashMap<Employee, Integer>();
		int level = userBean.getEmployee().getLevel();
		String Department = userBean.getEmployee().getDepartment();
		if (level == 2 || level == 3 || level == 4) {
			List<Employee> AllEmp = EmService.allEmpIdforTask();

			for (Employee element : AllEmp) {
				if (element.getLevel() <= level && element.getDepartment().equals(Department)) {
					int countError = AttService.CountError(element, month);
					System.out.println(countError);
					map.put(element, countError);
				}
			}
			List<Map.Entry> entryList = new ArrayList<>(map.entrySet());
			Comparator<Map.Entry> sortByValue = (e1, e2) -> {
				return ((Integer) e2.getValue()).compareTo((Integer) e1.getValue());
			};
			Collections.sort(entryList, sortByValue);
			Map<Employee, Integer> countMap = new LinkedHashMap<>();
			for (Map.Entry e : entryList) {
				countMap.put((Employee) e.getKey(), (Integer) e.getValue()); // 依value排序的Map
			}
			request.setAttribute("month", month);
			request.setAttribute("countMap", countMap);
			return "AttendanceDepartmentPage";
		} else {
			request.setAttribute("errormsg", "您的權限不足");
			return "AttendanceDepartmentPage";
		}
	}

	// 執行個人當日出勤查詢
	@RequestMapping(path = "/InquiryToday", method = RequestMethod.GET)
	public String InquiryToday(@ModelAttribute("LoginOK") Users userBean, HttpServletRequest request, Model model)
			throws Exception {
		List<Attendance> myPunch = AttService.InquiryToday(userBean.getEmployeeID());
		request.setAttribute("myPunch", myPunch);
		return "AttendancePunchPage";
	}

	// 執行打卡動作
	@RequestMapping(path = "/PunchAction", method = RequestMethod.POST)
	public String PunchAction(@ModelAttribute("LoginOK") Users userBean) throws Exception {
		try {
			Employee Emp = userBean.getEmployee();
			InetAddress localIp;
			localIp = InetAddress.getLocalHost();
			String ip = localIp.getHostAddress();
			System.out.println("IP:" + ip);

			if (ip.equals("192.168.1.127") || ip.equals("192.168.137.1") || ip.equals("192.168.27.143")) {
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
				cal.set(Calendar.HOUR_OF_DAY, 17);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				Date Time1700 = cal.getTime();
				Date now = new Date();
				String DayType = null;
				List<Attendance> myPunch = AttService.InquiryToday(userBean.getEmployeeID());
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
				if (DayType.equals("上班日")) {
					if (now.before(Time1700)) {
						if (myPunch.get(0).getStartTime() == null) {
							AttService.UpdateStartTime(Emp, Date, Time);
						} else {
							AttService.UpdateEndTime(Emp, Date, Time);
						}
					} else {
						AttService.UpdateEndTime(Emp, Date, Time);
					}
				} else {
					if (now.before(Time1700)) {
						if (myPunch == null || myPunch.size() == 0) {
							AttService.InsertStartTime(Emp, Date, Time);
						} else {
							AttService.UpdateEndTime(Emp, Date, Time);
						}
					} else {
						if (myPunch == null || myPunch.size() == 0) {
							AttService.InsertEndTime(Emp, Date, Time);
						} else {
							AttService.UpdateEndTime(Emp, Date, Time);
						}
					}
				}
				System.out.println("IP正確");
			} else {
				System.out.println("IP錯誤");
			}
		} catch (Exception e) {
			System.out.println("e:" + e);
		}
		return "redirect:/InquiryToday";
	}

	// 清除今日出勤表
	@RequestMapping(path = "/DeleteTodayAttendance", method = RequestMethod.GET)
	public String deleteTodayAttendance(@ModelAttribute("LoginOK") Users userBean, HttpServletRequest request)
			throws Exception {
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String datestr = nowdate.format(new Date());
		Date utilDate = nowdate.parse(datestr);
		java.sql.Date Date = new java.sql.Date(utilDate.getTime());
		AttService.DeleteTodayAttendance(Date);
		return "redirect:/InquiryToday";
	}

	// 更新為正常上下班
	@RequestMapping(path = "/UpdateOKAttemdance", method = RequestMethod.GET)
	public String UpdateOKAttemdance(@ModelAttribute("LoginOK") Users userBean, HttpServletRequest request)
			throws Exception {
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String datestr = nowdate.format(new Date());
		Date utilDate = nowdate.parse(datestr);
		java.sql.Date Date = new java.sql.Date(utilDate.getTime());
		AttService.UpdateOKAttemdance(userBean.getEmployee(), Date);
		return "redirect:/InquiryToday";
	}

	// 更新為異常上班
	@RequestMapping(path = "/UpdateStartNGAttemdance", method = RequestMethod.GET)
	public String UpdateStartNGAttemdance(@ModelAttribute("LoginOK") Users userBean, HttpServletRequest request)
			throws Exception {
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String datestr = nowdate.format(new Date());
		Date utilDate = nowdate.parse(datestr);
		java.sql.Date Date = new java.sql.Date(utilDate.getTime());
		AttService.UpdateStartNGAttemdance(userBean.getEmployee(), Date);
		return "redirect:/InquiryToday";
	}

	// 更新為異常下班
	@RequestMapping(path = "/UpdateEndNGAttemdance", method = RequestMethod.GET)
	public String UpdateEndNGAttemdance(@ModelAttribute("LoginOK") Users userBean, HttpServletRequest request)
			throws Exception {
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String datestr = nowdate.format(new Date());
		Date utilDate = nowdate.parse(datestr);
		java.sql.Date Date = new java.sql.Date(utilDate.getTime());
		AttService.UpdateEndNGAttemdance(userBean.getEmployee(), Date);
		return "redirect:/InquiryToday";
	}
}
