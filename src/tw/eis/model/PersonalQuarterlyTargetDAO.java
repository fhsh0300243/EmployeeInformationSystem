package tw.eis.model;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository
public class PersonalQuarterlyTargetDAO {
	private SessionFactory sessionFactory;
	private JSONArray j1;

	@Autowired
	public PersonalQuarterlyTargetDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public JSONArray getpersonwork(int deptID, Model m) {
		try {
			System.out.print(deptID);
			Session session = sessionFactory.getCurrentSession();
			String hqlstr = "From PersonalQuarterlyTarget Where deptID =:deptID";
			Query<PersonalQuarterlyTarget> query = session.createQuery(hqlstr, PersonalQuarterlyTarget.class);
			query.setParameter("deptID", deptID);
			List<PersonalQuarterlyTarget> plist = query.list();
			JSONArray ja = new JSONArray();
			for (int i = 0; i < plist.size(); i++) {
				String hqlwork = "From WorkProject Where pid =:pid";
				Query<WorkProject> wquery = session.createQuery(hqlwork, WorkProject.class);
				List<WorkProject> wlist = wquery.setParameter("pid", plist.get(i).getPid()).list();
				JSONArray jay = new JSONArray();
				for (WorkProject w : wlist) {
					JSONObject jb = new JSONObject();
					jb.put("dag", plist.get(i).getDepartmentAnnualGoal());
					jb.put("pID", plist.get(i).getPid());
					jb.put("PersonalQuarterlyTarget", plist.get(i).getPersonalQuarterlyTarget());
					jb.put("Work", w.getWork());
					jb.put("time", w.getDate());
					jb.put("worksetter", plist.get(i).getGoalSetters());
					jay.put(jb);
				}
				ja.put(jay);

			}
			j1 = ja;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print(j1);
		return j1;
	}

	public void InsertPQT(DepartmentalAnnualGoals d, PersonalQuarterlyTarget p, Model m) {
		Session session = sessionFactory.getCurrentSession();
		session.save(d);
		System.out.print("PQT save success");
	}

	// ���Ѽƪ�deptID����JavaBean�Ӫ�
	public DepartmentalAnnualGoals getdag(int deptID, Model m) {
		Session session = sessionFactory.getCurrentSession();
		String sqlstr = "From DepartmentalAnnualGoals Where deptID =: deptid";
		Query<DepartmentalAnnualGoals> query = session.createQuery(sqlstr, DepartmentalAnnualGoals.class);
		query.setParameter("deptid", deptID);
		DepartmentalAnnualGoals d = query.uniqueResult();
		return d;
	}

	public void InsertWP(PersonalQuarterlyTarget p, WorkProject w, Model m) {
		Session session = sessionFactory.getCurrentSession();
		session.save(p);
		System.out.print("WP save success");
	}

	public void ChangePQT(Model m, int pid, String pqt, String worksetter, Timestamp timestamp) {
		Session session = sessionFactory.getCurrentSession();
		String sqlstr = "From PersonalQuarterlyTarget Where pID =: pid";
		Query<PersonalQuarterlyTarget> query = session.createQuery(sqlstr, PersonalQuarterlyTarget.class);
		PersonalQuarterlyTarget p = query.setParameter("pid", pid).uniqueResult();
		p.setPersonalQuarterlyTarget(pqt);
		p.setGoalSetters(worksetter);
		p.setDate(timestamp);
		session.save(p);
	}

	public void DeletePQT(Model m, int pid) {
		Session session = sessionFactory.getCurrentSession();
		String sqlstr = "From PersonalQuarterlyTarget Where pID =: pid";
		Query<PersonalQuarterlyTarget> query = session.createQuery(sqlstr, PersonalQuarterlyTarget.class);
		PersonalQuarterlyTarget p = query.setParameter("pid", pid).uniqueResult();
		session.delete(p);
	}

	public int getdeptid(Model m, String dept) {
		Session session = sessionFactory.getCurrentSession();
//		System.out.print("============================"+dept);
		String sqlstr = "From Department Where deptAbb =: dept";
		Query<Department> query = session.createQuery(sqlstr, Department.class);
		Department d = query.setParameter("dept", dept).uniqueResult();
		return d.getDeptID();
	}

	public String getusername(Model m, int empid) {
		Session session = sessionFactory.getCurrentSession();
		String sqlstr = "From Employee Where empID =: empid";
		Query<Employee> query = session.createQuery(sqlstr, Employee.class);
		Employee e = query.setParameter("empid", empid).uniqueResult();
		return e.getName();
	}

	// add by 揚明---start
	public List<PersonalQuarterlyTarget> thisSeasonDeptPsersonTargetDetail(String deptabb) {
		DetachedCriteria mainQuery = DetachedCriteria.forClass(PersonalQuarterlyTarget.class);
		mainQuery.add(Property.forName("deptName").eq(deptabb));
		List<PersonalQuarterlyTarget> detail = mainQuery.getExecutableCriteria(sessionFactory.getCurrentSession())
				.list();
		detail = mainQuery.getExecutableCriteria(sessionFactory.getCurrentSession()).list();
		return detail;
	}

	public LinkedList<String> deptGoalAchievementRate(LinkedList<Integer> pids) {
		LinkedList<String> resultdata = new LinkedList<String>();
		DecimalFormat nf = (DecimalFormat) NumberFormat.getPercentInstance();
		nf.applyPattern("0%"); // 00表示小數點2位
		nf.setMaximumFractionDigits(2); // 2表示精確到小數點後2位
		Session session = sessionFactory.getCurrentSession();
		String workprojecthql = "From WorkProject where pID=:pid";
		String asignworktotalhql = "From AssignWork where wID=:wid";
		String asignworkhql = "From AssignWork where wID=:wid and WorkStatus=:status";
		double workprojectcount = 1;
		double goaledcount = 0;
		double totalcount = 0;
		double result = 0;
		List<WorkProject> list = null;
		List<AssignWork> atotal = null;
		List<AssignWork> aw = null;
		for (int i = 0; i < pids.size(); i++) {
			workprojectcount = 1;
			totalcount = 0;
			goaledcount = 0;
			result = 0;
			list = session.createQuery(workprojecthql, WorkProject.class).setParameter("pid", pids.get(i)).list();
			workprojectcount = list.size();
			for (WorkProject w : list) {
				atotal = session.createQuery(asignworktotalhql, AssignWork.class).setParameter("wid", w.getwID())
						.list();
				if (!atotal.isEmpty()) {
					totalcount += atotal.size();
				} else {
					totalcount = 1;
				}

//				if (!list2.isEmpty()) {
//					goaledcount++;
//				}

//				result = goaledcount / workprojectcount;
				aw = session.createQuery(asignworkhql, AssignWork.class).setParameter("wid", w.getwID())
						.setParameter("status", 3).list();
				if (!aw.isEmpty()) {
					goaledcount += aw.size();
				}
			}


			result = goaledcount / totalcount;
			resultdata.add(nf.format(result));
		}
		return resultdata;
	}

	public List<List<AssignWork>> personGoalAchievementstatus(int pid) {
		String workprojecthql = "From WorkProject where pID=:pid";
		String asignworkhql = "From AssignWork where wID=:wid";
		Session session = sessionFactory.getCurrentSession();
		List<AssignWork> aList=null;
		List<List<AssignWork>> data=new LinkedList<List<AssignWork>>();
		List<WorkProject> wList = session.createQuery(workprojecthql, WorkProject.class)
				.setParameter("pid", pid).list();
		for(WorkProject w:wList) {
				aList = session.createQuery(asignworkhql,AssignWork.class).setParameter("wid", w.getwID()).list();		
			data.add(aList);
		}
		return data;
	}
	// add by 揚明---end
}
