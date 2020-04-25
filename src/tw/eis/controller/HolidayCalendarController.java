package tw.eis.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.Employee;
import tw.eis.model.EmployeeService;
import tw.eis.model.HolidayCalendar;
import tw.eis.model.HolidayCalendarService;
import tw.eis.model.Users;

@Controller
@SessionAttributes(names = { "LoginOK", "usersResultMap", "errorMsgMap" })
public class HolidayCalendarController {

	private HolidayCalendarService HCService;
	private EmployeeService eService;

	@Autowired
	public HolidayCalendarController(HolidayCalendarService HCService) {
		this.HCService = HCService;
	}

	@RequestMapping(path = "/InqueryCalendar", method = RequestMethod.GET)
	public String InqueryCalendar(@ModelAttribute("LoginOK") Users userBean,
			HttpServletRequest request) {
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			List<HolidayCalendar> calenderlist = HCService.InqueryCalendar(year);
			request.setAttribute("calenderlist", calenderlist);
			return "HolidayCalendarSetup";
	}

	@RequestMapping(path = "/HolidayAction", method = RequestMethod.POST)
	public String HolidayAction(@ModelAttribute("LoginOK") Users userBean,
			@RequestParam("action") int action, @RequestParam("date") String date,
			@RequestParam("dateType") String dateType, @RequestParam("remark") String remark,
			HttpServletRequest request) {
		Employee Emp = userBean.getEmployee();
		System.out.println(date);
		if (action == 1) {
			HCService.InsertCalendar(Emp, date, dateType, remark);
		} else {
			HCService.UpdateCalendar(Emp, date, dateType, remark);
		}
		return "redirect:/InqueryCalendar";
	}

	@RequestMapping(path = "/DeleteCalendar", method = RequestMethod.POST)
	public String DeleteCalendar(@RequestParam("Date") List<String> date) {
		HCService.DeleteCalendar(date);
		return "redirect:/InqueryCalendar";
	}

}
