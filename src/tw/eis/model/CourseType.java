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
	private String deptTest;
	private String deptSales;
	private String deptPM;

	public CourseType() {

	}

	public CourseType(String TypeName, String TypeGroup, String deptHR, String deptRD, String deptTest,
			String deptSales, String deptPM) {
		this.TypeName = TypeName;
		this.TypeGroup = TypeGroup;
		this.deptHR = deptHR;
		this.deptRD = deptRD;
		this.deptTest = deptTest;
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

	@Column(name = "DEPTTEST")
	public String getDeptTest() {
		return deptTest;
	}

	public void setDeptTest(String deptTest) {
		this.deptTest = deptTest;
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
