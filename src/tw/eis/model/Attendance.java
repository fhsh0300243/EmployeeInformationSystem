package tw.eis.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "attendance")
@Component
public class Attendance implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int attendanceID;
	private Employee employee;
	private Date date;
	private Time startTime;
	private Time endTime;
	private String status;
	private String leaveType;


	public Attendance() {
	}
	
	public Attendance(Employee employee,Date date,Time startTime,Time endTime,String status,String leaveType) {
		this.employee = employee;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.leaveType = leaveType;
	}

	@Id
	@Column(name = "ATTENDANCEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getAttendanceID() {
		return attendanceID;	       
	}

	public void setAttendanceID(int attendanceID) {
		this.attendanceID = attendanceID;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EMPID")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@Column(name = "DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "STARTTIME")
	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	@Column(name = "ENDTIME")
	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "LEAVETYPE")
	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
}
