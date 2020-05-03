package tw.eis.model;

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

@Repository
public class AssignWorkDAO {
	private SessionFactory sessionFactory;
	private JSONArray j;

	@Autowired
	public AssignWorkDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void InsertAssignWork(int empid, int wid, String work) {
		Session session = sessionFactory.getCurrentSession();
		AssignWork aw = new AssignWork(empid, wid, work);
		session.save(aw);
	}
	public void ChangeAssignWork(int empid, int wid,int cwid,String work) {
		Session session = sessionFactory.getCurrentSession();
		String hqlstr = "From AssignWork Where empID ="+empid+"And wID=:wid";
		Query<AssignWork> query = session.createQuery(hqlstr,AssignWork.class);
		AssignWork aw = query.setParameter("wid", wid).uniqueResult();
		aw.setwID(cwid);
		aw.setWork(work);
		session.save(aw);
	}

	public JSONArray engworklist(int empid) {
		Session session = sessionFactory.getCurrentSession();
		String hqlstr = "From AssignWork Where empID=:empid and WorkStatus =0";
		Query<AssignWork> query = session.createQuery(hqlstr, AssignWork.class);
		query.setParameter("empid", empid);
		List<AssignWork> awlist = query.getResultList();
		JSONArray jay = new JSONArray();
		for (AssignWork aw : awlist) {
			JSONObject jb = new JSONObject();
			jb.put("awID", aw.getAwID());
			jb.put("EmpID", aw.getEmpID());
			jb.put("wID", aw.getwID());
			jb.put("Work", aw.getWork());
			jay.put(jb);
		}
		j = jay;
		return j;
	}
	public JSONArray wkstatus1(int empid) {
		Session session = sessionFactory.getCurrentSession();
		String hqlstr = "From AssignWork Where empID ="+empid+"and WorkStatus=:status";
		Query<AssignWork> query = session.createQuery(hqlstr,AssignWork.class);
		List<AssignWork>awlist =  query.setParameter("status", 1).getResultList();
		JSONArray jay = new JSONArray();
		for(AssignWork aw:awlist) {
			JSONObject jb = new JSONObject();
			jb.put("empid", aw.getEmpID());
			jb.put("awid", aw.getAwID());
			jb.put("Work", aw.getWork());
			jay.put(jb);
		}
		j = jay;
		return j;
	}
	public JSONArray wkstatus2(int empid) {
		Session session = sessionFactory.getCurrentSession();
		String hqlstr = "From AssignWork Where empID ="+empid+"and WorkStatus=:status";
		Query<AssignWork> query = session.createQuery(hqlstr,AssignWork.class);
		List<AssignWork>awlist =  query.setParameter("status", 2).getResultList();
		JSONArray jay = new JSONArray();
		for(AssignWork aw:awlist) {
			JSONObject jb = new JSONObject();
			jb.put("empid", aw.getEmpID());
			jb.put("awid", aw.getAwID());
			jb.put("Work", aw.getWork());
			jay.put(jb);
		}
		j = jay;
		return j;
	}
	public JSONArray wkstatus3(int empid) {
		Session session = sessionFactory.getCurrentSession();
		String hqlstr = "From AssignWork Where empID ="+empid+"and WorkStatus=:status";
		Query<AssignWork> query = session.createQuery(hqlstr,AssignWork.class);
		List<AssignWork>awlist =  query.setParameter("status", 3).getResultList();
		JSONArray jay = new JSONArray();
		for(AssignWork aw:awlist) {
			JSONObject jb = new JSONObject();
			jb.put("empid", aw.getEmpID());
			jb.put("awid", aw.getAwID());
			jb.put("Work", aw.getWork());
			jay.put(jb);
		}
		j = jay;
		return j;
	}
	public void setworkstatus1(Model m,int awid) {
		Session session = sessionFactory.getCurrentSession();
		String hqlstr = "From AssignWork Where awID =: awid";
		Query<AssignWork> query = session.createQuery(hqlstr,AssignWork.class);
		AssignWork aw = query.setParameter("awid", awid).uniqueResult();
		aw.setWorkStatus(1);
		session.save(aw);
	}
	public void setworkstatus2(Model m,int awid) {
		Session session = sessionFactory.getCurrentSession();
		String hqlstr = "From AssignWork Where awID =: awid";
		Query<AssignWork> query = session.createQuery(hqlstr,AssignWork.class);
		AssignWork aw = query.setParameter("awid", awid).uniqueResult();
		aw.setWorkStatus(2);
		session.save(aw);
	}
	public void setworkstatus3(Model m,int awid) {
		Session session = sessionFactory.getCurrentSession();
		String hqlstr = "From AssignWork Where awID =: awid";
		Query<AssignWork> query = session.createQuery(hqlstr,AssignWork.class);
		AssignWork aw = query.setParameter("awid", awid).uniqueResult();
		aw.setWorkStatus(3);
		session.save(aw);
	}
}
