package tw.eis.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import tw.eis.util.GlobalService;

@Repository("feeAppDAO")
public class feeAppDAO implements IfeeAppDAO {

	private SessionFactory sessionFacotry;

	@Autowired
	public feeAppDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	// 新增申請差旅費進資料庫
	public boolean addFeeApp(String department, Employee employeeID, String appItem, String appTime, String invoiceTime,
			String invoiceNb, String editor, String remark, int appMoney, String signerTime, String signerStatus,
			Employee signerID) {
		Session session = sessionFacotry.getCurrentSession();
		feeAppMember feeapp = new feeAppMember(department, employeeID, appItem, appTime, invoiceTime, invoiceNb, editor,
				remark, appMoney, signerTime, signerStatus, signerID);

		session.save(feeapp);
		return true;

	}

	// 查詢差旅費-員工編號、時間區間
	public List<feeAppMember> qFeeApp(Employee EmployeeID1, String searchA, String searchB) {

		Session session = sessionFacotry.getCurrentSession();
		// String qq="from feeAppMember where employeeID=?"+employeeID+" and invoiceTime
		// BETWEEN "+searchA+" AND "+searchB+" ORDER BY invoiceTime DESC";
		// System.out.println("test print:"+qq);
		// Query query = session.createQuery("from feeAppMember where employeeID=?0 and
		// invoiceTime BETWEEN invoiceTime=?1 AND invoiceTime=?2 ORDER BY invoiceTime
		// DESC",feeAppMember.class);
		// Query query = session.createQuery("from feeAppMember where employeeID=?0 and
		// invoiceTime=?1 ORDER BY invoiceTime DESC",feeAppMember.class);
		// query.setParameter(0, employeeID);
		// query.setParameter(1, searchA);
		// query.setParameter(2, searchB);

		// List<feeAppMember> lists = query.list();
//		Iterator iterator = lists.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());

		DetachedCriteria mainQuery = DetachedCriteria.forClass(feeAppMember.class);
		mainQuery.add(Restrictions.between("appTime", searchA, searchB));
		mainQuery.add(Restrictions.eq("employeeID", EmployeeID1));
		mainQuery.add(Restrictions.eq("employeeID", EmployeeID1));

		List<feeAppMember> list = mainQuery.getExecutableCriteria(sessionFacotry.getCurrentSession()).list();
//
//		}
		return list;
	}

	// 查詢差旅費-主管簽核頁面
	public List<feeAppMember> qfeeSingerApp(String department, String signerStatus, int level, Employee employeeIDB2) {
		DetachedCriteria mainQuery = DetachedCriteria.forClass(feeAppMember.class);
		mainQuery.add(Restrictions.eq("department", department));
		mainQuery.add(Restrictions.eq("signerStatus", signerStatus));
		mainQuery.add(Restrictions.eq("signerID", employeeIDB2));

		List<feeAppMember> list2 = mainQuery.getExecutableCriteria(sessionFacotry.getCurrentSession()).list();
		return list2;
	}

	public List<feeAppMember> qfeeSingerApp(int feeAppID) {
		Session session = sessionFacotry.getCurrentSession();

		Query query = session.createQuery("from feeAppMember where feeAppID=?0", feeAppMember.class);
		query.setParameter(0, feeAppID);
		List<feeAppMember> listByID = query.list();
		return listByID;
	}

	public boolean EditFeeApp(int feeAppID, String signerStatus, String singerTime, Employee signerID) {
		Session session = sessionFacotry.getCurrentSession();
		feeAppMember feeAppMember = session.get(feeAppMember.class, feeAppID);
		feeAppMember.setSignerTime(singerTime);
		feeAppMember.setSignerStatus(signerStatus);
		feeAppMember.setSignerID(signerID);
		session.update(feeAppMember);

		return true;

	}

	public List<feeAppMember> qfeeAppByID(Employee employeeIDB, String signerStatus) {
		Session session = sessionFacotry.getCurrentSession();

		Query qReturnByID = session.createQuery("from feeAppMember where employeeID=?0 and signerStatus=?1",
				feeAppMember.class);
		qReturnByID.setParameter(0, employeeIDB);
		qReturnByID.setParameter(1, signerStatus);
		List<feeAppMember> qlistByID = qReturnByID.list();
		return qlistByID;
	}

	public boolean ReturnEditFee(int feeAppID, String appTime, String invoiceTime, String invoiceNb, String editor,
			int appMoney, String remark, String signerStatus) {
		Session session = sessionFacotry.getCurrentSession();
		feeAppMember feeAppMember = session.get(feeAppMember.class, feeAppID);
		feeAppMember.setAppTime(appTime);
		feeAppMember.setInvoiceTime(invoiceTime);
		feeAppMember.setInvoiceNb(invoiceNb);
		feeAppMember.setEditor(editor);
		feeAppMember.setAppMoney(appMoney);
		feeAppMember.setRemark(remark);
		;
		feeAppMember.setSignerStatus(signerStatus);
		session.update(feeAppMember);
		return true;
	}
	
//	主管登入查看有無新申請請需要簽核 by GK
	public int query(int ID) {
		Session session = sessionFacotry.getCurrentSession();
		System.out.println("id:"+ID);
		Query Query = session.createQuery("from feeAppMember where signerID=:signerID and signerStatus='簽核中'");
		Query.setInteger("signerID", ID);
		return Query.list().size();
	}
	
//	員工查詢是否主管以簽核或退件
	public int querysucess(int ID,String oldDate,String newDate) {
		Session session = sessionFacotry.getCurrentSession();
		System.out.println("id:"+ID);
		Query Query = session.createQuery("from feeAppMember where employeeID=:employeeID and signerStatus<>'簽核中' and (signerTime>:oldDate and signerTime<:newDate)");
		Query.setInteger("employeeID", ID);
		Query.setString("oldDate", oldDate);
		Query.setString("newDate", newDate);
		return Query.list().size();
	}
//  End	
	
	
	
	
	
	
	
	// add by 揚明--start
	public List<Map<String, String>> deptFeeApplyCostPercent() {
		Map<String, String> deptCostPercent = new HashMap<String, String>();
		List<Map<String, String>> deptDataList = new ArrayList<Map<String, String>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(GlobalService.dateOfToday());
		DecimalFormat nf = (DecimalFormat) NumberFormat.getPercentInstance();
		nf.applyPattern("0%"); // 00表示小數點2位
		nf.setMaximumFractionDigits(2); // 2表示精確到小數點後2位
		int year = Integer.parseInt(today.substring(0, 4));
		int month = Integer.parseInt(today.substring(5, 7));
		String totalhql = "select sum(appMoney) from feeAppMember where appTime BETWEEN :start And :end ";
		String depthql = "select sum(appMoney) from feeAppMember where department=:dept and appTime BETWEEN :start And :end ";
		double thisSeasonTotalCost = 1;
		double thisSeasonHRCost = 0;
		double thisSeasonRDCost = 0;
		double thisSeasonQACost = 0;
		double thisSeasonSalesCost = 0;
		double thisSeasonPMCost = 0;
		double thisMonthTotalCost = 1;
		double thisMonthHRCost = 0;
		double thisMonthRDCost = 0;
		double thisMonthQACost = 0;
		double thisMonthSalesCost = 0;
		double thisMonthPMCost = 0;
		if (month == 1 || month == 2 || month == 3) {
			Query<Long> total = sessionFacotry.getCurrentSession().createQuery(totalhql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-01-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-04-01 00:00:00.000");
			try {
				thisSeasonTotalCost = Double.parseDouble(total.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonTotalCost = 1;
			}
			Query<Long> HR = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-01-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-04-01 00:00:00.000").setParameter("dept", "HR");
			try {
				thisSeasonHRCost = Double.parseDouble(HR.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonHRCost = 0;
			}
			Query<Long> RD = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-01-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-04-01 00:00:00.000").setParameter("dept", "RD");
			try {
				thisSeasonRDCost = Double.parseDouble(RD.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonRDCost = 0;
			}
			Query<Long> QA = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-01-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-04-01 00:00:00.000").setParameter("dept", "QA");
			try {
				thisSeasonQACost = Double.parseDouble(QA.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonQACost = 0;
			}
			Query<Long> Sales = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-01-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-04-01 00:00:00.000").setParameter("dept", "Sales");
			try {
				thisSeasonSalesCost = Double.parseDouble(Sales.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonSalesCost = 0;
			}

			Query<Long> PM = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-01-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-04-01 00:00:00.000").setParameter("dept", "PM");
			try {
				thisSeasonPMCost = Double.parseDouble(PM.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonPMCost = 0;
			}
			deptCostPercent.put("sHR", nf.format(thisSeasonHRCost / thisSeasonTotalCost));
			deptCostPercent.put("sRD", nf.format(thisSeasonRDCost / thisSeasonTotalCost));
			deptCostPercent.put("sQA", nf.format(thisSeasonQACost / thisSeasonTotalCost));
			deptCostPercent.put("sSales", nf.format(thisSeasonSalesCost / thisSeasonTotalCost));
			deptCostPercent.put("sPM", nf.format(thisSeasonPMCost / thisSeasonTotalCost));
			deptDataList.add(deptCostPercent);
			// return deptDataList;
		}
		if (month == 4 || month == 5 || month == 6) {
			Query<Long> total = sessionFacotry.getCurrentSession().createQuery(totalhql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-04-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-07-01 00:00:00.000");
			try {
				thisSeasonTotalCost = Double.parseDouble(total.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonTotalCost = 1;
			}
			Query<Long> HR = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-04-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-07-01 00:00:00.000").setParameter("dept", "HR");
			try {
				thisSeasonHRCost = Double.parseDouble(HR.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonHRCost = 0;
			}
			Query<Long> RD = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-04-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-07-01 00:00:00.000").setParameter("dept", "RD");
			try {
				thisSeasonRDCost = Double.parseDouble(RD.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonRDCost = 0;
			}
			Query<Long> QA = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-04-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-07-01 00:00:00.000").setParameter("dept", "QA");
			try {
				thisSeasonQACost = Double.parseDouble(QA.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonQACost = 0;
			}

			Query<Long> Sales = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-04-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-07-01 00:00:00.000").setParameter("dept", "Sales");
			try {
				thisSeasonSalesCost = Double.parseDouble(Sales.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonSalesCost = 0;
			}

			Query<Long> PM = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-04-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-07-01 00:00:00.000").setParameter("dept", "PM");
			try {
				thisSeasonPMCost = Double.parseDouble(PM.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonPMCost = 0;
			}
			deptCostPercent.put("sHR", nf.format(thisSeasonHRCost / thisSeasonTotalCost));
			deptCostPercent.put("sRD", nf.format(thisSeasonRDCost / thisSeasonTotalCost));
			deptCostPercent.put("sQA", nf.format(thisSeasonQACost / thisSeasonTotalCost));
			deptCostPercent.put("sSales", nf.format(thisSeasonSalesCost / thisSeasonTotalCost));
			deptCostPercent.put("sPM", nf.format(thisSeasonPMCost / thisSeasonTotalCost));
			deptDataList.add(deptCostPercent);
			// return deptDataList;
		}
		if (month == 7 || month == 8 || month == 9) {
			Query<Long> total = sessionFacotry.getCurrentSession().createQuery(totalhql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-07-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-10-01 00:00:00.000");
			try {
				thisSeasonTotalCost = Double.parseDouble(total.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonTotalCost = 1;
			}
			Query<Long> HR = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-07-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-10-01 00:00:00.000").setParameter("dept", "HR");
			try {
				thisSeasonHRCost = Double.parseDouble(HR.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonHRCost = 0;
			}
			Query<Long> RD = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-07-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-10-01 00:00:00.000").setParameter("dept", "RD");
			try {
				thisSeasonRDCost = Double.parseDouble(RD.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonRDCost = 0;
			}

			Query<Long> QA = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-07-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-10-01 00:00:00.000").setParameter("dept", "QA");
			try {
				thisSeasonQACost = Double.parseDouble(QA.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonQACost = 0;
			}
			Query<Long> Sales = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-07-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-10-01 00:00:00.000").setParameter("dept", "Sales");
			try {
				thisSeasonSalesCost = Double.parseDouble(Sales.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonSalesCost = 0;
			}
			Query<Long> PM = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-07-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-10-01 00:00:00.000").setParameter("dept", "PM");
			try {
				thisSeasonPMCost = Double.parseDouble(PM.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonPMCost = 0;
			}
			deptCostPercent.put("sHR", nf.format(thisSeasonHRCost / thisSeasonTotalCost));
			deptCostPercent.put("sRD", nf.format(thisSeasonRDCost / thisSeasonTotalCost));
			deptCostPercent.put("sQA", nf.format(thisSeasonQACost / thisSeasonTotalCost));
			deptCostPercent.put("sSales", nf.format(thisSeasonSalesCost / thisSeasonTotalCost));
			deptCostPercent.put("sPM", nf.format(thisSeasonPMCost / thisSeasonTotalCost));
			deptDataList.add(deptCostPercent);
			// return deptDataList;
		}
		if (month == 10 || month == 11 || month == 12) {
			int nextyear = year + 1;
			Query<Long> total = sessionFacotry.getCurrentSession().createQuery(totalhql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-10-01 00:00:00.000")
					.setParameter("end", Integer.toString(nextyear) + "-01-01 00:00:00.000");
			try {
				thisSeasonTotalCost = Double.parseDouble(total.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonTotalCost = 1;
			}
			Query<Long> HR = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-10-01 00:00:00.000")
					.setParameter("end", Integer.toString(nextyear) + "-01-01 00:00:00.000").setParameter("dept", "HR");
			try {
				thisSeasonHRCost = Double.parseDouble(HR.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonHRCost = 0;
			}
			Query<Long> RD = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-10-01 00:00:00.000")
					.setParameter("end", Integer.toString(nextyear) + "-01-01 00:00:00.000").setParameter("dept", "RD");
			try {
				thisSeasonRDCost = Double.parseDouble(RD.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonRDCost = 0;
			}
			Query<Long> QA = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-10-01 00:00:00.000")
					.setParameter("end", Integer.toString(nextyear) + "-01-01 00:00:00.000").setParameter("dept", "QA");
			try {
				thisSeasonQACost = Double.parseDouble(QA.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonQACost = 0;
			}
			Query<Long> Sales = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-10-01 00:00:00.000")
					.setParameter("end", Integer.toString(nextyear) + "-01-01 00:00:00.000")
					.setParameter("dept", "Sales");
			try {
				thisSeasonSalesCost = Double.parseDouble(Sales.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonSalesCost = 0;
			}
			Query<Long> PM = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-10-01 00:00:00.000")
					.setParameter("end", Integer.toString(nextyear) + "-01-01 00:00:00.000").setParameter("dept", "PM");
			try {
				thisSeasonPMCost = Double.parseDouble(PM.list().get(0).toString());
			} catch (Exception e) {
				thisSeasonPMCost = 0;
			}
			deptCostPercent.put("sHR", nf.format(thisSeasonHRCost / thisSeasonTotalCost));
			deptCostPercent.put("sRD", nf.format(thisSeasonRDCost / thisSeasonTotalCost));
			deptCostPercent.put("sQA", nf.format(thisSeasonQACost / thisSeasonTotalCost));
			deptCostPercent.put("sSales", nf.format(thisSeasonSalesCost / thisSeasonTotalCost));
			deptCostPercent.put("sPM", nf.format(thisSeasonPMCost / thisSeasonTotalCost));
			deptDataList.add(deptCostPercent);
			// return deptDataList;
		}
		// deptCostPercent.clear();
		if (month == 2) {
			if (year % 4 == 0 || year % 400 == 0) {
				Query<Long> total = sessionFacotry.getCurrentSession().createQuery(totalhql, Long.class)
						.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-29 00:00:00.000");
				try {
					thisMonthTotalCost = Double.parseDouble(total.list().get(0).toString());
				} catch (Exception e) {
					thisMonthTotalCost = 1;
				}
				Query<Long> HR = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
						.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-29 00:00:00.000")
						.setParameter("dept", "HR");
				try {
					thisMonthHRCost = Double.parseDouble(HR.list().get(0).toString());
				} catch (Exception e) {
					thisMonthHRCost = 0;
				}
				Query<Long> RD = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
						.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-29 00:00:00.000")
						.setParameter("dept", "RD");
				try {
					thisMonthRDCost = Double.parseDouble(RD.list().get(0).toString());
				} catch (Exception e) {
					thisMonthRDCost = 0;
				}
				Query<Long> QA = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
						.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-29 00:00:00.000")
						.setParameter("dept", "QA");
				try {
					thisMonthQACost = Double.parseDouble(QA.list().get(0).toString());
				} catch (Exception e) {
					thisMonthQACost = 0;
				}
				Query<Long> Sales = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
						.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-29 00:00:00.000")
						.setParameter("dept", "Sales");
				try {
					thisMonthSalesCost = Double.parseDouble(Sales.list().get(0).toString());
				} catch (Exception e) {
					thisMonthSalesCost = 0;
				}

				Query<Long> PM = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
						.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-29 00:00:00.000")
						.setParameter("dept", "PM");
				try {
					thisMonthPMCost = Double.parseDouble(PM.list().get(0).toString());
				} catch (Exception e) {
					thisMonthPMCost = 0;
				}
				deptCostPercent.put("mHR", nf.format(thisMonthHRCost / thisMonthTotalCost));
				deptCostPercent.put("mRD", nf.format(thisMonthRDCost / thisMonthTotalCost));
				deptCostPercent.put("mQA", nf.format(thisMonthQACost / thisMonthTotalCost));
				deptCostPercent.put("mSales", nf.format(thisMonthSalesCost / thisMonthTotalCost));
				deptCostPercent.put("mPM", nf.format(thisMonthPMCost / thisMonthTotalCost));
				deptDataList.add(deptCostPercent);
				return deptDataList;
			} else {
				Query<Long> total = sessionFacotry.getCurrentSession().createQuery(totalhql, Long.class)
						.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-28 00:00:00.000");
				try {
					thisMonthTotalCost = Double.parseDouble(total.list().get(0).toString());
				} catch (Exception e) {
					thisMonthTotalCost = 1;
				}
				Query<Long> HR = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
						.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-28 00:00:00.000")
						.setParameter("dept", "HR");
				try {
					thisMonthHRCost = Double.parseDouble(HR.list().get(0).toString());
				} catch (Exception e) {
					thisMonthHRCost = 0;
				}
				Query<Long> RD = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
						.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-28 00:00:00.000")
						.setParameter("dept", "RD");
				try {
					thisMonthRDCost = Double.parseDouble(RD.list().get(0).toString());
				} catch (Exception e) {
					thisMonthRDCost = 0;
				}
				Query<Long> QA = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
						.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-28 00:00:00.000")
						.setParameter("dept", "QA");
				try {
					thisMonthQACost = Double.parseDouble(QA.list().get(0).toString());
				} catch (Exception e) {
					thisMonthQACost = 0;
				}
				Query<Long> Sales = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
						.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-28 00:00:00.000")
						.setParameter("dept", "Sales");
				try {
					thisMonthSalesCost = Double.parseDouble(Sales.list().get(0).toString());
				} catch (Exception e) {
					thisMonthSalesCost = 0;
				}

				Query<Long> PM = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
						.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-28 00:00:00.000")
						.setParameter("dept", "PM");
				try {
					thisMonthPMCost = Double.parseDouble(PM.list().get(0).toString());
				} catch (Exception e) {
					thisMonthPMCost = 0;
				}
				deptCostPercent.put("mHR", nf.format(thisMonthHRCost / thisMonthTotalCost));
				deptCostPercent.put("mRD", nf.format(thisMonthRDCost / thisMonthTotalCost));
				deptCostPercent.put("mQA", nf.format(thisMonthQACost / thisMonthTotalCost));
				deptCostPercent.put("mSales", nf.format(thisMonthSalesCost / thisMonthTotalCost));
				deptCostPercent.put("mPM", nf.format(thisMonthPMCost / thisMonthTotalCost));
				deptDataList.add(deptCostPercent);
				return deptDataList;
			}
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			Query<Long> total = sessionFacotry.getCurrentSession().createQuery(totalhql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-30 00:00:00.000");
			try {
				thisMonthTotalCost = Double.parseDouble(total.list().get(0).toString());
			} catch (Exception e) {
				thisMonthTotalCost = 1;
			}
			Query<Long> HR = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-30 00:00:00.000")
					.setParameter("dept", "HR");
			try {
				thisMonthHRCost = Double.parseDouble(HR.list().get(0).toString());
			} catch (Exception e) {
				thisMonthHRCost = 0;
			}
			Query<Long> RD = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-30 00:00:00.000")
					.setParameter("dept", "RD");
			try {
				thisMonthRDCost = Double.parseDouble(RD.list().get(0).toString());
			} catch (Exception e) {
				thisMonthRDCost = 0;
			}
			Query<Long> QA = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-30 00:00:00.000")
					.setParameter("dept", "QA");
			try {
				thisMonthQACost = Double.parseDouble(QA.list().get(0).toString());
			} catch (Exception e) {
				thisMonthQACost = 0;
			}
			Query<Long> Sales = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-30 00:00:00.000")
					.setParameter("dept", "Sales");
			try {
				thisMonthSalesCost = Double.parseDouble(Sales.list().get(0).toString());
			} catch (Exception e) {
				thisMonthSalesCost = 0;
			}

			Query<Long> PM = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-30 00:00:00.000")
					.setParameter("dept", "PM");
			try {
				thisMonthPMCost = Double.parseDouble(PM.list().get(0).toString());
			} catch (Exception e) {
				thisMonthPMCost = 0;
			}
			deptCostPercent.put("mHR", nf.format(thisMonthHRCost / thisMonthTotalCost));
			deptCostPercent.put("mRD", nf.format(thisMonthRDCost / thisMonthTotalCost));
			deptCostPercent.put("mQA", nf.format(thisMonthQACost / thisMonthTotalCost));
			deptCostPercent.put("mSales", nf.format(thisMonthSalesCost / thisMonthTotalCost));
			deptCostPercent.put("mPM", nf.format(thisMonthPMCost / thisMonthTotalCost));
			deptDataList.add(deptCostPercent);
			return deptDataList;
		}
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			Query<Long> total = sessionFacotry.getCurrentSession().createQuery(totalhql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-31 00:00:00.000");
			try {
				thisMonthTotalCost = Double.parseDouble(total.list().get(0).toString());
			} catch (Exception e) {
				thisMonthTotalCost = 1;
			}
			Query<Long> HR = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-31 00:00:00.000")
					.setParameter("dept", "HR");
			try {
				thisMonthHRCost = Double.parseDouble(HR.list().get(0).toString());
			} catch (Exception e) {
				thisMonthHRCost = 0;
			}
			Query<Long> RD = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-31 00:00:00.000")
					.setParameter("dept", "RD");
			try {
				thisMonthRDCost = Double.parseDouble(RD.list().get(0).toString());
			} catch (Exception e) {
				thisMonthRDCost = 0;
			}
			Query<Long> QA = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-31 00:00:00.000")
					.setParameter("dept", "QA");
			try {
				thisMonthQACost = Double.parseDouble(QA.list().get(0).toString());
			} catch (Exception e) {
				thisMonthQACost = 0;
			}
			Query<Long> Sales = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-31 00:00:00.000")
					.setParameter("dept", "Sales");
			try {
				thisMonthSalesCost = Double.parseDouble(Sales.list().get(0).toString());
			} catch (Exception e) {
				thisMonthSalesCost = 0;
			}

			Query<Long> PM = sessionFacotry.getCurrentSession().createQuery(depthql, Long.class)
					.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
					.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-31 00:00:00.000")
					.setParameter("dept", "PM");
			try {
				thisMonthPMCost = Double.parseDouble(PM.list().get(0).toString());
			} catch (Exception e) {
				thisMonthPMCost = 0;
			}
			deptCostPercent.put("mHR", nf.format(thisMonthHRCost / thisMonthTotalCost));
			deptCostPercent.put("mRD", nf.format(thisMonthRDCost / thisMonthTotalCost));
			deptCostPercent.put("mQA", nf.format(thisMonthQACost / thisMonthTotalCost));
			deptCostPercent.put("mSales", nf.format(thisMonthSalesCost / thisMonthTotalCost));
			deptCostPercent.put("mPM", nf.format(thisMonthPMCost / thisMonthTotalCost));
			deptDataList.add(deptCostPercent);
			return deptDataList;
		}
		return deptDataList;
	}

	public List<feeAppMember> deptFeeApplyCostDetail(String sORm, String dept) {
		List<feeAppMember> detail = null;
		if (dept.equals("Sl")) {
			dept = "Sales";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(GlobalService.dateOfToday());
		int year = Integer.parseInt(today.substring(0, 4));
		int month = Integer.parseInt(today.substring(5, 7));
		String depthql = "from feeAppMember where department=:dept and appTime BETWEEN :start And :end ";
		if (sORm.equals("s")) {
			if (month == 1 || month == 2 || month == 3) {
				Query<feeAppMember> query = sessionFacotry.getCurrentSession().createQuery(depthql, feeAppMember.class)
						.setParameter("start", today.substring(0, 4) + "-01-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-04-01 00:00:00.000").setParameter("dept", dept);
				detail = query.list();
			}
			if (month == 4 || month == 5 || month == 6) {
				Query<feeAppMember> query = sessionFacotry.getCurrentSession().createQuery(depthql, feeAppMember.class)
						.setParameter("start", today.substring(0, 4) + "-04-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-07-01 00:00:00.000").setParameter("dept", dept);
				detail = query.list();
			}
			if (month == 7 || month == 8 || month == 9) {
				Query<feeAppMember> query = sessionFacotry.getCurrentSession().createQuery(depthql, feeAppMember.class)
						.setParameter("start", today.substring(0, 4) + "-07-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-10-01 00:00:00.000").setParameter("dept", dept);
				detail = query.list();
			}
			if (month == 10 || month == 11 || month == 12) {
				int nextyear = year + 1;
				Query<feeAppMember> query = sessionFacotry.getCurrentSession().createQuery(depthql, feeAppMember.class)
						.setParameter("start", today.substring(0, 4) + "-10-01 00:00:00.000")
						.setParameter("end", Integer.toString(nextyear) + "-01-01 00:00:00.000")
						.setParameter("dept", dept);
				detail = query.list();
			}
		}
		if (sORm.equals("m")) {
			if (month == 2) {
				if (year % 4 == 0 || year % 400 == 0) {
					Query<feeAppMember> query = sessionFacotry.getCurrentSession()
							.createQuery(depthql, feeAppMember.class)
							.setParameter("start",
									today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
							.setParameter("end",
									today.substring(0, 4) + "-" + today.substring(5, 7) + "-29 00:00:00.000")
							.setParameter("dept", dept);
					detail = query.list();
				} else {
					Query<feeAppMember> query = sessionFacotry.getCurrentSession()
							.createQuery(depthql, feeAppMember.class)
							.setParameter("start",
									today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
							.setParameter("end",
									today.substring(0, 4) + "-" + today.substring(5, 7) + "-28 00:00:00.000")
							.setParameter("dept", dept);
					detail = query.list();
				}
			}
			if (month == 4 || month == 6 || month == 9 || month == 11) {
				Query<feeAppMember> query = sessionFacotry.getCurrentSession().createQuery(depthql, feeAppMember.class)
						.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-30 00:00:00.000")
						.setParameter("dept", dept);
				detail = query.list();
			}
			if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				Query<feeAppMember> query = sessionFacotry.getCurrentSession().createQuery(depthql, feeAppMember.class)
						.setParameter("start", today.substring(0, 4) + "-" + today.substring(5, 7) + "-01 00:00:00.000")
						.setParameter("end", today.substring(0, 4) + "-" + today.substring(5, 7) + "-31 00:00:00.000")
						.setParameter("dept", dept);
				detail = query.list();
			}
		}
		return detail;
	}
	// add by 揚明--end

	@Override
	public boolean DelectItem(int feeAppID) {
		// TODO Auto-generated method stub
		return false;
	}
}
