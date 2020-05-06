package tw.eis.model;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import tw.eis.model.DepartmentalAnnualGoals;
import tw.eis.model.PersonalQuarterlyTarget;
import tw.eis.model.WorkProject;
import tw.eis.model.PersonalQuarterlyTargetDAO;

@Service
public class PersonalQuarterlyTargetService {
	private PersonalQuarterlyTargetDAO pqtDao;

	@Autowired
	public PersonalQuarterlyTargetService(PersonalQuarterlyTargetDAO pqtDao) {
		this.pqtDao = pqtDao;
	}

	public JSONArray getpersonwork(int deptID, Model m) {
		return pqtDao.getpersonwork(deptID, m);
	}

	public void InsertPQT(DepartmentalAnnualGoals d, PersonalQuarterlyTarget p, Model m) {
		pqtDao.InsertPQT(d, p, m);
	}

	public DepartmentalAnnualGoals getdag(int deptID, Model m) {
		return pqtDao.getdag(deptID, m);
	}

	public void InsertWP(PersonalQuarterlyTarget p, WorkProject w, Model m) {
		pqtDao.InsertWP(p, w, m);
	}

	public void ChangePQT(Model m, int pid, String pqt, String worksetter, Timestamp timestamp) {
		pqtDao.ChangePQT(m, pid, pqt, worksetter, timestamp);
	}

	public void DeletePQT(Model m, int pid) {
		pqtDao.DeletePQT(m, pid);
	}
	public int getdeptid(Model m,String dept) {
		return pqtDao.getdeptid(m, dept);
	}
	public String getusername(Model m,int empid) {
		return pqtDao.getusername(m, empid);
	}
		
	//add by 揚明---start
	public List<PersonalQuarterlyTarget> thisSeasonDeptPsersonTargetDetail(String deptabb){
		return pqtDao.thisSeasonDeptPsersonTargetDetail(deptabb);
	}
	public LinkedList<String> deptGoalAchievementRate(LinkedList<Integer> pids) {
		return pqtDao.deptGoalAchievementRate(pids);
	}
	public List<List<AssignWork>> personGoalAchievementstatus(int pid){
		return pqtDao.personGoalAchievementstatus(pid);
	}
	//add by 揚明---end
}
