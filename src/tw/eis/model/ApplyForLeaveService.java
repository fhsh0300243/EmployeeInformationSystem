package tw.eis.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyForLeaveService implements IApplyForLeaveService {

	private ApplyForLeaveDao aDAO;

	@Autowired
	public ApplyForLeaveService(ApplyForLeaveDao aDAO) {
		this.aDAO = aDAO;
	}

	@Override
	public void addApply(ApplyForLeave applyForLeave) {
		aDAO.addApply(applyForLeave);
	}

	@Override
	public void signOffApply(int applyId, ApplyForLeave applyForLeave) {
		aDAO.signOffApply(applyId, applyForLeave);
	}

	@Override
	public void deleteApply(int applyId) {
		aDAO.deleteApply(applyId);
	}

	@Override
	public List<ApplyForLeave> queryAllApply() {
		return aDAO.queryAllApply();
	}

	@Override
	public List<ApplyForLeave> queryApplyByEID(int employeeId) {
		return aDAO.queryApplyByEID(employeeId);
	}

	@Override
	public List<ApplyForLeave> queryUnsignedApplyBySID(int signatoriesId) {
		return aDAO.queryUnsignedApplyBySID(signatoriesId);
	}

	@Override
	public List<ApplyForLeave> querySignedApplyBySID(int signerId) {
		return aDAO.querySignedApplyBySID(signerId);
	}

	@Override
	public ApplyForLeave queryApplyByAID(int applyId) {
		return aDAO.queryApplyByAID(applyId);
	}

	@Override
	public String getStartHoursTag() {
		return aDAO.getStartHoursTag();
	}

	@Override
	public String getEndHoursTag() {
		return aDAO.getEndHoursTag();
	}

	@Override
	public BigDecimal countLeaveHours(String startD, String endD, String startH, String endH, String startM,
			String endM) throws ParseException {
		return aDAO.countLeaveHours(startD, endD, startH, endH, startM, endM);
	}
}
