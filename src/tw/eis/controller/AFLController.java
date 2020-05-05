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
import java.util.HashMap;
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

	@RequestMapping(path = "/applyforleave", method = RequestMethod.POST)
	public String processApplyAction(@ModelAttribute("LoginOK") Users userBean, @RequestParam("selLT") String eldID,
			@RequestParam("startdate") String startD, @RequestParam("selSH") String startH,
			@RequestParam("selSM") String startM, @RequestParam("enddate") String endD,
			@RequestParam("selEH") String endH, @RequestParam("selEM") String endM, @RequestParam("cause") String cause,
			@RequestParam("myFile") MultipartFile mFile, HttpServletRequest request, Model model)
			throws ParseException {
		int userID = userBean.getEmployeeID();
		int empLevel = eService.empData(userID).getLevel();
		model.addAttribute("empLevel", empLevel);
		String empDept = eService.empData(userID).getDepartment();
		model.addAttribute("empDept", empDept);

		// 開始時間、結束時間-判斷是否為休假日、國定假日
		String strError = "";
//		Calendar aCalendar = Calendar.getInstance();
//		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startD);
//		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endD);
//
//		aCalendar.setTime(startDate);
//		int sDate = aCalendar.get(Calendar.DAY_OF_WEEK);
//		List<HolidayCalendar> hcsBean = hcService.queryCalendarByDate(startD);
//
//		if (hcsBean.size() != 0) {
//			if (hcsBean.get(0).getDateType().equals("國定假日")) {
//				strError += startD + "為國定假日。";
//			}
//		} else {
//			if (sDate == Calendar.SATURDAY || sDate == Calendar.SUNDAY) {
//				strError += startD + "為休假日。";
//			}
//		}
//
//		// 開始時間、結束時間-不同日期再判斷，結束時間是否為休假日、國定假日
//		if (!startDate.equals(endDate)) {
//
//			aCalendar.setTime(endDate);
//			int eDate = aCalendar.get(Calendar.DAY_OF_WEEK);
//			List<HolidayCalendar> hceBean = hcService.queryCalendarByDate(endD);
//
//			if (hceBean.size() != 0) {
//				if (hceBean.get(0).getDateType().equals("國定假日")) {
//					strError += endD + "為國定假日。";
//				}
//			} else {
//				if (eDate == Calendar.SATURDAY || eDate == Calendar.SUNDAY) {
//					strError += endD + "為休假日。";
//				}
//			}
//		}

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
			Date cTime = new Date();
			String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cTime);
			aBean.setCreateTime(createTime);

			// 設定EmployeeId
			Employee eBean = eService.empData(userID);
			aBean.setEmployeeId(eBean);

			// 設定請假類別
			EmployeeLeaveDetail eldBean = eldService.queryValidLTByELDID(Integer.valueOf(eldID));
			String eldBeanLT = eldBean.getLeaveType();
			String applyLT = eldBeanLT + "-" + eldID;
			aBean.setLeaveType(applyLT);

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

	@RequestMapping(path = "/signforleave", method = RequestMethod.POST)
	public String processSignAction(@ModelAttribute("LoginOK") Users userBean, @RequestParam("applyId") String applyId,
			@RequestParam("sign") String sign, @RequestParam("comment") String comment, Model model) {

		int userID = userBean.getEmployeeID();
		int empLevel = eService.empData(userID).getLevel();
		model.addAttribute("empLevel", empLevel);
		String empDept = eService.empData(userID).getDepartment();
		model.addAttribute("empDept", empDept);

		Integer AID = Integer.valueOf(applyId);
		ApplyForLeave aBean = aService.queryApplyByAID(AID);

		if (aBean.getSignerId().getEmpID() == userID && aBean.getSigningProgress().equalsIgnoreCase("未簽核")) {

			ApplyForLeave confirmBean = new ApplyForLeave();
//			int EID = aBean.getEmployeeId().getEmpID();

			String leaveType = aBean.getLeaveType();
			String[] data = leaveType.split("-");
			Integer eldID = Integer.valueOf(data[1]);
			EmployeeLeaveDetail eldBean = eldService.queryValidLTByELDID(eldID);

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
	public String changeLeaveType(@RequestParam("eldID") String eldID) {
//		Integer employeeID = Integer.valueOf(usersResultMap.get("EmployeeID"));
		EmployeeLeaveDetail eldBean = eldService.queryValidLTByELDID(Integer.valueOf(eldID));

		BigDecimal SH = eldBean.getSurplusHours();
		String strSH = SH.toString();

		String[] data = strSH.split("\\.");
		String hours = data[0];
		Integer mins = (int) (Integer.valueOf(data[1]) * 0.1 * 60);

		Date sD = eldBean.getStartDate();
		Date eD = eldBean.getEndDate();

		String leaveType = eldBean.getLeaveType();
		String str = leaveType + "剩餘：" + hours + "時" + mins + "分，有效期限：" + sD + "~" + eD + "。";
		return str;
	}

	@ResponseBody
	@RequestMapping(path = "/changeDHM", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String changeDateHourMin(@RequestParam("eldID") String eldID, @RequestParam("startdate") String startD,
			@RequestParam("selSH") String startH, @RequestParam("selSM") String startM,
			@RequestParam("enddate") String endD, @RequestParam("selEH") String endH,
			@RequestParam("selEM") String endM) throws ParseException {

		JSONArray json = new JSONArray();
		JSONObject object = new JSONObject();

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
		object.put("dateError", strError);

		// 開始時間、結束時間-非國定假日、休假日才執行以下判斷
		if (strError.length() == 0) {
			BigDecimal sumH = countReallySumHours(startD, startH, startM, endD, endH, endM);
			// 取得剩餘假別的資料
			EmployeeLeaveDetail eldBean = eldService.queryValidLTByELDID(Integer.valueOf(eldID));
			String leaveType = eldBean.getLeaveType();

			BigDecimal surplusHours = eldBean.getSurplusHours();
			if (sumH.compareTo(surplusHours) == 1) {

				String sumHoursError = "申請時數大於" + leaveType + "剩餘時數。";
				object.put("sumHoursError", sumHoursError);
			}

			// 開始時間、結束時間-判斷是否在有效期限內
//			Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startD);
			Calendar dayS = Calendar.getInstance();
			dayS.setTime(startDate);

//			Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endD);
			Calendar dayE = Calendar.getInstance();
			dayE.setTime(endDate);

			Date sqlSD = eldBean.getStartDate();
			Calendar daySSQL = Calendar.getInstance();
			daySSQL.setTime(sqlSD);

			Date sqlED = eldBean.getEndDate();
			Calendar dayESQL = Calendar.getInstance();
			dayESQL.setTime(sqlED);

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
		}

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

	@RequestMapping(path = "/insertLT", method = RequestMethod.POST)
	public String processInsertAction(@ModelAttribute("LoginOK") Users userBean, @RequestParam("empID") String empID,
			@RequestParam("leaveType") String[] listLT, @RequestParam("year") String year,
			@RequestParam("hours") String hours, @RequestParam("selWho") String who, Model model)
			throws ParseException {
		int userID = userBean.getEmployeeID();
		int empLevel = eService.empData(userID).getLevel();
		model.addAttribute("empLevel", empLevel);
		String empDept = eService.empData(userID).getDepartment();
		model.addAttribute("empDept", empDept);

		Map<String, String> ErrorMap = new HashMap<String, String>();
		if (!eService.empData(userID).getDepartment().equalsIgnoreCase("HR")) {
			String hrError = "無人資權限，無法執行假別匯入。";
			ErrorMap.put("hrError", hrError);
			model.addAttribute("ErrorMap", ErrorMap);
			return "InsertLeaveType";
		}
		Integer empIDINT = Integer.valueOf(empID);
		if (eService.empData(empIDINT) == null) {
			String empError = "無員工編號：" + empIDINT + "的員工資訊，請再次確認。";
			ErrorMap.put("empError", empError);
			model.addAttribute("ErrorMap", ErrorMap);
			return "InsertLeaveType";
		}

		for (String leaveType : listLT) {
			if (leaveType.equalsIgnoreCase("s")) {
				List<EmployeeLeaveDetail> eldList = eldService.queryLTByEIDLTYear(empIDINT, "事假", year);
				if (eldList != null) {
					String sError = "員工編號：" + empIDINT + "，" + year + "年事假重複建立";
					ErrorMap.put("sError", sError);
				}
			}
			if (leaveType.equalsIgnoreCase("b")) {
				List<EmployeeLeaveDetail> eldList = eldService.queryLTByEIDLTYear(empIDINT, "病假", year);
				if (eldList != null) {
					String bError = "員工編號：" + empIDINT + "，" + year + "年病假重複建立";
					ErrorMap.put("bError", bError);
				}
			}
			if (leaveType.equalsIgnoreCase("t")) {
				java.sql.Date hireDay = eService.empData(empIDINT).getHireDay();
				if (hireDay == null) {
					String tError = "員工編號：" + empIDINT + "，查無到職日，請先設定到職日。";
					ErrorMap.put("tError", tError);
				} else {
					List<EmployeeLeaveDetail> eldList = eldService.queryLTByEIDLTYear(empIDINT, "特休", year);
					if (eldList != null) {
						String tError = "員工編號：" + empIDINT + "，" + year + "年特休重複建立";
						ErrorMap.put("tError", tError);
					}
				}
			}
		}
		if (ErrorMap.size() > 0) {
			model.addAttribute("ErrorMap", ErrorMap);
			return "InsertLeaveType";
		} else {
			for (String leaveType : listLT) {
				if (leaveType.equalsIgnoreCase("s")) {

					EmployeeLeaveDetail sBean = new EmployeeLeaveDetail();

					Date cTime = new Date();
					String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cTime);
					sBean.setCreateTime(createTime);

					Employee ueBean = eService.empData(userID);
					sBean.setCreatorId(ueBean);

					Employee eeBean = eService.empData(empIDINT);
					sBean.setEmployeeId(eeBean);

					sBean.setUsedHours(new BigDecimal(0.0));
					sBean.setApplyHours(new BigDecimal(0.0));

					sBean.setLeaveType("事假");
					sBean.setMaxHours(new BigDecimal(112.0));
					sBean.setSurplusHours(new BigDecimal(112.0));

					long sDate = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-1-1").getTime();
					sBean.setStartDate(new java.sql.Date(sDate));
					long eDate = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-12-31").getTime();
					sBean.setEndDate(new java.sql.Date(eDate));

					sBean.setRemarks("1年內合計不得超過14日。");
					eldService.addDetail(sBean);
				}

				if (leaveType.equalsIgnoreCase("b")) {

					EmployeeLeaveDetail bBean = new EmployeeLeaveDetail();

					Date cTime = new Date();
					String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cTime);
					bBean.setCreateTime(createTime);

					Employee ueBean = eService.empData(userID);
					bBean.setCreatorId(ueBean);

					Employee eeBean = eService.empData(empIDINT);
					bBean.setEmployeeId(eeBean);

					bBean.setUsedHours(new BigDecimal(0.0));
					bBean.setApplyHours(new BigDecimal(0.0));

					bBean.setLeaveType("病假");
					bBean.setMaxHours(new BigDecimal(240.0));
					bBean.setSurplusHours(new BigDecimal(240.0));

					long sDate = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-1-1").getTime();
					bBean.setStartDate(new java.sql.Date(sDate));
					long eDate = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-12-31").getTime();
					bBean.setEndDate(new java.sql.Date(eDate));

					bBean.setRemarks("1年內合計不得超過30日。");
					eldService.addDetail(bBean);
				}

				if (leaveType.equalsIgnoreCase("t")) {
					EmployeeLeaveDetail tBean = new EmployeeLeaveDetail();

					Date cTime = new Date();
					String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cTime);
					tBean.setCreateTime(createTime);

					Employee ueBean = eService.empData(userID);
					tBean.setCreatorId(ueBean);

					Employee eeBean = eService.empData(empIDINT);
					tBean.setEmployeeId(eeBean);

					tBean.setUsedHours(new BigDecimal(0.0));
					tBean.setApplyHours(new BigDecimal(0.0));

					tBean.setLeaveType("特休");

					java.sql.Date hireDay = eService.empData(empIDINT).getHireDay();
					Calendar calT = Calendar.getInstance();
					calT.setTime(hireDay);
					int hYear = calT.get(Calendar.YEAR);
					int hMonth = calT.get(Calendar.MONTH) + 1;
					int hDay = calT.get(Calendar.DATE);

					Integer yearINT = Integer.valueOf(year);
					int workingYears = yearINT - hYear;

					if (workingYears == 0) {
						calT.add(Calendar.MONTH, 6);
						if (calT.get(Calendar.YEAR) == yearINT) {
							tBean.setMaxHours(new BigDecimal(24.0));
							tBean.setSurplusHours(new BigDecimal(24.0));

							long sDate = calT.getTime().getTime();
							tBean.setStartDate(new java.sql.Date(sDate));

							calT.add(Calendar.MONTH, 6);
							calT.add(Calendar.DATE, -1);
							long eDate = calT.getTime().getTime();
							tBean.setEndDate(new java.sql.Date(eDate));

							tBean.setRemarks("6個月至未滿1年：3日。");
							eldService.addDetail(tBean);
						}
					}
					if (workingYears == 1) {
						calT.add(Calendar.MONTH, 6);
						if (calT.get(Calendar.YEAR) == yearINT) {
							EmployeeLeaveDetail t1Bean = new EmployeeLeaveDetail();
							t1Bean.setCreateTime(createTime);
							t1Bean.setCreatorId(ueBean);
							t1Bean.setEmployeeId(eeBean);
							t1Bean.setUsedHours(new BigDecimal(0.0));
							t1Bean.setApplyHours(new BigDecimal(0.0));
							t1Bean.setLeaveType("特休");
							t1Bean.setMaxHours(new BigDecimal(24.0));
							t1Bean.setSurplusHours(new BigDecimal(24.0));

							long sDate = calT.getTime().getTime();
							t1Bean.setStartDate(new java.sql.Date(sDate));

							calT.add(Calendar.MONTH, 6);
							calT.add(Calendar.DATE, -1);
							long eDate = calT.getTime().getTime();
							t1Bean.setEndDate(new java.sql.Date(eDate));

							t1Bean.setRemarks("6個月至未滿1年：3日。");
							eldService.addDetail(t1Bean);
						}

						tBean.setMaxHours(new BigDecimal(56.0));
						tBean.setSurplusHours(new BigDecimal(56.0));

						Date utilsDate = new SimpleDateFormat("yyyy-MM-dd").parse(yearINT + "-" + hMonth + "-" + hDay);
						long sDate = utilsDate.getTime();
						tBean.setStartDate(new java.sql.Date(sDate));

						calT.setTime(utilsDate);
						calT.add(Calendar.MONTH, 12);
						calT.add(Calendar.DATE, -1);
						long eDate = calT.getTime().getTime();
						tBean.setEndDate(new java.sql.Date(eDate));

						tBean.setRemarks("工作滿第1年：7日。");
						eldService.addDetail(tBean);
					}
					if (workingYears >= 2) {
						int tHours = 0;
						if (workingYears == 2) {
							tHours = 80;
							tBean.setRemarks("工作滿第2年：10日。");
						} else if (workingYears == 3 || workingYears == 4) {
							tHours = 112;
							tBean.setRemarks("工作滿第3~4年：14日。");
						} else if (workingYears >= 5 && workingYears <= 9) {
							tHours = 120;
							tBean.setRemarks("工作滿第5~9年：15日。");
						} else if (workingYears >= 10 && workingYears <= 23) {
							int i = workingYears - 10;
							tHours = (16 + i) * 8;
							tBean.setRemarks("工作滿第" + workingYears + "年：" + (16 + i) + "日。");
						} else if (workingYears >= 24) {
							tHours = 240;
							tBean.setRemarks("工作滿第24年含以上：30日。");
						}
						tBean.setMaxHours(new BigDecimal(tHours));
						tBean.setSurplusHours(new BigDecimal(tHours));

						Date utilsDate = new SimpleDateFormat("yyyy-MM-dd").parse(yearINT + "-" + hMonth + "-" + hDay);
						long sDate = utilsDate.getTime();
						tBean.setStartDate(new java.sql.Date(sDate));

						calT.setTime(utilsDate);
						calT.add(Calendar.MONTH, 12);
						calT.add(Calendar.DATE, -1);
						long eDate = calT.getTime().getTime();
						tBean.setEndDate(new java.sql.Date(eDate));
						eldService.addDetail(tBean);
					}
				}

				if (leaveType.equalsIgnoreCase("g")) {
					Date cTime = new Date();
					String nowYear = new SimpleDateFormat("yyyy").format(cTime);
					List<EmployeeLeaveDetail> eldList = eldService.queryLTByEIDLTYear(empIDINT, "公假", nowYear);
					if (eldList != null) {
						EmployeeLeaveDetail eldBeanOLD = eldList.get(0);
						BigDecimal maxHNEW = eldBeanOLD.getMaxHours().add(new BigDecimal(hours));
						eldBeanOLD.setMaxHours(maxHNEW);
						BigDecimal surplusHNEW = eldBeanOLD.getSurplusHours().add(new BigDecimal(hours));
						eldBeanOLD.setSurplusHours(surplusHNEW);
						eldService.updateHours(eldBeanOLD.getEldId(), eldBeanOLD);
					} else {
						EmployeeLeaveDetail gBean = new EmployeeLeaveDetail();

						String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cTime);
						gBean.setCreateTime(createTime);

						Employee ueBean = eService.empData(userID);
						gBean.setCreatorId(ueBean);

						Employee eeBean = eService.empData(empIDINT);
						gBean.setEmployeeId(eeBean);
						gBean.setUsedHours(new BigDecimal(0.0));
						gBean.setApplyHours(new BigDecimal(0.0));
						gBean.setLeaveType("公假");
						gBean.setMaxHours(new BigDecimal(hours));
						gBean.setSurplusHours(new BigDecimal(hours));

						long sDate = new SimpleDateFormat("yyyy-MM-dd").parse(nowYear + "-1-1").getTime();
						gBean.setStartDate(new java.sql.Date(sDate));
						long eDate = new SimpleDateFormat("yyyy-MM-dd").parse(nowYear + "-12-31").getTime();
						gBean.setEndDate(new java.sql.Date(eDate));

						gBean.setRemarks("依法令規定應給予公假者，其時數視實際需要定之。");
						eldService.addDetail(gBean);
					}
				}

				if (leaveType.equalsIgnoreCase("sa")) {
					Integer whoINT = Integer.valueOf(who);

					EmployeeLeaveDetail saBean = new EmployeeLeaveDetail();
					Date cTime = new Date();
					String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cTime);
					saBean.setCreateTime(createTime);

					Employee ueBean = eService.empData(userID);
					saBean.setCreatorId(ueBean);

					Employee eeBean = eService.empData(empIDINT);
					saBean.setEmployeeId(eeBean);
					saBean.setUsedHours(new BigDecimal(0.0));
					saBean.setApplyHours(new BigDecimal(0.0));

					int saHours = 0;
					if (whoINT == 1) {
						saHours = 64;
						saBean.setRemarks("父母、養父母、繼父母、配偶喪亡者，喪假8日。");
					} else if (whoINT == 2) {
						saHours = 48;
						saBean.setRemarks("祖父母、子女、配偶之父母、配偶之養父母或繼父母喪亡者，喪假6日。");
					} else if (whoINT == 3) {
						saHours = 24;
						saBean.setRemarks("曾祖父母、兄弟姊妹、配偶之祖父母喪亡者，喪假3日。");
					}

					saBean.setLeaveType("喪假");
					saBean.setMaxHours(new BigDecimal(saHours));
					saBean.setSurplusHours(new BigDecimal(saHours));

					long sqlcTime = cTime.getTime();
					saBean.setStartDate(new java.sql.Date(sqlcTime));

					Calendar calSa = Calendar.getInstance();
					calSa.setTime(cTime);
					calSa.add(Calendar.MONTH, 6);
					calSa.add(Calendar.DATE, -1);
					long eDate = calSa.getTime().getTime();
					saBean.setEndDate(new java.sql.Date(eDate));

					eldService.addDetail(saBean);
				}
			}
		}
		return "InsertSuccess";
	}
}
