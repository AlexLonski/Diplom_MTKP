package application;

import java.util.Date;

/* Функции:
 * Getters - вывод данных;
 * Setters - ввод данных;
 * getSQL - вывод всех параметров
 * SQL-запрос;
 * Глобальные параметры:
 * id, employee, archiveName, archiveType,
 * dateCreate, moreInfo - параметры
 * таблицы 'archive'.
 */

public class Archive{

	private Long id;
	private Integer employee;
	private String archiveName;
	private String archiveType;
	private Date dateCreate;
	private String moreInfo;

	// Конструктор класса
	public Archive(Long id, Integer employee, String archiveName, String archiveType, Date dateCreate, String moreInfo) 
	{
		super();
		this.id = id;
		this.employee = employee;
		this.archiveName = archiveName;
		this.archiveType = archiveType;
		this.dateCreate = dateCreate;
		this.moreInfo = moreInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEmployee() {
		return employee;
	}

	public void setEmployee(Integer employee) {
		this.employee = employee;
	}

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	public String getArchiveType() {
		return archiveType;
	}

	public void setArchiveType(String archiveType) {
		this.archiveType = archiveType;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	String getSQL()
	{
		return "'" + getEmployee() + "', '"+ getArchiveName() +"', '" + getArchiveType() +"', '" + getDateCreate() + "', '" + getMoreInfo()+"'";
	}

}