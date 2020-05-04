package tw.eis.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import tw.eis.model.Course;
import tw.eis.model.CourseService;
import tw.eis.model.EmployeeService;
import tw.eis.model.Users;

@Controller
@SessionAttributes(value = { "LoginOK", "Msg" })
public class CourseController {

	private CourseService CourseService;
	private EmployeeService eService;

	@Autowired
	public CourseController(CourseService courseService, EmployeeService eService) {
		this.CourseService = courseService;
		this.eService = eService;
	}

	@RequestMapping(path = "/addCourse", method = RequestMethod.POST)
	public String insertCourse(@ModelAttribute(name = "LoginOK") Users LoginOK, Model model,
			@RequestParam(value = "CourseId", required = false) String Id, 
			@RequestParam(value = "Topic") String Topic,
			@RequestParam(value = "TopicType") String TopicType, 
			@RequestParam(value = "TeacherId") String TeacherId,
			@RequestParam(value = "Teacher") String Teacher, 
			@RequestParam(value = "Place") String Place,
			@RequestParam(value = "DateFrom") String DateFrom, 
			@RequestParam(value = "DateTo") String DateTo,
			@RequestParam(value = "TimeFrom") String TimeFrom, 
			@RequestParam(value = "TimeTo") String TimeTo,
			@RequestParam(value = "Credit") int Credit, 
			@RequestParam(value = "ClassType") String ClassType,
			@RequestParam(value = "CourseType") String CourseType, 
			@RequestParam(value = "Uplimit") int Uplimit,
			@RequestParam(value = "Introduction") String Introduction, 
			@RequestParam(value = "Note") String Note,
			@RequestParam(value = "AttachmentFiles") MultipartFile multipartfile, HttpServletRequest request,Model model2, 
			@RequestParam(name = "dep", required = false) String dep) throws IOException, SQLException {


		Map<String, String> msgmap = new HashMap<String, String>();
		Map<String, String> inputmsg = new HashMap<String, String>();
		
		model.addAttribute("msgmap", msgmap);
		model.addAttribute("inputmsg", inputmsg);

		inputmsg.put("Course", Id);
		inputmsg.put("Topic", Topic);
		inputmsg.put("TopicType", TopicType);
		inputmsg.put("TeacherId", TeacherId);
		inputmsg.put("Teacher", Teacher);
		inputmsg.put("Place", Place);
		inputmsg.put("DateFrom", DateFrom);
		inputmsg.put("DateTo", DateTo);
		inputmsg.put("TimeFrom", TimeFrom);
		inputmsg.put("TimeTo", TimeTo);
		inputmsg.put("ClassType", ClassType);
		inputmsg.put("CourseType", CourseType);
		inputmsg.put("Introduction", Introduction);
		inputmsg.put("Note", Note);
		
		
		return "EduAddCoursePage";
	}

	@RequestMapping(path = "/queryCourseRecords", method = RequestMethod.GET, produces = "html/text;charset=UTF-8")
	@ResponseBody
	public String queryCourseRecords(@ModelAttribute(name = "LoginOK") Users loginOK, HttpServletResponse response)
			throws IOException {
		List<Course> Courses = CourseService.queryCourseRecords(loginOK.getEmployeeID());

		JSONArray json = new JSONArray();
		for (Course Course : Courses) {
			JSONObject object = new JSONObject();
			object.put("CourseID", Course.getCourseId());
			object.put("Topic", Course.getTopic());
			object.put("TopicType", Course.getTopicType());
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

	@RequestMapping(path = "/checkCourseFormData", method = RequestMethod.GET, produces = "html/text;charset=UTF-8")
	public @ResponseBody String checkCourseFormData(@ModelAttribute(name = "LoginOK") Users loginOK)
			throws IOException {
		List<Course> Courses = CourseService.queryCourseByAllow(loginOK.getEmployeeID(), loginOK.getUserName());

		JSONArray json = new JSONArray();
		for (Course Course : Courses) {
			JSONObject object = new JSONObject();
			object.put("CourseID", Course.getCourseId());
			object.put("Topic", Course.getTopic());
			object.put("TopicType", Course.getTopicType());
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

	@RequestMapping(path = "/queryCourseForLook", method = RequestMethod.GET, produces = "html/text;charset=UTF-8")
	public @ResponseBody String queryCourseForLook(@ModelAttribute(name = "LoginOK") Users loginOK) {
		List<Course> Courses = CourseService.queryCourse(loginOK.getDepartment());

		JSONArray json = new JSONArray();
		for (Course Course : Courses) {
			JSONObject object = new JSONObject();
			object.put("CourseID", Course.getCourseId());
			object.put("Topic", Course.getTopic());
			object.put("TopicType", Course.getTopicType());
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

	@RequestMapping(path = "/reflashCoursePage", method = RequestMethod.GET, produces = "html/text;charset=UTF-8")
	public @ResponseBody String reflashCoursePage(@ModelAttribute(name = "LoginOK") Users loginOK) throws IOException {
		List<Course> Courses = CourseService.queryCourse(loginOK.getDepartment());
		return Integer.toString(Courses.size());
	}

	@RequestMapping(path = "/deleteCourse", method = RequestMethod.POST, produces = "html/text;charset=UTF-8")
	public @ResponseBody String deleteCourse(@RequestParam(value = "CourseId") int CourseId) {
		CourseService.deleteCourse(CourseId);
		return "true";
	}

	@RequestMapping(path = "/edudownload", method = RequestMethod.GET)
	public ResponseEntity<byte[]> EduDownload(HttpServletRequest request,
			@RequestParam(value = "CourseId") String CourseId,
			@RequestParam(value = "AttachmentFiles") String AttachmentFiles) {
		byte[] file = CourseService.queryAttachmentFiles(Integer.parseInt(CourseId));
		String path = request.getServletContext().getRealPath("/file/");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("AttachmentFiles", AttachmentFiles);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		return new ResponseEntity<byte[]>(file, headers, HttpStatus.OK);

	}

}
