package tw.eis.model;

import java.math.BigDecimal;
import java.sql.Date;

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
@Table(name = "employeeleavedetail")
public class EmployeeLeaveDetail {
	@Id
	@Column(name = "ELDID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eldId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEEID", referencedColumnName = "EMPLOYEEID")
	private Users employeeId;

	@Column(name = "LEAVETYPE")
	private String leaveType;

	@Column(name = "MAXHOURS")
	private BigDecimal maxHours;

	@Column(name = "USEDHOURS")
	private BigDecimal usedHours;

	@Column(name = "SURPLUSHOURS")
	private BigDecimal surplusHours;

	@Column(name = "STARTDATE")
	private Date startDate;

	@Column(name = "ENDDATE")
	private Date endDate;

	@Column(name = "REMARKS")
	private String remarks;

	public int getEldId() {
		return eldId;
	}

	public void setEldId(int eldId) {
		this.eldId = eldId;
	}

	public Users getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Users employeeId) {
		this.employeeId = employeeId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public BigDecimal getMaxHours() {
		return maxHours;
	}

	public void setMaxHours(BigDecimal maxHours) {
		this.maxHours = maxHours;
	}

	public BigDecimal getUsedHours() {
		return usedHours;
	}

	public void setUsedHours(BigDecimal usedHours) {
		this.usedHours = usedHours;
	}

	public BigDecimal getSurplusHours() {
		return surplusHours;
	}

	public void setSurplusHours(BigDecimal surplusHours) {
		this.surplusHours = surplusHours;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
