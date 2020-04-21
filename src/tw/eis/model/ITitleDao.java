package tw.eis.model;

import java.util.List;

import tw.eis.model.Title;

public interface ITitleDao {
	public Title titleData(int id);
	public List<?> titleDataByDeptId(int deptId);
}
