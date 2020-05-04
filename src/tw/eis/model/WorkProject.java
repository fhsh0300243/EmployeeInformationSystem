package tw.eis.model;



import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WorkProject")
public class WorkProject {
	private int wID;
	private int deptID;
	private String PersonalQuarterlyTarget;
	private String Work;
	private String WorkSetter;
	private Timestamp Date;
	private PersonalQuarterlyTarget personalQuarterlyTargets;
	
	public WorkProject() {
		
	}
	public WorkProject(int deptid,String pqt) {
		this.deptID=deptid;
		this.PersonalQuarterlyTarget=pqt;
	}
	public WorkProject(int deptid,String pqt,String work) {
		this.deptID = deptid;
		this.PersonalQuarterlyTarget = pqt;
		this.Work = work;
	};
	public WorkProject(int deptid,String pqt,String work,String wsetter,Timestamp date) {
		this.deptID=deptid;
		this.PersonalQuarterlyTarget =pqt;
		this.Work = work;
		this.WorkSetter=wsetter;
		this.Date=date;
	}
	@Id
	@Column(name="WID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getwID() {
		return wID;
	}

	public void setwID(int wID) {
		this.wID = wID;
	}
	@Column(name="deptID")
	public int getDeptID() {
		return deptID;
	}
	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	@Column(name="PERSONALQUARTERLYTARGET")
	public String getPersonalQuarterlyTarget() {
		return PersonalQuarterlyTarget;
	}

	public void setPersonalQuarterlyTarget(String personalQuarterlyTarget) {
		PersonalQuarterlyTarget = personalQuarterlyTarget;
	}
	@Column(name = "WORK")
	public String getWork() {
		return Work;
	}

	public void setWork(String work) {
		Work = work;
	}
	@Column(name="WORKSETTER")
	public String getWorkSetter() {
		return WorkSetter;
	}

	public void setWorkSetter(String workSetter) {
		WorkSetter = workSetter;
	}
	@Column(name ="DATE")
	public Timestamp getDate() {
		return Date;
	}

	public void setDate(Timestamp date) {
		Date = date;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	public PersonalQuarterlyTarget getPersonalQuarterlyTargets() {
		return personalQuarterlyTargets;
	}
	public void setPersonalQuarterlyTargets(PersonalQuarterlyTarget personalQuarterlyTargets) {
		this.personalQuarterlyTargets = personalQuarterlyTargets;
	}

	
}
