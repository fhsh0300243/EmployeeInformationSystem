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
			String invoiceTime,String invoiceNb,String editor,String remark,int appMoney,String signerTime,String signerStatus,int signerID) {
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

	public boolean EditFeeApp(int feeAppID, String signerStatus,String singerTime,int signerID) {
		return feeAppDAO.EditFeeApp(feeAppID, signerStatus,singerTime,signerID);
		
	}

	public List<feeAppMember> qfeeAppByID(int employeeID, String signerStatus) {
		return feeAppDAO.qfeeAppByID(employeeID,signerStatus);
	}

	public boolean ReturnEditFee(int feeAppID, String appTime, String invoiceTime, String invoiceNb, String editor, int appMoney,
			String remark, String signerStatus) {
		return feeAppDAO.ReturnEditFee(feeAppID, appTime,invoiceTime,invoiceNb,editor,appMoney,remark,signerStatus);
		
	}

	


}
