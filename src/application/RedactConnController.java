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

/* �����:
 *  * Save_Con_Click - ���������� �����������.
 * ���������:
 * initialize - ������������� ������;
 * ���������� ���������:
 * host - ���� ��;
 * port - ���� ��;
 * database - �������� ��;
 * log_database - ����� ��;
 * pas_database - ������ ��.
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

	/* ����� ��������� �����������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * text - ��������� �����������;
	 * stage - ��������� ������ Stage;
	 * e - ����� ������ ������ ����������.
	 */
	@FXML
	void Save_Con_Click(MouseEvent event) {
		if(host.getText() != "" && port.getText() != "" && database.getText() != "" && log_database.getText() != "")
		{
			String text = host.getText() + "\n" + port.getText() + "\n" + database.getText() + "\n" + log_database.getText() + "\n" + pas_database.getText();
			try {
				Files.write(Paths.get("Connection.txt"),text.getBytes());
			} catch (IOException e) {
				ApplicationUtils.alerterror("������", "�� ������� �������� ������");
			}
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.close();
		}
		else
		{
			ApplicationUtils.alerterror("������", "��������� ��� ����");
		}
	}

	/* ��������� ������������� ������
	 * ���������� ���������:
	 * arg1 - URL-����� ������;
	 * arg0 - ����� ��������, ���������������
	 * ��� �������� ��������.
	 * ��������� ����������:
	 * file - ���� � ������� � �����������;
	 * admin_win - ���� ������ FXMLLoader;
	 * e - ����� ������� ������� ����������.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			File file = new File(Paths.get("Connection.txt").toUri());
			if(!file.exists() && !file.isFile())
			{
				ApplicationUtils.alertinformation("��������", "���� � ����������� �� ��� ������");
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
			ApplicationUtils.alerterror("������", "���� �� ��� ������");
			return;

		} catch (IOException e) {
			ApplicationUtils.alerterror("������", "������������ ���� ������");
			return;
		}
	}
}
