package tw.eis.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "feeApply")
@Component
public class feeAppMember {
	private int feeAppID;
	private String department;
	private Employee employeeID;
	private String appItem;
	private String appTime;
	private String invoiceTime;
	private String invoiceNb;
	private String editor;
	private String remark;
	private int appMoney;
	private String signerTime;
	private String signerStatus;
	private Employee signerID;
	
	public feeAppMember() {
	}
	
	public feeAppMember(String department,Employee employeeID,String appItem,String appTime,
			String invoiceTime,String invoiceNb,String editor,String remark,int appMoney,String signerTime,String signerStatus,Employee signerID) {
		this.department=department;
		this.employeeID=employeeID;
		this.appItem=appItem;
		this.appTime=appTime;
		this.invoiceTime=invoiceTime;
		this.invoiceNb=invoiceNb;
		this.editor=editor;
		this.remark=remark;
		this.appMoney=appMoney;
		this.signerTime=signerTime;
		this.signerStatus=signerStatus;
		this.signerID=signerID;
	
	}
	
	@Id
	@Column(name = "FEEAPPID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getFeeAppID() {
		return feeAppID;
	}
	public void setFeeAppID(int feeAppID) {
		this.feeAppID = feeAppID;
	}
	@Column(name = "DEPARTMENT")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEEID", referencedColumnName = "EMPID")
	//@Column(name = "EMPLOYEEID")
	public Employee getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Employee employeeID) {
		this.employeeID = employeeID;
	}
	@Column(name = "APPITEM")
	public String getAppItem() {
		return appItem;
	}
	public void setAppItem(String appItem) {
		this.appItem = appItem;
	}
	@Column(name = "APPTIME")
	public String getAppTime() {
		return appTime;
	}
	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}
	@Column(name = "INVOICETIME")
	public String getInvoiceTime() {
		return invoiceTime;
	}
	public void setInvoiceTime(String invoiceTime) {
		this.invoiceTime = invoiceTime;
	}
	@Column(name = "INVOICENB")
	public String getInvoiceNb() {
		return invoiceNb;
	}
	public void setInvoiceNb(String invoiceNb) {
		this.invoiceNb = invoiceNb;
	}
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "EDITOR")
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	@Column(name = "APPMONEY")
	public int getAppMoney() {
		return appMoney;
	}
	public void setAppMoney(int appMoney) {
		this.appMoney = appMoney;
	}

	@Column(name = "SIGNERTIME")
	public String getSignerTime() {
		return signerTime;
	}


	public void setSignerTime(String signerTime) {
		this.signerTime = signerTime;
	}

	@Column(name = "SIGNERSTATUS")
	public String getSignerStatus() {
		return signerStatus;
	}


	public void setSignerStatus(String signerStatus) {
		this.signerStatus = signerStatus;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SIGNERID", referencedColumnName = "EMPID")
	//@Column(name = "SIGNERID")
	public Employee getSignerID() {
		return signerID;
	}


	public void setSignerID(Employee signerID) {
		this.signerID = signerID;
	}
	
}
