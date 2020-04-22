package tw.eis.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "users")
@Component
public class Users implements Serializable{
	private int employeeID;
	private String userName;
	private String userPassword;
	private String title;
	private String department;
	private Employee employee;
	
//	布告欄映射 BY GK Start
	private Set<BulletinBoard> bulletinBoard = new HashSet<BulletinBoard>(0);
//	布告欄映射 BY GK End
	
	
	
	
	public Users() {

	}

	public Users(String userName, String userPassword, String title, String department) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.title = title;
		this.department = department;
	}

	@Id
	@Column(name = "EMPLOYEEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	@Column(name = "USERNAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USERPASSWORD")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "DEPARTMENT")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@OneToOne(fetch = FetchType.LAZY,mappedBy = "users",cascade = CascadeType.ALL)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
//	布告欄 getter&setter
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "Users")
	public Set<BulletinBoard> getBulletinBoard() {
		return bulletinBoard;
	}

	public void setBulletinBoard(Set<BulletinBoard> bulletinBoard) {
		this.bulletinBoard = bulletinBoard;
	}
	
//	End
	
}
