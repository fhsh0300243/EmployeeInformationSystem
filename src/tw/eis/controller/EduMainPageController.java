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
	
	@RequestMapping(path = "/EdugotoTrain.do", method = RequestMethod.GET)
	public String EdugotoTrain() {
		return "EdugotoTrain";
	}
	
	@RequestMapping(path = "/EdumyTrain.do", method = RequestMethod.GET)
	public String EdumyTrain() {
		return "EdumyTrain";
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
	
	@RequestMapping(path = "/EduQueryResult.do", method = RequestMethod.GET)
	public String EduQueryResult() {
		return "EduQueryResult";
	}
	
	@RequestMapping(path = "/EduScoreQuery.do", method = RequestMethod.GET)
	public String EduScoreQuery() { 
		return "EduScoreQuery";
	}
	
	@RequestMapping(path = "/EdudynamicDetail.do", method = RequestMethod.GET)
	public String EdudynamicDetail() {
		return "EdudynamicDetail";
	}
	
	
	@RequestMapping(path = "/EduAddCoursePage.do", method = RequestMethod.GET)
	public String EduAddCoursePage() {
		return "EduAddCoursePage";
	}
	
	@RequestMapping(path = "/EduDeleteCoursePage.do", method = RequestMethod.GET)
	public String EduDeleteCoursePage() {
		return "EduDeleteCoursePage";
	}
	
	
	@RequestMapping(path = "/EduUnsigned.do", method = RequestMethod.GET)
	public String EduUnsigned() {
		return "EduUnsigned";
	}
	
	@RequestMapping(path = "/EduSigned.do", method = RequestMethod.GET)
	public String EduSigned() {
		return "EduSigned";
	}
	

	@RequestMapping(path = "/EducationIndex.do", method = RequestMethod.GET)
	public String EducationIndex() {
		return "EducationIndex";
	}
	
	
	@RequestMapping(path = "/LoginSuccess", method = RequestMethod.GET)
	public String LoginSuccess() {
		return "LoginSuccess";
		
	}
	
	@RequestMapping(path = "/Eduajax2.do", method = RequestMethod.GET)
	public String Eduajax2() {
		return "Eduajax2";
		
	}

	
	


}
