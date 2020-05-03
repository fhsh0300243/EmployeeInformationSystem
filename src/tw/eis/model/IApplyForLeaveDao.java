package tw.eis.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IApplyForLeaveDao {
	public void addApply(ApplyForLeave applyForLeave);

	public void signOffApply(int applyId, ApplyForLeave applyForLeave);

	public void deleteApply(int applyId);

	public List<ApplyForLeave> queryAllApply();

	public List<ApplyForLeave> queryApplyByEID(int employeeId);

	public List<ApplyForLeave> queryUnsignedApplyBySID(int signerId);

	public List<ApplyForLeave> querySignedApplyBySID(int signerId);

	public ApplyForLeave queryApplyByAID(int applyId);

	public List<ApplyForLeave> checkApplyTime(Date startTime, Date endTime, int employeeId);

	public String getStartHoursTag();

	public String getEndHoursTag();

	public BigDecimal countHoursSTtoET(String startD, String endD, String startH, String endH, String startM,
			String endM) throws ParseException;

//	add By GK
	public int queryNewApply(int ID);

	public int querysucessApplyForLeave(int ID, String oldDate, String newDate);
//	END
}
