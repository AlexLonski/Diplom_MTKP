package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/* Метод:
 *  * Save_Con_Click - Сохранение подключения.
 * Процедуры:
 * initialize - Инициализация данных;
 * Глобальные параметры:
 * host - хост БД;
 * port - порт БД;
 * database - название БД;
 * log_database - логин БД;
 * pas_database - пароль БД.
 */

public class RedactConnController implements Initializable{
	@FXML
	private TextField host;
	@FXML
	private TextField port;
	@FXML
	private TextField database;
	@FXML
	private TextField log_database;
	@FXML
	private TextField pas_database;

	/* Метод настройки подключения
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * text - параметры подключения;
	 * stage - экземпляр класса Stage;
	 * e - новый объект класса исключений.
	 */
	@FXML
	void Save_Con_Click(MouseEvent event) {
		if(host.getText() != "" && port.getText() != "" && database.getText() != "" && log_database.getText() != "")
		{
			String text = host.getText() + "\n" + port.getText() + "\n" + database.getText() + "\n" + log_database.getText() + "\n" + pas_database.getText();
			try {
				Files.write(Paths.get("Connection.txt"),text.getBytes());
			} catch (IOException e) {
				ApplicationUtils.alerterror("Ошибка", "Не удалось записать данные");
			}
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.close();
		}
		else
		{
			ApplicationUtils.alerterror("Ошибка", "Заполните все поля");
		}
	}

	/* Процедура инициализации данных
	 * Формальные параметры:
	 * arg1 - URL-адрес класса;
	 * arg0 - пакет ресурсов, предназначенный
	 * для хранения объектов.
	 * Локальные переменные:
	 * file - файл с данными о подключении;
	 * admin_win - новы объект FXMLLoader;
	 * e - новые объекты классов исключений.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			File file = new File(Paths.get("Connection.txt").toUri());
			if(!file.exists() && !file.isFile())
			{
				ApplicationUtils.alertinformation("Внимание", "Файл с подключенем не был найден");
				file = new File("Connection.txt");
				file.createNewFile();
				return;
			}

			Scanner scan = new Scanner(file);
			if(scan.hasNext())
			{
				host.setText(scan.nextLine());
			} 
			if(scan.hasNext())
			{
				port.setText(scan.nextLine());
			}
			if(scan.hasNext())
			{
				database.setText(scan.nextLine());
			}
			if(scan.hasNext())
			{
				log_database.setText(scan.nextLine());
			}
			if(scan.hasNext())
			{
				pas_database.setText(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			ApplicationUtils.alerterror("Ошибка", "Файл не был найден");
			return;

		} catch (IOException e) {
			ApplicationUtils.alerterror("Ошибка", "Неправильный ввод данных");
			return;
		}
	}
}
