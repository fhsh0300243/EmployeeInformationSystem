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

import tw.eis.model.Employee;
import tw.eis.model.EmployeeService;
import tw.eis.model.Users;
import tw.eis.model.feeAppMember;
import tw.eis.model.feeAppService;

@Controller
@SessionAttributes(value = { "empID", "EmployeeID", "LoginOK" })
public class MainPageController {
	
	private feeAppService feeAppService;
	private EmployeeService eService;

	@Autowired
	public MainPageController(feeAppService feeAppService,EmployeeService eService) {
		this.feeAppService=feeAppService;
		this.eService = eService;
	}
	//轉頁差旅費查詢頁面
	@RequestMapping(path = "/FeeAllPage.action",method =RequestMethod.GET)
	public String MinPage() {
		
		return "FeeAllPage";
	}
	//轉頁差旅費申請頁面
	@RequestMapping(path = "/AddFeeApp.action",method =RequestMethod.GET)
	public String feeAppPage(@ModelAttribute("LoginOK") Users userBean, Model model) {
		String SingerTotal=Integer.toString(feeAppService.query(userBean.getEmployeeID()));
		if(SingerTotal.equals("0")) {
			SingerTotal="";
		}else {
			SingerTotal="("+SingerTotal+")";
		}
		model.addAttribute("SingerTotal", SingerTotal);
		
		int userID = userBean.getEmployeeID();
		Employee employeeIDB = eService.empData(userID);
		
		String signerStatus="退件";
		List<feeAppMember> qfeeAppByID= feeAppService.qfeeAppByID(employeeIDB,signerStatus);
				
		int qReturnTotal = 0;
		for (feeAppMember feeAppMember : qfeeAppByID) {
			qReturnTotal+= 1;

		}
		String qReturnTota = "";
		if(qReturnTotal==0) {
			qReturnTota = Integer.toString(qReturnTotal);
			qReturnTota="";
		}else {
			qReturnTota = "("+Integer.toString(qReturnTotal)+")";
		}
		
		model.addAttribute("qReturnTota", qReturnTota);
		
		return "feeApplicationForm";
	}
	//轉頁差旅費退件頁面
		@RequestMapping(path = "/FeeReturnPage.action",method =RequestMethod.GET)
		public String qfeeAppByID(@ModelAttribute("LoginOK") Users LoginOK, Model model) {
			
			int userID = LoginOK.getEmployeeID();
			Employee employeeIDB = eService.empData(userID);
			
			String signerStatus="退件";
			List<feeAppMember> qfeeAppByID= feeAppService.qfeeAppByID(employeeIDB,signerStatus);
			model.addAttribute("qfeeAppByID", qfeeAppByID);
			return "FeeReturnPage";
		}

	//轉頁差旅費簽核頁面+權限判斷
	@RequestMapping(path = "/FeeSingerPage.action", method = RequestMethod.GET)
	public String qfeeSingerApp(@ModelAttribute("LoginOK") Users LoginOK,
			@RequestParam(name = "FeeDetail", required = false)String FeeDetail,
			@RequestParam(name = "Status", required = false)String Status,
			@RequestParam(name = "send", required = false)String send, Model model) {
					
			int deptid = 0;
			try {
				deptid = LoginOK.getEmployee().getLevel();
			} catch (Exception e) {
				deptid = 0;
			}
			if (deptid >1) {		
		String department = LoginOK.getDepartment();
		String signerStatus="簽核中";
		int Level = LoginOK.getEmployee().getLevel();
		int userID = LoginOK.getEmployeeID();
		Employee employeeIDB2 = eService.empData(userID);
		//System.out.println("Level:"+Level);
		List<feeAppMember> dSList = feeAppService.qfeeSingerApp(department,signerStatus,Level,employeeIDB2);
		model.addAttribute("dSList", dSList);
		
		 return "FeeSingerPage";
	}
			return "AuthorityErrorPage";
	}
	

}
