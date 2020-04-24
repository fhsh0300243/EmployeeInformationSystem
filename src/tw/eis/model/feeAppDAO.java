package tw.eis.model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("feeAppDAO")
public class feeAppDAO implements IfeeAppDAO {

	private SessionFactory sessionFacotry;
	

	@Autowired
	public feeAppDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}
	//新增申請差旅費進資料庫
	public boolean addFeeApp(String department, int employeeID, String appItem, String appTime, String invoiceTime,
			String invoiceNb, int editor, String remark, int appMoney, String signerTime, String signerStatus,
			int signerID) {
		Session session = sessionFacotry.getCurrentSession();
		feeAppMember feeapp = new feeAppMember(department, employeeID, appItem, appTime, invoiceTime, invoiceNb, editor,
				remark, appMoney, signerTime, signerStatus, signerID);

		session.save(feeapp);
		return true;

	}
	//查詢差旅費-員工編號、時間區間
	public List<feeAppMember> qFeeApp(int employeeID,String searchA,String searchB) {
		
		Session session = sessionFacotry.getCurrentSession();
		//String qq="from feeAppMember where employeeID=?"+employeeID+" and invoiceTime BETWEEN "+searchA+" AND "+searchB+" ORDER BY invoiceTime DESC";
		//System.out.println("test print:"+qq);
		//Query query = session.createQuery("from feeAppMember where employeeID=?0 and invoiceTime BETWEEN invoiceTime=?1 AND invoiceTime=?2 ORDER BY invoiceTime DESC",feeAppMember.class);
		//Query query = session.createQuery("from feeAppMember where employeeID=?0 and invoiceTime=?1 ORDER BY invoiceTime DESC",feeAppMember.class);
		//query.setParameter(0, employeeID);
		//query.setParameter(1, searchA);
		//query.setParameter(2, searchB);
		
		//List<feeAppMember> lists = query.list();
//		Iterator iterator = lists.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
		
		DetachedCriteria mainQuery = DetachedCriteria.forClass(feeAppMember.class);
		mainQuery.add(Restrictions.between("appTime", searchA, searchB));
		mainQuery.add(Restrictions.eq("employeeID", employeeID));
		
		  List<feeAppMember> list = mainQuery.getExecutableCriteria(sessionFacotry.getCurrentSession()).list();
//
//		}
		return list;
	}
	//查詢差旅費-主管簽核頁面
	public List<feeAppMember> qfeeSingerApp(String department, String signerStatus, int level) {
		DetachedCriteria mainQuery = DetachedCriteria.forClass(feeAppMember.class);
		mainQuery.add(Restrictions.eq("department", department));
		mainQuery.add(Restrictions.eq("signerStatus", signerStatus));
		
		  List<feeAppMember> list2 = mainQuery.getExecutableCriteria(sessionFacotry.getCurrentSession()).list();
		return list2;
	}
	
}
