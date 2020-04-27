package tw.eis.model;


import java.util.List;
import java.util.Map;

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
	
	public boolean addFeeApp(String department,Employee employeeID,String appItem,String appTime,
			String invoiceTime,String invoiceNb,String editor,String remark,int appMoney,String signerTime,String signerStatus,Employee signerID) {
		return feeAppDAO.addFeeApp(department,employeeID,appItem,appTime,invoiceTime,invoiceNb,editor,remark,appMoney,signerTime,signerStatus,signerID);
	}
	public List<feeAppMember> qFeeApp(Employee EmployeeID1,String searchA,String searchB) {
		return feeAppDAO.qFeeApp(EmployeeID1,searchA,searchB);
	}

	public List<feeAppMember> qfeeSingerApp(String department, String signerStatus, int level,Employee employeeIDB2) {
		return feeAppDAO.qfeeSingerApp(department,signerStatus,level,employeeIDB2);
	}


	public List<feeAppMember> qapplyId(int feeAppID) {
		return feeAppDAO.qfeeSingerApp(feeAppID);
	}

	public boolean EditFeeApp(int feeAppID, String signerStatus,String singerTime,Employee signerID) {
		return feeAppDAO.EditFeeApp(feeAppID, signerStatus,singerTime,signerID);
		
	}


	// add by 揚明--start
	public List<Map<String,String>> deptFeeApplyCostPerSeason(){
		return feeAppDAO.deptFeeApplyCostPerSeason();
	}
	// add by 揚明--end

	public List<feeAppMember> qfeeAppByID(Employee employeeIDB, String signerStatus) {
		return feeAppDAO.qfeeAppByID(employeeIDB,signerStatus);
	}

	public boolean ReturnEditFee(int feeAppID, String appTime, String invoiceTime, String invoiceNb, String editor, int appMoney,
			String remark, String signerStatus) {
		return feeAppDAO.ReturnEditFee(feeAppID, appTime,invoiceTime,invoiceNb,editor,appMoney,remark,signerStatus);
		
	}

	



}
