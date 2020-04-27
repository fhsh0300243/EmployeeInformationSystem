package tw.eis.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
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
import tw.eis.model.HolidayCalendar;
import tw.eis.model.HolidayCalendarService;
import tw.eis.model.Users;

@Controller
@SessionAttributes(names = { "LoginOK", "usersResultMap", "errorMsgMap" })
public class AFLController {

	private ApplyForLeaveService aService;
	private EmployeeService eService;
	private EmployeeLeaveDetailService eldService;
	private HolidayCalendarService hcService;

	@Autowired
	public AFLController(ApplyForLeaveService aService, EmployeeService eService, EmployeeLeaveDetailService eldService,
			HolidayCalendarService hcService) {
		this.aService = aService;
		this.eService = eService;
		this.eldService = eldService;
		this.hcService = hcService;
	}

	@RequestMapping(path = "applyforleave", method = RequestMethod.POST)
	public String processApplyAction(@ModelAttribute("LoginOK") Users userBean, @RequestParam("selLT") String leaveType,
			@RequestParam("startdate") String startD, @RequestParam("selSH") String startH,
			@RequestParam("selSM") String startM, @RequestParam("enddate") String endD,
			@RequestParam("selEH") String endH, @RequestParam("selEM") String endM, @RequestParam("cause") String cause,
			@RequestParam("myFile") MultipartFile mFile, HttpServletRequest request, Model model)
			throws ParseException {
		int userID = userBean.getEmployeeID();
		int empLevel = eService.empData(userID).getLevel();
		model.addAttribute("empLevel", empLevel);

		// 開始時間、結束時間-判斷是否為休假日、國定假日
		String strError = "";
		Calendar aCalendar = Calendar.getInstance();
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startD);
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endD);

		aCalendar.setTime(startDate);
		int sDate = aCalendar.get(Calendar.DAY_OF_WEEK);
		List<HolidayCalendar> hcsBean = hcService.queryCalendarByDate(startD);

		if (hcsBean.size() != 0) {
			if (hcsBean.get(0).getDateType().equals("國定假日")) {
				strError += startD + "為國定假日。";
			}
		} else {
			if (sDate == Calendar.SATURDAY || sDate == Calendar.SUNDAY) {
				strError += startD + "為休假日。";
			}
		}

		// 開始時間、結束時間-不同日期再判斷，結束時間是否為休假日、國定假日
		if (!startDate.equals(endDate)) {

			aCalendar.setTime(endDate);
			int eDate = aCalendar.get(Calendar.DAY_OF_WEEK);
			List<HolidayCalendar> hceBean = hcService.queryCalendarByDate(endD);

			if (hceBean.size() != 0) {
				if (hceBean.get(0).getDateType().equals("國定假日")) {
					strError += endD + "為國定假日。";
				}
			} else {
				if (eDate == Calendar.SATURDAY || eDate == Calendar.SUNDAY) {
					strError += endD + "為休假日。";
				}
			}
		}

		// 開始時間、結束時間-格式化輸入的時間 (HH可顯示為12:00，hh會轉成00:00)
		Date sTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startD + " " + startH + ":" + startM);
		String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(sTime);
		Date eTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endD + " " + endH + ":" + endM);
		String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(eTime);

		// 開始時間、結束時間-確認是否與先前申請的時間重複
		List<ApplyForLeave> aBeanOLD = aService.checkApplyTime(sTime, eTime, userID);

		if (aBeanOLD != null) {
			strError += "與";
			Iterator<ApplyForLeave> iterator = aBeanOLD.iterator();
			while (iterator.hasNext()) {
				ApplyForLeave dBean = iterator.next();
				strError += dBean.getCreateTime() + "、";
			}
			strError = strError.substring(0, strError.length() - 1);
			strError += "申請的時間重複。";
		}

		// 判斷以上無錯誤訊息(休假日、重複申請)，才執行以下資料存入SQL
		if (strError.length() == 0 && aBeanOLD == null) {
			ApplyForLeave aBean = new ApplyForLeave();

			// 取得現在的時刻設定為建立時間
//			long cDate = new Date().getTime();
//			Timestamp createTime = new Timestamp(cDate);
			Date cTime = new Date();
			String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cTime);
			aBean.setCreateTime(createTime);

			// 設定EmployeeId
			Employee eBean = eService.empData(userID);
			aBean.setEmployeeId(eBean);

			// 設定請假類別
			aBean.setLeaveType(leaveType);

			// 設定開始時間、結束時間
			aBean.setStartTime(startTime);
			aBean.setEndTime(endTime);

			// 設定總時數-起始時間到結束時間，換算成總時數
			BigDecimal sumHours = countReallySumHours(startD, startH, startM, endD, endH, endM);
			aBean.setSumHours(sumHours);

			// 設定事由
			if (cause != null && cause.length() != 0) {
				aBean.setCause(cause);
			}

			// 取得EmployeeID直屬主管signerId的UsersBean
			Employee mBean = eService.empData(userID).getManager();
			aBean.setSignerId(mBean);

			// 設定簽核狀態
			aBean.setSigningProgress("未簽核");

			// 申請完成同時修改EmployeeLeaveDetail的申請時數與剩餘時數
			EmployeeLeaveDetail eldBean = eldService.queryValidLTByEIDandLT(userID, leaveType);
			// 取得原本的時數
			BigDecimal oldAH = eldBean.getApplyHours();
			BigDecimal oldSH = eldBean.getSurplusHours();
			// 扣除此次申請的時數
			BigDecimal newAH = oldAH.add(sumHours);
			BigDecimal newSH = oldSH.subtract(sumHours);
			// 執行更新
			eldBean.setApplyHours(newAH);
			eldBean.setSurplusHours(newSH);
			eldService.updateHours(eldBean.getEldId(), eldBean);

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
				return "ApplySuccess";
			}
			aService.addApply(aBean);
			return "ApplySuccess";
		}
		model.addAttribute("DateError", strError);

		model.addAttribute("selSH", aService.getStartHoursTag());
		model.addAttribute("selEH", aService.getEndHoursTag());
		model.addAttribute("selLT", eldService.getLeaveTypeTag(userID));
		return "ApplyPage";
	}

	@RequestMapping(path = "signforleave", method = RequestMethod.POST)
	public String processSignAction(@ModelAttribute("LoginOK") Users userBean, @RequestParam("applyId") String applyId,
			@RequestParam("sign") String sign, @RequestParam("comment") String comment, Model model) {

		int userID = userBean.getEmployeeID();
		int empLevel = eService.empData(userID).getLevel();
		model.addAttribute("empLevel", empLevel);

		Integer AID = Integer.valueOf(applyId);
		ApplyForLeave aBean = aService.queryApplyByAID(AID);

		if (aBean.getSignerId().getEmpID() == userID && aBean.getSigningProgress().equalsIgnoreCase("未簽核")) {

			ApplyForLeave confirmBean = new ApplyForLeave();
			int EID = aBean.getEmployeeId().getEmpID();
			String leaveType = aBean.getLeaveType();
			EmployeeLeaveDetail eldBean = eldService.queryValidLTByEIDandLT(EID, leaveType);
			BigDecimal confirmUH = aBean.getSumHours();

			if (sign.equalsIgnoreCase("yes")) {
				confirmBean.setSigningProgress("同意");

				BigDecimal oldUH = eldBean.getUsedHours();
				BigDecimal oldAH = eldBean.getApplyHours();

				BigDecimal newUH = oldUH.add(confirmUH);
				BigDecimal newAH = oldAH.subtract(confirmUH);

				eldBean.setUsedHours(newUH);
				eldBean.setApplyHours(newAH);
				eldService.updateHours(eldBean.getEldId(), eldBean);

			} else {
				confirmBean.setSigningProgress("不同意");

				BigDecimal oldAH = eldBean.getApplyHours();
				BigDecimal oldSH = eldBean.getSurplusHours();

				BigDecimal newAH = oldAH.subtract(confirmUH);
				BigDecimal newSH = oldSH.add(confirmUH);

				eldBean.setApplyHours(newAH);
				eldBean.setSurplusHours(newSH);
				eldService.updateHours(eldBean.getEldId(), eldBean);
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
	public String changeDateHourMin(@ModelAttribute("LoginOK") Users userBean,
			@RequestParam("leaveType") String leaveType, @RequestParam("startdate") String startD,
			@RequestParam("selSH") String startH, @RequestParam("selSM") String startM,
			@RequestParam("enddate") String endD, @RequestParam("selEH") String endH,
			@RequestParam("selEM") String endM) throws ParseException {

		JSONArray json = new JSONArray();
		JSONObject object = new JSONObject();
		int userID = userBean.getEmployeeID();
		BigDecimal sumH = countReallySumHours(startD, startH, startM, endD, endH, endM);

		// 取得剩餘假別的資料
		EmployeeLeaveDetail eldBean = eldService.queryValidLTByEIDandLT(userID, leaveType);
		BigDecimal surplusHours = eldBean.getSurplusHours();
		if (sumH.compareTo(surplusHours) == 1) {
			String sumHoursError = "申請時數大於" + leaveType + "剩餘時數。";
			object.put("sumHoursError", sumHoursError);
		}

		// 開始時間、結束時間-判斷是否在有效期限內
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startD);
		Calendar dayS = Calendar.getInstance();
		dayS.setTime(startDate);

		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endD);
		Calendar dayE = Calendar.getInstance();
		dayE.setTime(endDate);

		java.sql.Date sqlSD = eldBean.getStartDate();
		Date sqlSDDate = new java.util.Date(sqlSD.getTime());
		Calendar daySSQL = Calendar.getInstance();
		daySSQL.setTime(sqlSDDate);

		java.sql.Date sqlED = eldBean.getEndDate();
		Date sqlEDDate = new java.util.Date(sqlED.getTime());
		Calendar dayESQL = Calendar.getInstance();
		dayESQL.setTime(sqlEDDate);

		if (dayS.before(daySSQL) || dayS.after(dayESQL) || dayE.before(daySSQL) || dayE.after(dayESQL)) {
			String dateError = "申請時間超出" + leaveType + "的有效期限。";
			object.put("dateError", dateError);
		}

		// 總計時數字串
		DecimalFormat df1 = new DecimalFormat("0.0");
		String strSumH = df1.format(sumH);

		String[] data = strSumH.split("\\.");
		String hours = data[0];
		Integer mins = (int) (Integer.valueOf(data[1]) * 0.1 * 60);

		String sumHours = "總計：" + hours + "時" + mins + "分。";
		object.put("sumHours", sumHours);

		json.put(object);
		return json.toString();
	}

	// 開始時間、結束時間-計算區間內實際請假的總工時
	public BigDecimal countReallySumHours(String startD, String startH, String startM, String endD, String endH,
			String endM) throws ParseException {
		// 開始時間、結束時間-格式化輸入的資料，轉換成方便計算的型態
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startD);
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endD);

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		int dayS = startCal.get(Calendar.DAY_OF_YEAR);
		int yearS = startCal.get(Calendar.YEAR);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		int dayE = endCal.get(Calendar.DAY_OF_YEAR);
		int yearE = endCal.get(Calendar.YEAR);

		// 開始時間、結束時間-判斷是否跨年度
		int leaveDay = 0;
		if (yearS == yearE) {
			leaveDay = dayE - dayS;
		} else {
			Calendar lastCal = Calendar.getInstance();
			String lastDateOfYear = yearS + "-12-31";
			Date lastDate = new SimpleDateFormat("yyyy-MM-dd").parse(lastDateOfYear);
			lastCal.setTime(lastDate);
			int dayL = lastCal.get(Calendar.DAY_OF_YEAR);

			leaveDay = dayL - dayS + dayE;
		}

		// 開始時間、結束時間-計算共有幾天休假日
		int count = 0;
		if (leaveDay >= 2) {
			for (int i = 1; i < leaveDay; i++) {
				startCal.add(Calendar.DAY_OF_YEAR, 1);
				Date nDate = startCal.getTime();
				String newDate = new SimpleDateFormat("yyyy-MM-dd").format(nDate);

				int newWeek = startCal.get(Calendar.DAY_OF_WEEK);
				List<HolidayCalendar> hceBean = hcService.queryCalendarByDate(newDate);
				if (hceBean.size() != 0) {
					if (hceBean.get(0).getDateType().equals("國定假日")) {
						count++;
					}
				} else {
					if (newWeek == Calendar.SATURDAY || newWeek == Calendar.SUNDAY) {
						count++;
					}
				}
			}
		}

		// 開始時間、結束時間-區間總工時扣除休假日的時數
		BigDecimal sumHRaw = aService.countHoursSTtoET(startD, endD, startH, endH, startM, endM);
		BigDecimal sumHHoliday = new BigDecimal(count * 8.0);
		BigDecimal sumH = sumHRaw.subtract(sumHHoliday);
		return sumH;
	}
}
