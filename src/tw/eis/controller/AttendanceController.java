package tw.eis.controller;

import java.net.InetAddress;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.Attendance;
import tw.eis.model.HolidayCalendar;
import tw.eis.model.Users;
import tw.eis.model.AttendanceService;
import tw.eis.model.EmployeeService;
import tw.eis.model.HolidayCalendarService;
import tw.eis.model.UsersService;

@Controller
@SessionAttributes(value = { "empID", "EmployeeID", "LoginOK" })
public class AttendanceController {

	private AttendanceService AttService;
	private UsersService UService;
	private HolidayCalendarService HCService;
	private EmployeeService EmService;

	@Autowired
	public AttendanceController(AttendanceService AttService, UsersService UService, HolidayCalendarService HCService,
			EmployeeService EmService) {
		this.AttService = AttService;
		this.UService = UService;
		this.HCService = HCService;
		this.EmService = EmService;
	}

	@RequestMapping(path = "/gotoAttendanceOwnPage", method = RequestMethod.GET)
	public String gotoAttendanceOwnPage() {
		return "AttendanceOwnPage";
	}

	@RequestMapping(path = "/gotoAttendanceDepartmentPage", method = RequestMethod.GET)
	public String goToInquiryDepartmentPage(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			HttpServletRequest request) {
			return "AttendanceDepartmentPage";
	}

	@RequestMapping(path = "/InquiryAttendance", method = RequestMethod.POST)
	public String InquiryAttendance(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			@RequestParam("month") String month, HttpServletRequest request) throws Exception {
		List<Attendance> attlist = AttService.InquiryAttendance(usersResultMap.get("EmployeeID"), month);
		request.setAttribute("attlist", attlist);
		return "AttendanceOwnPage";
	}
	
	@RequestMapping(path = "/InquiryAttendanceDepartment", method = RequestMethod.POST)
	public String InquiryAttendanceDepartment(@ModelAttribute("EmployeeID") String empId,@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			@RequestParam("month") String month, HttpServletRequest request) throws Exception {
		int level = 4;
//		try {
//			level = EmService.empData(Integer.parseInt(empId)).getEmpTitle().getLevel();
//		} catch (Exception e) {
//			System.out.println("e:" + e);
//			level = 0;
//		}
		if (level == 2 || level == 3 ) {
			
			
			
			
			return "AttendanceDepartmentPage";
		}
		else if(level == 4){
			List<?> AllEmp= EmService.allEmpData();
			
			
			request.setAttribute("AllEmp", AllEmp);
			return "AttendanceDepartmentPage";
		}else {
			request.setAttribute("errormsg", "您的權限不足");
			return "AttendanceDepartmentPage";
		}
	}

	@RequestMapping(path = "/InquiryToday", method = RequestMethod.GET)
	public String InquiryToday(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			HttpServletRequest request) throws Exception {
		List<Attendance> myPunch = AttService.InquiryToday(usersResultMap);
		request.setAttribute("myPunch", myPunch);
		return "AttendancePunchPage";
	}

	@RequestMapping(path = "/PunchAction", method = RequestMethod.POST)
	public String PunchAction(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap) throws Exception {
		try {
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
				List<Attendance> myPunch = AttService.InquiryToday(usersResultMap);
				if (now.before(Time1700)) {
					if (myPunch == null || myPunch.size() == 0) {
						boolean Insert = AttService.InsertStartTime(usersResultMap, Date, Time);
					} else {
						boolean Update = AttService.UpdateEndTime(usersResultMap, Date, Time);
					}
				} else {
					if (myPunch == null || myPunch.size() == 0) {
						boolean Insert = AttService.InsertEndTime(usersResultMap, Date, Time);
					} else {
						boolean Update = AttService.UpdateEndTime(usersResultMap, Date, Time);
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

}
