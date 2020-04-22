package tw.eis.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
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

@Entity
@Table(name = "personalquarterlytarget")
public class PersonalQuarterlyTarget {
	private int pid;
	private String deptName;
	private String DepartmentAnnualGoal;
	private String PersonalQuarterlyTarget;
	private String GoalSetters;
	private Timestamp Date;
	private String Remarks;
	private DepartmentalAnnualGoals departmentalAnnualGoals;
	private Set<WorkProject> workProjects = new HashSet<WorkProject>(0);
	public PersonalQuarterlyTarget() {

	}

	public PersonalQuarterlyTarget(String deptname, String departmentannualgoal,
			String personalquarterlytarget, String goalsetters, Timestamp date) {
		this.deptName = deptname;
		this.DepartmentAnnualGoal = departmentannualgoal;
		this.PersonalQuarterlyTarget = personalquarterlytarget;
		this.GoalSetters = goalsetters;
		this.Date = date;

	}

	@Id
	@Column(name = "PID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@Column(name = "DEPTNAME")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "DEPARTMENTANNUALGOAL")
	public String getDepartmentAnnualGoal() {
		return DepartmentAnnualGoal;
	}

	public void setDepartmentAnnualGoal(String departmentAnnualGoal) {
		DepartmentAnnualGoal = departmentAnnualGoal;
	}

	@Column(name = "PERSONALQUARTERLYTARGET")
	public String getPersonalQuarterlyTarget() {
		return PersonalQuarterlyTarget;
	}

	public void setPersonalQuarterlyTarget(String personalQuarterlyTarget) {
		PersonalQuarterlyTarget = personalQuarterlyTarget;
	}

	@Column(name = "GOALSETTERS")
	public String getGoalSetters() {
		return GoalSetters;
	}

	public void setGoalSetters(String goalSetters) {
		GoalSetters = goalSetters;
	}

	@Column(name = "DATE")
	public Date getDate() {
		return Date;
	}

	public void setDate(Timestamp date) {
		Date = date;
	}

	@Column(name = "REMARKS")
	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPTID")
	public DepartmentalAnnualGoals getDepartmentalAnnualGoals() {
		return departmentalAnnualGoals;
	}

	public void setDepartmentalAnnualGoals(DepartmentalAnnualGoals departmentalAnnualGoals) {
		this.departmentalAnnualGoals = departmentalAnnualGoals;
	}
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "personalQuarterlyTargets",cascade = CascadeType.ALL)
	public Set<WorkProject> getWorkProjects() {
		return workProjects;
	}

	public void setWorkProjects(Set<WorkProject> workProjects) {
		this.workProjects = workProjects;
	}

	
}
