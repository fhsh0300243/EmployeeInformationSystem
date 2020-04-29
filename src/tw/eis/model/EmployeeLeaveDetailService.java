package tw.eis.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeLeaveDetailService implements IEmployeeLeaveDetailService {

	private EmployeeLeaveDetailDao eldDAO;

	@Autowired
	public EmployeeLeaveDetailService(EmployeeLeaveDetailDao eldDAO) {
		this.eldDAO = eldDAO;
	}

	@Override
	public void addDetail(EmployeeLeaveDetail employeeLeaveDetail) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateHours(int eldId, EmployeeLeaveDetail employeeLeaveDetail) {
		eldDAO.updateHours(eldId, employeeLeaveDetail);
	}

	@Override
	public void deleteDetail(int eldId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EmployeeLeaveDetail> queryAllDetail() {
		return eldDAO.queryAllDetail();
	}

	@Override
	public List<EmployeeLeaveDetail> queryValidLTByEID(int employeeId) {
		return eldDAO.queryValidLTByEID(employeeId);
	}

	@Override
	public EmployeeLeaveDetail queryValidLTByEIDandLT(int employeeId, String leaveType) {
		return eldDAO.queryValidLTByEIDandLT(employeeId, leaveType);
	}

	@Override
	public List<EmployeeLeaveDetail> queryLTByEIDLTYear(int employeeId, String leaveType, int year) {
		return eldDAO.queryLTByEIDLTYear(employeeId, leaveType, year);
	}

	@Override
	public String getLeaveTypeTag(int employeeId) {
		return eldDAO.getLeaveTypeTag(employeeId);
	}

}
