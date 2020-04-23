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
	public String EducationBasicInfoPage() {
		return "EduBasicInfo";
	}
	
	@RequestMapping(path = "/Topic.do", method = RequestMethod.GET)
	public String Topic() {
		return "Topic";
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
