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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.eis.model.Attendance;
import tw.eis.model.HolidayCalendar;
import tw.eis.model.Users;
import tw.eis.model.AttendanceService;
import tw.eis.model.HolidayCalendarService;
import tw.eis.model.UsersService;

@Controller
public class AttendanceController {

	private AttendanceService AttService;
	private UsersService UService;
	private HolidayCalendarService HCService;

	@Autowired
	public AttendanceController(AttendanceService AttService, UsersService UService, HolidayCalendarService HCService) {
		this.AttService = AttService;
		this.UService = UService;
		this.HCService = HCService;
	}

	@RequestMapping(path = "/gotoMainAttendancePage", method = RequestMethod.GET)
	public String gotoMainAttendancePage() {
		return "MainAttendancePage";
	}

	@RequestMapping(path = "/InquiryPage", method = RequestMethod.GET)
	public String goToInquiryPage() {
		return "AttendanceInquiry";
	}

	@RequestMapping(path = "/InquiryDepartmentPage", method = RequestMethod.GET)
	public String goToInquiryDepartmentPage(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			HttpServletRequest request) {
		String Title = usersResultMap.get("Title");
		if (Title.equals("staff")) {
			return "MainAttendancePage";
		}
		List<Users> userslist = UService.findStaff(usersResultMap);
		request.setAttribute("userslist", userslist);
		return "AttendanceInquiryDepartment";
	}

	@RequestMapping(path = "/InquiryAttendance", method = RequestMethod.POST)
	public String InquiryAttendance(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			@RequestParam("month") String month, HttpServletRequest request) throws Exception {
		List<Attendance> attlist = AttService.InquiryAttendance(usersResultMap, month);
		request.setAttribute("attlist", attlist);
		return "AttendanceInquiry";
	}

	@RequestMapping(path = "/InquiryToday", method = RequestMethod.GET)
	public String InquiryToday(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			HttpServletRequest request) throws Exception {
		List<Attendance> myPunch = AttService.InquiryToday(usersResultMap);
		request.setAttribute("myPunch", myPunch);
		return "AttendancePunch";
	}

	@RequestMapping(path = "/PunchAction", method = RequestMethod.POST)
	public String PunchAction(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap) throws Exception {

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

			Calendar cal08 = Calendar.getInstance();
			cal08.set(Calendar.HOUR_OF_DAY, 8);
			cal08.set(Calendar.MINUTE, 0);
			cal08.set(Calendar.SECOND, 0);
			Date Time0800 = cal08.getTime();
			Calendar cal17 = Calendar.getInstance();
			cal17.set(Calendar.HOUR_OF_DAY, 17);
			cal17.set(Calendar.MINUTE, 0);
			cal17.set(Calendar.SECOND, 0);
			Date Time1700 = cal17.getTime();
			int year = cal08.get(Calendar.YEAR);
			Date now = new Date();
			String DayType = null;

			List<HolidayCalendar> DateType = HCService.InqueryCalendarToday(datestr);
			if (cal08.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| cal08.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				if (DateType.get(0).getDateType().equals("補班")) {
					DayType = "上班日";
				}
				DayType = "休假";
			} else {
				if (DateType.get(0).getDateType().equals("國定假日")) {
					DayType = "休假";
				}
				DayType = "上班日";
			}
			if (DayType.equals("上班日")) {
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
				List<Attendance> afterPunch = AttService.InquiryToday(usersResultMap);
				Time sql0800 = new Time(0); // 08:00:00
				Time sql1700 = new Time(32400000); // 17:00:00
				boolean a = afterPunch.get(0).getStartTime().before(sql0800);
				boolean b = afterPunch.get(0).getEndTime().after(sql1700);
				if (afterPunch.get(0).getStartTime().before(sql0800) && afterPunch.get(0).getEndTime().after(sql1700)) {
					boolean Update = AttService.UpdateStatus(usersResultMap, Date, "正常");
				} else {
					boolean Update = AttService.UpdateStatus(usersResultMap, Date, "異常");
				}
				return "redirect:/InquiryToday";
			} else {
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
				boolean Update = AttService.UpdateStatus(usersResultMap, Date, "加班");
				return "redirect:/InquiryToday";
			}
		} else {
			System.out.println("IP錯誤");
		}

		return "redirect:/InquiryToday";
	}

}
