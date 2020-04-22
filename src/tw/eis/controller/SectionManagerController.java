package tw.eis.controller;

import java.lang.annotation.Repeatable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.DateUtil;
import tw.eis.model.PersonalQuarterlyTarget;
import tw.eis.model.Users;
import tw.eis.model.WorkProject;
import tw.eis.model.WorkProjectService;

@Controller
@SessionAttributes(names = {"usersResultMap", "errorMsgMap", "LoginOK", "userName","dag","pid"})
public class SectionManagerController {
	private WorkProjectService wpService;

	@Autowired
	public SectionManagerController(WorkProjectService wpService) {
		this.wpService = wpService;
	}
	@RequestMapping(path = "/performance", method = RequestMethod.GET)
	public String Performance(Model m,@ModelAttribute(name="LoginOK") Users u) {
		String title = u.getTitle();
		int deptid = wpService.getdeptid(m, u.getDepartment());
		String dag = wpService.getdag(m, deptid);
		System.out.print(dag);
		m.addAttribute("dag", dag);
		int level = wpService.getlevel(m, title);
		if(level == 3) {
			return "Performance_Manager";
		}else if(level == 2) {
			return "Performance_SectionManager";
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(path = "/jsontable", method = RequestMethod.GET)
	public String JsonTable(Model m,@ModelAttribute(name="LoginOK") Users u) {
		int deptid = wpService.getdeptid(m, u.getDepartment()) ;
		JSONArray jay = wpService.getworkdata1(deptid, m);
		String jaystr = jay.toString();
		return jaystr;
	}
	@RequestMapping(path = "/jsontableview", method = RequestMethod.GET)
	public String JsonTableView(Model m) {
		return "Performance_SectionManager";
	}
	@RequestMapping(path = "/insertwork",method = RequestMethod.POST)
	public String InsertWork(Model m,@RequestParam(name="pid") int pid,
			@RequestParam(name="pqt") String pqt,@RequestParam(name="work") String work,@ModelAttribute(name="LoginOK") Users u) {
		//���Ѽ�pid�qtable�W��
//		System.out.println("================================"+pid);
		int deptid = wpService.getdeptid(m, u.getDepartment());
		String username = wpService.getusername(m, u.getEmployeeID());
		System.out.print("==================="+username);
		PersonalQuarterlyTarget p = wpService.getpqt(pid, m);
		System.out.println(p);
		Set<WorkProject> wSet = new HashSet<WorkProject>();
		WorkProject w = new WorkProject(deptid,pqt,work,username,Timestamp.valueOf(DateUtil.getNowDate()));
		System.out.println(w);
		wSet.add(w);
		w.setPersonalQuarterlyTargets(p);
		p.setWorkProjects(wSet);
		wpService.insertwork(p,w, m);
		return "Performance_SectionManager";
	}
	@RequestMapping(path = "/changework",method = RequestMethod.POST)
	public String changework(Model m,@RequestParam(name="wid") int wid,
			@RequestParam(name="work")String work,@RequestParam(name="worksetter")String worksetter) {
		Timestamp timestamp = Timestamp.valueOf(DateUtil.getNowDate());
		wpService.changework(m, wid, work, worksetter,timestamp);
		return "Performance_SectionManager";
	}
	@RequestMapping(path="/deletework",method = RequestMethod.GET)
	public String deletework(Model m,@RequestParam(name="wid") int wid) {
		wpService.deletework(m, wid);
		return "Performance_SectionManager";
	}
	@ResponseBody
	@RequestMapping(path="/assignwork",method = RequestMethod.GET)
	public String AssignWork(Model m,@ModelAttribute(name="pid") int pid) {
		System.out.print("====================="+pid);
		JSONArray jay = wpService.getassignwork(m, pid);
		String jaystr = jay.toString();
		return jaystr;
	}
	@RequestMapping(path="/toAssignWork",method = RequestMethod.GET)
	public String toAssignWork(Model m,@RequestParam(value="pid")int pid) {

		m.addAttribute("pid",pid);
		return "AssignWork";
	}

	@ResponseBody
	@RequestMapping(path="/employeelist",method = RequestMethod.GET)
	public String EmployeeList(Model m,@ModelAttribute(name="LoginOK") Users u) {
		JSONArray jay = wpService.getdeptusername(m, u.getDepartment());
		String jaystr = jay.toString();
		return jaystr;
	}
	@RequestMapping(path="ChangeWork",method = RequestMethod.GET)
	public String ChangeWork(@RequestParam(value="wid")int wid,@RequestParam(value="worksetter")String worksetter,
			@RequestParam(value="time")String time) {
		return "ChangeWork";
	}
	@RequestMapping(path="InsertWork",method = RequestMethod.GET)
	public String Insertintowork(Model m,@RequestParam(value="pid")int pid,@RequestParam(value="pqt")String pqt) {
		return "InsertWork";
	}
}
