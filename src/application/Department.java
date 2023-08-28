package application;

/* Функции:
 * Getters - вывод данных;
 * Setters - ввод данных;
 * getSQL - вывод всех параметров
 * SQL-запрос;
 * Глобальные параметры:
 * id, location, nameDepart, jobTimeStart,
 * jobTimeEnd, jobWeekStart, 
 * jobWeekend - параметры таблицы 'dapartment'.
 */

public class Department {

	private Integer id;
	private Integer location;
	private String nameDepart;
	private String jobTimeStart;
	private String jobTimeEnd;
	private String jobWeekStart;
	private String jobWeekEnd;

	// Конструктор класса
	public Department(Integer id, Integer location, String nameDepart, String jobTimeStart, String jobTimeEnd, String jobWeekStart, String jobWeekEnd) 
	{
		this.id = id;
		this.location = location;
		this.nameDepart = nameDepart;
		this.jobTimeStart = jobTimeStart;
		this.jobTimeEnd = jobTimeEnd;
		this.jobWeekStart = jobWeekStart;
		this.jobWeekEnd = jobWeekEnd;
	}
	// Конструктор класса
	public Department(Integer location, String nameDepart, String jobTimeStart, String jobTimeEnd, String jobWeekStart, String jobWeekEnd) 
	{
		this.location = location;
		this.nameDepart = nameDepart;
		this.jobTimeStart = jobTimeStart;
		this.jobTimeEnd = jobTimeEnd;
		this.jobWeekStart = jobWeekStart;
		this.jobWeekEnd = jobWeekEnd;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLocation() {
		return this.location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public String getNameDepart() {
		return this.nameDepart;
	}

	public void setNameDepart(String nameDepart) {
		this.nameDepart = nameDepart;
	}


	public String getJobTimeStart() {
		return this.jobTimeStart;
	}

	public void setJobTimeStart(String jobTimeStart) {
		this.jobTimeStart = jobTimeStart;
	}


	public String getJobTimeEnd() {
		return this.jobTimeEnd;
	}

	public void setJobTimeEnd(String jobTimeEnd) {
		this.jobTimeEnd = jobTimeEnd;
	}

	public String getJobWeekStart() {
		return this.jobWeekStart;
	}

	public void setJobWeekStart(String jobWeekStart) {
		this.jobWeekStart = jobWeekStart;
	}


	public String getJobWeekEnd() {
		return this.jobWeekEnd;
	}

	public void setJobWeekEnd(String jobWeekEnd) {
		this.jobWeekEnd = jobWeekEnd;
	}

	String getSQL()
	{
		return "'"+ getNameDepart() +"', '" + getLocation() +"', '" + getJobTimeStart()+"', '" + getJobTimeEnd()+"', '" + getJobWeekStart() +"', '" + getJobWeekEnd() +"'";
	}

}
