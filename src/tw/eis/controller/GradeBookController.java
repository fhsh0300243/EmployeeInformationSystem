package tw.eis.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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

import tw.eis.model.GradeBook;
import tw.eis.model.GradeBookService;
import tw.eis.model.Users;

@Controller
@SessionAttributes(value = { "LoginOK", "Msg" })
public class GradeBookController {

	private GradeBookService GradeBookService;

	@Autowired
	public GradeBookController(GradeBookService GradeBookService) {
		this.GradeBookService = GradeBookService;
	}

	@RequestMapping(path = "/insertGradeBookPage", method = RequestMethod.POST)
	public String insertGradeBook(@RequestParam(value = "CourseId", required = false) String Id,
			@RequestParam(value = "EmployeeId") int EmployeeId, 
			@RequestParam(value = "EmployeeName") String EmployeeName,
			@RequestParam(value = "Grade") int Grade,
			@RequestParam(name = "dep", required = false) String dep, 
			@ModelAttribute(name = "LoginOK") Users LoginOK, Model model) throws Exception {

		System.out.println("dep:" + dep);

		Map<String, String> result = new HashMap<String, String>();
		model.addAttribute("result", result);

		Timestamp time = new Timestamp(Calendar.getInstance().getTime().getTime());

		if (dep == null) {
			dep = LoginOK.getDepartment();
		}

		GradeBook GradeBook = new GradeBook();

		if (Id == null || Id.length() == 0) {
			GradeBookService.insertGradeBook(GradeBook);
		}
		return "GradeBookPage";
	}
	
	@RequestMapping(path = "/queryGradeBookRecords",method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
	@ResponseBody
	public String queryGradeBookRecords(@ModelAttribute(name = "LoginOK") Users loginOK,HttpServletResponse response) throws IOException{
		List<GradeBook> GradeBooks = GradeBookService.queryGradeBookRecords(loginOK.getEmployeeID());
		
		JSONArray json = new JSONArray();
		for(GradeBook GradeBook:GradeBooks) {
			JSONObject object = new JSONObject();
			object.put("CourseID", GradeBook.getCourseId());
			object.put("EmployeeId", GradeBook.getEmployeeId());
			object.put("EmployeeName",GradeBook.getEmployeeName());
			object.put("Grade", GradeBook.getGrade());
			json.put(object);
		}
		
		return json.toString();	
	}
	
	@RequestMapping(path = "/checkGradeBookFormData", method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
	public @ResponseBody String checkGradeBookFormData(@ModelAttribute(name = "LoginOK") Users loginOK) throws IOException{
		List<GradeBook> GradeBooks = GradeBookService.queryGradeBookByAllow(loginOK.getEmployeeID(), loginOK.getUserName());
		
		JSONArray json = new JSONArray();
		for(GradeBook GradeBook:GradeBooks) {
			JSONObject object = new JSONObject();
			object.put("Coursed", GradeBook.getCourseId());
			object.put("EmployeeId", GradeBook.getEmployeeId());
			object.put("EmployeeName",GradeBook.getEmployeeName());
			object.put("Grade", GradeBook.getGrade());
			json.put(object);
		}
		
		return json.toString();	
	}
	
	@RequestMapping(path = "/queryGradeBookForLook",method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
	public @ResponseBody String queryGradeBookForLook(@ModelAttribute(name = "LoginOK") Users loginOK) {
		List<GradeBook> GradeBooks = GradeBookService.queryGradeBook(loginOK.getDepartment());
		
		JSONArray json = new JSONArray();
		for(GradeBook GradeBook:GradeBooks) {
			JSONObject object = new JSONObject();
			object.put("CourseId", GradeBook.getCourseId());
			object.put("EmployeeId", GradeBook.getEmployeeId());
			object.put("EmployeeName",GradeBook.getEmployeeName());
			object.put("Grade", GradeBook.getGrade());
			json.put(object);
		}
		
		return json.toString();	
	}
	
	@RequestMapping(path = "/reflashGradeBookPage",method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
	public @ResponseBody String reflashGradeBookPage(@ModelAttribute(name = "LoginOK") Users loginOK) throws IOException{
		List<GradeBook> GradeBooks = GradeBookService.queryGradeBook(loginOK.getDepartment());
		return Integer.toString(GradeBooks.size());
	}
	
	@RequestMapping(path = "/deleteGradeBook",method = RequestMethod.POST,produces = "html/text;charset=UTF-8")
	public @ResponseBody String deleteGradeBook(@RequestParam(value = "CourseId") int CourseId) {
		GradeBookService.deleteGradeBook(CourseId);
		return "true";
	}
	
//	@RequestMapping(path = "/queryCourseType",method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
//	public @ResponseBody String queryCourseType(@ModelAttribute(name = "LoginOK") Users loginOK) {
//		
//	}

}
