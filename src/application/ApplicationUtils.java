package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/* �������:
 * alerterror - ��������� �� ������;
 * alertinformation - ��������� ����������.
 */

public class ApplicationUtils {
	
	/* ������� ��������� �� ������
	 * ���������� ���������:
	 * query - ��������� �������.
	 * ��������� ����������:
	 * stmt - ����� ������ ������ Statement;
	 * result - ����� ������ ������ ResultSet;
	 * con - ����� ������ ������ Connection.
	 * ���������� ResultSet.
	 */
	public static void alerterror (String title, String text) 
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(text);
		alert.showAndWait();
	}

	/* ������� ��������� ����������
	 * ���������� ���������:
	 * query - ��������� �������.
	 * ��������� ����������:
	 * stmt - ����� ������ ������ Statement;
	 * result - ����� ������ ������ ResultSet;
	 * con - ����� ������ ������ Connection.
	 * ���������� ResultSet.
	 */
	public static void alertinformation (String title, String text)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);   
		alert.setHeaderText(text);
		alert.showAndWait();
	}
	
	
	
}
