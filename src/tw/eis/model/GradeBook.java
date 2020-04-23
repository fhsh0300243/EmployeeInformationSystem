package tw.eis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "GRADEBOOK")
@Component
public class GradeBook implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int CourseId;
	private int EmployeeId;
	private String EmployeeName;
	private int Grade;

	public GradeBook() {

	}

	@Id
	@Column(name="COURSEID")
	public int getCourseId() {
		return CourseId;
	}

	public void setCourseId(int courseId) {
		CourseId = courseId;
	}

	@Id
	@Column(name="EMPLOYEEID")
	public int getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}

	@Column(name="EMPLOYEENAME")
	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	@Column(name="GRADE")
	public int getGrade() {
		return Grade;
	}

	public void setGrade(int grade) {
		Grade = grade;
	}


}
