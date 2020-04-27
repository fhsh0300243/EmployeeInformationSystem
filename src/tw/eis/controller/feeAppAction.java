package tw.eis.controller;


import java.sql.Timestamp;
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

import tw.eis.model.Employee;
import tw.eis.model.EmployeeService;
import tw.eis.model.Users;
import tw.eis.model.feeAppMember;
import tw.eis.model.feeAppService;

@Controller
@SessionAttributes(names = { "LoginOK", "usersResultMap", "errorMsgMap" })
public class feeAppAction {
	
	private feeAppService feeAppService;
	private EmployeeService eService;

	@Autowired
	public feeAppAction(feeAppService feeAppService,EmployeeService eService) {
		this.feeAppService=feeAppService;
		this.eService = eService;
	}
	//差旅費申請+查詢資料庫
	@RequestMapping(path = "/AddFeeApp.action", method = RequestMethod.POST)
	public String addfeeApp(@ModelAttribute("LoginOK") Users userBean,
			//@RequestParam(name = "department", required = false) String department,
			//@RequestParam(name = "employeeID", required = false) String employeeID,
			@RequestParam(name = "appItem", required = false) String appItem,
			//@RequestParam(name = "appTime", required = false) String appTime,
			@RequestParam(name = "invoiceTime", required = false) String invoiceTime,
			@RequestParam(name = "invoiceNb", required = false) String invoiceNb,
			@RequestParam(name = "editor", required = false) String editor,
			@RequestParam(name = "remark", required = false) String remark,
			@RequestParam(name = "appMoney", required = false) String appMoney, Model model) {
		String signerTime=null;
		String signerStatus="簽核中";
		//int signerID=1;
		String department = userBean.getDepartment();		
		//int employeeIDint= Integer.parseInt(employeeID);
		int userID = userBean.getEmployeeID();
		Employee employeeID = eService.empData(userID);
		//Date invoiceTimeD = Date.valueOf(invoiceTime);
		
		int appMoneyint= Integer.parseInt(appMoney);
		Employee signerID = eService.empData(userID).getManager();
//		Timestamp tsmp=new Timestamp(System.currentTimeMillis());
//		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//		Date appTime = Date.valueOf(sdFormat.format(tsmp));	
//		System.out.println("System Time:"+appTime.toString());
		
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		String appTime = sdFormat.format(date);
		System.out.println("System Time:"+appTime);
		feeAppService.addFeeApp(department,employeeID,appItem,appTime.toString(),invoiceTime,invoiceNb,editor,remark,appMoneyint,signerTime,signerStatus,signerID);
		
		 return "feeApplicationForm";
	}
	//差旅費查詢頁面+查詢資料庫
	@RequestMapping(path = "/FeeAllPage.action", method = RequestMethod.POST)
	public String qfeeApp(@ModelAttribute("LoginOK") Users userBean,
			@RequestParam(name = "searchA", required = false)String searchA,
			@RequestParam(name = "searchB", required = false) String searchB, Model model) {
		//int employeeIDint = userBean.getEmployeeID();
		int userID = userBean.getEmployeeID();
		Employee EmployeeID1 = eService.empData(userID);
		//System.out.println("EmployeeID1:"+EmployeeID1);
		List<feeAppMember> dList = feeAppService.qFeeApp(EmployeeID1,searchA,searchB);					
			model.addAttribute("dList", dList);
		
		 return "FeeAllPage";
	}
	
	@RequestMapping(path = "/SingerPage", method = RequestMethod.GET)
	public String SingerPage(@RequestParam("feeAppID") int feeAppID, Model model) {
		List<feeAppMember> appIDList = feeAppService.qapplyId(feeAppID);
				
		int S_feeAppID=0;
//		String S_department="";
//		String S_appItem="";
//		String S_appTime="";
//		String S_invoiceTime="";
//		String S_invoiceNb="";
//		String S_editor="";
//		String S_remark="";
//		int S_appMoney=0;
				
		for(feeAppMember feeAppMember:appIDList) {
			S_feeAppID = feeAppMember.getFeeAppID();
//			S_department = feeAppMember.getDepartment();
//			S_appItem = feeAppMember.getAppItem();
//			S_appTime = feeAppMember.getAppTime();
//			S_invoiceTime = feeAppMember.getInvoiceTime();
//			S_invoiceNb = feeAppMember.getInvoiceNb();
//			S_editor = feeAppMember.getEditor();
//			S_remark = feeAppMember.getRemark();
//			S_appMoney = feeAppMember.getAppMoney();
		}
		model.addAttribute("appIDList", appIDList);
		model.addAttribute("S_feeAppID", S_feeAppID);
//		model.addAttribute("S_department", S_department);
//		model.addAttribute("S_appItem", S_appItem);
//		model.addAttribute("S_appTime", S_appTime);
//		model.addAttribute("S_feeAppID", S_feeAppID);
//		model.addAttribute("S_invoiceTime", S_invoiceTime);
//		model.addAttribute("S_invoiceNb", S_invoiceNb);
//		model.addAttribute("S_editor", S_editor);
//		model.addAttribute("S_remark", S_remark);
//		model.addAttribute("S_appMoney", S_appMoney);
		
	 return "FeeSingerDetails";
	}
	@RequestMapping(path = "/SingerPassPage", method = RequestMethod.POST)
	public String SingerPassPage(@ModelAttribute("LoginOK") Users LoginOK,
			@RequestParam("feeAppID") int feeAppID,
			@RequestParam("decide") String signerStatus,Model model) {
		
		SimpleDateFormat SingerFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		String singerTime = SingerFormat.format(date);
		
		//int signerID = LoginOK.getEmployeeID();
		int userID = LoginOK.getEmployeeID();
		Employee signerID = eService.empData(userID);
		feeAppService.EditFeeApp(feeAppID,signerStatus,singerTime,signerID);		
	 return "FeeSingerDecide";
	}

	@RequestMapping(path = "/ReturnPage", method = RequestMethod.GET)
	public String ReturnPage(@RequestParam("feeAppID") int feeAppID, Model model) {
		List<feeAppMember> appfeeIDList = feeAppService.qapplyId(feeAppID);
				
		int S_feeAppID=0;
//		String S_department="";
//		String S_feeAppName="";
//		String S_appItem="";
//		String S_appTime="";
//		String S_invoiceTime="";
//		String S_invoiceNb="";
//		String S_editor="";
//		String S_remark="";
//		int S_appMoney=0;
				
		for(feeAppMember feeAppMember:appfeeIDList) {
			S_feeAppID = feeAppMember.getFeeAppID();
//			S_feeAppName=feeAppMember.getEmployeeID().getName();
//			S_department = feeAppMember.getDepartment();
//			S_appItem = feeAppMember.getAppItem();
//			S_appTime = feeAppMember.getAppTime();
//			S_invoiceTime = feeAppMember.getInvoiceTime();
//			S_invoiceNb = feeAppMember.getInvoiceNb();
//			S_editor = feeAppMember.getEditor();
//			S_remark = feeAppMember.getRemark();
//			S_appMoney = feeAppMember.getAppMoney();
		}
		model.addAttribute("appfeeIDList", appfeeIDList);
		model.addAttribute("S_feeAppID", S_feeAppID);
//		model.addAttribute("S_department", S_department);
//		model.addAttribute("S_appItem", S_appItem);
//		model.addAttribute("S_appTime", S_appTime);
//		model.addAttribute("S_feeAppID", S_feeAppID);
//		model.addAttribute("S_invoiceTime", S_invoiceTime);
//		model.addAttribute("S_invoiceNb", S_invoiceNb);
//		model.addAttribute("S_editor", S_editor);
//		model.addAttribute("S_remark", S_remark);
//		model.addAttribute("S_appMoney", S_appMoney);
		
	 return "FeeReturnModify";
	}
	@RequestMapping(path = "/FeeReturnEditPage", method = RequestMethod.POST)
	public String FeeReturnEditPage(@ModelAttribute("LoginOK") Users LoginOK,
			@RequestParam("feeAppID") int feeAppID,
			@RequestParam("invoiceTime") String invoiceTime,
			@RequestParam("invoiceNb") String invoiceNb,
			@RequestParam("editor") String editor,
			@RequestParam("appMoney") int appMoney,
			@RequestParam("remark") String remark,Model model) {
		
		SimpleDateFormat appTimeFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		String appTime = appTimeFormat.format(date);
		String signerStatus="簽核中";	
		
		feeAppService.ReturnEditFee(feeAppID,appTime,invoiceTime,invoiceNb,editor,appMoney,remark,signerStatus);		
	 return "FeeSingerDecide";
	}
	
	
	}

