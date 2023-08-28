package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.Node;
import javafx.scene.Scene;

/* Методы:
 * Setings_Click - Настройки подключения;
 * AuthClck - Авторизация пользователя.
 * Процедуры:
 * checkbox_click - Переключение пароля
 * на видимый режим.
 * Глобальные параметры:
 * Labtop - Текст на хедере формы;
 * TB_Login - поле логина;
 * TB_Pass - поле пароля;
 * TB_Pass_copy - поле видимого пароля;
 * image_check - переменная изображения;
 * check - переменная для проверки переключения
 * поля пароля;
 * users - переменная для заполнения list_user.
 */


public class AuthWinController {

	@FXML
	private Label Labtop;
	@FXML
	private TextField TB_Login;
	@FXML
	private PasswordField TB_Pass;
	@FXML
	private TextField TB_Pass_copy;
	@FXML
	private ImageView image_check;


	private boolean check = false;
	ObservableList<Users> users = FXCollections.observableArrayList();


	/* Метод авторизации пользователя
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * db - новый объект класса DB;
	 * list_user - новый объект класса Users;
	 * stage - новый объект класса Stage;
	 * win - новый объект класса FXMLLoader;
	 * ex - новые объекты классов исключений.
	 */
	@FXML
	void AuthClck(ActionEvent event) throws IOException {
		if(check == true)
			TB_Pass.setText(TB_Pass_copy.getText().toString());
		else
			TB_Pass_copy.setText(TB_Pass.getText().toString());


		if (TB_Login.getText() != "" && TB_Pass.getText() != "") 
		{    
			DB db = new DB();
			if(db.check_Conn_DB())
			{
				Users list_user = null;
				users.clear();
				try {
					ResultSet result = db.selecttable("users Where login ='"+TB_Login.getText()+"' and pass = '"+TB_Pass.getText()+"'");
					if(result != null)
					{
						while (result.next()) {
							list_user = new Users(result.getInt("id"), result.getString("login"), result.getString("pass"), result.getString("role"));
							users.add(list_user);
						}
					}else {
						ApplicationUtils.alerterror("Ошибка", "Проверьте подключение к базе данных");
						return;
					}

				}
				catch (SQLException ex) {
					ApplicationUtils.alerterror("Ошибка", "Проверьте подключение к базе данных");
					return;
				}

				Stage stage = new Stage();
				FXMLLoader win;
				try {
					if(users.isEmpty()) {
						ApplicationUtils.alertinformation("Внимание", "Введите правильно логин и пароль.");
						return;
					}else
						if(list_user.getRole().equals("Администратор"))
						{
							win = new FXMLLoader(AdminWinController.class.getResource("AdminWin.fxml"));
							stage = (Stage)((Node)event.getSource()).getScene().getWindow();
							stage.setScene(new Scene(win.load(),1111,700));
							stage.setMinWidth(1111);
							stage.setMinHeight(665);
							stage.setTitle("Меню Администратора");
							stage.centerOnScreen();
							stage.show();
						}
						else
						{
							if(list_user.getRole().equals("HR-специалист"))
							{
								win = new FXMLLoader(MainWinDepHumController.class.getResource("MainWinDepHum.fxml"));
								stage = (Stage)((Node)event.getSource()).getScene().getWindow();
								stage.setScene(new Scene (win.load(),945, 700));
								stage.setMinWidth(945);
								stage.setMinHeight(700);
								stage.setTitle("Меню Менеджера");
								stage.centerOnScreen();
								stage.show(); 
							}
							else ApplicationUtils.alertinformation("Внимание", "Доступ запрещён");
						}
				} catch (Exception ex) {
					ApplicationUtils.alerterror("Ошибка", "Открытие меню не удалось");
					return;
				}
			} else
				ApplicationUtils.alerterror("Ошибка", "Заполните подключение к базе данных");

		} else {
			ApplicationUtils.alertinformation("Данные", "Введите логин или пароль");
			return;
		}


	}


	/* Метод настройки подключения
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * stage - новый объект класса Stage;
	 * admin_win - новый объект класса FXMLLoader;
	 * ex - новый объект класса исключений.
	 */
	@FXML
	void Setings_Click(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		try {
			FXMLLoader admin_win = new FXMLLoader(RedactConnController.class.getResource("RedactConn.fxml"));
			stage.setScene(new Scene(admin_win.load(),300,430));
			stage.setMinWidth(270);
			stage.setMinHeight(370);
			stage.setTitle("Меню Настройки подключения");
			stage.centerOnScreen();
			stage.showAndWait();
		} catch (Exception ex) {
			ApplicationUtils.alerterror("Ошибка", "Открытие меню не удалось");
			return;					
		}
	}


	/* Процедура снемы изображения
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 */
	@FXML
	void checkbox_click(MouseEvent event) {

		if(check == false)
		{
			image_check.setImage(new Image(getClass().getResourceAsStream("unvisible.png")));
			TB_Pass_copy.setText(TB_Pass.getText());	
			TB_Pass_copy.setVisible(true);
			TB_Pass.setVisible(false);
			check = true;
			return;
		}
		image_check.setImage(new Image(getClass().getResourceAsStream("visible.png")));
		check = false;
		TB_Pass.setText(TB_Pass_copy.getText());
		TB_Pass_copy.setVisible(false);
		TB_Pass.setVisible(true);
	}


}

