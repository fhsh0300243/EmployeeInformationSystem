package tw.eis.model;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import tw.eis.util.GlobalService;

@Repository
public class DepartmentalAnnualGoalsDAO {
	private SessionFactory sessionFactory;

	@Autowired
	public DepartmentalAnnualGoalsDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//���Ѽƪ�deptID����JavaBean�Ӫ�
	public DepartmentalAnnualGoals getdag(int deptID, Model m) {
		Session session = sessionFactory.getCurrentSession();
		String sqlstr = "From DepartmentalAnnualGoals Where deptID := deptid";
		Query<DepartmentalAnnualGoals> query = session.createQuery(sqlstr, DepartmentalAnnualGoals.class);
		query.setParameter("deptid", deptID);
		DepartmentalAnnualGoals d = query.uniqueResult();
		return d;
	}

	//add by 揚明---start
	public List<DepartmentalAnnualGoals> thisYearAllDeptGoals() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(GlobalService.dateOfToday());
		int year = Integer.parseInt(today.substring(0, 4));
		int nextyear = year + 1;
		String hql = "From DepartmentalAnnualGoals where Date BETWEEN :start And :end ";
		List<DepartmentalAnnualGoals> list = sessionFactory.getCurrentSession()
				.createQuery(hql, DepartmentalAnnualGoals.class)
				.setParameter("start", today.substring(0, 4) + "-01-01 00:00:00.000")
				.setParameter("end", Integer.toString(nextyear) + "-01-01 00:00:00.000").list();
		return list;
	}
	//add by 揚明---end
}
