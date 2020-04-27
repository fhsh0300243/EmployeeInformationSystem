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

import tw.eis.model.BulletinBoard;
import tw.eis.model.BulletinBoardService;
import tw.eis.model.Users;

@Controller
@SessionAttributes(value = { "LoginOK", "Msg" })
public class BullBoardController {

	private BulletinBoardService BulletinBoardService;

	@Autowired
	public BullBoardController(BulletinBoardService BulletinBoardService) {
		this.BulletinBoardService = BulletinBoardService;
	}
	
	@RequestMapping(path = "/BullenBoardPage",method = RequestMethod.GET)
	public String MainPage(@ModelAttribute(name = "LoginOK") Users loginOK,Model model) {
		model.addAttribute("Level", loginOK.getEmployee().getLevel());
		return "BullenBoardPage";
	}
	
//	新增
	@RequestMapping(path = "/insert", method = RequestMethod.POST)
	public String insertNotice(@RequestParam(value="BulletinBoardid",required = false)String id,@RequestParam(value = "title") String title,
			@RequestParam(value = "content") String content, @RequestParam(value = "uptime") Date uptime,
			@RequestParam(value = "downtime") Date downtime,
			@RequestParam(value = "file") MultipartFile multipartfile,@RequestParam(name = "dep",required = false) String dep, @ModelAttribute(name = "LoginOK") Users LoginOK,
			Model model) throws IOException, SQLException {
		System.out.println("dep:"+dep);
		System.out.println("id:"+id);
		
		Map<String, String> Msg = new HashMap<String, String>();
		model.addAttribute("Msg", Msg);

		Timestamp tim = new Timestamp(Calendar.getInstance().getTime().getTime());
		
		
		if(dep==null) {
			dep = LoginOK.getDepartment();
		}
			BulletinBoard BulletinBoard = new BulletinBoard(LoginOK, title, content, dep, uptime, downtime, tim);
		
		
		if (!multipartfile.isEmpty()) {
			String FileName = multipartfile.getOriginalFilename();

			System.out.println("FileName:" + FileName);

			byte[] b = multipartfile.getBytes();

			BulletinBoard.setAttachedFilesName(FileName);
			BulletinBoard.setAttachedFiles(b);

		}
		
		if (id==null || id.length()==0||id.equals(",")) {
			
			BulletinBoardService.insertBulletin(BulletinBoard);
		}
		else {
			id=id.replace(",", "");
			BulletinBoard.setBulletinBoardID(Integer.valueOf(id));
			BulletinBoardService.updateBulletin(BulletinBoard);
			
		}
		
		return "BullenBoardPage";
	}
	
//	歷史紀錄
	@RequestMapping(path = "/queryBulletinRecord",method = RequestMethod.GET,produces = "html/text;charset=utf-8")
	@ResponseBody
	public String queryBulletinRecord(@ModelAttribute(name = "LoginOK") Users loginOK,HttpServletResponse response) throws IOException {
		List<BulletinBoard> BulletinBoards = BulletinBoardService.queryBulletinRecord(loginOK.getEmployeeID());
		

		JSONArray jay = new JSONArray();
		for(BulletinBoard BulletinBoard:BulletinBoards) {
			JSONObject job = new JSONObject();
			job.put("BulletinBoardID", BulletinBoard.getBulletinBoardID());
			job.put("Title", BulletinBoard.getTitle());
			job.put("Context",BulletinBoard.getContent());
			job.put("AttachedFiles", BulletinBoard.getAttachedFiles());
			job.put("AttachedFilesName", BulletinBoard.getAttachedFilesName());
			job.put("Authority", BulletinBoard.getAuthority());
			job.put("upTime", BulletinBoard.getUpTime());
			job.put("downTime", BulletinBoard.getDownTime());
			job.put("Date", BulletinBoard.getDate());
			job.put("Department",BulletinBoard.getUsers().getDepartment());
			jay.put(job);
		}
		
		return jay.toString();
	}
	
	@RequestMapping(path = "/checkdata",method = RequestMethod.GET,produces = "html/text;charset=utf-8")
	
	public @ResponseBody String checkdata(@ModelAttribute(name = "LoginOK") Users loginOK) throws IOException {
		System.out.println("getEmployeeID"+loginOK.getEmployeeID());
		List<BulletinBoard> BulletinBoards = BulletinBoardService.queryBulletinByOwnCreate(loginOK.getEmployeeID());

		JSONArray jay = new JSONArray();
		for(BulletinBoard BulletinBoard:BulletinBoards) {
			
			JSONObject job = new JSONObject();
			job.put("BulletinBoardID", BulletinBoard.getBulletinBoardID());
			job.put("Title", BulletinBoard.getTitle());
			job.put("Context",BulletinBoard.getContent());
			job.put("AttachedFiles", BulletinBoard.getAttachedFiles());
			job.put("AttachedFilesName", BulletinBoard.getAttachedFilesName());
			job.put("Authority", BulletinBoard.getAuthority());
			job.put("upTime", BulletinBoard.getUpTime());
			job.put("downTime", BulletinBoard.getDownTime());
			job.put("Date", BulletinBoard.getDate());
			job.put("Department",BulletinBoard.getUsers().getDepartment());
			jay.put(job);
		}

		return jay.toString();
	}
	
	@RequestMapping(path = "/queryBulletinForLook",method = RequestMethod.GET,produces = "html/text;charset=utf-8")
	public @ResponseBody String queryBulletinForLook(@ModelAttribute(name = "LoginOK") Users loginOK){
		List<BulletinBoard> BulletinBoards = BulletinBoardService.queryBulletinForLook(loginOK.getDepartment());
		
		JSONArray jay = new JSONArray();
		for(BulletinBoard BulletinBoard:BulletinBoards) {
			JSONObject job = new JSONObject();
			job.put("BulletinBoardID", BulletinBoard.getBulletinBoardID());
			job.put("Title", BulletinBoard.getTitle());
			job.put("Context",BulletinBoard.getContent());
//			job.put("AttachedFiles", BulletinBoard.getAttachedFiles());
			job.put("AttachedFilesName", BulletinBoard.getAttachedFilesName());
			job.put("Authority", BulletinBoard.getAuthority());
			job.put("upTime", BulletinBoard.getUpTime());
			job.put("downTime", BulletinBoard.getDownTime());
			job.put("Date", BulletinBoard.getDate());
			job.put("Department",BulletinBoard.getUsers().getDepartment());
			jay.put(job);
		
		}
		
		return jay.toString();
	}
	
	@RequestMapping(path = "/reflash",method = RequestMethod.GET,produces = "html/text;charset=utf-8")
	public @ResponseBody String reflash(@ModelAttribute(name = "LoginOK") Users loginOK) throws IOException {
		List<BulletinBoard> BulletinBoards = BulletinBoardService.queryBulletinForLook(loginOK.getDepartment());
		return Integer.toString(BulletinBoards.size());
	}

	@RequestMapping(path="/delete",method = RequestMethod.GET,produces = "html/text;charset=utf-8")
	public @ResponseBody String delete(@RequestParam(value="BulletinBoardid")int id) {
		BulletinBoardService.deleteBulletin(id);
		return "true";
	}
	
	@RequestMapping(path="/download",method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(HttpServletRequest request,@RequestParam(value = "BulletinBoardID") String BulletinBoardID,@RequestParam(value="fileName") String fileName) {
		byte[] fileCon = BulletinBoardService.queryFile(Integer.parseInt(BulletinBoardID));
		String path = request.getServletContext().getRealPath("/file/");

//		File file = new File(path + File.separator + fileName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		
		return new ResponseEntity<byte[]>(fileCon, headers, HttpStatus.OK);
		
	}
	




















}
