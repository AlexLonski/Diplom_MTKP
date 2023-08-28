package application;

/* Функции:
 * Getters - вывод данных;
 * Setters - ввод данных;
 * getSQL - вывод всех параметров под
 * SQL-запрос;
 * Глобальные параметры:
 * id, login, pass,
 * role - параметры таблицы 'users'.
 */

public class Users {

	private Integer id;
	private String login;
	private String pass;
	private String role;

	// Конструктор
	public Users(Integer id, String login, String pass, String role) {
		this.id = id;
		this.login = login;
		this.pass = pass;
		this.role = role;
	}

	// Конструктор
	public Users( String login, String pass, String role) {
		this.login = login;
		this.pass = pass;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	String getSQL()
	{
		return "'" +getLogin() +"', '"+ getPass() +"', '"+ getRole() +"'";
	}


}
