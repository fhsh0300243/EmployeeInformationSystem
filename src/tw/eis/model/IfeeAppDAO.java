package tw.eis.model;

import java.util.List;
import java.util.Map;

public interface IfeeAppDAO {

	public boolean addFeeApp(String department, int employeeID, String appItem, String appTime, String invoiceTime,
			String invoiceNb, int editor, String remark, int appMoney, String signerTime, String signerStatus,
			int signerID);

	public List<feeAppMember> qFeeApp(int employeeID, String searchA, String searchB);

	public List<feeAppMember> qfeeSingerApp(String department, String signerStatus, int level);

	public List<feeAppMember> qfeeSingerApp(int feeAppID);

	public boolean EditFeeApp(int feeAppID, String signerStatus, String singerTime, int signerID);


	// add by 揚明--start
	public List<Map<String, String>> deptFeeApplyCostPercent();
	public List<feeAppMember> deptFeeApplyCostDetail(String sORm,String dept);
	// add by 揚明--end


	public List<feeAppMember> qfeeAppByID(int employeeID, String signerStatus);
	public boolean ReturnEditFee(int feeAppID, String appTime, String invoiceTime, String invoiceNb, String editor, int appMoney,
			String remark, String signerStatus);

}
