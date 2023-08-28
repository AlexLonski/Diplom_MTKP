package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Функции:
 * Getters - вывод данных;
 * Setters - ввод данных;
 * getSQL - вывод всех параметров под
 * SQL-запрос;
 * Глобальные параметры:
 * id, fio, desiredPosition, jobtime, 
 * yourSalary, city, birhday, aboutme,
 * achievs, skills, addInfo, workExp,
 * anotherLanguage - параметры таблицы
 * 'resume'.
 */

public class Resume  {

	private Integer id;
	private String fio;
	private String desiredPosition;
	private String jobTime;
	private long yourSalary;
	private String city;
	private Date birhday;
	private String email;
	private String aboutMe;
	private String achievs;
	private String skills;
	private String addInfo;
	private String workExp;
	private String anotherLanguage;

	//Конструктор
	public Resume(Integer id, String fio, String desiredPosition, String jobTime, long yourSalary, String city,
			Date birhday, String email, String aboutMe, String achievs, String skills, String addInfo, String workExp,
			String anotherLanguage) {
		super();
		this.id = id;
		this.fio = fio;
		this.desiredPosition = desiredPosition;
		this.jobTime = jobTime;
		this.yourSalary = yourSalary;
		this.city = city;
		this.birhday = birhday;
		this.email = email;
		this.aboutMe = aboutMe;
		this.achievs = achievs;
		this.skills = skills;
		this.addInfo = addInfo;
		this.workExp = workExp;
		this.anotherLanguage = anotherLanguage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getDesiredPosition() {
		return desiredPosition;
	}

	public void setDesiredPosition(String desiredPosition) {
		this.desiredPosition = desiredPosition;
	}

	public String getJobTime() {
		return jobTime;
	}

	public void setJobTime(String jobTime) {
		this.jobTime = jobTime;
	}

	public long getYourSalary() {
		return yourSalary;
	}

	public void setYourSalary(long yourSalary) {
		this.yourSalary = yourSalary;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getBirhday() {
		return birhday;
	}

	public String get_Birthday_access() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String data = df.format(birhday);
		return data;
	}

	public void setBirhday(Date birhday) {
		this.birhday = birhday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getAchievs() {
		return achievs;
	}

	public void setAchievs(String achievs) {
		this.achievs = achievs;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}

	public String getWorkExp() {
		return workExp;
	}

	public void setWorkExp(String workExp) {
		this.workExp = workExp;
	}

	public String getAnotherLanguage() {
		return anotherLanguage;
	}

	public void setAnotherLanguage(String anotherLanguage) {
		this.anotherLanguage = anotherLanguage;
	}
	String getSQL()
	{
		return "'" +getFio() +"', '"+ getDesiredPosition() +"', '"
				+ getJobTime() +"', '"+ getYourSalary() +"', '"+ getCity() 
				+"', '"+ get_Birthday_access() +"', '"+ getEmail() +"', '"+ getAboutMe() 
				+"', '"+ getAchievs() +"', '"+ getSkills() +"', '"+ getAddInfo() 
				+"', '"+ getWorkExp() +"', '"+ getAnotherLanguage()+"'";
	}

}
