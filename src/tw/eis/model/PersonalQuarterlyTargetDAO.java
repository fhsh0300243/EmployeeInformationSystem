package tw.eis.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import tw.eis.model.Department;
import tw.eis.model.DepartmentalAnnualGoals;
import tw.eis.model.Employee;
import tw.eis.model.PersonalQuarterlyTarget;
import tw.eis.model.WorkProject;

@Repository
public class PersonalQuarterlyTargetDAO {
	private SessionFactory sessionFactory;
	private JSONArray j1;
	
	
	@Autowired
	public PersonalQuarterlyTargetDAO(@Qualifier(value="sessionFactory")SessionFactory sessionFactory) {
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
	public void InsertPQT(DepartmentalAnnualGoals d, PersonalQuarterlyTarget p,Model m) {
		Session session = sessionFactory.getCurrentSession();
		session.save(d);
		System.out.print("PQT save success");
	}
	//���Ѽƪ�deptID����JavaBean�Ӫ�
	public DepartmentalAnnualGoals getdag(int deptID,Model m) {
		Session session = sessionFactory.getCurrentSession();
		String sqlstr = "From DepartmentalAnnualGoals Where deptID =: deptid";
		Query<DepartmentalAnnualGoals> query = session.createQuery(sqlstr,DepartmentalAnnualGoals.class);
		query.setParameter("deptid",deptID);
		DepartmentalAnnualGoals d = query.uniqueResult();
		return d;
	}
	public void InsertWP(PersonalQuarterlyTarget p,WorkProject w,Model m) {
		Session session = sessionFactory.getCurrentSession();
		session.save(p);
		System.out.print("WP save success");
	}
	public void ChangePQT(Model m,int pid,String pqt,String worksetter,Timestamp timestamp) {
		Session session = sessionFactory.getCurrentSession();
		String sqlstr = "From PersonalQuarterlyTarget Where pID =: pid";
		Query<PersonalQuarterlyTarget> query = session.createQuery(sqlstr,PersonalQuarterlyTarget.class);
		PersonalQuarterlyTarget p = query.setParameter("pid",pid).uniqueResult();
		p.setPersonalQuarterlyTarget(pqt);
		p.setGoalSetters(worksetter);
		p.setDate(timestamp);
		session.save(p);
	}
	public void DeletePQT(Model m,int pid) {
		Session session = sessionFactory.getCurrentSession();
		String sqlstr = "From PersonalQuarterlyTarget Where pID =: pid";
		Query<PersonalQuarterlyTarget> query = session.createQuery(sqlstr,PersonalQuarterlyTarget.class);
		PersonalQuarterlyTarget p = query.setParameter("pid",pid).uniqueResult();
		session.delete(p);
	}
	public int getdeptid(Model m,String dept) {
		Session session = sessionFactory.getCurrentSession();
//		System.out.print("============================"+dept);
		String sqlstr = "From Department Where deptAbb =: dept";
		Query<Department> query = session.createQuery(sqlstr,Department.class);
		Department d = query.setParameter("dept", dept).uniqueResult();
		return d.getDeptID();
	}
	public String getusername(Model m,int empid) {
		Session session = sessionFactory.getCurrentSession();
		String sqlstr = "From Employee Where empID =: empid";
		Query<Employee> query = session.createQuery(sqlstr,Employee.class);
		Employee e = query.setParameter("empid", empid).uniqueResult();
		return e.getName();
	}
}
