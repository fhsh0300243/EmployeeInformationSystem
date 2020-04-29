package tw.eis.controller;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.Attendance;
import tw.eis.model.AttendanceService;
import tw.eis.model.Employee;
import tw.eis.model.EmployeeService;
import tw.eis.model.Users;

@Controller
@SessionAttributes(value = { "empID", "EmployeeID", "LoginOK" })
public class AttendanceController {

	private AttendanceService AttService;
	private EmployeeService EmService;

	@Autowired
	public AttendanceController(AttendanceService AttService, EmployeeService EmService) {
		this.AttService = AttService;
		this.EmService = EmService;
	}

	//轉至個人查詢頁面
	@RequestMapping(path = "/gotoAttendanceOwnPage", method = RequestMethod.GET)	
	public String gotoAttendanceOwnPage() {
		return "AttendanceOwnPage";
	}

	//轉至部門查詢頁面
	@RequestMapping(path = "/gotoAttendanceDepartmentPage", method = RequestMethod.GET)	
	public String goToInquiryDepartmentPage() {
		return "AttendanceDepartmentPage";
	}

	//執行個人出勤查詢(月份)
	@RequestMapping(path = "/InquiryAttendance", method = RequestMethod.POST)	
	public String InquiryAttendance(@ModelAttribute("LoginOK") Users userBean, @RequestParam("month") String month,
			HttpServletRequest request) throws Exception {
		List<Attendance> attlist = AttService.InquiryAttendance(userBean.getEmployeeID(), month);
		request.setAttribute("attlist", attlist);
		request.setAttribute("month", month);
		return "AttendanceOwnPage";
	}

	//主管執行員工出勤查詢(月份)
	@RequestMapping(path = "/InquiryAttendanceByBoss", method = RequestMethod.GET)
	public String InquiryAttendanceByBoss(@RequestParam("EmpId") String EmpId, @RequestParam("month") String month,
			HttpServletRequest request) throws Exception {
		int id = Integer.parseInt(EmpId);
		List<Attendance> attlist = AttService.InquiryAttendance(id, month);
		request.setAttribute("attlist", attlist);
		request.setAttribute("month", month);
		return "AttendanceOwnPage";
	}

	//主管執行部門出勤查詢
	@RequestMapping(path = "/InquiryAttendanceDepartment", method = RequestMethod.POST)
	public String InquiryAttendanceDepartment(@ModelAttribute("LoginOK") Users userBean,
			@RequestParam("month") String month, HttpServletRequest request) throws Exception {
		Map<Employee, Integer> countMap = new HashMap<Employee, Integer>();
		int level = userBean.getEmployee().getLevel();
		String Department = userBean.getEmployee().getDepartment();
		if (level == 2 || level == 3 || level == 4) {
			List<Employee> AllEmp = EmService.allEmpIdforTask();
			for (Employee element : AllEmp) {
				if (element.getLevel() <= level && element.getDepartment().equals(Department)) {
					int countError = AttService.CountError(element, month);
					System.out.println(countError);
					countMap.put(element, countError);
				}
			}
			List<Map.Entry<Employee, Integer>> list = new ArrayList<Map.Entry<Employee, Integer>>(countMap.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<Employee, Integer>>() {
				public int compare(Entry<Employee, Integer> o1, Entry<Employee, Integer> o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});
			request.setAttribute("month", month);
			request.setAttribute("countMap", countMap);
			return "AttendanceDepartmentPage";
		} else {
			request.setAttribute("errormsg", "您的權限不足");
			return "AttendanceDepartmentPage";
		}
	}

	//執行個人當日出勤查詢
	@RequestMapping(path = "/InquiryToday", method = RequestMethod.GET)
	public String InquiryToday(@ModelAttribute("LoginOK") Users userBean, HttpServletRequest request) throws Exception {
		List<Attendance> myPunch = AttService.InquiryToday(userBean.getEmployeeID());
		request.setAttribute("myPunch", myPunch);
		return "AttendancePunchPage";
	}

	//執行打卡動作
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
				List<Attendance> myPunch = AttService.InquiryToday(userBean.getEmployeeID());

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
