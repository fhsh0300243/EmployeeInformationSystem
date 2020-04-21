package tw.eis.model;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.eis.model.Attendance;
import tw.eis.model.AttendanceDAO;

@Service
public class AttendanceService {

	private AttendanceDAO attendanceDao;

	@Autowired
	public AttendanceService(AttendanceDAO attendanceDao) {
		this.attendanceDao = attendanceDao;
	}
	
	public List<Attendance> InquiryToday(Map<String, String> usersResultMap) {
		return attendanceDao.InquiryToday(usersResultMap);
	}
	
	public List<Attendance> InquiryAttendance(Map<String, String> usersResultMap, String month) {
		return attendanceDao.InquiryAttendance(usersResultMap, month);
	}
	
	public boolean InsertStartTime(Map<String, String> usersResultMap,java.sql.Date Date,java.sql.Time Time) {
		return attendanceDao.InsertStartTime(usersResultMap,Date,Time);
	}
	
	public boolean InsertEndTime(Map<String, String> usersResultMap,java.sql.Date Date,java.sql.Time Time) {
		return attendanceDao.InsertEndTime(usersResultMap,Date,Time);
	}
	
	public boolean UpdateEndTime(Map<String, String> usersResultMap,java.sql.Date Date,java.sql.Time Time) {
		return attendanceDao.UpdateEndTime(usersResultMap,Date,Time);
	}
	
	public boolean UpdateStatus(Map<String, String> usersResultMap,java.sql.Date Date,String Status) {
		return attendanceDao.UpdateStatus(usersResultMap,Date,Status);
	}

}
