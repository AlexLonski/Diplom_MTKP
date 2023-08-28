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
/* �������:
 * check_Conn_DB - ��������;
 * selecttable - ������ �� �������
 * �������;
 * insert - ������ �� ����������;
 * update - ������ �� ���������;
 * delete - ������ �� ��������;
 * selectcolumn -������ �� �������
 * ����(��).
 * ���������� ���������:
 * url - ����� �� ������ �����;
 * user - ���� ������;
 * password - ���� ������;
 * host - ���� �������� ������;
 * port - ���������� �����������;
 * database - ���������� ��� ��������
 * ������������ ���� ������.
 */
public class DB {
	private String url;
	private String user;
	private String password;
	
	private String host;
	private String port;
	private String database;

	/* ����������� ������
	 * ��������� ����������:
	 * file - ���� � ������� � �����������;
	 * scan - ����� ������ ������ Scan,
	 * ��� ��������� �������� �� file;
	 * e - ����� ������ ������ ����������.
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
	// ������� �� �������� ������ ��������
	// ���������� ��������� �������� false, 
	// ���� ���� �������� null, � ��������
	// ������ true.
	Boolean check_Conn_DB()
	{
		if (user != null && host != null && port != null && database != null)
			return true;

		return false;
	}

	/* ������� ������� �������
	 * ���������� ���������:
	 * query - ��������� �������.
	 * ��������� ����������:
	 * stmt - ����� ������ ������ Statement;
	 * result - ����� ������ ������ ResultSet;
	 * con - ����� ������ ������ Connection.
	 * ���������� ResultSet.
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

	/* ������� ������� �������
	 * ���������� ���������:
	 * column - �������(���),
	 * �������(��) ����� �����;
	 * query - ��������� �������.
	 * ��������� ����������:
	 * stmt - ����� ������ ������ Statement;
	 * result - ����� ������ ������ ResultSet;
	 * con - ����� ������ ������ Connection.
	 * ���������� ResultSet. 
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

	/* ������� ���������� �������� � �������
	 * ���������� ���������:
	 * table - �������, ������� ���� ��������;
	 * column - ������� �������;
	 * values - �������� ��������.
	 * ��������� ����������:
	 * stmt - ����� ������ ������ Statement;
	 * con - ����� ������ ������ Users.
	 * ���������� ��������� ���������� false, 
	 * ���� ���� �������� null, � ��������
	 * ������ true.
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
	/* ������� ��������� �������� � �������
	 * ���������� ���������:
	 * table - �������, ������� ���� ��������;
	 * query - ��������� �������;
	 * id - ����� �������� � �������.
	 * ��������� ����������:
	 * stmt - ����� ������ ������ Statement;
	 * con - ����� ������ ������ Users.
	 * ���������� ��������� ���������� false, 
	 * ���� ���� �������� null, � ��������
	 * ������ true.
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
	/* ������� �������� �������� � �������
	 * ���������� ���������:
	 * query - ��������� �������.
	 * ��������� ����������:
	 * stmt - ����� ������ ������ Statement;
	 * con - ����� ������ ������ Users.
	 * ���������� ��������� ���������� false, 
	 * ���� ���� �������� null, � ��������
	 * ������ true.
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
