package tw.eis.model;

import java.math.BigDecimal;

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
@Table(name = "applyforleave")
public class ApplyForLeave {
	@Id
	@Column(name = "APPLYID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int applyId;

	@Column(name = "CREATETIME")
	private String createTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEEID", referencedColumnName = "EMPID")
//	@Column(name = "EMPLOYEEID")
	private Employee employeeId;

	@Column(name = "LEAVETYPE")
	private String leaveType;

	@Column(name = "STARTTIME")
	private String startTime;

	@Column(name = "ENDTIME")
	private String endTime;

	@Column(name = "SUMHOURS")
	private BigDecimal sumHours;

	@Column(name = "CAUSE")
	private String cause;

	@Column(name = "ATTACHMENT")
	private byte[] attachment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SIGNERID", referencedColumnName = "EMPID")
//	@Column(name = "SIGNERID")
	private Employee signerId;

	@Column(name = "SIGNINGPROGRESS")
	private String signingProgress;

	@Column(name = "CONFIRMTIME")
	private String confirmTime;

	@Column(name = "COMMENT")
	private String comment;

	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getSumHours() {
		return sumHours;
	}

	public void setSumHours(BigDecimal sumHours) {
		this.sumHours = sumHours;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public Employee getSignerId() {
		return signerId;
	}

	public void setSignerId(Employee signerId) {
		this.signerId = signerId;
	}

	public String getSigningProgress() {
		return signingProgress;
	}

	public void setSigningProgress(String signingProgress) {
		this.signingProgress = signingProgress;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
