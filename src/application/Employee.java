package application;

/* Функции:
 * Getters - вывод данных;
 * Setters - ввод данных;
 * getSQL, getSQL_main - вывод всех
 * параметров под SQL-запрос;
 * Глобальные параметры:
 * id, department, resume, fio, post,
 * gender, salary, famStatus, passport, 
 * email, isFired - параметры таблицы 'employee'.
 */

public class Employee {

	private Integer id;
	private Integer department;
	private Integer resume;
	private String fio;
	private String post;
	private String gender;
	private long salary;
	private String famStatus;
	private String passport;
	private String email;
	private Boolean isFired;

	// Конструктор класса
	public Employee(Integer id,  String fio, Integer department, Integer resume, String post, String gender, long salary,
			String famStatus, String passport, String email, Boolean isFired) {
		this.id = id;
		this.department = department;
		this.resume = resume;
		this.fio = fio;
		this.post = post;
		this.gender = gender;
		this.salary = salary;
		this.famStatus = famStatus;
		this.passport = passport;
		this.email = email;
		this.isFired = isFired;
	}

	// Конструктор класса
	public Employee( String fio, Integer department, Integer resume, String post, String gender, long salary,
			String famStatus, String passport, String email, Boolean isFired) {
		this.fio = fio;
		this.department = department;
		this.resume = resume;
		this.post = post;
		this.gender = gender;
		this.salary = salary;
		this.famStatus = famStatus;
		this.passport = passport;
		this.email = email;
		this.isFired = isFired;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public Integer getResume() {
		return resume;
	}

	public void setResume(Integer resume) {
		this.resume = resume;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getFamStatus() {
		return famStatus;
	}

	public void setFamStatus(String famStatus) {
		this.famStatus = famStatus;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsFired() {
		return isFired;
	}

	public void setIsFired(Boolean isFired) {
		this.isFired = isFired;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	String getSQL()
	{
		return "'"+getDepartment() +"', '"+ getFio() +"', '"+ getPost() +"', '"
				+ getResume() +"', '"+ getGender() +"', '"+ getSalary() 
				+"', '"+ getFamStatus() +"', '"+ getPassport() 
				+"', '"+ getEmail() +"', "+ getIsFired()+"";
	}
	String getSQL_main()
	{
		return "'"+getDepartment() +"', '"+ getFio() +"', '"+ getPost() +"', '"
				+ getResume() +"', '"+ getGender() +"', '"+ getSalary() 
				+"', '"+ getFamStatus() +"', '"+ getPassport() 
				+"', '"+ getEmail() +"', "+false+"";
	}
}
