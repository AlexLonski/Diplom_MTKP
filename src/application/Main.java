package application;

import java.io.File;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;

/* Выпускная квалификационная работа
 * по теме: Автоматизированная система отдела 
 * кадров предприятия 
 * Название: Система отдела кадров предприятия
 * Разработал: Лонский Алексей Васильевич
 * Группа: ТИП-81
 * Дата и номер версии: 27.05.2022 v1.0
 * Язык: Java
 * Краткое описание: Данная программа является
 * информационной системой для отдела кадров
предприятия
 * 
 * Задание:
 * 1) создать базу данных для информационной 
 * системы;
 * 2) создать пользовательскую форму, 
 * соответствующую таблицам базы данных,
 * с возможностью просмотра,
 * добавления, обновления записей в таблицах
 * базы данных, а также импорт резюме 
 * в docx;
 * 3) Создать форму администратора для
 *  изменения в недоступных таблицах;
 * 4) Создать форму авторизации;
 * 5) Создать форму для редактирования подключения с
 * базой данных;
 * 6) Создать отдельную форму для редактирования
 * резюме;
 * 
 * Используемые формы:
 * AutWin - форма авторизации пользователя;
 * RedactConn - форма редактирования подключения;
 * MainWinDepHum - форма главного меню менеджера;
 * RedResume - форма редактирования "Резюме";
 * AdminWin - форма администратора;
 */




public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			// Открытие окна авторизации
			FXMLLoader fxmlLoader = new FXMLLoader(AuthWinController.class.getResource("AuthWin.fxml"));
			Scene scene = new Scene(fxmlLoader.load(),350,300);
			stage.setMinWidth(320);
			stage.setMinHeight(290);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Отдел кадров");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("logo.png")));
			//new Image(new File(Paths.get("Image.ico").toUri()).toString()));
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
//java --module-path "C:\\Java\\javafx-sdk-18.0.1\\lib" --add-modules ALL-MODULE-PATH -jar C:\Java\Workspace\DepHumDip\Myprog.jar
