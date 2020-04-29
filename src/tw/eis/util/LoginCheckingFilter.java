package tw.eis.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.eis.model.Users;

@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "mustLogin1", value = "/applyforleave"), 
				@WebInitParam(name = "mustLogin2", value = "/signforleave"), 
				@WebInitParam(name = "mustLogin3", value = "/changeLT"),
				@WebInitParam(name = "mustLogin4", value = "/changeDHM"),
				@WebInitParam(name = "mustLogin5", value = "/insertaw"), 
				@WebInitParam(name = "mustLogin6", value = "/preAttImage"), 
				@WebInitParam(name = "mustLogin7", value = "/getAttImage"),
				@WebInitParam(name = "mustLogin8", value = "/gotoAttendanceOwnPage"),
				@WebInitParam(name = "mustLogin9", value = "/gotoAttendanceDepartmentPage"), 
				@WebInitParam(name = "mustLogin10", value = "/InquiryAttendance"), 
				@WebInitParam(name = "mustLogin11", value = "/InquiryAttendanceDepartment"),
				@WebInitParam(name = "mustLogin12", value = "/InquiryToday"),
				@WebInitParam(name = "mustLogin13", value = "/PunchAction"), 
				@WebInitParam(name = "mustLogin14", value = "/BullenBoardPage"), 
				@WebInitParam(name = "mustLogin15", value = "/insert"),
				@WebInitParam(name = "mustLogin16", value = "/queryBulletinRecord"),
				@WebInitParam(name = "mustLogin17", value = "/checkdata"), 
				@WebInitParam(name = "mustLogin18", value = "/queryBulletinForLook"), 
				@WebInitParam(name = "mustLogin19", value = "/reflash"),
				@WebInitParam(name = "mustLogin20", value = "/delete"),
				@WebInitParam(name = "mustLogin21", value = "/download"), 
				@WebInitParam(name = "mustLogin22", value = "/insertCoursePage"), 
				@WebInitParam(name = "mustLogin23", value = "/queryCourseRecords"),
				@WebInitParam(name = "mustLogin24", value = "/checkCourseFormData"),
				@WebInitParam(name = "mustLogin25", value = "/queryCourseForLook"), 
				@WebInitParam(name = "mustLogin26", value = "/reflashCoursePage"), 
				@WebInitParam(name = "mustLogin27", value = "/deleteCourse"),
				@WebInitParam(name = "mustLogin28", value = "/insertCourseTypePage.do"),
				@WebInitParam(name = "mustLogin29", value = "/queryCourseTypeRecords.do"), 
				@WebInitParam(name = "mustLogin30", value = "/checkCourseTypeFormData.do"), 
				@WebInitParam(name = "mustLogin31", value = "/queryCourseTypeForLook.do"),
				@WebInitParam(name = "mustLogin32", value = "/reflashCourseTypePage.do"),
				@WebInitParam(name = "mustLogin33", value = "/deleteCourseType.do"), 
				@WebInitParam(name = "mustLogin34", value = "/EducationPage.do"), 
				@WebInitParam(name = "mustLogin35", value = "/EduBasicInfo.do"),
				@WebInitParam(name = "mustLogin36", value = "/EduGotoTrain.do"),
				@WebInitParam(name = "mustLogin37", value = "/EdumyTrain.do"), 
				@WebInitParam(name = "mustLogin38", value = "/Topic.do"), 
				@WebInitParam(name = "mustLogin39", value = "/EduNotice.do"),
				@WebInitParam(name = "mustLogin40", value = "/EduSchedule.do"),
				@WebInitParam(name = "mustLogin41", value = "/EduEmpComment.do"), 
				@WebInitParam(name = "mustLogin42", value = "/EduCommentTrain.do"), 
				@WebInitParam(name = "mustLogin43", value = "/EduTextbook.do"),
				@WebInitParam(name = "mustLogin44", value = "/EduserBinding.do"),
				@WebInitParam(name = "mustLogin45", value = "/EducationIndex.do"), 
				@WebInitParam(name = "mustLogin46", value = "/LoginSuccess"), 
				@WebInitParam(name = "mustLogin47", value = "/EmployeePage.do"),
				@WebInitParam(name = "mustLogin48", value = "/QueryEmployee.do"),
				@WebInitParam(name = "mustLogin49", value = "/QueryDeptFeeApply.do"), 
				@WebInitParam(name = "mustLogin50", value = "/QueryEmpAttendance.do"), 
				@WebInitParam(name = "mustLogin51", value = "/AddEmployee.do"),
				@WebInitParam(name = "mustLogin52", value = "/EditEmployee.do"),
				@WebInitParam(name = "mustLogin53", value = "/AddEmployee.action"), 
				@WebInitParam(name = "mustLogin54", value = "/EditAddEmployee.action"), 
				@WebInitParam(name = "mustLogin55", value = "/QueryEmp.action"),
				@WebInitParam(name = "mustLogin56", value = "/DeptCostPercent.action"),
				@WebInitParam(name = "mustLogin57", value = "/DeptCostDetail.action"), 
				@WebInitParam(name = "mustLogin58", value = "/QueryEmpAttdenance.action"), 
				@WebInitParam(name = "mustLogin59", value = "/EmpList"),
				@WebInitParam(name = "mustLogin60", value = "/BullBoardListOfHR"),
				@WebInitParam(name = "mustLogin61", value = "/test.do"), 
				@WebInitParam(name = "mustLogin62", value = "/AddFeeApp.action"), 
				@WebInitParam(name = "mustLogin63", value = "/FeeAllPage.action"),
				@WebInitParam(name = "mustLogin64", value = "/SingerPage"),
				@WebInitParam(name = "mustLogin65", value = "/SingerPassPage"), 
				@WebInitParam(name = "mustLogin66", value = "/ReturnPage"), 
				@WebInitParam(name = "mustLogin67", value = "/FeeReturnEditPage"),
				@WebInitParam(name = "mustLogin68", value = "/insertGradeBookPage"),
				@WebInitParam(name = "mustLogin69", value = "/queryGradeBookRecords"), 
				@WebInitParam(name = "mustLogin70", value = "/checkGradeBookFormData"), 
				@WebInitParam(name = "mustLogin71", value = "/queryGradeBookForLook"),
				@WebInitParam(name = "mustLogin72", value = "/reflashGradeBookPage"),
				@WebInitParam(name = "mustLogin73", value = "/deleteGradeBook"), 
				@WebInitParam(name = "mustLogin74", value = "/InqueryCalendar"), 
				@WebInitParam(name = "mustLogin75", value = "/HolidayAction"),
				@WebInitParam(name = "mustLogin76", value = "/DeleteCalendar"),
				@WebInitParam(name = "mustLogin77", value = "/toLoginPage"), 
				@WebInitParam(name = "mustLogin78", value = "/FeeAllPage.action"), 
				@WebInitParam(name = "mustLogin79", value = "/AddFeeApp.action"),
				@WebInitParam(name = "mustLogin80", value = "/FeeReturnPage.action"),
				@WebInitParam(name = "mustLogin81", value = "/FeeSingerPage.action"), 
				@WebInitParam(name = "mustLogin82", value = "/chairmantable"), 
				@WebInitParam(name = "mustLogin83", value = "/managertable"),
				@WebInitParam(name = "mustLogin84", value = "/managerview"),
				@WebInitParam(name = "mustLogin85", value = "/insertpqt"), 
				@WebInitParam(name = "mustLogin86", value = "/changepqt"), 
				@WebInitParam(name = "mustLogin87", value = "/deletepqt"),
				@WebInitParam(name = "mustLogin88", value = "/ChangePQT"),
				@WebInitParam(name = "mustLogin89", value = "/InsertPQT"), 
				@WebInitParam(name = "mustLogin90", value = "/fivedept"), 
				@WebInitParam(name = "mustLogin91", value = "/qatarget"),
				@WebInitParam(name = "mustLogin92", value = "/performance"),
				@WebInitParam(name = "mustLogin93", value = "/CreateTable0300"),
				@WebInitParam(name = "mustLogin94", value = "/CheckStatus0830"),
				@WebInitParam(name = "mustLogin95", value = "/CheckStatus2330"),
				@WebInitParam(name = "mustLogin96", value = "/QueryPerformance.do")			

		})
public class LoginCheckingFilter implements Filter {
	List<String> url = new ArrayList<String>();
	String servletPath;
	String contextPath;
	String requestURI;
	
	public void init(FilterConfig fConfig) throws ServletException {
		Enumeration<String> e = fConfig.getInitParameterNames();
		while (e.hasMoreElements()) {
			String path = e.nextElement();
			url.add(fConfig.getInitParameter(path));
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		boolean isRequestedSessionIdValid = false;
		if (request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			servletPath = req.getServletPath();  
			contextPath = req.getContextPath();
			requestURI  = req.getRequestURI();
			isRequestedSessionIdValid = req.isRequestedSessionIdValid();

			if (mustLogin()) {           
				if (checkLogin(req)) {   
					chain.doFilter(request, response);
				} else {				
					HttpSession session = req.getSession();
				    
					
					if ( ! isRequestedSessionIdValid ) {
						session.setAttribute("timeOut", "使用逾時，請重新登入");
					} else {
						session.setAttribute("requestURI", requestURI);	
					}
					resp.sendRedirect(contextPath + "/userLogin");
					return;
				}
			} else {  
				chain.doFilter(request, response);
			}
		} else {
			throw new ServletException("Request/Response 型態錯誤(極不可能發生)");
		}
	}
	private boolean checkLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		Users loginToken = (Users) session.getAttribute("LoginOK");
		if (loginToken == null) {
			return false;
		} else {
			return true;
		}
	}

	private boolean mustLogin() {
		boolean login = false;
		for (String sURL : url) {
			if (sURL.endsWith("*")) {
				sURL = sURL.substring(0, sURL.length() - 1);
				if (servletPath.startsWith(sURL)) {
					login = true;
					break;
				}
			} else {
				if (servletPath.equals(sURL)) {
					login = true;
					break;
				}
			}
		}
		return login;
	}
	@Override
	public void destroy() {
	}
}