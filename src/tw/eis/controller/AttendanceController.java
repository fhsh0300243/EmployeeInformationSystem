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
		request.setAttribute("attlist", attlist); // 利用request傳送資料
		request.setAttribute("month", month);
		return "AttendanceOwnPage";	// 回到個人查詢頁面
	}

	// 主管執行員工出勤查詢(月份)
	@RequestMapping(path = "/InquiryAttendanceByBoss", method = RequestMethod.GET)
	public String InquiryAttendanceByBoss(@RequestParam("EmpId") String EmpId, @RequestParam("month") String month,
			HttpServletRequest request) throws Exception {
		int id = Integer.parseInt(EmpId);	//將EmpId字串轉為int id
		List<Attendance> attlist = AttService.InquiryAttendance(id, month);
		request.setAttribute("attlist", attlist); // 利用request傳送資料
		request.setAttribute("month", month);
		return "AttendanceStaffPage"; // 轉至顯示員工出勤資料頁面
	}

	// 主管執行部門出勤查詢
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/InquiryAttendanceDepartment", method = RequestMethod.POST)
	public String InquiryAttendanceDepartment(@ModelAttribute("LoginOK") Users userBean,
			@RequestParam("month") String month, HttpServletRequest request) throws Exception {
		Map<Employee, Integer> map = new HashMap<Employee, Integer>();	//宣告HashMap存放員工、異常次數
		int level = userBean.getEmployee().getLevel();	//取得使用者權限level
		String Department = userBean.getEmployee().getDepartment();	//取得使用者部門
		if (level == 2 || level == 3 || level == 4) {
			List<Employee> AllEmp = EmService.allEmpIdforTask();	//查詢所有員工資料
			for (Employee element : AllEmp) {
				if (element.getLevel() <= level && element.getDepartment().equals(Department)) {	//若員工職等<=使用者職等，相同部門
					int countError = AttService.CountError(element, month);	//計算該月份出勤異常次數
					map.put(element, countError);	//存入map中
				}
			}
			List<Map.Entry> entryList = new ArrayList<>(map.entrySet());	//進行map的value排序
			Comparator<Map.Entry> sortByValue = (e1, e2) -> {
				return ((Integer) e2.getValue()).compareTo((Integer) e1.getValue());
			};
			Collections.sort(entryList, sortByValue);
			Map<Employee, Integer> countMap = new LinkedHashMap<>();
			for (Map.Entry e : entryList) {
				countMap.put((Employee) e.getKey(), (Integer) e.getValue()); // 依value排序的Map
			}
			request.setAttribute("month", month);	// 利用request傳送資料
			request.setAttribute("countMap", countMap);
			return "AttendanceDepartmentPage";	//返回部門查詢頁面
		} else {
			request.setAttribute("errormsg", "您的權限不足");
			return "AttendanceDepartmentPage";
		}
	}

	// 執行個人當日出勤查詢
	@RequestMapping(path = "/InquiryToday", method = RequestMethod.GET)
	public String InquiryToday(@ModelAttribute("LoginOK") Users userBean, HttpServletRequest request, Model model)
			throws Exception {
		List<Attendance> myPunch = AttService.InquiryToday(userBean.getEmployeeID()); // 查詢今日出勤資料
		request.setAttribute("myPunch", myPunch); // 利用request傳送資料
		return "AttendancePunchPage"; // 回到打卡頁面
	}

	// 執行打卡動作
	@RequestMapping(path = "/PunchAction", method = RequestMethod.POST)
	public String PunchAction(@ModelAttribute("LoginOK") Users userBean) throws Exception {
		try {
			Employee Emp = userBean.getEmployee(); // 取得員工
			InetAddress localIp; // 取得IP位址
			localIp = InetAddress.getLocalHost();
			String ip = localIp.getHostAddress();

			if (ip.equals("192.168.1.127") || ip.equals("192.168.137.1") || ip.equals("192.168.27.143")
					|| ip.equals("192.168.24.69")) { // IP位址相同才可打卡
				SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd"); // 取得日期及時間
				SimpleDateFormat nowtime = new SimpleDateFormat("HH:mm:ss");
				nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
				nowtime.setTimeZone(TimeZone.getTimeZone("GMT+8"));
				String datestr = nowdate.format(new Date());
				String timestr = nowtime.format(new Date());
				Date utilDate = nowdate.parse(datestr);
				Date utilTime = nowtime.parse(timestr);
				java.sql.Date Date = new java.sql.Date(utilDate.getTime()); // 轉型為java.sql.Date/Time
				java.sql.Time Time = new java.sql.Time(utilTime.getTime());

				Calendar cal = Calendar.getInstance(); // 使用Calendar類別取得完整日期
				cal.set(Calendar.HOUR_OF_DAY, 17); // 設定時間
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				Date Time1700 = cal.getTime();
				Date now = new Date();
				String DayType = null; // 宣告DayType(上班日/休假)

				List<Attendance> myPunch = AttService.InquiryToday(userBean.getEmployeeID()); // 取得使用者當天出勤資料
				List<HolidayCalendar> TodayCalendar = HCService.InqueryCalendarToday(datestr); // 取得今日行事曆資料

				if (TodayCalendar == null || TodayCalendar.size() < 1) { // 若無行事曆資料
					if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY // 判斷是否為六日
							|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
						DayType = "休假";
					} else {
						DayType = "上班日";
					}
				} else {
					if (TodayCalendar.get(0).getDateType().equals("補班")) { // 若有行事曆資料且種類為補班
						DayType = "上班日";
					} else {
						DayType = "休假";
					}
				}

				if (DayType.equals("上班日")) { // 若為上班日
					if (now.before(Time1700)) { // 若現在時間在17:00前
						if (myPunch.get(0).getStartTime() == null) { // 若沒有上班紀錄
							AttService.UpdateStartTime(Emp, Date, Time);
						} else {
							AttService.UpdateEndTime(Emp, Date, Time);
						}
					} else {
						AttService.UpdateEndTime(Emp, Date, Time);
					}
				} else {
					if (now.before(Time1700)) { // 若現在時間在17:00後
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
		return "redirect:/InquiryToday"; // 執行當日出勤查詢
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
	public String UpdateOKAttemdance(HttpServletRequest request) throws Exception {
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String datestr = nowdate.format(new Date());
		Date utilDate = nowdate.parse(datestr);
		java.sql.Date Date = new java.sql.Date(utilDate.getTime());
		AttService.UpdateOKAttemdance(Date);
		return "redirect:/InquiryToday";
	}

	// 更新為異常上班
	@RequestMapping(path = "/UpdateStartNGAttemdance", method = RequestMethod.GET)
	public String UpdateStartNGAttemdance(HttpServletRequest request) throws Exception {
		SimpleDateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String datestr = nowdate.format(new Date());
		Date utilDate = nowdate.parse(datestr);
		java.sql.Date Date = new java.sql.Date(utilDate.getTime());
		AttService.UpdateStartNGAttemdance(Date);
		return "redirect:/InquiryToday";
	}

}
