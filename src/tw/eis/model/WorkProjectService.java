package tw.eis.model;

import java.sql.Timestamp;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import tw.eis.model.PersonalQuarterlyTarget;
import tw.eis.model.WorkProject;
import tw.eis.model.WorkProjectDAO;

@Service
public class WorkProjectService {
	private WorkProjectDAO wpDao;

	@Autowired
	public WorkProjectService(WorkProjectDAO wpDao) {
		this.wpDao = wpDao;
	}

	public JSONArray getworkdata1(int deptID, Model m) {
		return wpDao.getworkdata1(deptID, m);
	}

	public void insertwork(PersonalQuarterlyTarget p, WorkProject w, Model m) {
		wpDao.insertwork(p, w, m);
	}

	public PersonalQuarterlyTarget getpqt(int pid, Model m) {
		return wpDao.getpqt(pid, m);
	}

	public void changework(Model m, int wid, String work, String worksetter, Timestamp timestamp) {
		wpDao.changework(m, wid, work, worksetter, timestamp);
	}

	public void deletework(Model m, int wid) {
		wpDao.deletework(m, wid);
	}

	public int getdeptid(Model m, String dept) {
		return wpDao.getdeptid(m, dept);
	}

	public int getlevel(Model m, String title) {
		return wpDao.getlevel(m, title);
	}

	public String getdag(Model m, int deptid) {
		return wpDao.getdag(m, deptid);
	}

	public String getusername(Model m, int empid) {
		return wpDao.getusername(m, empid);
	}

	public JSONArray getassignwork(Model m, int pid) {
		return wpDao.getassignwork(m, pid);
	}

	public JSONArray getdeptusername(Model m, String dept) {
		return wpDao.getdeptusername(m, dept);
	}
}
