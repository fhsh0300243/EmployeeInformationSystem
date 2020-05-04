package tw.eis.controller;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.DateUtil;
import tw.eis.model.DepartmentalAnnualGoals;
import tw.eis.model.PersonalQuarterlyTarget;
import tw.eis.model.Users;
import tw.eis.model.PersonalQuarterlyTargetService;
import tw.eis.model.WorkProject;
@Controller
@SessionAttributes(names = {"usersResultMap", "errorMsgMap", "LoginOK", "userName","deptid"})
public class ManagerController {
	private PersonalQuarterlyTargetService pqtService;
	
@Autowired
public ManagerController(PersonalQuarterlyTargetService pqtService) {
	this.pqtService = pqtService;
}

	@ResponseBody
	@RequestMapping(path="/chairmantable",method = RequestMethod.GET)
	public String ChairTable(Model m,@ModelAttribute(name="deptid")int deptid) {
		JSONArray jary = pqtService.getpersonwork(deptid, m);
		String jaystr = jary.toString();
		return jaystr;
	}
	@ResponseBody
	@RequestMapping(path="/managertable",method = RequestMethod.GET)
	public String JsonTable(Model m,@ModelAttribute(name="LoginOK") Users u) {
		int deptid = pqtService.getdeptid(m, u.getDepartment()) ;
		JSONArray jary = pqtService.getpersonwork(deptid, m);
		String jaystr = jary.toString();
		return jaystr;
	}
	@RequestMapping(path = "/managerview", method = RequestMethod.GET)
	public String JsonTableView(Model m) {
		return "Performance_Manager";
	}
	@RequestMapping(path = "/insertpqt",method = RequestMethod.POST)
	public String InsertPQT(Model m,@RequestParam(name="dag")String dag,@RequestParam(name="pqt")String pqt,@ModelAttribute(name="LoginOK") Users u) {
		//�o�̪��Ѽ�1�A����|�qJavaBean����X
		int deptid = pqtService.getdeptid(m, u.getDepartment());
		String username = pqtService.getusername(m, u.getEmployeeID());
		m.addAttribute("userName",username);
		DepartmentalAnnualGoals d = pqtService.getdag(deptid, m);
		Set<PersonalQuarterlyTarget> pset = new HashSet<PersonalQuarterlyTarget>();
		PersonalQuarterlyTarget p = new PersonalQuarterlyTarget(u.getDepartment(),dag,pqt,username,
				Timestamp.valueOf(DateUtil.getNowDate()));
		pset.add(p);
		p.setDepartmentalAnnualGoals(d);
		d.setPersonalQuarterlyTargets(pset);
		pqtService.InsertPQT(d,p, m);
		Set<WorkProject> wset = new HashSet<WorkProject>();
		WorkProject w = new WorkProject(deptid,pqt,"請點擊輸入第一份工作");
		wset.add(w);
		w.setPersonalQuarterlyTargets(p);
		p.setWorkProjects(wset);
		pqtService.InsertWP(p, w, m);
		return "Performance_Manager";
	}
	@RequestMapping(path = "/changepqt" ,method = RequestMethod.POST)
	public String ChangePQT(Model m,@RequestParam(name="pid")int pid,
			@RequestParam(name="pqt")String pqt,@RequestParam(name="worksetter")String worksetter) {
		Timestamp timestamp = Timestamp.valueOf(DateUtil.getNowDate());
		pqtService.ChangePQT(m, pid, pqt, worksetter, timestamp);
		return "Performance_Manager";
	}
	@RequestMapping(path = "/deletepqt" ,method = RequestMethod.POST)
	public String DeletePQT(Model m,@RequestParam(name="pid")int pid) {
		pqtService.DeletePQT(m, pid);
		return "Performance_Manager";
	}
	@RequestMapping(path = "/ChangePQT", method = RequestMethod.GET)
	public String Changeintopqt(Model m,@RequestParam(name="pid")int pid,
			@RequestParam(name="time")String time,@RequestParam(name="worksetter")String worksetter) {
		return "ChangePQT";
	}
	@RequestMapping(path = "/InsertPQT",method=RequestMethod.GET)
	public String Insertintopqt(Model m) {
		return "InsertPQT";
	}
	@RequestMapping(path ="fivedept",method=RequestMethod.GET)
	public String fivedept(Model m) {
		return "fivedept";
	}
	@RequestMapping(path="/pmtarget",method = RequestMethod.GET)
	public String PMtarget(Model m) {
	int deptid = 5;
	m.addAttribute("deptid",deptid);
	return "Performance_ChairMan";
}
	@RequestMapping(path="/salestarget",method = RequestMethod.GET)
	public String Salestarget(Model m) {
	int deptid = 4;
	m.addAttribute("deptid",deptid);
	return "Performance_ChairMan";
}
	@RequestMapping(path="/qatarget",method = RequestMethod.GET)
	public String QAtarget(Model m) {
	int deptid = 3;
	m.addAttribute("deptid",deptid);
	return "Performance_ChairMan";
}
	@RequestMapping(path="/rdtarget",method = RequestMethod.GET)
	public String RDtarget(Model m) {
	int deptid = 2;
	m.addAttribute("deptid",deptid);
	return "Performance_ChairMan";
}
	@RequestMapping(path="/hrtarget",method = RequestMethod.GET)
	public String HRtarget(Model m) {
	int deptid = 1;
	m.addAttribute("deptid",deptid);
	return "Performance_ChairMan";
}
}
