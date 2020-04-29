package tw.eis.controller;

import java.text.SimpleDateFormat;
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
import tw.eis.model.HolidayCalendar;
import tw.eis.model.HolidayCalendarService;
import tw.eis.model.Users;

@Controller
@SessionAttributes(names = { "LoginOK", "usersResultMap", "errorMsgMap" })
public class HolidayCalendarController {

	private HolidayCalendarService HCService;

	@Autowired
	public HolidayCalendarController(HolidayCalendarService HCService) {
		this.HCService = HCService;
	}

	//查詢當年國定假日行事曆
	@RequestMapping(path = "/InqueryCalendar", method = RequestMethod.GET)
	public String InqueryCalendar(@ModelAttribute("LoginOK") Users userBean,
			HttpServletRequest request) {
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			List<HolidayCalendar> calenderlist = HCService.InqueryCalendar(year);
			request.setAttribute("calenderlist", calenderlist);
			return "HolidayCalendarSetup";
	}

	//新增、修改國定假日行事曆
	@RequestMapping(path = "/HolidayAction", method = RequestMethod.POST)
	public String HolidayAction(@ModelAttribute("LoginOK") Users userBean,
			@RequestParam("action") int action, @RequestParam("date") String date,
			@RequestParam("dateType") String dateType, @RequestParam("remark") String remark,
			HttpServletRequest request) throws Exception {
		Employee Emp = userBean.getEmployee();
		SimpleDateFormat nowdate = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date utildate = nowdate.parse(date);
		java.sql.Date Date = new java.sql.Date(utildate.getTime());
		if (action == 1) {
			HCService.InsertCalendar(Emp, Date, dateType, remark);
		} else {
			HCService.UpdateCalendar(Emp, Date, dateType, remark);
		}
		return "redirect:/InqueryCalendar";
	}

	//刪除國定假日行事曆
	@RequestMapping(path = "/DeleteCalendar", method = RequestMethod.POST)
	public String DeleteCalendar(@RequestParam("Date") List<String> date) {
		HCService.DeleteCalendar(date);
		return "redirect:/InqueryCalendar";
	}

}
