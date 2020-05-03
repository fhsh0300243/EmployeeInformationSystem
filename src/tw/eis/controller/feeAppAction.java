package tw.eis.controller;

import java.sql.Timestamp;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.ResponseBody;
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
	public feeAppAction(feeAppService feeAppService, EmployeeService eService) {
		this.feeAppService = feeAppService;
		this.eService = eService;
	}

	// 差旅費申請+查詢資料庫
	@RequestMapping(path = "/AddFeeApp.action", method = RequestMethod.POST)
	public String addfeeApp(@ModelAttribute("LoginOK") Users userBean,
			// @RequestParam(name = "department", required = false) String department,
			// @RequestParam(name = "employeeID", required = false) String employeeID,
			@RequestParam(name = "appItem", required = false) String appItem,
			// @RequestParam(name = "appTime", required = false) String appTime,
			@RequestParam(name = "invoiceTime", required = false) String invoiceTime,
			@RequestParam(name = "invoiceNb", required = false) String invoiceNb,
			@RequestParam(name = "editor", required = false) String editor,
			@RequestParam(name = "remark", required = false) String remark,
			@RequestParam(name = "appMoney", required = false) String appMoney, Model model) {

		Map<String, String> feemsgmap = new HashMap<String, String>();
		model.addAttribute("feemsgmap", feemsgmap);
//		System.out.println("System Time:"+"1"+appItem+"1");
		if (appItem.equals("====請選擇項目====")) {
			feemsgmap.put("appItem", "未輸入申請項目");
			return "feeApplicationForm";
		}
		if (invoiceTime.equals("")) {
			feemsgmap.put("invoiceTime", "未選擇發票日期");
			return "feeApplicationForm";
		}
		if (appMoney == null || appMoney.length() == 0) {
			feemsgmap.put("appMoney", "未輸入金額");
			return "feeApplicationForm";
		} else {

			String signerTime = null;
			String signerStatus = "簽核中";
			// int signerID=1;
			String department = userBean.getDepartment();
			// int employeeIDint= Integer.parseInt(employeeID);
			int userID = userBean.getEmployeeID();
			Employee employeeID = eService.empData(userID);
			// Date invoiceTimeD = Date.valueOf(invoiceTime);
			try {
			int appMoneyint = Integer.parseInt(appMoney);
			
			Employee signerID = eService.empData(userID).getManager();

			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			Date date = new Date();
			String appTime = sdFormat.format(date);
			// System.out.println("System Time:"+appTime);
			feeAppService.addFeeApp(department, employeeID, appItem, appTime.toString(), invoiceTime, invoiceNb, editor,
					remark, appMoneyint, signerTime, signerStatus, signerID);
			feemsgmap.put("Success", "已送簽核");
			}catch (Exception e) {
				feemsgmap.put("appMoney", "請輸入申請金額");
				return "feeApplicationForm";
			}
			return "feeApplicationForm";
		}
	}

	// 差旅費查詢頁面+查詢資料庫
	@RequestMapping(path = "/FeeAllPage.action", method = RequestMethod.POST)
	public String qfeeApp(@ModelAttribute("LoginOK") Users userBean,
			@RequestParam(name = "searchA", required = false) String searchA,
			@RequestParam(name = "searchB", required = false) String searchB, Model model) {
		
		Map<String, String> feemsgmap = new HashMap<String, String>();
		Map<String, String> appinputmsg = new HashMap<String, String>();
		model.addAttribute("feemsgmap", feemsgmap);
		model.addAttribute("appinputmsg", appinputmsg);
		
		if (searchA.equals("") || searchB.equals("")) {
			feemsgmap.put("searchday", "未選擇搜尋範圍");
			return "FeeAllPage";
		}
		appinputmsg.put("searchA", searchA);
		appinputmsg.put("searchB", searchB);
		// int employeeIDint = userBean.getEmployeeID();
		int userID = userBean.getEmployeeID();
		Employee EmployeeID1 = eService.empData(userID);
				
		searchA=searchA+" 00:01";
		searchB=searchB+" 23:59";

		List<feeAppMember> dList = feeAppService.qFeeApp(EmployeeID1, searchA, searchB);
		model.addAttribute("dList", dList);
		
		int AppTotalMoney = 0;
		for (feeAppMember feeAppMember : dList) {
			AppTotalMoney+= feeAppMember.getAppMoney();
		}
		String TotalMoney = "申請金額:"+Integer.toString(AppTotalMoney)+"元";
		
		model.addAttribute("TotalMoney", TotalMoney);

		
		
		return "FeeAllPage";
	}

	@RequestMapping(path = "/SingerPage", method = RequestMethod.GET)
	public String SingerPage(@RequestParam("feeAppID") int feeAppID, Model model) {
		List<feeAppMember> appIDList = feeAppService.qapplyId(feeAppID);

		int S_feeAppID = 0;


		for (feeAppMember feeAppMember : appIDList) {
			S_feeAppID = feeAppMember.getFeeAppID();

		}
		model.addAttribute("appIDList", appIDList);
		model.addAttribute("S_feeAppID", S_feeAppID);

		return "FeeSingerDetails";
	}

	@RequestMapping(path = "/SingerPassPage", method = RequestMethod.POST)
	public String SingerPassPage(@ModelAttribute("LoginOK") Users LoginOK, @RequestParam("feeAppID") int feeAppID,
			@RequestParam("decide") String signerStatus, Model model) {

		SimpleDateFormat SingerFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String singerTime = SingerFormat.format(date);

		// int signerID = LoginOK.getEmployeeID();
		int userID = LoginOK.getEmployeeID();
		Employee signerID = eService.empData(userID);
		feeAppService.EditFeeApp(feeAppID, signerStatus, singerTime, signerID);
		return "FeeSingerDecide";
	}

	@RequestMapping(path = "/ReturnPage", method = RequestMethod.GET)
	public String ReturnPage(@RequestParam("feeAppID") int feeAppID, Model model) {
		List<feeAppMember> appfeeIDList = feeAppService.qapplyId(feeAppID);

		int S_feeAppID = 0;


		for (feeAppMember feeAppMember : appfeeIDList) {
			S_feeAppID = feeAppMember.getFeeAppID();

		}
		model.addAttribute("appfeeIDList", appfeeIDList);
		model.addAttribute("S_feeAppID", S_feeAppID);


		return "FeeReturnModify";
	}

	@RequestMapping(path = "/FeeReturnEditPage", method = RequestMethod.POST)
	public String FeeReturnEditPage(@ModelAttribute("LoginOK") Users LoginOK,
			@RequestParam("bot") String bot,
			@RequestParam("feeAppID") int feeAppID, @RequestParam("invoiceTime") String invoiceTime,
			@RequestParam("invoiceNb") String invoiceNb, @RequestParam("editor") String editor,
			@RequestParam("appMoney") int appMoney, @RequestParam("remark") String remark, Model model) {
		if(bot.equals("送出")) {
		SimpleDateFormat appTimeFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		String appTime = appTimeFormat.format(date);
		String signerStatus = "簽核中";

		feeAppService.ReturnEditFee(feeAppID, appTime, invoiceTime, invoiceNb, editor, appMoney, remark, signerStatus);
		}
		if(bot.equals("刪除此筆申請")) {
			feeAppService.DelectItem(feeAppID);
		}
		
		return "FeeSingerDecide";
	}

//	主管登入查看有無新申請請需要簽核 by GK

	@RequestMapping(path = "/queySingerPage", method = RequestMethod.GET,produces = "html/text;charset=utf-8")
	public @ResponseBody String queySingerPage(@ModelAttribute("LoginOK") Users LoginOK) {
		return Integer.toString(feeAppService.query(LoginOK.getEmployeeID()));
	}

//	員工查詢是否主管以簽核或退件

	@RequestMapping(path = "/querysucess", method = RequestMethod.GET,produces = "html/text;charset=utf-8")
	public @ResponseBody String querysucess(@ModelAttribute("LoginOK") Users LoginOK,@RequestParam String oldDate,@RequestParam String newDate) throws ParseException {
		System.out.println("oldDate:"+oldDate);
		System.out.println("newDate:"+newDate);
		
		return Integer.toString(feeAppService.querysucess(LoginOK.getEmployeeID(),oldDate,newDate));
	}

//	GK End

}
