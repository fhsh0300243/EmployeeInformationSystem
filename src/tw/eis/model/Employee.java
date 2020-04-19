package tw.eis.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "employee")
@Component
public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8955501788882285090L;
	private int empID;
	private String name;
	private String gender;
	private Date birthDay;
	private String address;
	private String title;
	private int level;
	private String department;
	private Employee manager;
	private String extensionNum;
	private String phoneNum;
	private String email;
	private byte[] photo;
	private int salary;
	private Date hireDay;
	private Date lastWorkDay;
	private Users users;
	private Set<Employee> subordinates = new HashSet<Employee>();
	private Department empDept;
	private Title empTitle;

	public Employee() {

	}

	public Employee(String name, String gender, Date birthDay, String address, String title, int level,
			String department, Employee manager, String extensionNum, String phoneNum, String email, byte[] photo,
			int salary, Date hireDay, Date lastWorkDay, Department empDept, Title empTitle) {
		this.name = name;
		this.gender = gender;
		this.birthDay = birthDay;
		this.address = address;
		this.title = title;
		this.level = level;
		this.department = department;
		this.manager = manager;
		this.extensionNum = extensionNum;
		this.phoneNum = phoneNum;
		this.email = email;
		this.photo = photo;
		this.salary = salary;
		this.hireDay = hireDay;
		this.lastWorkDay = lastWorkDay;
		this.empDept = empDept;
		this.empTitle = empTitle;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "users"))
	@Id
	@Column(name = "EMPID")
	@GeneratedValue(generator = "generator")
	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "GENDER")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "BIRTHDAY")
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "LEVEL")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Column(name = "DEPARTMENT")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "MANAGER")
	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	@Column(name = "EXTENSIONNUM")
	public String getExtensionNum() {
		return extensionNum;
	}

	public void setExtensionNum(String extensionNum) {
		this.extensionNum = extensionNum;
	}

	@Column(name = "PHONENUM")
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PHOTO")
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Column(name = "SALARY")
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Column(name = "HIREDAY")
	public Date getHireDay() {
		return hireDay;
	}

	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}

	@Column(name = "LASTWORKDAY")
	public Date getLastWorkDay() {
		return lastWorkDay;
	}

	public void setLastWorkDay(Date lastWorkDay) {
		this.lastWorkDay = lastWorkDay;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@OneToMany(mappedBy = "manager")
	public Set<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPTID")
	public Department getEmpDept() {
		return empDept;
	}

	public void setEmpDept(Department empDept) {
		this.empDept = empDept;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TITLEID")
	public Title getEmpTitle() {
		return empTitle;
	}

	public void setEmpTitle(Title empTitle) {
		this.empTitle = empTitle;
	}

}
