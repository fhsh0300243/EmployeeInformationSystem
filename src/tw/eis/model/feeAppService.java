package tw.eis.model;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.eis.model.feeAppDAO;
import tw.eis.model.feeAppMember;

@Service("feeAppService")
public class feeAppService implements IfeeAppService{

	private feeAppDAO feeAppDAO;

	@Autowired
	public feeAppService(feeAppDAO feeAppDAO) {
		this.feeAppDAO=feeAppDAO;
	}
	
	public boolean addFeeApp(String department,int employeeID,String appItem,String appTime,
			String invoiceTime,String invoiceNb,int editor,String remark,int appMoney,String signerTime,String signerStatus,int signerID) {
		return feeAppDAO.addFeeApp(department,employeeID,appItem,appTime,invoiceTime,invoiceNb,editor,remark,appMoney,signerTime,signerStatus,signerID);
	}
	public List<feeAppMember> qFeeApp(int employeeID,String searchA,String searchB) {
		return feeAppDAO.qFeeApp(employeeID,searchA,searchB);
	}

	public List<feeAppMember> qfeeSingerApp(String department, String signerStatus, int level) {
		return feeAppDAO.qfeeSingerApp(department,signerStatus,level);
	}

	public List<feeAppMember> qapplyId(int feeAppID) {
		return feeAppDAO.qfeeSingerApp(feeAppID);
	}

	


}
