package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/* Функции:
 * alerterror - сообщение об ошибки;
 * alertinformation - сообщение информации.
 */

public class ApplicationUtils {
	
	/* Функция сообщения об ошибке
	 * Формальные параметры:
	 * query - параметры запроса.
	 * Локальные переменные:
	 * stmt - новый объект класса Statement;
	 * result - новый объект класса ResultSet;
	 * con - новый объект класса Connection.
	 * Возвращает ResultSet.
	 */
	public static void alerterror (String title, String text) 
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(text);
		alert.showAndWait();
	}

	/* Функция сообщения информации
	 * Формальные параметры:
	 * query - параметры запроса.
	 * Локальные переменные:
	 * stmt - новый объект класса Statement;
	 * result - новый объект класса ResultSet;
	 * con - новый объект класса Connection.
	 * Возвращает ResultSet.
	 */
	public static void alertinformation (String title, String text)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);   
		alert.setHeaderText(text);
		alert.showAndWait();
	}
	
	
	
}
