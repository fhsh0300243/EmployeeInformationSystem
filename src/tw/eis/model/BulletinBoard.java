package tw.eis.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;





@Component
@Table(name="BulletinBoard")
@Entity
public class BulletinBoard implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bulletinBoardID")
	private int bulletinBoardID;
	@Column(name="title")
	private String title;
	@Column(name="content")
	private String content;
	@Column(name="AttachedFiles")
	private byte[] attachedFiles;
	
	@Column(name = "AttachedFilesName")
	private String attachedFilesName;
	
	@Column(name= "Authority")
	private String authority;
	
	@Column(name="upTime")
	private Date upTime;
	
	@Column(name="downTime")
	private Date downTime;
	
	@Column(name="Date")
	private Timestamp date;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEEID")
	private Users Users;
	
	public BulletinBoard(Users Users,String title,String content,String authority,Date upTime,Date downTime,Timestamp date) {
		this.Users=Users;
		this.title=title;
		this.content=content;
		this.authority=authority;
		this.upTime=upTime;
		this.downTime=downTime;
		this.date=date;
	}

	
	
	public BulletinBoard() {}
	
	
	
	
	public int getBulletinBoardID() {
		return bulletinBoardID;
	}
	public void setBulletinBoardID(int bulletinBoardID) {
		this.bulletinBoardID = bulletinBoardID;
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public byte[] getAttachedFiles() {
		return attachedFiles;
	}
	public void setAttachedFiles(byte[] attachedFiles) {
		this.attachedFiles = attachedFiles;
	}
	
		
	public String getAttachedFilesName() {
		return attachedFilesName;
	}

	public void setAttachedFilesName(String attachedFilesName) {
		this.attachedFilesName = attachedFilesName;
	}



	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public Date getUpTime() {
		return upTime;
	}
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}
	public Date getDownTime() {
		return downTime;
	}
	public void setDownTime(Date downTime) {
		this.downTime = downTime;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Users getUsers() {
		return Users;
	}



	public void setUsers(Users users) {
		Users = users;
	}

	
	
	
}
