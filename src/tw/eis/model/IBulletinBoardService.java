package tw.eis.model;

import java.util.List;

public interface IBulletinBoardService {

	public boolean insertBulletin(BulletinBoard BulletinBoard);
	public boolean updateBulletin(BulletinBoard BulletinBoard);
	public boolean deleteBulletin(int id);
	public List<BulletinBoard> queryBulletinRecord(int EmployeeID);
	public List<BulletinBoard> queryBulletinForLook(String department);
	public List<BulletinBoard> queryBulletinByOwnCreate(int EmployeeID);
	public byte[] queryFile(int BulletinBoardID);
}
