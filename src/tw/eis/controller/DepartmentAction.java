package tw.eis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tw.eis.model.service.DepartmentService;
import tw.eis.model.service.EmployeeService;
import tw.eis.model.service.TitleService;
import tw.eis.model.service.UsersService;

@Controller
public class DepartmentAction {

	private UsersService uService;
	private EmployeeService eService;
	private DepartmentService dService;
	private TitleService tService;

	@Autowired
	public DepartmentAction(UsersService uService, EmployeeService eService, DepartmentService dService,
			TitleService tService) {
		this.uService = uService;
		this.eService = eService;
		this.dService = dService;
		this.tService = tService;
	}
	
	@RequestMapping(path = "/DepartmentPage.do", method = RequestMethod.GET)
	public String processDepartmentPage() {
		return "DepartmentPage";
	}
	
	@RequestMapping(path = "/AddDepartment.action", method = RequestMethod.GET)
	public void processAddEmployee(@RequestParam(name = "deptname", required = false) String DeptName,
			@RequestParam(name = "deptabb", required = false) String DeptAbb) {
		
	}
	
}
