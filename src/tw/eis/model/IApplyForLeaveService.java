package tw.eis.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

public interface IApplyForLeaveService {
	public void addApply(ApplyForLeave applyForLeave);

	public void signOffApply(int applyId, ApplyForLeave applyForLeave);

	public void deleteApply(int applyId);

	public List<ApplyForLeave> queryAllApply();

	public List<ApplyForLeave> queryApplyByEID(int employeeId);

	public List<ApplyForLeave> queryUnsignedApplyBySID(int signatoriesId);
	
	public List<ApplyForLeave> querySignedApplyBySID(int signerId);

	public ApplyForLeave queryApplyByAID(int applyId);

	public String getStartHoursTag();

	public String getEndHoursTag();

	public BigDecimal countLeaveHours(String startD, String endD, String startH, String endH, String startM,
			String endM) throws ParseException;
}
