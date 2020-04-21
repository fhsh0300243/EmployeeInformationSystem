package tw.eis.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import tw.eis.model.Users;

@Entity
@Table(name = "COURSE")
@Component
public class Course implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int CourseId; // 課程id代碼
	private String Topic; // 課程主題
	private String TopicType; // 主題類別
	private String TeacherId; // 講師id
	private String Teacher;
	private String Place; // 開課地點
	private Date DateFrom; // 開課日期起訖
	private Date DateTo;
	private Timestamp TimeFrom; // 開課時間起訖
	private Timestamp TimeTo;
	private int Credit; // 評分
	private String ClassType; // 課程檢索分類
	private String CourseType; // 開課部門代碼
	private int Uplimit; // 人數上限
	private String Introduction;
	private String Note; // 備註

	private Users Users;
	private Set<Users> member = new HashSet<Users>();

	public Course() {

	}

	public Course(Users Users,String Topic, String TopicType, String TeacherId, String Teacher, String Place,
			Date DateFrom, Date DateTo, Timestamp TimeFrom, Timestamp TimeTo, Integer Credit, String ClassType, String CourseType,
			Integer Uplimit, String Introduction, String Note) {
		this.Users = Users;
		this.Topic = Topic;
		this.TopicType = TopicType;
		this.TeacherId = TeacherId;
		this.Teacher = Teacher;
		this.Place = Place;
		this.DateFrom = DateFrom;
		this.DateTo = DateTo;
		this.TimeFrom = TimeFrom;
		this.TimeTo = TimeTo;
		this.Credit = Credit;
		this.ClassType = ClassType;
		this.CourseType = CourseType;
		this.Uplimit = Uplimit;
		this.Introduction = Introduction;
		this.Note = Note;

	}

	@Id
	@Column(name = "COURSEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getCourseId() {
		return CourseId;
	}

	public void setCourseId(int courseId) {
		CourseId = courseId;
	}

	@Column(name = "TOPIC")
	public String getTopic() {
		return Topic;
	}

	public void setTopic(String topic) {
		Topic = topic;
	}

	@Column(name = "TOPICTYPE")
	public String getTopicType() {
		return TopicType;
	}

	public void setTopicType(String topicType) {
		TopicType = topicType;
	}

	@Column(name = "TEACHERID")
	public String getTeacherId() {
		return TeacherId;
	}

	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}

	@Column(name = "TEACHER")
	public String getTeacher() {
		return Teacher;
	}

	public void setTeacher(String teacher) {
		Teacher = teacher;
	}

	@Column(name = "PLACE")
	public String getPlace() {
		return Place;
	}

	public void setPlace(String place) {
		Place = place;
	}

	@Column(name = "DATEFROM")
	public Date getDateFrom() {
		return DateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		DateFrom = dateFrom;
	}

	@Column(name = "DATETO")
	public Date getDateTo() {
		return DateTo;
	}

	public void setDateTo(Date dateTo) {
		DateTo = dateTo;
	}

	@Column(name = "TIMEFROM")
	public Timestamp getTimeFrom() {
		return TimeFrom;
	}

	public void setTimeFrom(Timestamp timeFrom) {
		TimeFrom = timeFrom;
	}

	@Column(name = "TIMETO")
	public Timestamp getTimeTo() {
		return TimeTo;
	}

	public void setTimeTo(Timestamp timeTo) {
		TimeTo = timeTo;
	}

	@Column(name = "CREDIT")
	public int getCredit() {
		return Credit;
	}

	public void setCredit(int credit) {
		Credit = credit;
	}

	@Column(name = "CLASSTYPE")
	public String getClassType() {
		return ClassType;
	}

	public void setClassType(String classType) {
		ClassType = classType;
	}

	@Column(name = "COURSETYPE")
	public String getCourseType() {
		return CourseType;
	}

	public void setCourseType(String courseType) {
		CourseType = courseType;
	}

	@Column(name = "UPLIMIT")
	public int getUplimit() {
		return Uplimit;
	}

	public void setUplimit(int uplimit) {
		Uplimit = uplimit;
	}

	@Column(name = "INTRODUCTION")
	public String getIntroduction() {
		return Introduction;
	}

	public void setIntroduction(String introduction) {
		Introduction = introduction;
	}

	@Column(name = "NOTE")
	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "EmployeeID")
//	public Users getUsers() {
//		return Users;
//	}
//
//	public void setUsers(Users users) {
//		Users = users;
//	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USERCOURSE", joinColumns = { @JoinColumn(name = "COURSEID") }, 
	inverseJoinColumns = {@JoinColumn(name = "EMPLOYEEID") })
	public Set<Users> getMember() {
		return member;
	}

	public void setMember(Set<Users> member) {
		this.member = member;
	}

	

	
	

}
