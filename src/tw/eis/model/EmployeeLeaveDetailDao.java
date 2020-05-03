package tw.eis.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeLeaveDetailDao implements IEmployeeLeaveDetailDao {

	private SessionFactory sessionFactory;

	@Autowired
	public EmployeeLeaveDetailDao(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	@Override
	public void addDetail(EmployeeLeaveDetail employeeLeaveDetail) {
		getSession().save(employeeLeaveDetail);
	}

	@Override
	public void updateHours(int eldId, EmployeeLeaveDetail employeeLeaveDetail) {
		EmployeeLeaveDetail result = getSession().get(EmployeeLeaveDetail.class, eldId);
		result.setMaxHours(employeeLeaveDetail.getMaxHours());
		result.setUsedHours(employeeLeaveDetail.getUsedHours());
		result.setApplyHours(employeeLeaveDetail.getApplyHours());
		result.setSurplusHours(employeeLeaveDetail.getSurplusHours());
		getSession().update(result);
	}

	@Override
	public void deleteDetail(int eldId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EmployeeLeaveDetail> queryAllDetail() {
		Query<EmployeeLeaveDetail> query = getSession().createQuery("From EmployeeLeaveDetail",
				EmployeeLeaveDetail.class);
		List<EmployeeLeaveDetail> list = query.list();
		if (list.size() != 0) {
			return list;
		}
		return null;
	}

	@Override
	public List<EmployeeLeaveDetail> queryValidLTByEID(int employeeId) {
		Query<EmployeeLeaveDetail> query = getSession().createQuery(
				"From EmployeeLeaveDetail Where EmployeeID=?0 and EndDate>getdate()", EmployeeLeaveDetail.class);
		query.setParameter(0, employeeId);
		List<EmployeeLeaveDetail> list = query.list();
		if (list.size() != 0) {
			return list;
		}
		return null;
	}

//	@Override
//	public EmployeeLeaveDetail queryValidLTByEIDandLT(int employeeId, String leaveType) {
//		Query<EmployeeLeaveDetail> query = getSession().createQuery(
//				"From EmployeeLeaveDetail Where EmployeeID=?0 and EndDate>getdate() and LeaveType=?1",
//				EmployeeLeaveDetail.class);
//		query.setParameter(0, employeeId);
//		query.setParameter(1, leaveType);
//		List<EmployeeLeaveDetail> list = query.list();
//		EmployeeLeaveDetail result = list.get(0);
//		if (result != null) {
//			return result;
//		}
//		return null;
//	}

	@Override
	public EmployeeLeaveDetail queryValidLTByELDID(int ELDID) {
		Query<EmployeeLeaveDetail> query = getSession().createQuery("From EmployeeLeaveDetail Where ELDID=?0",
				EmployeeLeaveDetail.class);
		query.setParameter(0, ELDID);
		List<EmployeeLeaveDetail> list = query.list();
		EmployeeLeaveDetail result = list.get(0);
		if (result != null) {
			return result;
		}
		return null;
	}

	@Override
	public List<EmployeeLeaveDetail> queryLTByEIDLTYear(int employeeId, String leaveType, String year) {
		Query<EmployeeLeaveDetail> query = getSession().createQuery(
				"From EmployeeLeaveDetail Where EmployeeID=?0 and LeaveType=?1 and StartDate Between ?2 and ?3",
				EmployeeLeaveDetail.class);
		query.setParameter(0, employeeId);
		query.setParameter(1, leaveType);
		query.setParameter(2, year + "-1-1");
		query.setParameter(3, year + "-12-31");
		List<EmployeeLeaveDetail> list = query.list();
		if (list.size() != 0) {
			return list;
		}
		return null;
	}

	@Override
	public String getLeaveTypeTag(int employeeId) {
		String hqlStr = "From EmployeeLeaveDetail Where EmployeeID=?0 and EndDate>getdate()";
		Query<EmployeeLeaveDetail> query = getSession().createQuery(hqlStr, EmployeeLeaveDetail.class);
		query.setParameter(0, employeeId);
		List<EmployeeLeaveDetail> list = query.list();

		String ans = "";
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				int iELDID = list.get(i).getEldId();
				String iELD = list.get(i).getLeaveType();
				ans += "<option value='" + iELDID + "'>" + iELD + "</option>";
			}
		}
		return ans;
	}

}
