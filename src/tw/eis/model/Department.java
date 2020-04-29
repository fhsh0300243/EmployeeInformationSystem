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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "department")
@Component
public class Department implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5956793577062195257L;
	private int deptID;
	private String deptName;
	private String deptAbb;
	private Set<Employee> employees=new LinkedHashSet<Employee>();
	private Set<Title> titles=new LinkedHashSet<Title>();
	//private Set<PersonalQuarterlyTarget> pqts=new LinkedHashSet<PersonalQuarterlyTarget>();
	//private Set<DepartmentalAnnualGoals> dags=new LinkedHashSet<DepartmentalAnnualGoals>();

	@Id
	@Column(name = "DEPTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	@Column(name = "DEPTNAME")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "DEPTABB")
	public String getDeptAbb() {
		return deptAbb;
	}

	public void setDeptAbb(String deptAbb) {
		this.deptAbb = deptAbb;
	}

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "department",cascade = CascadeType.ALL)
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "department",cascade = CascadeType.ALL)
	public Set<Title> getTitles() {
		return titles;
	}

	public void setTitles(Set<Title> titles) {
		this.titles = titles;
	}

//	@OneToMany(fetch = FetchType.LAZY,mappedBy = "department",cascade = CascadeType.ALL)
//	public Set<PersonalQuarterlyTarget> getPqts() {
//		return pqts;
//	}
//
//	public void setPqts(Set<PersonalQuarterlyTarget> pqts) {
//		this.pqts = pqts;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY,mappedBy = "department",cascade = CascadeType.ALL)
//	public Set<DepartmentalAnnualGoals> getDags() {
//		return dags;
//	}
//
//	public void setDags(Set<DepartmentalAnnualGoals> dags) {
//		this.dags = dags;
//	}
	
}
