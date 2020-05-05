package tw.eis.model;

import java.util.List;
import java.util.Map;

public interface IfeeAppDAO {
	public boolean addFeeApp(String department,Employee employeeID,String appItem,String appTime,
			String invoiceTime,String invoiceNb,String editor,String remark,int appMoney,String signerTime,String signerStatus,Employee signerID);
	public List<feeAppMember> qFeeApp(Employee EmployeeID1,String searchA,String searchB);
	public List<feeAppMember> qfeeSingerApp(String department, String signerStatus, int level,Employee employeeIDB2);
public List<feeAppMember> qfeeSingerApp(int feeAppID);
	public boolean EditFeeApp(int feeAppID, String signerStatus,String singerTime,Employee signerID);
//	查詢主管是否有簽核 by GK	
	public int query(int ID);
//end

	// add by 揚明--start
	public List<Map<String, String>> deptFeeApplyCostPercent();
	public List<feeAppMember> deptFeeApplyCostDetail(String sORm,String dept);
	// add by 揚明--end


	public List<feeAppMember> qfeeAppByID(Employee employeeIDB, String signerStatus);
	public boolean ReturnEditFee(int feeAppID, String appTime, String invoiceTime, String invoiceNb, int appMoney,
			String remark, String signerStatus);
	public boolean DelectItem(int feeAppID);

}
