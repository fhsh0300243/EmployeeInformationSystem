package tw.eis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "COURSETYPE")
@Component
public class CourseType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int CourseTypeId;
	private String TypeName;
	private String TypeGroup;
	private String deptHR;
	private String deptRD;
	private String deptQA;
	private String deptTEST;
	private String deptSales;
	private String deptPM;

	public CourseType() {

	}

	public CourseType(String TypeName, String TypeGroup, String deptHR, String deptRD, String deptQA, String deptTEST,
			String deptSales, String deptPM) {
		this.TypeName = TypeName;
		this.TypeGroup = TypeGroup;
		this.deptHR = deptHR;
		this.deptRD = deptRD;
		this.deptQA = deptQA;
		this.deptTEST = deptTEST;
		this.deptSales = deptSales;
		this.deptPM = deptPM;
	}

	@Id
	@Column(name = "COURSETYPE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getCourseTypeId() {
		return CourseTypeId;
	}

	public void setCourseTypeId(int courseTypeId) {
		CourseTypeId = courseTypeId;
	}

	@Column(name = "TYPENAME")
	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}

	@Column(name = "TYPEGROUP")
	public String getTypeGroup() {
		return TypeGroup;
	}

	public void setTypeGroup(String typeGroup) {
		TypeGroup = typeGroup;
	}

	@Column(name = "DEPTHR")
	public String getDeptHR() {
		return deptHR;
	}

	public void setDeptHR(String deptHR) {
		this.deptHR = deptHR;
	}

	@Column(name = "DEPTRD")
	public String getDeptRD() {
		return deptRD;
	}

	public void setDeptRD(String deptRD) {
		this.deptRD = deptRD;
	}
	
	
	@Column(name = "DEPTQA")
	public String getDeptQA() {
		return deptQA;
	}

	public void setDeptQA(String deptQA) {
		this.deptRD = deptQA;
	}
	

	@Column(name = "DEPTTEST")
	public String getDeptTEST() {
		return deptTEST;
	}

	public void setDeptTEST(String deptTEST) {
		this.deptTEST = deptTEST;
	}

	@Column(name = "DEPTSALES")
	public String getDeptSales() {
		return deptSales;
	}

	public void setDeptSales(String deptSales) {
		this.deptSales = deptSales;
	}

	@Column(name = "DEPTPM")
	public String getDeptPM() {
		return deptPM;
	}

	public void setDeptPM(String deptPM) {
		this.deptPM = deptPM;
	}

}
