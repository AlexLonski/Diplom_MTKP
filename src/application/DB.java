package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/* Функции:
 * check_Conn_DB - проверка;
 * selecttable - Запрос на выборку
 * таблицы;
 * insert - Запрос на добавление;
 * update - Запрос на изменение;
 * delete - Запрос на удаление;
 * selectcolumn -Запрос на выборку
 * поля(ей).
 * Глобальные параметры:
 * url - Текст на хедере формы;
 * user - поле логина;
 * password - поле пароля;
 * host - поле видимого пароля;
 * port - переменная изображения;
 * database - переменная для проверки
 * переключения поля пароля.
 */
public class DB {
	private String url;
	private String user;
	private String password;
	
	private String host;
	private String port;
	private String database;

	/* Конструктор класса
	 * Локальные переменные:
	 * file - файл с данными о подключении;
	 * scan - новый объект класса Scan,
	 * для получения значений из file;
	 * e - новый объект класса исключений.
	 */
	public DB() {

		File file = new File(Paths.get("Connection.txt").toUri());
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(scan.hasNext())
		{
			host=scan.nextLine();
		} 
		if(scan.hasNext())
		{
			port=scan.nextLine();
		}
		if(scan.hasNext())
		{
			database=scan.nextLine();
		}
		if(scan.hasNext())
		{
			user=scan.nextLine();
		}
		if(scan.hasNext())
		{
			password=scan.nextLine();
		}
		url = "jdbc:mysql://"+host+":"+port+"/"+database;


	}
	// Функция на проверку пустых значений
	// Возвращает результат проверки false, 
	// если есть значения null, в обратном
	// случае true.
	Boolean check_Conn_DB()
	{
		if (user != null && host != null && port != null && database != null)
			return true;

		return false;
	}

	/* Функция выборки таблицы
	 * Формальные параметры:
	 * query - параметры запроса.
	 * Локальные переменные:
	 * stmt - новый объект класса Statement;
	 * result - новый объект класса ResultSet;
	 * con - новый объект класса Connection.
	 * Возвращает ResultSet.
	 */
	ResultSet selecttable(String query) throws SQLException
	{
		Statement stmt = null;
		ResultSet result = null;
		Connection con = null;

		con = DriverManager.getConnection(url,user,password);
		stmt = con.createStatement();
		result = stmt.executeQuery("select * from "+query+"");

		return result;
	}

	/* Функция выборки столбца
	 * Формальные параметры:
	 * column - столбец(бцы),
	 * который(ые) нужно найти;
	 * query - параметры запроса.
	 * Локальные переменные:
	 * stmt - новый объект класса Statement;
	 * result - новый объект класса ResultSet;
	 * con - новый объект класса Connection.
	 * Возвращает ResultSet. 
	 */
	ResultSet selectcolumn(String column, String query) throws SQLException
	{
		Statement stmt = null;
		ResultSet result = null;
		Connection con = null;

		con = DriverManager.getConnection(url,user,password);
		stmt = con.createStatement();
		result = stmt.executeQuery("select '"+column+"' from "+query+"");

		return result;
	}

	/* Функция добавления значений в столбцы
	 * Формальные параметры:
	 * table - таблица, которую надо изменить;
	 * column - столбцы таблицы;
	 * values - значения столбцов.
	 * Локальные переменные:
	 * stmt - новый объект класса Statement;
	 * con - новый объект класса Users.
	 * Возвращает результат добавления false, 
	 * если есть значения null, в обратном
	 * случае true.
	 */
	Boolean insert( String table, String colunms, String values) throws SQLException
	{
		Statement stmt = null;
		Connection con = null;
		con = DriverManager.getConnection(url,user,password);
		stmt = con.createStatement();
		stmt.executeUpdate("Insert into "+table+"("+colunms+") values("+values+");");
		con.close();

		return true;
	}
	/* Функция изменения значений в таблице
	 * Формальные параметры:
	 * table - таблица, которую надо изменить;
	 * query - параметры запроса;
	 * id - номер значения в таблице.
	 * Локальные переменные:
	 * stmt - новый объект класса Statement;
	 * con - новый объект класса Users.
	 * Возвращает результат добавления false, 
	 * если есть значения null, в обратном
	 * случае true.
	 */
	Boolean update( String table, String query, String id) throws SQLException
	{
		Statement stmt = null;
		Connection con = null;

		con = DriverManager.getConnection(url,user,password);
		stmt = con.createStatement();
		stmt.executeUpdate("Update "+table+" set "+query+" where id = " + id + ";");
		con.close();

		return true;
	}
	/* Функция удаления значения в таблице
	 * Формальные параметры:
	 * query - параметры запроса.
	 * Локальные переменные:
	 * stmt - новый объект класса Statement;
	 * con - новый объект класса Users.
	 * Возвращает результат добавления false, 
	 * если есть значения null, в обратном
	 * случае true.
	 */
	Boolean delete( String query) throws SQLException
	{
		Statement stmt = null;
		Connection con = null;

		con = DriverManager.getConnection(url,user,password);
		stmt = con.createStatement();
		stmt.executeUpdate("Delete from "+query+";");
		con.close();

		return true;
	}

}
