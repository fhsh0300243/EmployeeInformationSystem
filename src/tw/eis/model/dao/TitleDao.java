package tw.eis.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import tw.eis.model.Title;

@Repository("titleDao")
public class TitleDao implements ITitleDao {

	private SessionFactory sessionFactory;

	public TitleDao() {

	}

	@Autowired
	public TitleDao(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Title titleData(int id) {
		return sessionFactory.getCurrentSession().get(Title.class, id);
	}

	@Override
	public List<?> titleDataByDeptId(int deptId) {
		List<?> list = null;
		DetachedCriteria mainQuery = DetachedCriteria.forClass(Title.class);
		if (deptId == 0) {
			list = mainQuery.add(Restrictions.eq("titleID", 1))
					.getExecutableCriteria(sessionFactory.getCurrentSession()).list();
			return list;
		} else {
			list = mainQuery.add(Property.forName("department.deptID").eq(deptId))
					.getExecutableCriteria(sessionFactory.getCurrentSession()).list();
			return list;
		}
	}
}
