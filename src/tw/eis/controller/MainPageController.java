package tw.eis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.Users;

@Controller
@SessionAttributes(value = { "empID", "EmployeeID", "LoginOK" })
public class MainPageController {

	@RequestMapping(path = "/FeeAllPage.action",method =RequestMethod.GET)
	public String MinPage() {
		return "FeeAllPage";
	}
	@RequestMapping(path = "/AddFeeApp.action",method =RequestMethod.GET)
	public String feeAppPage() {
		return "feeApplicationForm";
	}
	@RequestMapping(path = "/FeeSingerPage.action",method =RequestMethod.GET)
	public String FeeSingerPage(@ModelAttribute("LoginOK") Users LoginOK) {
		
		int deptid = 0;
		try {
			// deptid = eService.empData(Integer.parseInt(empId)).getEmpDept().getDeptID();
			deptid = LoginOK.getEmployee().getLevel();
		} catch (Exception e) {
			deptid = 0;
		}
		if (deptid >1) {
			return "FeeSingerPage";
		}
		
		return "AuthorityErrorPage";
	}
	

}
