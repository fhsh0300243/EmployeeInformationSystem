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

import tw.eis.model.CourseType;
import tw.eis.model.CourseTypeService;
import tw.eis.model.Users;

@Controller
@SessionAttributes(value = { "LoginOK", "Msg" })
public class CourseTypeController {

	private CourseTypeService CourseTypeService;

	@Autowired
	public CourseTypeController(CourseTypeService CourseTypeService) {
		this.CourseTypeService = CourseTypeService;
	}

	@RequestMapping(path = "/insertCourseTypePage.do", method = RequestMethod.POST)
	public String insertCourseType(@RequestParam(value = "CourseTypeId", required = false) String Id,
			@RequestParam(value = "TypeName") String TypeName, 
			@RequestParam(value = "TypeGroup") String TypeGroup,
			@RequestParam(value = "deptHR") String deptHR,
			@RequestParam(value = "deptRD") String deptRD, 
			@RequestParam(value = "deptQA") String deptQA, 
			@RequestParam(value = "deptTEST") String deptTEST,
			@RequestParam(value = "deptSales") String deptSales, 
			@RequestParam(value = "deptPM") String deptPM,
			@RequestParam(name = "dep", required = false) String dep, 
			@ModelAttribute(name = "LoginOK") Users LoginOK, Model model) throws Exception {

		System.out.println("dep:" + dep);

		Map<String, String> result = new HashMap<String, String>();
		model.addAttribute("result", result);

		Timestamp time = new Timestamp(Calendar.getInstance().getTime().getTime());

		if (dep == null) {
			dep = LoginOK.getDepartment();
		}

		CourseType CourseType = new CourseType();

		if (Id == null || Id.length() == 0) {
			CourseTypeService.insertCourseType(CourseType);
		}
		return "insertCourseTypePage";
	}
	
	@RequestMapping(path = "/queryCourseTypeRecords.do",method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
	@ResponseBody
	public String queryCourseTypeRecords(@ModelAttribute(name = "LoginOK") Users loginOK,HttpServletResponse response) throws IOException{
		List<CourseType> CourseTypes = CourseTypeService.queryCourseTypeRecords(loginOK.getEmployeeID());
		
		JSONArray json = new JSONArray();
		for(CourseType CourseType:CourseTypes) {
			JSONObject object = new JSONObject();
			object.put("CourseTypeID", CourseType.getCourseTypeId());
			object.put("TypeName", CourseType.getTypeName());
			object.put("TypeGroup",CourseType.getTypeGroup());
			object.put("deptHR", CourseType.getDeptHR());
			object.put("deptRD", CourseType.getDeptRD());
			object.put("deptQA", CourseType.getDeptQA());
			object.put("deptTest", CourseType.getDeptTEST());
			object.put("deptSales", CourseType.getDeptSales());
			object.put("deptPM", CourseType.getDeptPM());
			json.put(object);
		}
		
		return json.toString();	
	}
	
	@RequestMapping(path = "/checkCourseTypeFormData.do", method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
	public @ResponseBody String checkCourseTypeFormData(@ModelAttribute(name = "LoginOK") Users loginOK) throws IOException{
		List<CourseType> CourseTypes = CourseTypeService.queryCourseTypeByAllow(loginOK.getEmployeeID(), loginOK.getUserName());
		
		JSONArray json = new JSONArray();
		for(CourseType CourseType:CourseTypes) {
			JSONObject object = new JSONObject();
			object.put("CourseTypeId", CourseType.getCourseTypeId());
			object.put("TypeName", CourseType.getTypeName());
			object.put("TypeGroup",CourseType.getTypeGroup());
			object.put("deptHR", CourseType.getDeptHR());
			object.put("deptRD", CourseType.getDeptRD());
			object.put("deptQA", CourseType.getDeptQA());
			object.put("deptTEST", CourseType.getDeptTEST());
			object.put("deptSales", CourseType.getDeptSales());
			object.put("deptPM", CourseType.getDeptPM());
			json.put(object);
		}
		
		return json.toString();	
	}
	
	@RequestMapping(path = "/queryCourseTypeForLook.do",method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
	public @ResponseBody String queryCourseTypeForLook(@ModelAttribute(name = "LoginOK") Users loginOK) {
		List<CourseType> CourseTypes = CourseTypeService.queryCourseType(loginOK.getDepartment());
		
		JSONArray json = new JSONArray();
		for(CourseType CourseType:CourseTypes) {
			JSONObject object = new JSONObject();
			object.put("CourseTypeId", CourseType.getCourseTypeId());
			object.put("TypeName", CourseType.getTypeName());
			object.put("TypeGroup",CourseType.getTypeGroup());
			object.put("deptHR", CourseType.getDeptHR());
			object.put("deptRD", CourseType.getDeptRD());
			object.put("deptQA", CourseType.getDeptQA());
			object.put("deptTEST", CourseType.getDeptTEST());
			object.put("deptSales", CourseType.getDeptSales());
			object.put("deptPM", CourseType.getDeptPM());
			json.put(object);
		}
		
		return json.toString();	
	}
	
	@RequestMapping(path = "/reflashCourseTypePage.do",method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
	public @ResponseBody String reflashCourseTypePage(@ModelAttribute(name = "LoginOK") Users loginOK) throws IOException{
		List<CourseType> CourseTypes = CourseTypeService.queryCourseType(loginOK.getDepartment());
		return Integer.toString(CourseTypes.size());
	}
	
	@RequestMapping(path = "/deleteCourseType.do",method = RequestMethod.POST,produces = "html/text;charset=UTF-8")
	public @ResponseBody String deleteCourseType(@RequestParam(value = "CourseTypeId") int CourseTypeId) {
		CourseTypeService.deleteCourseType(CourseTypeId);
		return "true";
	}
	
//	@RequestMapping(path = "/queryCourseType",method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
//	public @ResponseBody String queryCourseType(@ModelAttribute(name = "LoginOK") Users loginOK) {
//		
//	}

}
