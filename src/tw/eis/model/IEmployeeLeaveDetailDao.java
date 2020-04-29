package tw.eis.model;

import java.util.List;

public interface IEmployeeLeaveDetailDao {

	public void addDetail(EmployeeLeaveDetail employeeLeaveDetail);

	public void updateHours(int eldId, EmployeeLeaveDetail employeeLeaveDetail);

	public void deleteDetail(int eldId);

	public List<EmployeeLeaveDetail> queryAllDetail();

	public List<EmployeeLeaveDetail> queryValidLTByEID(int employeeId);

	public EmployeeLeaveDetail queryValidLTByEIDandLT(int employeeId, String leaveType);

	public List<EmployeeLeaveDetail> queryLTByEIDLTYear(int employeeId, String leaveType, int year);

	public String getLeaveTypeTag(int employeeId);
}
