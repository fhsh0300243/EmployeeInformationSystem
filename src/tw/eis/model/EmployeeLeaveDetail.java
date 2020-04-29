package tw.eis.model;

import java.math.BigDecimal;
import java.util.Date;

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
	@JoinColumn(name = "CREATORID", referencedColumnName = "EMPID")
	private Employee creatorId;

	@Column(name = "CREATETIME")
	private String createTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEEID", referencedColumnName = "EMPID")
	private Employee employeeId;

	@Column(name = "LEAVETYPE")
	private String leaveType;

	@Column(name = "MAXHOURS")
	private BigDecimal maxHours;

	@Column(name = "USEDHOURS")
	private BigDecimal usedHours;

	@Column(name = "APPLYHOURS")
	private BigDecimal applyHours;

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

	public Employee getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Employee creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
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

	public BigDecimal getApplyHours() {
		return applyHours;
	}

	public void setApplyHours(BigDecimal applyHours) {
		this.applyHours = applyHours;
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
