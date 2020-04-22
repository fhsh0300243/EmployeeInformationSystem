package tw.eis.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import tw.eis.model.ApplyForLeave;
import tw.eis.model.ApplyForLeaveService;
import tw.eis.model.Employee;
import tw.eis.model.EmployeeLeaveDetail;
import tw.eis.model.EmployeeLeaveDetailService;
import tw.eis.model.EmployeeService;
import tw.eis.model.Users;

@Controller
@SessionAttributes(names = { "LoginOK", "usersResultMap", "errorMsgMap" })
public class AFLController {

	private ApplyForLeaveService aService;
	private EmployeeService eService;
	private EmployeeLeaveDetailService eldService;

	@Autowired
	public AFLController(ApplyForLeaveService aService, EmployeeService eService,
			EmployeeLeaveDetailService eldService) {
		this.aService = aService;
		this.eService = eService;
		this.eldService = eldService;
	}

	@RequestMapping(path = "applyforleave", method = RequestMethod.POST)
	public String processApplyAction(@ModelAttribute("LoginOK") Users userBean, @RequestParam("selLT") String leaveType,
			@RequestParam("startdate") String startD, @RequestParam("selSH") String startH,
			@RequestParam("selSM") String startM, @RequestParam("enddate") String endD,
			@RequestParam("selEH") String endH, @RequestParam("selEM") String endM, @RequestParam("cause") String cause,
			@RequestParam("myFile") MultipartFile mFile, HttpServletRequest request) throws ParseException {

		ApplyForLeave aBean = new ApplyForLeave();
		int userID = userBean.getEmployeeID();

		// 取得現在的時刻設定為建立時間
//		long cDate = new Date().getTime();
//		Timestamp createTime = new Timestamp(cDate);
		Date cTime = new Date();
		String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cTime);
		aBean.setCreateTime(createTime);

		// 設定EmployeeId
		Employee eBean = eService.empData(userID);
		aBean.setEmployeeId(eBean);

		// 設定LeaveType
		aBean.setLeaveType(leaveType);

		// 起始時間格式化輸入的時間 (HH可顯示為12:00，hh會轉成00:00)
		Date sTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startD + " " + startH + ":" + startM);
		String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(sTime);
//		long sDate = inputSD.getTime();
//		Timestamp startTime = new Timestamp(sDate);
		aBean.setStartTime(startTime);

		// 結束時間格式化輸入的時間 (HH可顯示為12:00，hh會轉成00:00)
		String endTime = endD + " " + endH + ":" + endM;
		aBean.setEndTime(endTime);

		// 起始時間-結束時間換算成總時數
		BigDecimal sumHours = aService.countLeaveHours(startD, endD, startH, endH, startM, endM);
		aBean.setSumHours(sumHours);

		// 設定Cause
		if (cause != null && cause.length() != 0) {
			aBean.setCause(cause);
		}

		// 取得EmployeeID直屬主管signerId的UsersBean
		Employee mBean = eService.empData(userID).getManager();
		aBean.setSignerId(mBean);

		// 設定SigningProgress
		aBean.setSigningProgress("未簽核");

		// 上傳檔案存入SQL欄位Attachment
		try {
			String fileName = mFile.getOriginalFilename();
			String savePath = request.getServletContext().getRealPath("/") + fileName;
			// "FileTemp\\" +

			System.out.println(savePath);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);

			File saveFile = new File(savePath);

			mFile.transferTo(saveFile);
			if (fileName != null && fileName.length() != 0) {
				InputStream is1 = new FileInputStream(savePath);
				byte[] b = new byte[is1.available()];
				is1.read(b);
				is1.close();
				aBean.setAttachment(b);
			}
			// 若無上傳附件則不setAttachment()
		} catch (IOException e) {
			aService.addApply(aBean);
			System.out.println("NO Image.");
			return "ApplySuccess";
		}
		aService.addApply(aBean);
		return "ApplySuccess";
	}

	@RequestMapping(path = "signforleave", method = RequestMethod.POST)
	public String processSignAction(@ModelAttribute("LoginOK") Users userBean, @RequestParam("applyId") String applyId,
			@RequestParam("sign") String sign, @RequestParam("comment") String comment, Model model) {

		int userID = userBean.getEmployeeID();
		Integer AID = Integer.valueOf(applyId);
		ApplyForLeave aBean = aService.queryApplyByAID(AID);

		if (aBean.getSignerId().getEmpID() == userID && aBean.getSigningProgress().equalsIgnoreCase("未簽核")) {

			ApplyForLeave confirmBean = new ApplyForLeave();

			if (sign.equalsIgnoreCase("yes")) {
				confirmBean.setSigningProgress("同意");

				int EID = aBean.getEmployeeId().getEmpID();
				String leaveType = aBean.getLeaveType();
				EmployeeLeaveDetail eldBean = eldService.queryValidLTByEIDandLT(EID, leaveType);

				BigDecimal confirmUH = aBean.getSumHours();
				BigDecimal oldUH = eldBean.getUsedHours();
				BigDecimal oldSH = eldBean.getSurplusHours();

				BigDecimal newUH = oldUH.add(confirmUH);
				BigDecimal newSH = oldSH.subtract(confirmUH);

//				System.out.println("newUH:" + newUH);
//				System.out.println("newSH:" + newSH);

				eldBean.setUsedHours(newUH);
				eldBean.setSurplusHours(newSH);
				eldService.updateDetail(eldBean.getEldId(), eldBean);

			} else {
				confirmBean.setSigningProgress("不同意");
			}

			Date cfTime = new Date();
			String confirmTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cfTime);
			confirmBean.setConfirmTime(confirmTime);

			if (comment != null && comment.length() != 0) {
				confirmBean.setComment(comment);
			}

			aService.signOffApply(AID, confirmBean);

			return "SignSuccess";
		} else {
			List<ApplyForLeave> SignList = aService.queryUnsignedApplyBySID(userID);

			if (aBean.getSignerId().getEmpID() != userID) {
				model.addAttribute("SignList", SignList);
				model.addAttribute("SignError", "無簽核權限，請再次確認要簽核的請假申請。");
			} else {
				model.addAttribute("SignList", SignList);
				model.addAttribute("SignError", "簽核狀態異常，請重新選取要簽核的請假申請。");
			}
			return "UnsignedPage";
		}
	}

	@ResponseBody
	@RequestMapping(path = "/changeLT", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String changeLeaveType(@ModelAttribute("usersResultMap") Map<String, String> usersResultMap,
			@RequestParam("leaveType") String leaveType) {
		Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));
		System.out.println("leaveType=" + leaveType);

		EmployeeLeaveDetail eldBean = eldService.queryValidLTByEIDandLT(employeeID, leaveType);

		BigDecimal SH = eldBean.getSurplusHours();
		String strSH = SH.toString();

		String[] data = strSH.split("\\.");
		String hours = data[0];
		Integer mins = (int) (Integer.valueOf(data[1]) * 0.1 * 60);

		java.sql.Date sD = eldBean.getStartDate();
		java.sql.Date eD = eldBean.getEndDate();

		String str = leaveType + "剩餘：" + hours + "時" + mins + "分，有效期限：" + sD + "~" + eD + "。";

		return str;

	}

	@ResponseBody
	@RequestMapping(path = "/changeDHM", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String changeDateHourMin(@RequestParam("startdate") String startD, @RequestParam("selSH") String startH,
			@RequestParam("selSM") String startM, @RequestParam("enddate") String endD,
			@RequestParam("selEH") String endH, @RequestParam("selEM") String endM) throws ParseException {

		BigDecimal sumH = aService.countLeaveHours(startD, endD, startH, endH, startM, endM);
		DecimalFormat df1 = new DecimalFormat("0.0");
		String strSumH = df1.format(sumH);

		String[] data = strSumH.split("\\.");
		String hours = data[0];
		Integer mins = (int) (Integer.valueOf(data[1]) * 0.1 * 60);

		String sumHours = "總計：" + hours + "時" + mins + "分。";

		return sumHours;
	}

}
