package tw.eis.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.eis.model.Users;
import tw.eis.model.UsersService;

@Controller
@SessionAttributes(value = { "LoginOK"})
public class LoginUpTimeController {
	private UsersService uService;
	
	@Autowired
	public LoginUpTimeController(UsersService uService){
		this.uService = uService;
	}
	
	
	@RequestMapping(path = "/checkDate",method = RequestMethod.GET,produces = "html/text;charset=utf-8")
	public @ResponseBody String checkDate(@ModelAttribute(name = "LoginOK") Users loginOK) throws IOException {
		Timestamp Date = uService.queryLoginTime(loginOK.getEmployeeID());
		Timestamp tim = new Timestamp(Calendar.getInstance().getTime().getTime());
		loginOK.setLoginTime(tim);
		uService.updateLoginTime(loginOK);
		JSONArray jay = new JSONArray();		
			JSONObject job = new JSONObject();
			System.out.println("Date:"+Date);
			job.put("oldDate", Date);
			job.put("newDate", tim);
			jay.put(job);
			
			
			
		return jay.toString();
	}
	
	
}
