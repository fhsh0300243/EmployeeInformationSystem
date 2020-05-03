package tw.eis.controller;

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
import org.springframework.web.bind.support.SessionStatus;

import tw.eis.model.ApplyForLeave;
import tw.eis.model.ApplyForLeaveService;
import tw.eis.model.EmployeeLeaveDetail;
import tw.eis.model.EmployeeLeaveDetailService;
import tw.eis.model.EmployeeService;

@Controller
@SessionAttributes(names = { "LoginOK", "usersResultMap", "errorMsgMap" })
public class PageController {

	private ApplyForLeaveService aService;
	private EmployeeService eService;
	private EmployeeLeaveDetailService eldService;

	@Autowired
	public PageController(ApplyForLeaveService aService, EmployeeService eService,
			EmployeeLeaveDetailService eldService) {
		this.aService = aService;
		this.eService = eService;
		this.eldService = eldService;
	}

	@RequestMapping(path = "/preapplypage", method = RequestMethod.GET)
	public String enterApplyPage(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap, Model model) {
		model.addAttribute("selSH", aService.getStartHoursTag());
		model.addAttribute("selEH", aService.getEndHoursTag());

		Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));
		model.addAttribute("selLT", eldService.getLeaveTypeTag(employeeID));

		int empLevel = eService.empData(employeeID).getLevel();
		model.addAttribute("empLevel", empLevel);
		String empDept = eService.empData(employeeID).getDepartment();
		model.addAttribute("empDept", empDept);
		return "ApplyPage";
	}

	@RequestMapping(path = "/preapplyrecord", method = RequestMethod.GET)
	public String enterApplyRecord(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap, Model model) {
		Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));

		List<ApplyForLeave> ApplyList = aService.queryApplyByEID(employeeID);
		if (ApplyList != null && ApplyList.size() != 0) {
			for (int i = 0; i < ApplyList.size(); i++) {
				ApplyForLeave newABean = ApplyList.get(i);
				newABean.setLeaveType(newABean.getLeaveType().substring(0, 2));
			}
		}
		model.addAttribute("ApplyList", ApplyList);

		int empLevel = eService.empData(employeeID).getLevel();
		model.addAttribute("empLevel", empLevel);
		String empDept = eService.empData(employeeID).getDepartment();
		model.addAttribute("empDept", empDept);
		return "ApplyRecord";
	}

	@RequestMapping(path = "/preleavetype", method = RequestMethod.GET)
	public String enterLeaveType(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap, Model model) {
		Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));
		List<EmployeeLeaveDetail> LeaveList = eldService.queryValidLTByEID(employeeID);
		model.addAttribute("LeaveList", LeaveList);

		int empLevel = eService.empData(employeeID).getLevel();
		model.addAttribute("empLevel", empLevel);
		String empDept = eService.empData(employeeID).getDepartment();
		model.addAttribute("empDept", empDept);
		return "LeaveType";
	}

	@RequestMapping(path = "/preunsignedpage", method = RequestMethod.GET)
	public String enterUnsignedPage(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap, Model model) {
		Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));

		List<ApplyForLeave> SignList = aService.queryUnsignedApplyBySID(employeeID);
		if (SignList != null && SignList.size() != 0) {
			for (int i = 0; i < SignList.size(); i++) {
				ApplyForLeave newABean = SignList.get(i);
				newABean.setLeaveType(newABean.getLeaveType().substring(0, 2));
			}
		}
		model.addAttribute("SignList", SignList);

		int empLevel = eService.empData(employeeID).getLevel();
		model.addAttribute("empLevel", empLevel);
		String empDept = eService.empData(employeeID).getDepartment();
		model.addAttribute("empDept", empDept);
		return "UnsignedPage";
	}

	@RequestMapping(path = "/presigningpage", method = RequestMethod.GET)
	public String enterSigningPage(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap,
			@ModelAttribute("errorMsgMap") Map<String, String> errorMsgMap, @RequestParam("applyId") int applyID,
			Model model, SessionStatus status) {
		Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));

		ApplyForLeave ApplyList = aService.queryApplyByAID(applyID);
		if (ApplyList != null) {
			ApplyList.setLeaveType(ApplyList.getLeaveType().substring(0, 2));
		}

		int signerID = ApplyList.getSignerId().getEmpID();
		if (employeeID == signerID) {
			model.addAttribute("ApplyList", ApplyList);
			int empLevel = eService.empData(employeeID).getLevel();
			model.addAttribute("empLevel", empLevel);
			String empDept = eService.empData(employeeID).getDepartment();
			model.addAttribute("empDept", empDept);
			return "SigningPage";
		} else {
			status.setComplete();
			errorMsgMap.put("LoginError", "無簽核權限，請重新登入。");
			model.addAttribute("errorMsgMap", errorMsgMap);
			return "UserLogin";
		}
	}

	@RequestMapping(path = "/presignedpage", method = RequestMethod.GET)
	public String enterSignedPage(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap, Model model) {
		Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));

		List<ApplyForLeave> SignList = aService.querySignedApplyBySID(employeeID);
		if (SignList != null && SignList.size() != 0) {
			for (int i = 0; i < SignList.size(); i++) {
				ApplyForLeave newABean = SignList.get(i);
				newABean.setLeaveType(newABean.getLeaveType().substring(0, 2));
			}
		}
		model.addAttribute("SignList", SignList);

		int empLevel = eService.empData(employeeID).getLevel();
		model.addAttribute("empLevel", empLevel);
		String empDept = eService.empData(employeeID).getDepartment();
		model.addAttribute("empDept", empDept);
		return "SignedPage";
	}

	@RequestMapping(path = "/preleavedetail", method = RequestMethod.GET)
	public String enterLeaveDetail(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap,
			@RequestParam("applyId") int applyID, Model model) {
		ApplyForLeave ApplyList = aService.queryApplyByAID(applyID);
		if (ApplyList != null) {
			ApplyList.setLeaveType(ApplyList.getLeaveType().substring(0, 2));
		}
		model.addAttribute("ApplyList", ApplyList);

		Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));
		int empLevel = eService.empData(employeeID).getLevel();
		model.addAttribute("empLevel", empLevel);
		String empDept = eService.empData(employeeID).getDepartment();
		model.addAttribute("empDept", empDept);
		return "LeaveDetail";
	}

	@RequestMapping(path = "/preinsertleavetype", method = RequestMethod.GET)
	public String enterInsertLeaveType(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap,
			@ModelAttribute("errorMsgMap") Map<String, String> errorMsgMap, Model model, SessionStatus status) {
		Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));

		if (eService.empData(employeeID).getDepartment().equalsIgnoreCase("HR")) {
			int empLevel = eService.empData(employeeID).getLevel();
			model.addAttribute("empLevel", empLevel);
			String empDept = eService.empData(employeeID).getDepartment();
			model.addAttribute("empDept", empDept);
			return "InsertLeaveType";
		} else {
			status.setComplete();
			errorMsgMap.put("LoginError", "無人資權限，請重新登入。");
			model.addAttribute("errorMsgMap", errorMsgMap);
			return "UserLogin";
		}
	}
}
