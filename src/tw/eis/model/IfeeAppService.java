package tw.eis.model;


import java.util.List;

import tw.eis.model.feeAppMember;

public interface IfeeAppService {
	public boolean addFeeApp(String department,int employeeID,String appItem,String appTime,
			String invoiceTime,String invoiceNb,int editor,String remark,int appMoney,String signerTime,String signerStatus,int signerID);
	public List<feeAppMember> qFeeApp(int employeeID,String searchA,String searchB);
	public List<feeAppMember> qfeeSingerApp(String department, String signerStatus, int level);
	public List<feeAppMember> qapplyId(int feeAppID);
}
