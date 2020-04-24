package tw.eis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.Users;
import tw.eis.model.feeAppMember;
import tw.eis.model.feeAppService;

@Controller
@SessionAttributes(value = { "empID", "EmployeeID", "LoginOK" })
public class MainPageController {
	
	private feeAppService feeAppService;

	@Autowired
	public MainPageController(feeAppService feeAppService) {
		this.feeAppService=feeAppService;
	}

	@RequestMapping(path = "/FeeAllPage.action",method =RequestMethod.GET)
	public String MinPage() {
		return "FeeAllPage";
	}
	@RequestMapping(path = "/AddFeeApp.action",method =RequestMethod.GET)
	public String feeAppPage() {
		return "feeApplicationForm";
	}
//	@RequestMapping(path = "/FeeSingerPage.action",method =RequestMethod.GET)
//	public String FeeSingerPage(@ModelAttribute("LoginOK") Users LoginOK) {
//		
//		int deptid = 0;
//		try {
//			// deptid = eService.empData(Integer.parseInt(empId)).getEmpDept().getDeptID();
//			deptid = LoginOK.getEmployee().getLevel();
//		} catch (Exception e) {
//			deptid = 0;
//		}
//		if (deptid >1) {
//			return "FeeSingerPage";
//		}
//		
//		return "AuthorityErrorPage";
//	}
	
	@RequestMapping(path = "/FeeSingerPage.action", method = RequestMethod.GET)
	public String qfeeSingerApp(@ModelAttribute("LoginOK") Users userBean,
			@RequestParam(name = "FeeDetail", required = false)String FeeDetail,
			@RequestParam(name = "Status", required = false)String Status,
			@RequestParam(name = "send", required = false)String send, Model model) {
		String department = userBean.getDepartment();
		String signerStatus="Send";
		int Level = userBean.getEmployee().getLevel();
		System.out.println("Level:"+Level);
		List<feeAppMember> dSList = feeAppService.qfeeSingerApp(department,signerStatus,Level);
		model.addAttribute("dSList", dSList);
		
		 return "FeeSingerPage";
	}
	

}
