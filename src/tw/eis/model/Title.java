package tw.eis.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "title")
@Component
public class Title implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7154528162726067325L;
	private int titleID;
	private String titleName;
	private String titleChName;
	private int level;
	private Set<Employee> employees=new LinkedHashSet<Employee>();
	private Department department;
	
	@Id
	@Column(name = "TITLEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getTitleID() {
		return titleID;
	}
	public void setTitleID(int titleID) {
		this.titleID = titleID;
		///
	}
	
	@Column(name = "TITLENAME")
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	@Column(name = "TITLECHNAME")
	public String getTitleChName() {
		return titleChName;
	}
	public void setTitleChName(String titleChName) {
		this.titleChName = titleChName;
	}
	
	@Column(name = "LEVEL")
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "title",cascade = CascadeType.ALL)
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPTID")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}	
}
