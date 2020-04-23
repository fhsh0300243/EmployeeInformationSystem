package tw.eis.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
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
@SessionAttributes(names = { "LoginOK", "usersResultMap", "errorMsgMap" })
public class feeAppAction {
	
	private feeAppService feeAppService;

	@Autowired
	public feeAppAction(feeAppService feeAppService) {
		this.feeAppService=feeAppService;
	}
	
	@RequestMapping(path = "/AddFeeApp.action", method = RequestMethod.POST)
	public String addfeeApp(@ModelAttribute("LoginOK") Users userBean,@RequestParam(name = "department", required = false) String department,
			//@RequestParam(name = "employeeID", required = false) String employeeID,
			@RequestParam(name = "appItem", required = false) String appItem,
			//@RequestParam(name = "appTime", required = false) String appTime,
			@RequestParam(name = "invoiceTime", required = false) String invoiceTime,
			@RequestParam(name = "invoiceNb", required = false) String invoiceNb,
			@RequestParam(name = "editor", required = false) String editor,
			@RequestParam(name = "remark", required = false) String remark,
			@RequestParam(name = "appMoney", required = false) String appMoney, Model model) {
		String signerTime=null;
		String signerStatus=null;
		int signerID=1;
				
		//int employeeIDint= Integer.parseInt(employeeID);
		int employeeIDint = userBean.getEmployeeID();
		//Date invoiceTimeD = Date.valueOf(invoiceTime);
		int editorint= Integer.parseInt(editor);
		int appMoneyint= Integer.parseInt(appMoney);
		
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		String appTime = sdFormat.format(date);
		//System.out.println(strDate);
		
		feeAppService.addFeeApp(department,employeeIDint,appItem,appTime,invoiceTime,invoiceNb,editorint,remark,appMoneyint,signerTime,signerStatus,signerID);
		
		 return "feeApplicationForm";
	}
	
	@RequestMapping(path = "/FeeAllPage.action", method = RequestMethod.POST)
	public String qfeeApp(@RequestParam(name = "employeeID", required = false) String employeeID, Model model) {
		int employeeIDint= Integer.parseInt(employeeID);
		List<feeAppMember> dList = feeAppService.qFeeApp(employeeIDint);					
			model.addAttribute("dList", dList);
		
		 return "FeeAllPage";
	}
			

	}

