package tw.eis.model;

import java.util.List;

public interface IEmployeeLeaveDetailDao {

	public void addDetail(EmployeeLeaveDetail employeeLeaveDetail);

	public void updateDetail(int eldId, EmployeeLeaveDetail employeeLeaveDetail);

	public void deleteDetail(int eldId);

	public List<EmployeeLeaveDetail> queryAllDetail();

	public List<EmployeeLeaveDetail> queryValidLTByEID(int employeeId);

	public EmployeeLeaveDetail queryValidLTByEIDandLT(int employeeId, String leaveType);

	public String getLeaveTypeTag(int employeeId);
}
