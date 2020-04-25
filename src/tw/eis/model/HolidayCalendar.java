package tw.eis.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "holidaycalendar")
@Component
public class HolidayCalendar {
	
	private Date Date;
	private String DateType;
	private String Remark;
	private Employee employee;

	public HolidayCalendar() {
	}
	
	public HolidayCalendar(Employee employee,Date Date,String DateType,String Remark,int EmployeeID) {
		this.Date = Date;
		this.DateType = DateType;
		this.Remark = Remark;
		this.employee = employee;
	}

	@Id 
	@Column(name = "DATE")
	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		this.Date = date;
	}
	
	@Column(name = "DATETYPE")
	public String getDateType() {
		return DateType;
	}

	public void setDateType(String datetype) {
		this.DateType = datetype;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		this.Remark = remark;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EMPID")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
