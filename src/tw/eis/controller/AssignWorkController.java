package tw.eis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tw.eis.model.AssignWorkService;
@Controller
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

}
