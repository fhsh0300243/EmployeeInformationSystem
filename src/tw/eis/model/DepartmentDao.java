package tw.eis.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import tw.eis.model.Department;

@Repository("departmentDao")
public class DepartmentDao implements IDepartmentDao {

	private SessionFactory sessionFactory;

	public DepartmentDao() {

	}

	@Autowired
	public DepartmentDao(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Department deptData(int id) {
		return sessionFactory.getCurrentSession().get(Department.class, id);
	}

	@Override
	public List<?> allDeptData() {
		DetachedCriteria mainQuery = DetachedCriteria.forClass(Department.class);
		List<?> list = mainQuery.getExecutableCriteria(sessionFactory.getCurrentSession()).list();
		return list;
	}

	@Override
	public String deptIdByDeptAbb(int deptid) {
		Department dept=sessionFactory.getCurrentSession().createQuery("From Department where deptID=:deptid", Department.class)
				.setParameter("deptid", deptid).uniqueResult();
		return dept.getDeptAbb();
	}

}
