package tw.eis.controller;

import java.sql.Date;
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
import org.springframework.web.multipart.MultipartFile;

import tw.eis.model.Attendance;
import tw.eis.model.AttendanceService;
import tw.eis.model.BulletinBoard;
import tw.eis.model.BulletinBoardService;
import tw.eis.model.Department;
import tw.eis.model.DepartmentService;
import tw.eis.model.Employee;
import tw.eis.model.EmployeeService;
import tw.eis.model.Title;
import tw.eis.model.TitleService;
import tw.eis.model.Users;
import tw.eis.model.UsersService;
import tw.eis.model.feeAppMember;
import tw.eis.model.feeAppService;
import tw.eis.util.AESUtil;
import tw.eis.util.GlobalService;

@Controller
@SessionAttributes(value = { "empID", "EmployeeID", "LoginOK" })
public class EmployeeAction {

	private UsersService uService;
	private EmployeeService eService;
	private DepartmentService dService;
	private TitleService tService;
	private BulletinBoardService bService;
	private AttendanceService aService;
	private feeAppService feeAppService;
	AESUtil aes = new AESUtil();

	@Autowired
	public EmployeeAction(UsersService uService, EmployeeService eService, DepartmentService dService,
			TitleService tService, BulletinBoardService bService, AttendanceService aService,
			feeAppService feeAppService) {
		this.uService = uService;
		this.eService = eService;
		this.dService = dService;
		this.tService = tService;
		this.bService = bService;
		this.aService = aService;
		this.feeAppService = feeAppService;
	}

	@RequestMapping(path = "/EmployeePage.do", method = RequestMethod.GET)
	public String processEmployeePage(@ModelAttribute("LoginOK") Users LoginOK) {
		int deptid = 0;
		try {
			// deptid = eService.empData(Integer.parseInt(empId)).getEmpDept().getDeptID();
			deptid = LoginOK.getEmployee().getEmpDept().getDeptID();
		} catch (Exception e) {
			deptid = 0;
		}
		if (deptid == 1 || deptid == 0) {
			return "EmployeePage";
		}
		return "AuthorityErrorPage";
	}

	@RequestMapping(path = "/QueryEmployee.do", method = RequestMethod.GET)
	public String processQueryEmployeePage(@ModelAttribute("EmployeeID") String empId) {
		int level = 0;
		try {
			level = eService.empData(Integer.parseInt(empId)).getEmpTitle().getLevel();
			// level=LoginOK.getEmployee().getEmpTitle().getLevel();
		} catch (Exception e) {
			System.out.println("e:" + e);
			level = 0;
		}
		if (level == 1 || level == 2 || level == 3 || level == 4) {
			return "QueryEmployee";
		}
		return "AuthorityErrorPage";
	}

	@RequestMapping(path = "/QueryDeptFeeApply.do", method = RequestMethod.GET)
	public String processQueryDeptFeeApplyPage(@ModelAttribute("EmployeeID") String empId) {
		int level = 0;
		try {
			level = eService.empData(Integer.parseInt(empId)).getEmpTitle().getLevel();
			// level=LoginOK.getEmployee().getEmpTitle().getLevel();
		} catch (Exception e) {
			System.out.println("e:" + e);
			level = 0;
		}
		if (level == 1 || level == 2 || level == 3 || level == 4) {
			return "QueryDeptFeeApply";
		}
		return "AuthorityErrorPage";
	}

	@RequestMapping(path = "/QueryEmpAttendance.do", method = RequestMethod.GET)
	public String processQueryEmpAttendancePage(@ModelAttribute("EmployeeID") String empId) {
		int level = 0;
		try {
			level = eService.empData(Integer.parseInt(empId)).getEmpTitle().getLevel();
			// level=LoginOK.getEmployee().getEmpTitle().getLevel();
		} catch (Exception e) {
			System.out.println("e:" + e);
			level = 0;
		}
		if (level == 1 || level == 2 || level == 3 || level == 4) {
			return "QueryEmpAttendance";
		}
		return "AuthorityErrorPage";
	}

	@RequestMapping(path = "/AddEmployee.do", method = RequestMethod.GET)
	public String processAddEmployee() {
//		@ModelAttribute("EmployeeID") String empId
//		int level = 0;
//		try {
//			level = eService.empData(Integer.parseInt(empId)).getEmpTitle().getLevel();
//		} catch (Exception e) {
//			level = 0;
//		}
//		if (level == 1 || level == 2 || level == 3) {
			return "AddEmployee";
//		}	
//	    return "AuthorityErrorPage";
	}

	@RequestMapping(path = "/EditEmployee.do", method = RequestMethod.GET)
	public String processEditEmployeePage(@ModelAttribute("EmployeeID") String empId, @RequestParam("id") String id,
			Model model) {
		int level = 0;
		try {
			level = eService.empData(Integer.parseInt(empId)).getEmpTitle().getLevel();
		} catch (Exception e) {
			level = 0;
		}
		if (level == 1 || level == 2 || level == 3) {
			Map<String, String> emp = new HashMap<String, String>();
			Map<String, String> user = new HashMap<String, String>();
			model.addAttribute("emp", emp);
			model.addAttribute("user", user);
			model.addAttribute("empID", id);
			try {
				Employee myEmp = eService.empData(Integer.parseInt(id));
				Users myUser = uService.userData(Integer.parseInt(id));
				user.put("userName", myUser.getUserName());
				// user.put("userPassword", myUser.getUserPassword());
				emp.put("title", Integer.toString(myEmp.getEmpTitle().getTitleID()));
				if (myEmp.getEmpDept() == null) {
					emp.put("department", "");
				} else {
					emp.put("department", Integer.toString(myEmp.getEmpDept().getDeptID()));
				}
				emp.put("name", myEmp.getName());
				emp.put("gender", myEmp.getGender());
				emp.put("salary", Integer.toString(myEmp.getSalary()));
				if (myEmp.getBirthDay() == null) {
					emp.put("birthDay", "");
				} else {
					emp.put("birthDay", myEmp.getBirthDay().toString());
				}
				emp.put("address", myEmp.getAddress());
				emp.put("extensionNum", myEmp.getExtensionNum());
				emp.put("phoneNum", myEmp.getPhoneNum());
				emp.put("email", myEmp.getEmail());
				if (myEmp.getHireDay() == null) {
					emp.put("hireDay", "");
				} else {
					emp.put("hireDay", myEmp.getHireDay().toString());
				}
				if (myEmp.getLastWorkDay() == null) {
					emp.put("lastWorkDay", "");
				} else {
					emp.put("lastWorkDay", myEmp.getLastWorkDay().toString());
				}
				if (myEmp.getManager() == null) {
					emp.put("manager", "");
				} else {
					emp.put("manager", Integer.toString(myEmp.getManager().getEmpID()));
				}
				return "EditEmployeePage";
			} catch (Exception e) {
				System.out.println("From processEditEmployeePage:" + e);
				return "EmployeePage";
			}
		} else {
			return "AuthorityErrorPage";
		}

	}

	@RequestMapping(path = "/AddEmployee.action", method = RequestMethod.POST)
	public String addEmployee(@RequestParam(name = "userName", required = false) String UserName,
			@RequestParam(name = "userPassword", required = false) String UserPassword,
			@RequestParam(name = "title", required = false) String Titleid,
			@RequestParam(name = "dept", required = false) String Departmentid,
			@RequestParam(name = "manager", required = false) String Managerid,
			@RequestParam(name = "name", required = false) String Name,
			@RequestParam(name = "salary", required = false) String Salary,
			@RequestParam(name = "gender", required = false) String Gender,
			@RequestParam(name = "birth", required = false) String BirthDay,
			@RequestParam(name = "address", required = false) String Address,
			@RequestParam(name = "exnum", required = false) String ExtensionNum,
			@RequestParam(name = "phonenum", required = false) String PhoneNum,
			@RequestParam(name = "email", required = false) String Email,
			@RequestParam(name = "file", required = false) MultipartFile mfile,
			@RequestParam(name = "hireDay", required = false) String HireDay,
			@RequestParam(name = "lastWorkDay", required = false) String LastWorkDay, Model model) {

		Map<String, String> msgmap = new HashMap<String, String>();
		Map<String, String> inputmsg = new HashMap<String, String>();

		model.addAttribute("msgmap", msgmap);
		model.addAttribute("inputmsg", inputmsg);

		inputmsg.put("username", UserName);
		inputmsg.put("userpwd", UserPassword);
		inputmsg.put("name", Name);
		inputmsg.put("gender", Gender);
		inputmsg.put("birthday", BirthDay);
		inputmsg.put("address", Address);
		inputmsg.put("department", Departmentid);
		inputmsg.put("manager", Managerid);
		inputmsg.put("title", Titleid);
		inputmsg.put("salary", Salary);
		inputmsg.put("extensionNum", ExtensionNum);
		inputmsg.put("phoneNum", PhoneNum);
		inputmsg.put("email", Email);
		inputmsg.put("hireDay", HireDay);
		inputmsg.put("lastWorkDay", LastWorkDay);

		if (UserName == null || UserName.length() == 0) {
			msgmap.put("username", "請輸入員工帳號");
			return "AddEmployee";
		}

		if (UserPassword == null || UserPassword.length() == 0) {
			msgmap.put("userpwd", "請輸入員工預設密碼");
			return "AddEmployee";
		}

		if (Name == null || Name.length() == 0) {
			msgmap.put("name", "請輸入員工姓名");
			return "AddEmployee";
		}

		if (Titleid.equals("")) {
			msgmap.put("title", "請選擇員工職稱");
			return "AddEmployee";
		}

		if (Salary.equals("")) {
			msgmap.put("salary", "請輸入員工薪資");
			return "AddEmployee";
		}

		if (Email.equals("")) {
			msgmap.put("email", "請輸入email");
			return "AddEmployee";
		}

		byte[] photo = null;
		int salary;
		try {
			salary = Integer.parseInt(Salary);
			if (salary < 0) {
				msgmap.put("salary", "薪資不得為負數，請重新輸入");
				return "AddEmployee";
			}
		} catch (Exception e) {
			msgmap.put("salary", "薪資輸入錯誤，請重新輸入");
			return "AddEmployee";
		}

		Title title = tService.titleData(Integer.parseInt(Titleid));

		Department department;
		String DeptAbb = null;
		try {
			department = dService.deptData(Integer.parseInt(Departmentid));
		} catch (Exception e) {
			department = null;
		}
		if (department != null) {
			DeptAbb = department.getDeptAbb();
		}

		Employee manager;
		try {
			manager = eService.empData(Integer.parseInt(Managerid));
		} catch (Exception e) {
			manager = null;
		}

		Date birthday;
		Date hireDay;
		Date lastWorkDay;
		try {
			birthday = Date.valueOf(BirthDay);
		} catch (Exception e) {
			birthday = null;
		}

		try {
			hireDay = Date.valueOf(HireDay);
		} catch (Exception e) {
			hireDay = null;
		}

		try {
			lastWorkDay = Date.valueOf(LastWorkDay);
		} catch (Exception e) {
			lastWorkDay = null;
		}

		if (mfile != null) {
			photo = GlobalService.processImgData(mfile);
		}

		UserPassword = aes.parseByte2HexStr(aes.encrypt(UserPassword));
		if (msgmap.isEmpty()) {
			int status = uService.addUsers(UserName, UserPassword, title.getTitleName(), title.getLevel(), DeptAbb,
					manager, Name, Gender, birthday, Address, ExtensionNum, PhoneNum, Email, photo, salary, hireDay,
					lastWorkDay, department, title);

			if (status == 1) {
				msgmap.put("status", "新增成功");
			} else if (status == 2) {
				msgmap.put("status", "帳號重複，請重新輸入");
			} else if (status == 3) {
				msgmap.put("status", "email重複，請重新輸入");
			} else {
				msgmap.put("status", "發生異常，請重新嘗試");
			}
		}
		return "AddEmployee";
	}

	@RequestMapping(path = "/EditAddEmployee.action", method = RequestMethod.POST)
	public String editEmployee(@ModelAttribute("empID") String idstr,
			@RequestParam(name = "userName", required = false) String UserName,
			@RequestParam(name = "userPassword", required = false) String UserPassword,
			@RequestParam(name = "title", required = false) String Titleid,
			@RequestParam(name = "dept", required = false) String Departmentid,
			@RequestParam(name = "manager", required = false) String Managerid,
			@RequestParam(name = "name", required = false) String Name,
			@RequestParam(name = "salary", required = false) String Salary,
			@RequestParam(name = "gender", required = false) String Gender,
			@RequestParam(name = "birth", required = false) String BirthDay,
			@RequestParam(name = "address", required = false) String Address,
			@RequestParam(name = "exnum", required = false) String ExtensionNum,
			@RequestParam(name = "phonenum", required = false) String PhoneNum,
			@RequestParam(name = "email", required = false) String Email,
			@RequestParam(name = "file", required = false) MultipartFile mfile,
			@RequestParam(name = "hireDay", required = false) String HireDay,
			@RequestParam(name = "lastWorkDay", required = false) String LastWorkDay, Model model) {

		Map<String, String> msgmap = new HashMap<String, String>();
		Map<String, String> emp = new HashMap<String, String>();
		Map<String, String> user = new HashMap<String, String>();

		model.addAttribute("emp", emp);
		model.addAttribute("user", user);
		model.addAttribute("msgmap", msgmap);

		user.put("userName", UserName);
		emp.put("name", Name);
		emp.put("gender", Gender);
		emp.put("birthday", BirthDay);
		emp.put("address", Address);
		emp.put("department", Departmentid);
		emp.put("manager", Managerid);
		emp.put("title", Titleid);
		emp.put("salary", Salary);
		emp.put("extensionNum", ExtensionNum);
		emp.put("phoneNum", PhoneNum);
		emp.put("email", Email);
		emp.put("hireDay", HireDay);
		emp.put("lastWorkDay", LastWorkDay);
		if (BirthDay == null) {
			emp.put("birthDay", "");
		} else {
			emp.put("birthDay", BirthDay);
		}
		if (BirthDay == null) {
			emp.put("birthDay", "");
		} else {
			emp.put("birthDay", BirthDay);
		}
		emp.put("address", Address);
		emp.put("extensionNum", ExtensionNum);
		emp.put("phoneNum", PhoneNum);
		if (HireDay == null) {
			emp.put("hireDay", "");
		} else {
			emp.put("hireDay", HireDay);
		}
		if (LastWorkDay == null) {
			emp.put("lastWorkDay", "");
		} else {
			emp.put("lastWorkDay", LastWorkDay);
		}
		int id;
		try {
			id = Integer.parseInt(idstr);
		} catch (Exception e) {
			System.out.println("Integer.parseInt exception:" + e);
			id = 0;
		}
		if (Name == null || Name.length() == 0) {
			msgmap.put("name", "請輸入員工姓名");
			return "EditEmployeePage";
		}

		if (Titleid.equals("")) {
			msgmap.put("title", "請選擇員工職稱");
			return "EditEmployeePage";
		}

		if (Salary.equals("")) {
			msgmap.put("salary", "請輸入員工薪資");
			return "EditEmployeePage";
		}

		if (Email.equals("")) {
			msgmap.put("email", "請輸入email");
			return "EditEmployeePage";
		}

		byte[] photo = null;
		int salary;
		try {
			salary = Integer.parseInt(Salary);
			if (salary < 0) {
				msgmap.put("salary", "薪資不得為負數，請重新輸入");
				return "EditEmployeePage";
			}
		} catch (Exception e) {
			msgmap.put("salary", "薪資輸入錯誤，請重新輸入");
			return "EditEmployeePage";
		}
		Date birthday;
		Date hireDay;
		Date lastWorkDay;
		Employee manager;
		Department department;
		Title title;
		try {
			birthday = Date.valueOf(BirthDay);
		} catch (Exception e) {
			birthday = null;
		}

		try {
			hireDay = Date.valueOf(HireDay);
		} catch (Exception e) {
			hireDay = null;
		}

		try {
			lastWorkDay = Date.valueOf(LastWorkDay);
		} catch (Exception e) {
			lastWorkDay = null;
		}

		if (mfile != null) {
			photo = GlobalService.processImgData(mfile);
		}
		try {
			manager = eService.empData(Integer.parseInt(Managerid));
		} catch (Exception e) {
			manager = null;
		}
		try {
			department = dService.deptData(Integer.parseInt(Departmentid));
		} catch (Exception e) {
			department = null;
		}
		try {
			title = tService.titleData(Integer.parseInt(Titleid));
		} catch (Exception e) {
			title = null;
		}

		if (msgmap.isEmpty()) {

			boolean status = eService.editEmp(id, title.getTitleName(), title.getLevel(), department.getDeptAbb(),
					manager, Name, Gender, birthday, Address, ExtensionNum, PhoneNum, Email, photo, salary, hireDay,
					lastWorkDay, department, title);

			if (status) {
				msgmap.put("status", "修改成功");
			} else {
				msgmap.put("status", "修改失敗，請再嘗試");
			}
		}

		return "EditEmployeePage";
	}

	@RequestMapping(path = "/QueryEmp.action", method = RequestMethod.GET, produces = "html/text;charset=UTF-8")
	public @ResponseBody String queryEmp(@RequestParam(name = "searchid", required = false) String idstr,
			@RequestParam(name = "searchname", required = false) String Name,
			@RequestParam(name = "searchdept", required = false) String Department,
			@RequestParam(value = "resigned", required = false) String Resigned, Model model) {

		int id = 0;
		try {
			id = Integer.parseInt(idstr);
		} catch (Exception e) {
			System.out.println("Integer.parseInt exception:" + e);
			id = 0;
		}

		if (Name == null || Name.length() == 0) {
			Name = "na";
		}

		if (Department == null || Department.length() == 0) {
			Department = "na";
		}

		List<?> list = null;
		if (Name.equals("na") && Department.equals("na") && Resigned.equals("false") && id == 0) {
			list = eService.allEmpData();
		} else {
			list = eService.queryEmp(id, Name, Department, Resigned);
		}

		try {
			JSONArray jsonarray = new JSONArray();
			for (Object emp : list) {
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("title", ((Employee) emp).getEmpTitle().getTitleChName());
				jsonobject.put("username", ((Employee) emp).getUsers().getUserName());
				if (((Employee) emp).getDepartment() == null) {
					jsonobject.put("department", "--");
				} else {
					jsonobject.put("department", ((Employee) emp).getDepartment());
				}
				if (((Employee) emp).getManager() == null) {
					jsonobject.put("manager", "--");
				} else {
					jsonobject.put("manager", ((Employee) emp).getManager().getName());
				}
				jsonobject.put("empID", ((Employee) emp).getEmpID());
				jsonobject.put("name", ((Employee) emp).getName());
				jsonobject.put("gender", ((Employee) emp).getGender());
				if (((Employee) emp).getBirthDay() == null) {
					jsonobject.put("birthDay", "");
				} else {
					jsonobject.put("birthDay", ((Employee) emp).getBirthDay());
				}
				jsonobject.put("address", ((Employee) emp).getAddress());
				if (((Employee) emp).getExtensionNum().equals("")) {
					jsonobject.put("extensionNum", "--");
				} else {
					jsonobject.put("extensionNum", ((Employee) emp).getExtensionNum());
				}
				jsonobject.put("phoneNum", ((Employee) emp).getPhoneNum());
				jsonobject.put("email", ((Employee) emp).getEmail());
				if (((Employee) emp).getBirthDay() == null) {
					jsonobject.put("hireDay", "");
				} else {
					jsonobject.put("hireDay", ((Employee) emp).getHireDay());
				}
				if (((Employee) emp).getBirthDay() == null) {
					jsonobject.put("lastWorkDay", "");
				} else {
					jsonobject.put("lastWorkDay", ((Employee) emp).getLastWorkDay());
				}
				jsonarray.put(jsonobject);
			}
			return jsonarray.toString();
		} catch (Exception e) {
			System.out.println("From queryEmp:" + e);
			return "";
		}
	}

	@RequestMapping(path = "/DeptCostPercent.action", method = RequestMethod.GET, produces = "html/text;charset=UTF-8")
	public @ResponseBody String deptCostPercent() {
		List<Map<String, String>> list = feeAppService.deptFeeApplyCostPercent();
		try {
			JSONArray jsonarray = new JSONArray();
			Map<String, String> sdata = list.get(0);
			Map<String, String> mdata = list.get(1);
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("sHRcost", sdata.get("sHR"));
			jsonobject.put("sRDcost", sdata.get("sRD"));
			jsonobject.put("sQAcost", sdata.get("sQA"));
			jsonobject.put("sSalescost", sdata.get("sSales"));
			jsonobject.put("sPMcost", sdata.get("sPM"));
			jsonobject.put("mHRcost", mdata.get("mHR"));
			jsonobject.put("mRDcost", mdata.get("mRD"));
			jsonobject.put("mQAcost", mdata.get("mQA"));
			jsonobject.put("mSalescost", mdata.get("mSales"));
			jsonobject.put("mPMcost", mdata.get("mPM"));
			jsonarray.put(jsonobject);

			return jsonarray.toString();
		} catch (Exception e) {
			System.out.println("From thisSeasonDeptCostPercent:" + e);
			return "";
		}
	}

	@RequestMapping(path = "/DeptCostDetail.action", method = RequestMethod.GET, produces = "html/text;charset=UTF-8")
	public @ResponseBody String deptCostDetail(@RequestParam(name = "type", required = false) String type) {
		List<feeAppMember> list = feeAppService.deptFeeApplyCostDetail(type.substring(0, 1), type.substring(1));
		try {
			JSONArray jsonarray = new JSONArray();
			for (feeAppMember fee : list) {
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("empID", fee.getEmployeeID().getEmpID());
				jsonobject.put("name", fee.getEmployeeID().getName()); 
				jsonobject.put("appItem", fee.getAppItem());
				jsonobject.put("invoiceTime", fee.getInvoiceTime());
				jsonobject.put("invoiceNb", fee.getInvoiceNb());
				jsonobject.put("appTime", fee.getAppTime());
				jsonobject.put("remark", fee.getRemark());
				jsonobject.put("appMoney", Integer.toString(fee.getAppMoney()));
				jsonarray.put(jsonobject);
			}
			return jsonarray.toString();
		} catch (Exception e) {
			System.out.println("From deptCostDetail:" + e);
			return "[]";
		}
	}

	@RequestMapping(path = "/QueryEmpAttdenance.action", method = RequestMethod.GET, produces = "html/text;charset=UTF-8")
	public @ResponseBody String queryEmpAttendance(@RequestParam(name = "searchid", required = false) String idstr,
			@RequestParam(name = "searchname", required = false) String Name,
			@RequestParam(name = "searchdept", required = false) String Department,
			@RequestParam(name = "startdate", required = false) String StartDate,
			@RequestParam(name = "enddate", required = false) String EndDate, Model model) {

		Map<String, String> msgmap = new HashMap<String, String>();
		model.addAttribute("msgmap", msgmap);
		int id = 0;
		try {
			id = Integer.parseInt(idstr);
		} catch (Exception e) {
			System.out.println("Integer.parseInt exception:" + e);
			id = 0;
		}
		if (Name == null || Name.length() == 0) {
			Name = "na";
		}
		if (Department == null || Department.length() == 0) {
			Department = "na";
		}
		if (StartDate == null || StartDate.length() == 0) {
			StartDate = "na";
		}
		if (EndDate == null || EndDate.length() == 0) {
			EndDate = "na";
		}
		List<?> list = null;
		java.sql.Date startDate;
		java.sql.Date endDate;
		if (Name.equals("na") && Department.equals("na") && id == 0 && StartDate.equals("na") && EndDate.equals("na")) {
			return "[]";
		} else {
			try {
				startDate = java.sql.Date.valueOf(StartDate);
			} catch (Exception e) {
				startDate = null;
			}
			try {
				endDate = java.sql.Date.valueOf(EndDate);
			} catch (Exception e) {
				endDate = null;
			}
			list = aService.queryEmpAttendanceData(id, Name, Department, startDate, endDate);
		}
		try {
			JSONArray jsonarray = new JSONArray();
			for (Object att : list) {
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("empID", ((Attendance) att).getEmployee().getEmpID());
				jsonobject.put("name", ((Attendance) att).getEmployee().getName());
				if (((Attendance) att).getEmployee().getDepartment() == null) {
					jsonobject.put("department", "--");
				} else {
					jsonobject.put("department", ((Attendance) att).getEmployee().getDepartment());
				}
				jsonobject.put("date", ((Attendance) att).getDate());
				jsonobject.put("starttime", ((Attendance) att).getStartTime());
				jsonobject.put("endtime", ((Attendance) att).getEndTime());
				jsonobject.put("status", ((Attendance) att).getStatus());
				jsonarray.put(jsonobject);
			}
			return jsonarray.toString();
		} catch (Exception e) {
			System.out.println("From queryEmpAttendance:" + e);
			return "[]";
		}
	}

	@RequestMapping(path = "/EmpList", method = RequestMethod.GET, produces = "html/text;charset=UTF-8")
	public @ResponseBody String empList() {
		try {
			JSONArray jsonarray = new JSONArray();
			for (Object emp : eService.allEmpData()) {
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("title", ((Employee) emp).getEmpTitle().getTitleChName());
				if (((Employee) emp).getDepartment() == null) {
					jsonobject.put("department", "--");
				} else {
					jsonobject.put("department", ((Employee) emp).getDepartment());
				}
				if (((Employee) emp).getManager() == null) {
					jsonobject.put("manager", "--");
				} else {
					jsonobject.put("manager", ((Employee) emp).getManager().getName());
				}
				jsonobject.put("empID", ((Employee) emp).getEmpID());
				jsonobject.put("name", ((Employee) emp).getName());
				jsonobject.put("gender", ((Employee) emp).getGender());
				if (((Employee) emp).getBirthDay() == null) {
					jsonobject.put("birthDay", "");
				} else {
					jsonobject.put("birthDay", ((Employee) emp).getBirthDay());
				}
				jsonobject.put("address", ((Employee) emp).getAddress());
				if (((Employee) emp).getExtensionNum() == null) {
					jsonobject.put("extensionNum", "--");
				} else {
					jsonobject.put("extensionNum", ((Employee) emp).getExtensionNum());
				}
				jsonobject.put("phoneNum", ((Employee) emp).getPhoneNum());
				jsonobject.put("email", ((Employee) emp).getEmail());
				if (((Employee) emp).getBirthDay() == null) {
					jsonobject.put("hireDay", "");
				} else {
					jsonobject.put("hireDay", ((Employee) emp).getHireDay());
				}
				if (((Employee) emp).getBirthDay() == null) {
					jsonobject.put("lastWorkDay", "");
				} else {
					jsonobject.put("lastWorkDay", ((Employee) emp).getLastWorkDay());
				}
				jsonarray.put(jsonobject);
			}
			return jsonarray.toString();
		} catch (Exception e) {
			System.out.println("From empList:" + e);
			return "";
		}
	}

	@RequestMapping(path = "/BullBoardListOfHR", method = RequestMethod.GET, produces = "html/text;charset=UTF-8")
	public @ResponseBody String bullBoardListOfHR() {
		try {
			JSONArray jsonarray = new JSONArray();
			for (BulletinBoard b : bService.queryBulletinForLook("HR")) {
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("id", b.getBulletinBoardID());
				jsonobject.put("announcer", b.getUsers().getEmployee().getName());
				jsonobject.put("title", b.getTitle());
				jsonobject.put("content", b.getContent());
				jsonobject.put("announcDate", GlobalService.formatToyyyyMMddHHmmss(b.getDate()));
				jsonarray.put(jsonobject);
			}
			return jsonarray.toString();
		} catch (Exception e) {
			System.out.println("From bullBoardListOfHR:" + e);
			return "";
		}
	}

	@RequestMapping(path = "/test.do", method = RequestMethod.GET)
	public void testpage() {
		feeAppService.deptFeeApplyCostPercent();
	}

}
