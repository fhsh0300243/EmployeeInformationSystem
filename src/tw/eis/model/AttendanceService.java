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

	public List<Attendance> InquiryToday(int EmpId) {
		return attendanceDao.InquiryToday(EmpId);
	}

	public List<Attendance> InquiryAttendance(int EmpId, String month) {
		return attendanceDao.InquiryAttendance(EmpId, month);
	}

	public void InsertStartTime(Employee Emp, java.sql.Date Date, java.sql.Time Time) {
		attendanceDao.InsertStartTime(Emp, Date, Time);
	}

	public void InsertEndTime(Employee Emp, java.sql.Date Date, java.sql.Time Time) {
		attendanceDao.InsertEndTime(Emp, Date, Time);
	}

	public void UpdateEndTime(Employee Emp, java.sql.Date Date, java.sql.Time Time) {
		attendanceDao.UpdateEndTime(Emp, Date, Time);
	}

	public void UpdateStartTime(Employee Emp, java.sql.Date Date, java.sql.Time Time) {
		attendanceDao.UpdateStartTime(Emp, Date, Time);
	}

	public void UpdateAttendanceStatus(java.sql.Date Date, int Id, String Status, String LeaveType) {
		attendanceDao.UpdateAttendanceStatus(Date, Id, Status, LeaveType);
	}

	public List<Attendance> InquiryAllToday(String todaystr) {
		return attendanceDao.InquiryAllToday(todaystr);
	}

	public void NewAttendance(Employee Emp, java.sql.Date Date) {
		attendanceDao.NewAttendance(Emp, Date);
	}

	public List<?> queryEmpAttendanceData(int empId, String Name, String Department, java.sql.Date StartDate,
			java.sql.Date EndDate) {
		return attendanceDao.queryEmpAttendanceData(empId, Name, Department, StartDate, EndDate);
	}

	public int CountError(Employee Emp, String month) {
		return attendanceDao.CountError(Emp, month);
	}

	public void DeleteTodayAttendance(java.sql.Date Date) {
		attendanceDao.DeleteTodayAttendance(Date);
	}

	public void UpdateOKAttemdance(java.sql.Date Date) {
		attendanceDao.UpdateOKAttemdance(Date);
	}

	public void UpdateStartNGAttemdance(java.sql.Date Date) {
		attendanceDao.UpdateStartNGAttemdance(Date);
	}

}
