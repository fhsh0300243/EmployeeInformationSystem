package tw.eis.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departmentalannualgoals")
public class DepartmentalAnnualGoals {
private int deptID;
private String deptName;
private String CompanyAnnualGoals;
private String DepartmentAnnualGoal;
private String GoalSetter;
private Date Date;
private String Remarks;
private Set<PersonalQuarterlyTarget> personalQuarterlyTargets = new HashSet<PersonalQuarterlyTarget>(0);

public DepartmentalAnnualGoals() {
	
}

public DepartmentalAnnualGoals(int deptid,String deptname,String companyannualgoals,
		String departmentannualgoal,String goalsetter,Date date,String remarks) {
	this.deptID = deptid;
	this.deptName = deptname;
	this.CompanyAnnualGoals = companyannualgoals;
	this.DepartmentAnnualGoal = departmentannualgoal;
	this.GoalSetter = goalsetter;
	this.Date = date;
	this.Remarks = remarks;
}
@Id
@Column(name="DEPTID")
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
@Column(name = "COMPANYANNUALGOALS")
public String getCompanyAnnualGoals() {
	return CompanyAnnualGoals;
}

public void setCompanyAnnualGoals(String companyAnnualGoals) {
	CompanyAnnualGoals = companyAnnualGoals;
}
@Column(name = "DEPARTMENTANNUALGOAL")
public String getDepartmentAnnualGoal() {
	return DepartmentAnnualGoal;
}

public void setDepartmentAnnualGoal(String departmentAnnualGoal) {
	DepartmentAnnualGoal = departmentAnnualGoal;
}
@Column(name = "GOALSETTER")
public String getGoalSetter() {
	return GoalSetter;
}

public void setGoalSetter(String goalSetter) {
	GoalSetter = goalSetter;
}
@Column(name = "DATE")
public Date getDate() {
	return Date;
}

public void setDate(Date date) {
	Date = date;
}
@Column(name = "REMARKS")
public String getRemarks() {
	return Remarks;
}

public void setRemarks(String remarks) {
	Remarks = remarks;
}

@OneToMany(fetch = FetchType.LAZY,mappedBy = "departmentalAnnualGoals",cascade = CascadeType.ALL)
public Set<PersonalQuarterlyTarget> getPersonalQuarterlyTargets() {
	return personalQuarterlyTargets;
}

public void setPersonalQuarterlyTargets(Set<PersonalQuarterlyTarget> personalQuarterlyTargets) {
	this.personalQuarterlyTargets = personalQuarterlyTargets;
}



}
