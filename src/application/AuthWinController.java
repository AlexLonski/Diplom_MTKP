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

/* ������:
 * Setings_Click - ��������� �����������;
 * AuthClck - ����������� ������������.
 * ���������:
 * checkbox_click - ������������ ������
 * �� ������� �����.
 * ���������� ���������:
 * Labtop - ����� �� ������ �����;
 * TB_Login - ���� ������;
 * TB_Pass - ���� ������;
 * TB_Pass_copy - ���� �������� ������;
 * image_check - ���������� �����������;
 * check - ���������� ��� �������� ������������
 * ���� ������;
 * users - ���������� ��� ���������� list_user.
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


	/* ����� ����������� ������������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * db - ����� ������ ������ DB;
	 * list_user - ����� ������ ������ Users;
	 * stage - ����� ������ ������ Stage;
	 * win - ����� ������ ������ FXMLLoader;
	 * ex - ����� ������� ������� ����������.
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
						ApplicationUtils.alerterror("������", "��������� ����������� � ���� ������");
						return;
					}

				}
				catch (SQLException ex) {
					ApplicationUtils.alerterror("������", "��������� ����������� � ���� ������");
					return;
				}

				Stage stage = new Stage();
				FXMLLoader win;
				try {
					if(users.isEmpty()) {
						ApplicationUtils.alertinformation("��������", "������� ��������� ����� � ������.");
						return;
					}else
						if(list_user.getRole().equals("�������������"))
						{
							win = new FXMLLoader(AdminWinController.class.getResource("AdminWin.fxml"));
							stage = (Stage)((Node)event.getSource()).getScene().getWindow();
							stage.setScene(new Scene(win.load(),1111,700));
							stage.setMinWidth(1111);
							stage.setMinHeight(665);
							stage.setTitle("���� ��������������");
							stage.centerOnScreen();
							stage.show();
						}
						else
						{
							if(list_user.getRole().equals("HR-����������"))
							{
								win = new FXMLLoader(MainWinDepHumController.class.getResource("MainWinDepHum.fxml"));
								stage = (Stage)((Node)event.getSource()).getScene().getWindow();
								stage.setScene(new Scene (win.load(),945, 700));
								stage.setMinWidth(945);
								stage.setMinHeight(700);
								stage.setTitle("���� ���������");
								stage.centerOnScreen();
								stage.show(); 
							}
							else ApplicationUtils.alertinformation("��������", "������ ��������");
						}
				} catch (Exception ex) {
					ApplicationUtils.alerterror("������", "�������� ���� �� �������");
					return;
				}
			} else
				ApplicationUtils.alerterror("������", "��������� ����������� � ���� ������");

		} else {
			ApplicationUtils.alertinformation("������", "������� ����� ��� ������");
			return;
		}


	}


	/* ����� ��������� �����������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * stage - ����� ������ ������ Stage;
	 * admin_win - ����� ������ ������ FXMLLoader;
	 * ex - ����� ������ ������ ����������.
	 */
	@FXML
	void Setings_Click(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		try {
			FXMLLoader admin_win = new FXMLLoader(RedactConnController.class.getResource("RedactConn.fxml"));
			stage.setScene(new Scene(admin_win.load(),300,430));
			stage.setMinWidth(270);
			stage.setMinHeight(370);
			stage.setTitle("���� ��������� �����������");
			stage.centerOnScreen();
			stage.showAndWait();
		} catch (Exception ex) {
			ApplicationUtils.alerterror("������", "�������� ���� �� �������");
			return;					
		}
	}


	/* ��������� ����� �����������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
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

