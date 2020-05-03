package tw.eis.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.ApplyForLeave;
import tw.eis.model.ApplyForLeaveService;
import tw.eis.model.Users;

@Controller
@SessionAttributes(names = { "LoginOK", "usersResultMap", "errorMsgMap" })
public class AttController {

	private ApplyForLeaveService aService;

	@Autowired
	public AttController(ApplyForLeaveService aService) {
		this.aService = aService;
	}

	@RequestMapping(path = "/preAttImage", method = RequestMethod.GET)
	public String preAttImage(@RequestParam("applyId") String applyId) {
		int AID = Integer.parseInt(applyId);
		ApplyForLeave myAtt = aService.queryApplyByAID(AID);

		byte[] image = myAtt.getAttachment();
		if (image == null) {
			return null;
		} else {
			return "redirect:getAttImage?applyId=" + applyId;
		}
	}

	@RequestMapping(path = "/getAttImage", method = RequestMethod.GET)
	public void processImageAction(@RequestParam("applyId") String applyId, HttpServletResponse response)
			throws IOException {
		int AID = Integer.parseInt(applyId);
		ApplyForLeave myAtt = aService.queryApplyByAID(AID);

		response.setContentType("image/png");
		ServletOutputStream os1 = response.getOutputStream();

		byte[] image = myAtt.getAttachment();

		InputStream is1 = new ByteArrayInputStream(image);
		byte[] bytes = new byte[is1.available()];
		int len = 0;
		while ((len = is1.read(bytes)) != -1) {
			os1.write(bytes, 0, len);
		}
		is1.close();
		os1.close();
	}

//	@RequestMapping(path = "/getFullImg", method = RequestMethod.GET)
//	public ResponseEntity<byte[]> processFullImgAction(@RequestParam("applyId") String applyId,
//			HttpServletResponse response) throws IOException {
//		int AID = Integer.parseInt(applyId);
//		ApplyForLeave myAtt = aService.queryApplyByAID(AID);
//
//		byte[] image = myAtt.getAttachment();
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.IMAGE_JPEG);
//
//		return new ResponseEntity<byte[]>(image, headers, HttpStatus.OK);
//	}
	
	
	
//	主管登入查看有無新申請請需要簽核 by GK

	@RequestMapping(path = "/queryNewApply", method = RequestMethod.GET,produces = "html/text;charset=utf-8")
	public @ResponseBody String queryNewApply(@ModelAttribute("LoginOK") Users LoginOK) {
		return Integer.toString(aService.queryNewApply(LoginOK.getEmployeeID()));
	}

//	員工查詢是否主管以簽核或退件

	@RequestMapping(path = "/querysucessApplyForLeave", method = RequestMethod.GET,produces = "html/text;charset=utf-8")
	public @ResponseBody String querysucessApplyForLeave(@ModelAttribute("LoginOK") Users LoginOK,@RequestParam String oldDate,@RequestParam String newDate) throws ParseException {
		System.out.println("--------------------------oldDate:"+oldDate);
		System.out.println("-------------------------newDate:"+newDate);
		
		return Integer.toString(aService.querysucessApplyForLeave(LoginOK.getEmployeeID(),oldDate,newDate));
	}

//	GK End
	
	
	
	
	
}
