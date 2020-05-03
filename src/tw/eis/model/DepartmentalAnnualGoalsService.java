package tw.eis.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import tw.eis.model.DepartmentalAnnualGoals;
import tw.eis.model.DepartmentalAnnualGoalsDAO;

@Service
public class DepartmentalAnnualGoalsService {
	private DepartmentalAnnualGoalsDAO dagDao;

	@Autowired
	public DepartmentalAnnualGoalsService(DepartmentalAnnualGoalsDAO dagDao) {
		this.dagDao = dagDao;
	}

	public DepartmentalAnnualGoals getdag(int deptID, Model m) {
		return dagDao.getdag(deptID, m);
	}

	//add by 揚明---start
	public List<DepartmentalAnnualGoals> thisYearAllDeptGoals() {
		return dagDao.thisYearAllDeptGoals();
	}
	//add by 揚明---end
}
