package tw.eis.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import tw.eis.model.HolidayCalendar;
import tw.eis.model.HolidayCalendarService;

@Controller
public class HolidayCalendarController {

	private HolidayCalendarService HCService;

	@Autowired
	public HolidayCalendarController(HolidayCalendarService HCService) {
		this.HCService = HCService;
	}

	@RequestMapping(path = "/InqueryCalendar", method = RequestMethod.GET)
	public String InqueryCalendar(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			HttpServletRequest request) {
		String Department = usersResultMap.get("Department");
		if(Department.equals("HR")) {
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			List<HolidayCalendar> calenderlist = HCService.InqueryCalendar(year);
			request.setAttribute("calenderlist", calenderlist);
			return "HolidayCalendarSetup";
		}
		return "HolidayCalendarSetup";
	}

	@RequestMapping(path = "/HolidayAction", method = RequestMethod.POST)
	public String HolidayAction(@SessionAttribute("usersResultMap") Map<String, String> usersResultMap,
			@RequestParam("action") int action, @RequestParam("date") String date,
			@RequestParam("dateType") String dateType, @RequestParam("remark") String remark,
			HttpServletRequest request) {
		if (action == 1) {
			boolean Insert = HCService.InsertCalendar(usersResultMap, date, dateType, remark);
		} else {
			boolean Update = HCService.UpdateCalendar(usersResultMap, date, dateType, remark);
		}
		return "redirect:/InqueryCalendar";
	}

	@RequestMapping(path = "/DeleteCalendar", method = RequestMethod.POST)
	public String DeleteCalendar(@RequestParam("Date") List<String> date) {
		boolean Delete = HCService.DeleteCalendar(date);
		return "redirect:/InqueryCalendar";
	}

}
