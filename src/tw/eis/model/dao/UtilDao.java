package tw.eis.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import tw.eis.model.Department;
import tw.eis.model.Employee;
import tw.eis.model.Title;

@Repository("utilDao")
public class UtilDao implements IUtilDao {

	private SessionFactory sessionFactory;

	public UtilDao() {

	}

	@Autowired
	public UtilDao(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String createDeptList() {
		List<Department> list = sessionFactory.getCurrentSession().createQuery("From Department", Department.class)
				.list();
		JSONArray jsonarray = new JSONArray();

		for (Department dept : list) {
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("deptid", dept.getDeptID());
			jsonobject.put("deptname", dept.getDeptName());
			jsonobject.put("deptabb", dept.getDeptAbb());
			jsonarray.put(jsonobject);
		}
		return jsonarray.toString();
	}

	@Override
	public String createTitleList() {
		List<Title> list = sessionFactory.getCurrentSession().createQuery("From Title", Title.class).list();
		JSONArray jsonarray = new JSONArray();

		for (Title title : list) {
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("titleid", title.getTitleID());
			jsonobject.put("titlechname", title.getTitleChName());
			jsonobject.put("titlename", title.getTitleName());
			jsonarray.put(jsonobject);
		}
		return jsonarray.toString();
	}

	@Override
	public String createEmpList() {		
		Session session = sessionFactory.getCurrentSession();
		DetachedCriteria dc = DetachedCriteria.forClass(Employee.class);

		dc.add(Restrictions.or(Restrictions.eq("title", "Mamager"),Restrictions.eq("title","Section Mamager")));
		Criteria c = dc.getExecutableCriteria(session);
		List<?> list = c.list();
		JSONArray jsonarray = new JSONArray();

		for (Object emp : list) {
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("empid", ((Employee) emp).getEmpID());
			jsonobject.put("title", ((Employee) emp).getTitle());
			jsonobject.put("name", ((Employee) emp).getName());
			jsonarray.put(jsonobject);
		}
		return jsonarray.toString();
	}

}
