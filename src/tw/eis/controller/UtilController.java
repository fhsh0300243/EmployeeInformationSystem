package tw.eis.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.Department;
import tw.eis.model.Employee;
import tw.eis.model.Title;
import tw.eis.model.DepartmentService;
import tw.eis.model.EmployeeService;
import tw.eis.model.TitleService;

@Controller
@SessionAttributes(names = { "empID" })
public class UtilController {

	private EmployeeService eService;
	private DepartmentService dService;
	private TitleService tService;

	@Autowired
	public UtilController(DepartmentService dService, TitleService tService, EmployeeService eService) {
		this.dService = dService;
		this.tService = tService;
		this.eService = eService;
	}

	@RequestMapping(path = "/DeptList", method = RequestMethod.GET, produces = "html/text;charset=UTF-8")
	public @ResponseBody String deptList() {
		try {
			JSONArray jsonarray = new JSONArray();
			for (Object dept : dService.allDeptData()) {
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("deptid", ((Department) dept).getDeptID());
				jsonobject.put("deptname", ((Department) dept).getDeptName());
				jsonobject.put("deptabb", ((Department) dept).getDeptAbb());
				jsonarray.put(jsonobject);
			}
			return jsonarray.toString();
		} catch (Exception e) {
			System.out.println("From deptList:" + e);
			return "[]";
		}
	}

	@RequestMapping(path = "/TitleList", method = RequestMethod.GET, produces = "html/text;charset=UTF-8")
	public @ResponseBody String titleList(@RequestParam(name = "deptid", required = false) String Departmentid) {
		int deptid = 0;
		try {
			deptid = Integer.parseInt(Departmentid);
		} catch (Exception e) {
			deptid = 0;
		}
		try {
			JSONArray jsonarray = new JSONArray();
			for (Object title : tService.titleDataByDeptId(deptid)) {
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("titleid", ((Title) title).getTitleID());
				jsonobject.put("titlechname", ((Title) title).getTitleChName());
				jsonobject.put("titlename", ((Title) title).getTitleName());
				jsonarray.put(jsonobject);
			}
			return jsonarray.toString();
		} catch (Exception e) {
			System.out.println("From titleList:" + e);
			return "[]";
		}
	}

	@RequestMapping(path = "/ManagerList", method = RequestMethod.GET, produces = "html/text;charset=UTF-8")
	public @ResponseBody String managerList(@RequestParam(name = "deptid", required = false) String Departmentid) {
		int deptid = 0;
		try {
			deptid = Integer.parseInt(Departmentid);
		} catch (Exception e) {
			deptid = 0;
		}
		try {
			JSONArray jsonarray = new JSONArray();
			List<?> list = eService.managerDataByDeptId(deptid);
			if (list!=null) {
				for (Object emp : list) {
					JSONObject jsonobject = new JSONObject();
					jsonobject.put("empid", ((Employee) emp).getEmpID());
					jsonobject.put("name", ((Employee) emp).getName());
					jsonobject.put("title", ((Employee) emp).getTitle());
					jsonarray.put(jsonobject);
				}
				return jsonarray.toString();
			}
			return jsonarray.toString();
		} catch (Exception e) {
			System.out.println("From managerList:" + e);
			return "[]";
		}
	}

	@RequestMapping(path = "/empimgurl", method = RequestMethod.GET)
	public ResponseEntity<byte[]> showEmpImg(@ModelAttribute("empID") String idstr) throws IOException {
		HttpHeaders headers = null;
		try {
			Employee myEmp = eService.empData(Integer.parseInt(idstr));
			headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			byte[] empImgByte = myEmp.getPhoto();
			if (empImgByte != null) {
				return new ResponseEntity<byte[]>(empImgByte, headers, HttpStatus.OK);
			}
			File file = new File("C:/temp/files/default.jpg");
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Exception from showEmpImg:" + e);
			headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			File file = new File("C:/temp/files/default.jpg");
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
		}

	}

}
