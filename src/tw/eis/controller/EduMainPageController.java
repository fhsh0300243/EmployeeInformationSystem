package tw.eis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//@RequestMapping(path = "/login")
@SessionAttributes(value = { "LoginOK", "Msg" })
public class EduMainPageController {

	@RequestMapping(path = "/EducationPage.do", method = RequestMethod.GET)
	public String EducationMainPage() {
		return "EducationPage";
	}

	@RequestMapping(path = "/EduBasicInfo.do", method = RequestMethod.GET)
	public String EducationBasicInfo() {
		return "EduBasicInfo";
	}
	
	@RequestMapping(path = "/EduGotoTrain.do", method = RequestMethod.GET)
	public String EduGotoTrain() {
		return "EduGotoTrain";
	}
	
	@RequestMapping(path = "/EdumyTrain.do", method = RequestMethod.GET)
	public String EdumyTrain() {
		return "EdumyTrain";
	}
	

	@RequestMapping(path = "/Topic.do", method = RequestMethod.GET)
	public String Topic() {
		return "Topic";
	}
	
	@RequestMapping(path = "/EduNotice.do", method = RequestMethod.GET)
	public String EduNotice() {
		return "EduNotice";
	}


	@RequestMapping(path = "/EduSchedule.do", method = RequestMethod.GET)
	public String EduSchedule() {
		return "EduSchedule";
	}

	@RequestMapping(path = "/EduEmpComment.do", method = RequestMethod.GET)
	public String EduEmpComment() {
		return "EduEmpComment";
	}
	
	@RequestMapping(path = "/EduCommentTrain.do", method = RequestMethod.GET)
	public String EduCommentTrain() {
		return "EduCommentTrain";
	}

	@RequestMapping(path = "/EduTextbook.do", method = RequestMethod.GET)
	public String EduTextbook() {
		return "EduTextbook";
	}
	
	@RequestMapping(path = "/EduserBinding.do", method = RequestMethod.GET)
	public String EduserBinding() {
		return "EduserBinding";
	}
	

	@RequestMapping(path = "/EducationIndex.do", method = RequestMethod.GET)
	public String EducationIndex() {
		return "EducationIndex";
	}
	
	@RequestMapping(path = "/LoginSuccess", method = RequestMethod.GET)
	public String LoginSuccess() {
		return "LoginSuccess";
	}

}
