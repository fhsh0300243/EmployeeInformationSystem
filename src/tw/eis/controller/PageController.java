package tw.eis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.ApplyForLeave;
import tw.eis.model.ApplyForLeaveService;
import tw.eis.model.EmployeeLeaveDetail;
import tw.eis.model.EmployeeLeaveDetailService;
import tw.eis.model.Users;

@Controller
@SessionAttributes(names = { "LoginOK", "usersResultMap", "errorMsgMap" })
public class PageController {

	private ApplyForLeaveService aService;
	private EmployeeLeaveDetailService eldService;

	@Autowired
	public PageController(ApplyForLeaveService aService, EmployeeLeaveDetailService eldService) {
		this.aService = aService;
		this.eldService = eldService;
	}

	// 強制設定Session-----開始(測試用以下刪除)
	@RequestMapping(path = "/preleave", method = RequestMethod.GET)
	public String preApplyPage(Model model) {

		// 強制設定LoginOK
		Users uBean = new Users();
		uBean.setEmployeeID(1);
		uBean.setUserName("陳小樂");
		uBean.setUserPassword("test123");
		uBean.setTitle("課長");
		uBean.setDepartment("RD");

//		uBean.setEmployeeID(2);
//		uBean.setUserName("李小明");
//		uBean.setUserPassword("test123");
//		uBean.setTitle("工程師");
//		uBean.setDepartment("RD");

//		uBean.setEmployeeID(3);
//		uBean.setUserName("王小富");
//		uBean.setUserPassword("test123");
//		uBean.setTitle("工程師");
//		uBean.setDepartment("RD");
		model.addAttribute("LoginOK", uBean);
		// 強制設定usersResultMap
		Map<String, String> usersResultMap = new HashMap<String, String>();
		usersResultMap.put("EmployeeID", String.valueOf(uBean.getEmployeeID()));
		usersResultMap.put("UserName", uBean.getUserName());
		usersResultMap.put("UserPassword", uBean.getUserPassword());
		usersResultMap.put("Title", uBean.getTitle());
		usersResultMap.put("Department", uBean.getDepartment());
		model.addAttribute("usersResultMap", usersResultMap);
		// 強制設定errorMsgMap
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		model.addAttribute("errorMsgMap", errorMsgMap);

		return "LeaveMain";

	}
	// 強制設定Session-----結束(測試用以上刪除)

	@RequestMapping(path = "/preLoginLeave", method = RequestMethod.GET)
	public String preLeaveMainPage() {
		return "LeaveMain";
	}

	@RequestMapping(path = "/preapplypage", method = RequestMethod.GET)
	public String enterApplyPage(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap,
			@ModelAttribute("errorMsgMap") Map<String, String> errorMsgMap, Model model) {

		if (model.getAttribute("LoginOK") != null) {
			model.addAttribute("selSH", aService.getStartHoursTag());
			model.addAttribute("selEH", aService.getEndHoursTag());
			Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));
			model.addAttribute("selLT", eldService.getLeaveTypeTag(employeeID));
			return "ApplyPage";
		} else {
			errorMsgMap.put("LoginError", "請先登入，才能使用相關功能。");
			model.addAttribute("errorMsgMap", errorMsgMap);
			return "UserLogin";
		}
	}

	@RequestMapping(path = "/preapplyrecord", method = RequestMethod.GET)
	public String enterApplyRecord(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap,
			@ModelAttribute("errorMsgMap") Map<String, String> errorMsgMap, Model model) {
		if (model.getAttribute("LoginOK") != null) {
			Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));
			List<ApplyForLeave> ApplyList = aService.queryApplyByEID(employeeID);
			model.addAttribute("ApplyList", ApplyList);
			return "ApplyRecord";
		} else {
			errorMsgMap.put("LoginError", "請先登入，才能使用相關功能。");
			model.addAttribute("errorMsgMap", errorMsgMap);
			return "UserLogin";
		}
	}

	@RequestMapping(path = "/preleavetype", method = RequestMethod.GET)
	public String enterLeaveType(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap,
			@ModelAttribute("errorMsgMap") Map<String, String> errorMsgMap, Model model) {
		if (model.getAttribute("LoginOK") != null) {
			Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));
			List<EmployeeLeaveDetail> LeaveList = eldService.queryValidLTByEID(employeeID);
			model.addAttribute("LeaveList", LeaveList);
			return "LeaveType";
		} else {
			errorMsgMap.put("LoginError", "請先登入，才能使用相關功能。");
			model.addAttribute("errorMsgMap", errorMsgMap);
			return "UserLogin";
		}
	}

	@RequestMapping(path = "/preunsignedpage", method = RequestMethod.GET)
	public String enterUnsignedPage(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap,
			@ModelAttribute("errorMsgMap") Map<String, String> errorMsgMap, Model model) {
		if (model.getAttribute("LoginOK") != null) {
			Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));
			List<ApplyForLeave> SignList = aService.queryUnsignedApplyBySID(employeeID);
			model.addAttribute("SignList", SignList);
			return "UnsignedPage";
		} else {
			errorMsgMap.put("LoginError", "請先登入，才能使用相關功能。");
			model.addAttribute("errorMsgMap", errorMsgMap);
			return "UserLogin";
		}
	}

	@RequestMapping(path = "/presigningpage", method = RequestMethod.GET)
	public String enterSigningPage(@ModelAttribute("errorMsgMap") Map<String, String> errorMsgMap,
			@RequestParam("applyId") int applyID, Model model) {
		if (model.getAttribute("LoginOK") != null) {
			ApplyForLeave ApplyList = aService.queryApplyByAID(applyID);
			model.addAttribute("ApplyList", ApplyList);
			return "SigningPage";
		} else {
			errorMsgMap.put("LoginError", "請先登入，才能使用相關功能。");
			model.addAttribute("errorMsgMap", errorMsgMap);
			return "UserLogin";
		}
	}

	@RequestMapping(path = "/presignedpage", method = RequestMethod.GET)
	public String enterSignedPage(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap,
			@ModelAttribute("errorMsgMap") Map<String, String> errorMsgMap, Model model) {
		if (model.getAttribute("LoginOK") != null) {
			Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));
			List<ApplyForLeave> SignList = aService.querySignedApplyBySID(employeeID);
			model.addAttribute("SignList", SignList);
			return "SignedPage";
		} else {
			errorMsgMap.put("LoginError", "請先登入，才能使用相關功能。");
			model.addAttribute("errorMsgMap", errorMsgMap);
			return "UserLogin";
		}
	}

	@RequestMapping(path = "/preleavedetail", method = RequestMethod.GET)
	public String enterLeaveDetail(@ModelAttribute("errorMsgMap") Map<String, String> errorMsgMap,
			@RequestParam("applyId") int applyID, Model model) {
		if (model.getAttribute("LoginOK") != null) {
			ApplyForLeave ApplyList = aService.queryApplyByAID(applyID);
			model.addAttribute("ApplyList", ApplyList);
			return "LeaveDetail";
		} else {
			errorMsgMap.put("LoginError", "請先登入，才能使用相關功能。");
			model.addAttribute("errorMsgMap", errorMsgMap);
			return "UserLogin";
		}
	}
}
