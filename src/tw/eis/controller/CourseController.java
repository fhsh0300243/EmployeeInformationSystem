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

import tw.eis.model.Course;
import tw.eis.model.CourseService;
import tw.eis.model.Users;

@Controller
@SessionAttributes(value = { "LoginOK", "Msg" })
public class CourseController {

	private CourseService CourseService;

	@Autowired
	public CourseController(CourseService courseService) {
		this.CourseService = courseService;
	}

	@RequestMapping(path = "/insertCourse", method = RequestMethod.POST)
	public String insertLesson(@RequestParam(value = "CourseId", required = false) String Id,
			@RequestParam(value = "Topic") String Topic, 
			@RequestParam(value = "TopicType") String TopicType,
			@RequestParam(value = "TeacherId") String TeacherId,
			@RequestParam(value = "Teacher") String Teacher, 
			@RequestParam(value = "Place") String Place,
			@RequestParam(value = "DateFrom") Date DateFrom, 
			@RequestParam(value = "DateTo") Date DateTo,
			@RequestParam(value = "TimeFrom") Timestamp TimeFrom, 
			@RequestParam(value = "TimeTo") Timestamp TimeTo,
			@RequestParam(value = "Credit") int Credit,
			@RequestParam(value = "ClassType") String ClassType, 
			@RequestParam(value = "CourseType") String CourseType,
			@RequestParam(value = "Uplimit") int Uplimit,
			@RequestParam(value = "Introduction") String Introduction,
			@RequestParam(value = "Note") String Note,
			@RequestParam(name = "dep", required = false) String dep, 
			@ModelAttribute(name = "LoginOK") Users LoginOK, Model model) throws Exception {

		System.out.println("dep:" + dep);

		Map<String, String> result = new HashMap<String, String>();
		model.addAttribute("result", result);

		Timestamp time = new Timestamp(Calendar.getInstance().getTime().getTime());

		if (dep == null) {
			dep = LoginOK.getDepartment();
		}

		Course course = new Course();

		if (Id == null || Id.length() == 0) {
			CourseService.insertCourse(course);
		}
		return "Course";
	}
	
	@RequestMapping(path = "/queryCourseRecords",method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
	@ResponseBody
	public String queryCourseRecords(@ModelAttribute(name = "LoginOK") Users loginOK,HttpServletResponse response) throws IOException{
		List<Course> Courses = CourseService.queryCourseRecords(loginOK.getEmployeeID());
		
		JSONArray json = new JSONArray();
		for(Course Course:Courses) {
			JSONObject object = new JSONObject();
			object.put("CourseID", Course.getCourseId());
			object.put("Topic", Course.getTopic());
			object.put("TopicType",Course.getTopicType());
			object.put("TeacherID", Course.getTeacherId());
			object.put("Teacher", Course.getTeacher());
			object.put("Place", Course.getPlace());
			object.put("DateFrom", Course.getDateFrom());
			object.put("DateTo", Course.getDateTo());
			object.put("TimeFrom", Course.getTimeFrom());
			object.put("TimeTo", Course.getTimeTo());
			object.put("Credit", Course.getCredit());
			object.put("ClassType", Course.getClassType());
			object.put("CourseType", Course.getCourseType());
			object.put("Uplimit", Course.getUplimit());
			object.put("Introduction", Course.getIntroduction());
			object.put("Note", Course.getNote());
			json.put(object);
		}
		
		return json.toString();	
	}
	
	@RequestMapping(path = "/checkCourseFormData", method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
	public @ResponseBody String checkFormData(@ModelAttribute(name = "LoginOK") Users loginOK) throws IOException{
		List<Course> Courses = CourseService.queryCourseByAllow(loginOK.getEmployeeID(), loginOK.getUserName());
		
		JSONArray json = new JSONArray();
		for(Course Course:Courses) {
			JSONObject object = new JSONObject();
			object.put("CourseID", Course.getCourseId());
			object.put("Topic", Course.getTopic());
			object.put("TopicType",Course.getTopicType());
			object.put("TeacherID", Course.getTeacherId());
			object.put("Teacher", Course.getTeacher());
			object.put("Place", Course.getPlace());
			object.put("DateFrom", Course.getDateFrom());
			object.put("DateTo", Course.getDateTo());
			object.put("TimeFrom", Course.getTimeFrom());
			object.put("TimeTo", Course.getTimeTo());
			object.put("Credit", Course.getCredit());
			object.put("ClassType", Course.getClassType());
			object.put("CourseType", Course.getCourseType());
			object.put("Uplimit", Course.getUplimit());
			object.put("Introduction", Course.getIntroduction());
			object.put("Note", Course.getNote());
			json.put(object);
		}
		
		return json.toString();	
	}
	
	@RequestMapping(path = "/queryCourse",method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
	public @ResponseBody String queryCourse(@ModelAttribute(name = "LoginOK") Users loginOK) {
		List<Course> Courses = CourseService.queryCourse(loginOK.getDepartment());
		
		JSONArray json = new JSONArray();
		for(Course Course:Courses) {
			JSONObject object = new JSONObject();
			object.put("CourseID", Course.getCourseId());
			object.put("Topic", Course.getTopic());
			object.put("TopicType",Course.getTopicType());
			object.put("TeacherID", Course.getTeacherId());
			object.put("Teacher", Course.getTeacher());
			object.put("Place", Course.getPlace());
			object.put("DateFrom", Course.getDateFrom());
			object.put("DateTo", Course.getDateTo());
			object.put("TimeFrom", Course.getTimeFrom());
			object.put("TimeTo", Course.getTimeTo());
			object.put("Credit", Course.getCredit());
			object.put("ClassType", Course.getClassType());
			object.put("CourseType", Course.getCourseType());
			object.put("Uplimit", Course.getUplimit());
			object.put("Introduction", Course.getIntroduction());
			object.put("Note", Course.getNote());
			json.put(object);
		}
		
		return json.toString();	
	}
	
	@RequestMapping(path = "/reflashPage",method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
	public @ResponseBody String reflashPage(@ModelAttribute(name = "LoginOK") Users loginOK) throws IOException{
		List<Course> Courses = CourseService.queryCourse(loginOK.getDepartment());
		return Integer.toString(Courses.size());
	}
	
	@RequestMapping(path = "/delete",method = RequestMethod.GET,produces = "html/text;charset=UTF-8")
	public @ResponseBody String delete(@RequestParam(value = "CourseId") int CourseId) {
		CourseService.deleteCourse(CourseId);
		return "true";
	}

}
