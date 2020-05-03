package tw.eis.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.AssignWorkService;
import tw.eis.model.Users;
@Controller
@SessionAttributes(names = {"usersResultMap", "errorMsgMap", "LoginOK", "userName","dag","pid","deptid"})
public class AssignWorkController {
private AssignWorkService awService;
@Autowired
public AssignWorkController(AssignWorkService awService) {
	this.awService = awService;
}
@RequestMapping(path = "/insertaw", method =RequestMethod.POST)
public String InsertAssignWork(Model m,@RequestParam(value="empid")int empid,@RequestParam(value="wid")int wid,@RequestParam(value="work")String work) {
	awService.InsertAssignWork(empid, wid, work);
	return null;
}
@RequestMapping(path = "/changeaw", method =RequestMethod.POST)
public String CharngAssignWork(Model m,@RequestParam(value="empid")int empid,@RequestParam(value="wid")int wid,@RequestParam(value="cwid")int cwid,@RequestParam(value="work")String work) {
	awService.ChangeAssignWork(empid, wid,cwid, work);
	return null;
}
@ResponseBody
@RequestMapping(path = "/engworklist",method = RequestMethod.GET)
public String Engworklist(Model m,@ModelAttribute(name="LoginOK") Users u) {
	int empid = u.getEmployeeID();
	JSONArray jay = awService.engworklist(empid);
	String jaystr = jay.toString();
	return jaystr;
}
@RequestMapping(path = "/workstatus",method = RequestMethod.POST)
public String workstatus(Model m,@RequestParam(value="awid")int awid,@RequestParam(value="wkstatus")int wkstatus) {
	if(wkstatus == 1) {
		awService.setworkstatus1(m, awid);
	}else if(wkstatus ==2) {
		awService.setworkstatus2(m, awid);
	}else if(wkstatus==3) {
		awService.setworkstatus3(m, awid);
	}
	return null;
}
@ResponseBody
@RequestMapping(path = "/wkstatus1",method = RequestMethod.GET)
public String Wkstatus1(Model m,@ModelAttribute(name="LoginOK") Users u) {
	int empid = u.getEmployeeID();
	JSONArray jay = awService.wkstatus1(empid);
	String jaystr = jay.toString();
	return jaystr;
}
@ResponseBody
@RequestMapping(path = "/wkstatus2",method = RequestMethod.GET)
public String Wkstatus2(Model m,@ModelAttribute(name="LoginOK") Users u) {
	int empid = u.getEmployeeID();
	JSONArray jay = awService.wkstatus2(empid);
	String jaystr = jay.toString();
	return jaystr;
}
@ResponseBody
@RequestMapping(path = "/wkstatus3",method = RequestMethod.GET)
public String Wkstatus3(Model m,@ModelAttribute(name="LoginOK") Users u) {
	int empid = u.getEmployeeID();
	JSONArray jay = awService.wkstatus3(empid);
	String jaystr = jay.toString();
	return jaystr;
}
}
