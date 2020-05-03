package tw.eis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AssignWork")
public class AssignWork {
	private int awID;
	private int empID;
	private int wID;
	private String Work;
	private int WorkStatus;
	
	public AssignWork() {
		
	}
	public AssignWork(int empid,int wid,String work) {
		this.empID = empid;
		this.wID = wid;
		this.Work = work;
	}
	
	@Id
	@Column(name="AWID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getAwID() {
		return awID;
	}
	public void setAwID(int asID) {
		this.awID = asID;
	}
	@Column(name = "EMPID")
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	@Column(name = "WID")
	public int getwID() {
		return wID;
	}
	public void setwID(int wID) {
		this.wID = wID;
	}
	@Column(name = "WORK")
	public String getWork() {
		return Work;
	}
	public void setWork(String work) {
		Work = work;
	}
	@Column(name = "WORKSTATUS")
	public int getWorkStatus() {
		return WorkStatus;
	}
	public void setWorkStatus(int workStatus) {
		WorkStatus = workStatus;
	}
	
	
}
