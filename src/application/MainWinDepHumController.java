package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/* ������:
 * TV_Mouse_Click - ����� ���������� �����;
 * Upd_employee_Click, Add_employee_Click,
 * Delete_employee_Click,
 * Redact_resume_Click - ������ ����������,
 * ��������� ������;
 * CB_action, Search_Depart_Click, 
 * Search_Resume_Click, 
 * Search_arth_Click - ������ ������ ������;
 * Clear_Selection_Click, �lear_Selected_Derart_Click, 
 * Clear_Selected_Resume_Click,
 * Clear_Selected_Arth_Click - ������ ������� �����;
 * Save_res_word_Click - ����� ���������� ���������;
 * �������:
 * set_TV_Resume, set_TV_Archive, set_TV_Employee,
 * set_TV_Department - ������� ������������� ������;
 * setSingleLineSpacing - ��������� ��������� �����
 * ���������;
 * inializible - ��������� ������������� ������;
 * ���������� ���������:
 * 
 * TV_Archive - TableView ������ Archive;
 * tv_arch_id, tv_arch_emp, tv_arch_name, tv_arch_type,
 * tv_arch_date, tv_arch_info - ������� TV_Archive;
 * selected_arth_emp, selected_arth_type - ���� ��� 
 * ������ � ����������; 
 * 
 * TV_Resume - TableView ������ Resume;
 * tv_resume_id, tv_resume_fio, tv_resume_des_post,
 * tv_resume_job_time, tv_resume_des_sal,
 * tv_resume_city, tv_resume_birth,
 * tv_resume_email - ������� TV_Resume;
 * selected_resume_id - ���� ��� ������;
 * 
 * TV_employ - TableView ������ Employee;
 * tv_id, tv_fio, tv_department, tv_post, tv_res,
 * tv_gend, tv_sal, tv_sem_stat, tv_passpr,
 * tv_email - ������� TV_employ;
 * employee_id, tf_fio, tf_post, tf_department,
 * tf_resume, cb_gender, tf_salary, tf_sem_stat,
 * tf_passport, tf_email - ���� ��� ������;
 * tf_id_search, tf_passport_search, cb_select_gender,
 * cb_select_post, cb_select_fam_stat - ���� ��� 
 * ������ � ����������;
 * 
 * TV_Depart - TableView ������ Department;
 * tv_depart_id, tv_depart_name, tv_depart_loc,
 * tv_depart_time_start, tv_depart_time_end,
 * tv_depart_date_start, 
 * tv_depart_date_end - ������� TV_Depart;
 * selected_depart_id -���� ��� ������;
 * 
 * department, resume, archive,employee - List(�)
 * ��� ��������� ������;
 * gender_items, post_items, fam_stat_items,
 * weeks_items, arch_type_items - ��������� ���
 * ������������� ��������;
 * db - ����� ������ ������ DB.
 */

public class MainWinDepHumController extends AuthWinController implements Initializable, Controller {

	@FXML
	private TableView<Archive> TV_Archive;

	@FXML
	private TableColumn<Archive, Number> tv_arch_id;
	@FXML
	private TableColumn<Archive, Number> tv_arch_emp;
	@FXML
	private TableColumn<Archive, String> tv_arch_name;
	@FXML
	private TableColumn<Archive, String> tv_arch_type;
	@FXML
	private TableColumn<Archive, Date> tv_arch_date;
	@FXML
	private TableColumn<Archive, String> tv_arch_info;

	@FXML
	private TextField selected_arth_emp;
	@FXML
	private ComboBox<String> selected_arth_type;


	@FXML
	private TableView<Resume> TV_Resume;
	@FXML
	private TableColumn<Resume,Number> tv_resume_id;
	@FXML
	private TableColumn<Resume,String> tv_resume_fio;
	@FXML
	private TableColumn<Resume,String> tv_resume_des_post;
	@FXML
	private TableColumn<Resume,String> tv_resume_job_time;
	@FXML
	private TableColumn<Resume,Number> tv_resume_dis_sal;
	@FXML
	private TableColumn<Resume,String> tv_resume_city;
	@FXML
	private TableColumn<Resume,Date> tv_resume_birth;
	@FXML
	private TableColumn<Resume,String> tv_resume_email;

	@FXML
	private TextField selected_resume_id;


	@FXML
	private TableView<Employee> TV_employ;
	@FXML
	private TableColumn<Employee, Number> tv_id;
	@FXML
	private TableColumn<Employee, String> tv_fio;
	@FXML
	private TableColumn<Employee, Number> tv_department;
	@FXML
	private TableColumn<Employee, String> tv_post;
	@FXML
	private TableColumn<Employee, Number> tv_res;
	@FXML
	private TableColumn<Employee, String> tv_gend;
	@FXML
	private TableColumn<Employee, Number> tv_sal;
	@FXML
	private TableColumn<Employee, String> tv_sem_stat;
	@FXML
	private TableColumn<Employee, String> tv_passpr;
	@FXML
	private TableColumn<Employee, String> tv_email;


	private Integer employee_id;
	@FXML
	private TextField tf_fio;
	@FXML
	private TextField tf_post;
	@FXML
	private TextField tf_department;
	@FXML
	private TextField tf_resume;
	@FXML
	private ComboBox<String> cb_gender;
	@FXML
	private TextField tf_salary;
	@FXML
	private TextField tf_sem_stat;
	@FXML
	private TextField tf_passport;
	@FXML
	private TextField tf_email;

	@FXML
	private TextField tf_id_search;
	@FXML
	private TextField tf_passport_search;
	@FXML
	private ComboBox<String> cb_select_gender;
	@FXML
	private ComboBox<String> cb_select_post;
	@FXML
	private ComboBox<String> cb_select_fam_stat;


	@FXML
	private TableView<Department> TV_Depart;
	@FXML
	private TableColumn<Department,Number> tv_depart_id;
	@FXML
	private TableColumn<Department,String> tv_depart_name;
	@FXML
	private TableColumn<Department,Number> tv_depart_loc;
	@FXML
	private TableColumn<Department,String> tv_depart_time_start;
	@FXML
	private TableColumn<Department,String> tv_depart_time_end;
	@FXML
	private TableColumn<Department,String> tv_depart_date_start;
	@FXML
	private TableColumn<Department,String> tv_depart_date_end;

	@FXML
	private TextField selected_depart_id;

	ObservableList<Department> department = FXCollections.observableArrayList();
	ObservableList<Resume> resume = FXCollections.observableArrayList();
	ObservableList<Archive> archive = FXCollections.observableArrayList();
	ObservableList<Employee> employee = FXCollections.observableArrayList();
	ObservableList<String> gender_items = FXCollections.observableArrayList("�����","�������", "�������");
	ObservableList<String> post_items = FXCollections.observableArrayList("�����","�����������", "��������", "��������", "�������������");
	ObservableList<String> fam_stat_items = FXCollections.observableArrayList("�����","�������", "�����", "�� �����", "�� �������");
	ObservableList<String> weeks_items = FXCollections.observableArrayList("�����","�����������","�������", "�����", "�������", "�������", "�������");
	ObservableList<String> arch_type_items = FXCollections.observableArrayList("�����","����������", "����������", "�����������");
	DB db = new DB();
	@FXML

	/* ����� ���������� ��������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * TV_stroka - ����� ������ ������ Employee,
	 * �������� �� TableView;
	 */
	void TV_Mouse_Click(MouseEvent event) {

		// �������� �� ����� �������
		if (TV_employ.getSelectionModel().getSelectedItem() != null) {

			// �������� �������� ������� � ������ ������
			Employee TV_stroka = (Employee) TV_employ.getSelectionModel().getSelectedItem();

			// ��������� ����������
			employee_id = TV_stroka.getId();
			tf_fio.setText(TV_stroka.getFio());
			tf_post.setText(TV_stroka.getPost());
			tf_department.setText(TV_stroka.getDepartment().toString());
			tf_resume.setText(TV_stroka.getResume().toString());
			cb_gender.setValue(TV_stroka.getGender());
			tf_salary.setText((TV_stroka.getSalary()) + "");
			tf_sem_stat.setText(TV_stroka.getFamStatus());
			tf_passport.setText(TV_stroka.getPassport());
			tf_email.setText(TV_stroka.getEmail());
		}
	}

	/* ����� ��������� ��������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * emp - ����� ������ ������ Employee,
	 * ����� �������� �� �����;
	 * str - ����� ������ ������ Employee,
	 * ������ �������� �� �������;
	 * calendar - ����� ������ ������ �alendar;
	 * e - ����� ������� ������ ����������.
	 */
	@FXML
	void Upd_employee_Click(ActionEvent event) {
		if (tf_fio.getText() != "" && tf_post.getText() != "" && tf_department.getText() != "" && cb_gender.getValue() != "" && tf_salary.getText() != ""&& tf_sem_stat.getText() != "" && tf_passport.getText() != "" && tf_resume.getText() != "" && TV_employ.getSelectionModel().getSelectedItem() != null) 
		{
			Employee emp;
			try
			{
				emp = new Employee(employee_id, tf_fio.getText(), Integer.parseInt(tf_department.getText()), Integer.parseInt(tf_resume.getText()), tf_post.getText(), cb_gender.getValue(), Long.parseLong(tf_salary.getText()), tf_sem_stat.getText(), tf_passport.getText(), tf_email.getText(), false);
			}
			catch(NumberFormatException e)
			{
				ApplicationUtils.alertinformation("��������", "��������� ���� � �������");
				return;
			}
			Employee str = (Employee) TV_employ.getSelectionModel().getSelectedItem();
			Calendar calendar = Calendar.getInstance();
			try {
				if((db.selectcolumn("id", "resume_ where id = '"+emp.getResume()+"'")).next() && (db.selectcolumn("id", "department where id = '"+emp.getDepartment()+"'")).next())
				{
					if(!(db.selectcolumn("id_resume", "employee where id_resume = '"+emp.getResume()+"'")).next() || emp.getResume() == str.getResume())
					{
						if(db.update("employee", "FIO= '" + emp.getFio()+ "', department_id= '" + emp.getDepartment()+ "', id_resume= '" + emp.getResume().toString() + "', post= '" + emp.getPost() + "', gender= '" + emp.getGender() + "', salary= '" + emp.getSalary() + "', Fam_status= '" + emp.getFamStatus()+ "', passport= '" + emp.getPassport() +  "', email= '" + emp.getEmail() + "'", emp.getId().toString()))
						{
							if(!emp.getDepartment().equals(str.getDepartment()))
								db.insert("archive", "id_employee,archive_name,archive_type,date_create,more_info", "" + emp.getId() + ", '������', '�����������', '"+calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) +"', '������ ��������� ��� ��������� � "+emp.getDepartment()+" �����������'");
							set_TV_Employee("employee where isFired = 0 or isFired = null");
							set_TV_Archive("archive");
							tf_fio.clear();
							tf_department.clear();
							tf_post.clear();
							tf_resume.clear();
							cb_gender.setValue(null);
							tf_salary.clear();
							tf_sem_stat.clear();
							tf_passport.clear();
							tf_email.clear();
							TV_employ.refresh();
						}

					}else ApplicationUtils.alertinformation("��������", "������ ������ ������ ������ �����������");

				}else ApplicationUtils.alertinformation("��������", "����� ������ ��� ������������ �� ����������");

			} catch (SQLException e) {
				ApplicationUtils.alerterror("������", "�������� � ������������ � ���� ������");
				return;
			}
		} else ApplicationUtils.alertinformation("��������", "�� �� ��������� ����");
	}

	/* ����� ���������� ��������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * emp - ����� ������ ������ Employee,
	 * ����� �������� �� �����;
	 * calendar - ����� ������ ������ �alendar;
	 * e - ����� ������� ������ ����������.
	 */
	@FXML
	void Add_employee_Click(ActionEvent event) {

		if (tf_fio.getText() != "" && tf_post.getText() != "" && tf_department.getText() != "" && cb_gender.getValue() != "" && tf_salary.getText() != ""&& tf_sem_stat.getText() != "" && tf_passport.getText() != "" && tf_resume.getText() != "" && TV_employ.getSelectionModel().getSelectedItem() != null) 
		{
			Employee emp;
			try 
			{
				emp = new Employee(employee_id, tf_fio.getText(), Integer.parseInt(tf_department.getText()), Integer.parseInt(tf_resume.getText()), tf_post.getText(), cb_gender.getValue(), Long.parseLong(tf_salary.getText()), tf_sem_stat.getText(), tf_passport.getText(), tf_email.getText(), false);
			}
			catch(NumberFormatException e)
			{
				ApplicationUtils.alertinformation("��������", "��������� ���� � �������");
				return;
			}
			Calendar calendar = Calendar.getInstance();

			try {
				if((db.selectcolumn("id", "resume_ where id = '"+emp.getResume()+"'")).next() && (db.selectcolumn("id", "department where id = '"+emp.getDepartment()+"'")).next())
				{
					if(!(db.selectcolumn("id_resume", "employee where id_resume = '"+emp.getResume()+"'")).next())
					{
						if(db.insert("employee","department_id,FIO,post,id_resume,gender,salary,Fam_status,passport,email,isFired",emp.getSQL()))
						{
							db.insert("archive", "id_employee,archive_name,archive_type,date_create,more_info", "" + emp.getId() + ", '������', '����������', '"+calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) +"', '������ ��������� ��� �������� �� ������'");
							set_TV_Employee("employee where isFired = 0 or isFired = null");
							set_TV_Archive("archive");
							tf_fio.clear();
							tf_department.clear();
							tf_post.clear();
							tf_resume.clear();
							cb_gender.setValue(null);
							tf_salary.clear();
							tf_sem_stat.clear();
							tf_passport.clear();
							tf_email.clear();
							TV_employ.refresh();

						}
					}else ApplicationUtils.alertinformation("��������", "������ ������ ������ ������ �����������");

				}else ApplicationUtils.alertinformation("��������", "����� ������ ��� ������������ �� ����������");

			} catch (SQLException e) {
				ApplicationUtils.alerterror("������", "�������� � ������������ � ���� ������");
				return;
			}

		} else
			ApplicationUtils.alertinformation("��������", "��������� ���� ��� ����������");

	}

	/* ����� ���������� ����������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * TV_Col - ����� ������ ������ Employee,
	 * �������� �� TableView;
	 * calendar - ����� ������ ������ �alendar;
	 * e - ����� ������ ������ ����������.
	 */
	@FXML
	void Delete_employee_Click(ActionEvent event) {

		Employee TV_col = (Employee)TV_employ.getSelectionModel().getSelectedItem();
		Calendar calendar = Calendar.getInstance();

		// �������� �� ������ ������
		if (TV_employ.getSelectionModel().getSelectedItem() != null) {
			try {
				if(db.update("employee", "isFired  = true" , TV_col.getId().toString()))
				{
					set_TV_Employee("Employee where isFired = 0 or isFired = null");

					db.insert("archive", "id_employee,archive_name,archive_type,date_create,more_info", "" + TV_col.getId() + ", '������', '����������', '"+calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) +"', '������ ��������� ��� ������'");

					set_TV_Archive("archive");
					tf_fio.clear();
					tf_department.clear();
					tf_post.clear();
					tf_resume.clear();
					cb_gender.setValue(null);
					tf_salary.clear();
					tf_sem_stat.clear();
					tf_passport.clear();
					tf_email.clear();
					TV_employ.refresh();			
				}
			} catch (SQLException e) {
				ApplicationUtils.alerterror("������", "�������� � ������������ � ���� ������");
				return;
			}

		}else
			ApplicationUtils.alertinformation("��������", "�������� ������");
	}

	/* ����� ���������� � ������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * query - ��������� �������;
	 */
	@FXML
	void CB_action(ActionEvent event) {

		String query = "employee where isFired = 0";

		if (cb_select_gender.getSelectionModel().getSelectedItem() != "�����") query += " and gender = '"+cb_select_gender.getValue().toString()+"'";
		if (cb_select_post.getSelectionModel().getSelectedItem() != "�����") query += " and post = '"+cb_select_post.getValue().toString()+"'";
		if (cb_select_fam_stat.getSelectionModel().getSelectedItem() != "�����") query += " and Fam_status = '"+cb_select_fam_stat.getValue().toString()+"'";
		if (tf_id_search.getText() != "") query += " and id = '"+tf_id_search.getText()+"'";
		if (tf_passport_search.getText() != "")	query += " and passport = '"+tf_passport_search.getText()+"'";
		set_TV_Employee(query);
	}

	/* ����� �������� ����� 
	 * ���������� � ������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 */
	@FXML
	void Clear_Selection_Click(ActionEvent event) {
		tf_id_search.setText("");
		tf_passport_search.setText("");
		set_TV_Employee("Employee where isFired = 0 or isFired = null");
	}

	/* ����� ������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * query - ��������� �������;
	 */
	@FXML
	void Search_Depart_Click(MouseEvent event) {
		String query = "department ";
		if(selected_depart_id.getText() != "")
		{
			query += "where id = "+selected_depart_id.getText()+"";
		}

		set_TV_Department(query);
	}

	/* ����� �������� ����� 
	 * ���������� � ������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 */
	@FXML
	void �lear_Selected_Derart_Click(MouseEvent event) {
		selected_depart_id.setText("");
		set_TV_Department("department");
	}

	/* ����� ������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * query - ��������� �������;
	 */
	@FXML
	void Search_Resume_Click(MouseEvent event) {
		String query = "resume_ ";
		if(selected_resume_id.getText() != "")
		{
			query += "where id = "+selected_resume_id.getText()+"";
		}
		set_TV_Resume(query);
	}

	/* ����� �������� ����� 
	 * ���������� � ������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 */
	@FXML
	void Clear_Selected_Resume_Click(MouseEvent event) {
		selected_resume_id.setText("");
		set_TV_Resume("resume_");
	}

	/* ����� ���������� � ������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * query - ��������� �������;
	 */
	@FXML
	void Search_arth_Click(ActionEvent event) {
		String query = "archive ";
		Boolean check_search = false;
		if(selected_arth_emp.getText() != "" && check_search == false)
		{
			query += "where id_employee = '"+selected_arth_emp.getText()+"'";
			check_search = true;
		}else
			if(selected_arth_emp.getText() != "" && check_search == true)
			{
				query += " and id_employee = '"+selected_arth_emp.getText()+"'";
			}
		if (selected_arth_type.getSelectionModel().getSelectedItem() != "�����" && check_search == false)
		{
			query += "where archive_type = '"+selected_arth_type.getValue().toString()+"'";
			check_search = true;
		}else
			if (selected_arth_type.getSelectionModel().getSelectedItem() != "�����" && check_search == true)
			{
				query += " and archive_type = '"+selected_arth_type.getValue().toString()+"'";
			}
		set_TV_Archive(query);

	}

	/* ����� �������� ����� 
	 * ���������� � ������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 */
	@FXML
	void Clear_Selected_Arth_Click(ActionEvent event) {
		selected_arth_emp.setText("");
		selected_arth_type.setValue("�����");
		set_TV_Archive("archive");

	}



	/* ����� ���������� ���������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * file - ���� ���������;
	 * str - ����� ������ Resume ���������
	 * ������ � TV_Resume;
	 * out - ����� ������ FileOutputStream,
	 * ��� ������;
	 * document - ����� ������ XWPFDocument,
	 * ������������� Word ���������;
	 * sectPr -  ����� ������ CTSectPr,
	 * ���������� ����� ������ ���������;
	 * pageMar - ����� ������ CTPageMar,
	 * ���������� ������ �� ���� ����� 
	 * ���������;
	 * title - ����� ������ XWPFParagraph,
	 * ������ ����� ��������;
	 * parag - ����� ������ XWPFParagraph,
	 * ������ ����� ��������;
	 * run - ����� ������ XWPFRun,
	 * ���������� ������� ������;
	 * desktop - ����� ������ Desctop,
	 * ��������� ��������� ����������;
	 * e - ����� ������ ������ ����������.
	 */
	@FXML
	void Save_res_word_Click(ActionEvent event) {

		// �������� ��� ������ �����
		if (TV_Resume.getSelectionModel().getSelectedItem() != null) {
			File file = (Paths.get(new File("������.docx").toURI()).toFile());
			try {
				new FileOutputStream(file);
			}catch(IOException e) {
				ApplicationUtils.alertinformation("��������","�������� ��� ������������ ���� ��� �������");
				return;
			}

			Resume str = (Resume) TV_Resume.getSelectionModel().getSelectedItem();

			//��������

			//������ �����
			FileOutputStream out;
			//��������
			try (XWPFDocument document = new XWPFDocument();){

				CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
				CTPageMar pageMar = sectPr.addNewPgMar();
				pageMar.setLeft(BigInteger.valueOf(1700L));
				pageMar.setTop(BigInteger.valueOf(1415L));
				pageMar.setRight(BigInteger.valueOf(850L));
				pageMar.setBottom(BigInteger.valueOf(1135L));
				// �������� ������
				XWPFParagraph title = document.createParagraph();  
				title.setAlignment(ParagraphAlignment.CENTER);  
				XWPFRun titleParagraphRun = title.createRun(); 
				setSingleLineSpacing(title, 400);
				titleParagraphRun.setText("������");  
				titleParagraphRun.setColor("000000"); 
				titleParagraphRun.setFontFamily("Times New Ronan");
				titleParagraphRun.setFontSize(18);  

				// �������� ����� ���������
				{
					XWPFParagraph parag = document.createParagraph();
					setSingleLineSpacing(parag, 40);
					parag.setSpacingBetween(1.5);
					XWPFRun run = parag.createRun(); 
					run.setBold(true);
					run.setText("��������� ���������: " + str.getDesiredPosition());
					run.setFontFamily("Times New Ronan");
					run.setFontSize(14);  
				}
				{
					XWPFParagraph parag = document.createParagraph();
					parag.setSpacingBefore(6);
					setSingleLineSpacing(parag, 100);
					parag.setSpacingBetween(1.5);
					XWPFRun run = parag.createRun(); 
					run.setText( "���� ������: " + str.getWorkExp().replaceAll(";",";"));
					run.setFontFamily("Times New Ronan");
					run.setFontSize(10);  
				}
				{
					XWPFParagraph parag = document.createParagraph();
					parag.setSpacingBefore(6);
					setSingleLineSpacing(parag, 100);
					parag.setSpacingBetween(1.5);
					XWPFRun run = parag.createRun(); 
					run.setText(str.getFio());
					run.setFontFamily("Times New Ronan");
					run.setFontSize(12);  
				}
				{
					XWPFParagraph parag = document.createParagraph();
					parag.setSpacingBefore(6);
					setSingleLineSpacing(parag, 100);
					parag.setSpacingBetween(1.5);
					XWPFRun run = parag.createRun(); 
					run.setText("��������� ��������: " + str.getYourSalary() + " ���.");
					run.setFontFamily("Times New Ronan");
					run.setFontSize(12);  
				}
				{
					XWPFParagraph parag = document.createParagraph();
					parag.setSpacingBefore(6);
					setSingleLineSpacing(parag, 100);
					parag.setSpacingBetween(1.5);
					XWPFRun run = parag.createRun(); 
					run.setText("���� ��������: " + str.get_Birthday_access());
					run.setFontFamily("Times New Ronan");
					run.setFontSize(12);  
				}
				{
					XWPFParagraph parag = document.createParagraph();
					parag.setSpacingBefore(6);
					setSingleLineSpacing(parag, 100);
					parag.setSpacingBetween(1.5);
					XWPFRun run = parag.createRun(); 
					run.setText("�����: " + str.getCity());
					run.setFontFamily("Times New Ronan");
					run.setFontSize(12);  
				}
				{
					XWPFParagraph parag = document.createParagraph();
					parag.setSpacingBefore(6);
					setSingleLineSpacing(parag, 100);
					parag.setSpacingBetween(1.5);
					XWPFRun run = parag.createRun(); 
					run.setItalic(true);
					run.setText( "����������� �����: "+str.getAnotherLanguage().replaceAll(";","; ").replace(".",". ").replace(":",": "));
					run.setFontFamily("Times New Ronan");
					run.setFontFamily("Times New Ronan");
					run.setFontSize(11);  
				}
				{
					XWPFParagraph parag = document.createParagraph();
					parag.setSpacingBefore(6);
					setSingleLineSpacing(parag, 200);
					parag.setSpacingBetween(1.5);
					XWPFRun run = parag.createRun(); 
					run.setItalic(true);
					run.setText("��� ���: " + str.getAboutMe().replaceAll(";","; ").replace(".",". ").replace(":",": "));
					run.setFontFamily("Times New Ronan");
					run.setFontFamily("Times New Ronan");
					run.setFontSize(11);  
				}
				{
					XWPFParagraph parag = document.createParagraph();
					parag.setSpacingBefore(6);
					setSingleLineSpacing(parag, 200);
					parag.setSpacingBetween(1.5);
					XWPFRun run = parag.createRun(); 
					run.setItalic(true);
					run.setText( "����������: "+str.getAchievs().replaceAll(";","; ").replace(".",". ").replace(":",": "));
					run.setFontFamily("Times New Ronan");
					run.setFontFamily("Times New Ronan");
					run.setFontSize(11);  
				}
				{
					XWPFParagraph parag = document.createParagraph();
					parag.setSpacingBefore(6);
					setSingleLineSpacing(parag, 200);
					parag.setSpacingBetween(1.5);
					XWPFRun run = parag.createRun(); 
					run.setItalic(true);
					run.setText("������: "+str.getAchievs().replaceAll(";","; ").replace(".",". ").replace(":",": "));
					run.setFontFamily("Times New Ronan");
					run.setFontSize(11);  
				}
				{
					XWPFParagraph parag = document.createParagraph();
					parag.setSpacingBefore(6);
					setSingleLineSpacing(parag, 200);
					parag.setSpacingBetween(1.5);
					XWPFRun run = parag.createRun(); 
					run.setItalic(true);
					run.setText( "�������������� ���������: "+str.getAddInfo().replaceAll(";","; ").replace(".",". ").replace(":",": "));
					run.setFontFamily("Times New Ronan");
					run.setFontFamily("Times New Ronan");
					run.setFontSize(11);  
				}
				{
					XWPFParagraph parag = document.createParagraph();
					parag.setSpacingBefore(6);
					setSingleLineSpacing(parag, 200);
					parag.setSpacingBetween(1.5);
					XWPFRun run = parag.createRun(); 
					run.setBold(true);
					run.setText( "�����: " + str.getEmail());
					run.setFontFamily("Times New Ronan");
					run.setFontSize(12);  
				}




				out = new FileOutputStream(file);
				document.write(out);
				document.close();
				out.close();  

			} catch (Exception e) {
				ApplicationUtils.alerterror("������","�� ���������� ��������� ��������");
				return;
			}

			Desktop desktop = null;
			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
			}

			try {
				desktop.open(file);
			} catch (IOException e) {
				ApplicationUtils.alerterror("������","�� ���������� ������� ����");
				return;
			}
		}
		else ApplicationUtils.alertinformation("����������", "�������� ������");
	}

	/* ��������� ��������� ����� ���������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * emp - ����� ������ ������ Employee,
	 * ����� �������� �� �����;
	 * str - ����� ������ ������ Employee,
	 * ������ �������� �� �������;
	 * calendar - ����� ������ ������ �alendar.
	 */
	void setSingleLineSpacing(XWPFParagraph para, Integer after) {
		CTPPr ppr = para.getCTP().getPPr();
		if (ppr == null) ppr = para.getCTP().addNewPPr();
		CTSpacing spacing = ppr.isSetSpacing()? ppr.getSpacing() : ppr.addNewSpacing();
		spacing.setAfter(BigInteger.valueOf(after));
		spacing.setBefore(BigInteger.valueOf(0));
		spacing.setLineRule(STLineSpacingRule.AUTO);
		spacing.setLine(BigInteger.valueOf(240));
	}

	/* ����� �������������� ������
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * m - ����� ������ 
	 * ������ RedResumeController;
	 * stage - ����� ������ ������ Stage;
	 * win - ����� ������ ������ FXMLLoader;
	 * e - ����� ������ ������ ����������.
	 */
	@FXML
	void Redact_resume_Click(MouseEvent event) throws IOException {
		if (TV_Resume.getSelectionModel().getSelectedItem() != null)
		{
			Stage stage = new Stage();
			try {
				FXMLLoader win = new FXMLLoader(RedResumeController.class.getResource("RedResume.fxml"));
				stage.setScene(new Scene(win.load(),700,700));
				stage.setMinWidth(650);
				stage.setMinHeight(670);
				stage.setMaxWidth(870);
				RedResumeController m = win.<RedResumeController>getController();
				m.set_lines((Resume)TV_Resume.getSelectionModel().getSelectedItem());
				m.set_window(this);
				m.setCheck_butl(true);
				m.set_button("��������");
				stage.setTitle("�������������� ������");
				stage.centerOnScreen();
				stage.showAndWait();
			} catch (Exception e) {
				ApplicationUtils.alerterror("������", "�������� ���� �� �������");
				return;
			}
		} else {
			ApplicationUtils.alertinformation("��������", "�� �� ������� ����");
		}

	}


	/* ��������� ������������� ������
	 * ���������� ���������:
	 * arg1 - URL-����� ������;
	 * arg0 - ����� ��������, ���������������
	 * ��� �������� ��������.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		cb_gender.setItems(gender_items);
		cb_select_gender.setItems(gender_items);
		cb_select_post.setItems(post_items);
		cb_select_fam_stat.setItems(fam_stat_items);
		selected_arth_type.setItems(arch_type_items);
		cb_gender.setValue(" ");
		selected_arth_type.setValue("�����");
		cb_select_gender.setValue("�����");
		cb_select_fam_stat.setValue("�����");
		cb_select_post.setValue("�����");
		tf_id_search.setText(" ");
		tf_passport_search.setText(" ");


		set_TV_Archive("archive");	
		tv_arch_id.setCellValueFactory(new PropertyValueFactory<Archive, Number>("id"));
		tv_arch_emp.setCellValueFactory(new PropertyValueFactory<Archive, Number>("id_employee"));
		tv_arch_name.setCellValueFactory(new PropertyValueFactory<Archive, String>("archive_name"));
		tv_arch_type.setCellValueFactory(new PropertyValueFactory<Archive, String>("archive_type"));
		tv_arch_date.setCellValueFactory(new PropertyValueFactory<Archive, Date>("date_create"));
		tv_arch_info.setCellValueFactory(new PropertyValueFactory<Archive, String>("more_info"));
		TV_Archive.setItems(archive);
		tv_arch_id.setCellValueFactory(cellback -> new SimpleLongProperty(cellback.getValue().getId()));
		tv_arch_emp.setCellValueFactory(cellback -> new SimpleIntegerProperty(cellback.getValue().getEmployee()));
		tv_arch_name.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getArchiveName()));
		tv_arch_type.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getArchiveType()));
		tv_arch_date.setCellValueFactory(cellback -> new SimpleObjectProperty(cellback.getValue().getDateCreate()));
		tv_arch_info.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getMoreInfo()));

		set_TV_Resume("resume_");
		tv_resume_id.setCellValueFactory(cellback -> new SimpleIntegerProperty(cellback.getValue().getId()));
		tv_resume_fio.setCellValueFactory(new PropertyValueFactory<Resume, String>("fio"));
		tv_resume_des_post.setCellValueFactory(new PropertyValueFactory<Resume, String>("desired_position"));
		tv_resume_job_time.setCellValueFactory(new PropertyValueFactory<Resume, String>("job_time"));
		tv_resume_dis_sal.setCellValueFactory(new PropertyValueFactory<Resume, Number>("your_salary"));
		tv_resume_city.setCellValueFactory(new PropertyValueFactory<Resume, String>("city"));
		tv_resume_birth.setCellValueFactory(new PropertyValueFactory<Resume, Date>("birhday"));
		tv_resume_email.setCellValueFactory(new PropertyValueFactory<Resume, String>("email"));
		TV_Resume.setItems(resume);
		tv_resume_id.setCellValueFactory(cellback -> new SimpleIntegerProperty(cellback.getValue().getId()));
		tv_resume_fio.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getFio()));
		tv_resume_des_post.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getDesiredPosition()));
		tv_resume_job_time.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getJobTime()));
		tv_resume_dis_sal.setCellValueFactory(cellback -> new SimpleLongProperty(cellback.getValue().getYourSalary()));
		tv_resume_city.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getCity()));
		tv_resume_birth.setCellValueFactory(cellback ->new SimpleObjectProperty(cellback.getValue().getBirhday()));
		tv_resume_email.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getEmail()));


		set_TV_Employee("Employee where isFired = 0 or isFired = null");
		tv_id.setCellValueFactory(new PropertyValueFactory<Employee, Number>("id"));
		tv_fio.setCellValueFactory(new PropertyValueFactory<Employee, String>("fio"));
		tv_department.setCellValueFactory(new PropertyValueFactory<Employee,Number>("department"));
		tv_gend.setCellValueFactory(new PropertyValueFactory<Employee, String>("gender"));
		tv_res.setCellValueFactory(new PropertyValueFactory<Employee, Number>("resume"));
		tv_post.setCellValueFactory(new PropertyValueFactory<Employee, String>("post"));
		tv_sal.setCellValueFactory(new PropertyValueFactory<Employee, Number>("salary"));
		tv_sem_stat.setCellValueFactory(new PropertyValueFactory<Employee, String>("famStatus"));
		tv_passpr.setCellValueFactory(new PropertyValueFactory<Employee, String>("passport"));
		tv_email.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));

		TV_employ.setItems(employee);

		tv_id.setCellValueFactory(cellback -> new SimpleIntegerProperty(cellback.getValue().getId()));
		tv_fio.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getFio()));
		tv_department.setCellValueFactory(cellback -> new SimpleIntegerProperty(cellback.getValue().getDepartment()));
		tv_gend.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getGender()));
		tv_res.setCellValueFactory(cellback -> new SimpleIntegerProperty(cellback.getValue().getResume()));
		tv_post.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getPost()));
		tv_sal.setCellValueFactory(cellback -> new SimpleLongProperty(cellback.getValue().getSalary()));
		tv_sem_stat.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getFamStatus()));
		tv_passpr.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getPassport()));
		tv_email.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getEmail()));

		set_TV_Department("department");
		tv_depart_id.setCellValueFactory(new PropertyValueFactory<Department, Number>("id"));
		tv_depart_name.setCellValueFactory(new PropertyValueFactory<Department, String>("name_depart"));
		tv_depart_loc.setCellValueFactory(new PropertyValueFactory<Department, Number>("location_id"));
		tv_depart_time_start.setCellValueFactory(new PropertyValueFactory<Department, String>("job_time_start"));
		tv_depart_time_end.setCellValueFactory(new PropertyValueFactory<Department, String>("job_time_end"));
		tv_depart_date_start.setCellValueFactory(new PropertyValueFactory<Department, String>("job_week_start"));
		tv_depart_date_end.setCellValueFactory(new PropertyValueFactory<Department, String>("job_week_end"));
		TV_Depart.setItems(department);
		tv_depart_id.setCellValueFactory(cellback -> new SimpleIntegerProperty(cellback.getValue().getId()));
		tv_depart_name.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getNameDepart()));
		tv_depart_loc.setCellValueFactory(cellback -> new SimpleIntegerProperty(cellback.getValue().getLocation()));
		tv_depart_time_start.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getJobTimeStart()));
		tv_depart_time_end.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getJobTimeEnd()));
		tv_depart_date_start.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getJobWeekStart()));
		tv_depart_date_end.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getJobWeekEnd()));

	}

	/* ������� ������������� �������
	 * ���������� ���������:
	 * query - ��������� �������.
	 * ��������� ����������:
	 * result - ����� ������ ������ ResultSet;
	 * e - ����� ������ ������ ����������.
	 */
	public void set_TV_Resume(String query)
	{
		try {
			ResultSet result = db.selecttable(query);
			resume.clear();

			while (result.next())
				resume.add(new Resume(result.getInt("id"), result.getString("fio"), result.getString("desired_position"), result.getString("job_time"), result.getLong("your_salary"), result.getString("city"), result.getDate("birhday"), result.getString("email"), result.getString("about_me"), result.getString("achievs"), result.getString("skills"), result.getString("add_info"), result.getString("work_exp"), result.getString("another_language")));

			TV_Resume.refresh();

		} catch (SQLException e) {
			ApplicationUtils.alerterror("������ ", "������� ������� �� ����������");
			return;
		}
	}
	/* ������� ������������� �������
	 * ���������� ���������:
	 * query - ��������� �������.
	 * ��������� ����������:
	 * result - ����� ������ ������ ResultSet;
	 * e - ����� ������ ������ ����������.
	 */
	void set_TV_Archive(String query)
	{
		try {
			ResultSet result = db.selecttable(query);
			archive.clear();

			while (result.next()) 
				archive.add(new Archive(result.getLong("id"), result.getInt("id_employee"), result.getString("archive_name"), result.getString("archive_type"), result.getDate("date_create"), result.getString("more_info")));

			TV_Archive.refresh();

		} catch (SQLException e) {
			ApplicationUtils.alerterror("������ ", "������� ������ �� ����������");
			return;
		}
	}
	/* ������� ������������� �������
	 * ���������� ���������:
	 * query - ��������� �������.
	 * ��������� ����������:
	 * result - ����� ������ ������ ResultSet;
	 * e - ����� ������ ������ ����������.
	 */
	void set_TV_Employee(String query)
	{
		try {

			ResultSet result = db.selecttable(query);
			employee.clear();

			while (result.next()) {
				employee.add(new Employee(result.getInt("id"),result.getString("FIO"), result.getInt("department_id"), result.getInt("id_resume"), result.getString("post"), result.getString("gender"), result.getLong("salary"), result.getString("Fam_status"), result.getString("passport"), result.getString("email"), result.getBoolean("isFired")));

			}
			TV_employ.refresh();
		}
		catch(SQLException ex)
		{
			ApplicationUtils.alerterror("������ ", "������� ����������� �� ����������");
			return;
		}
	}
	/* ������� ������������� �������
	 * ���������� ���������:
	 * query - ��������� �������.
	 * ��������� ����������:
	 * result - ����� ������ ������ ResultSet;
	 * e - ����� ������ ������ ����������.
	 */
	void set_TV_Department(String query)
	{
		try {

			ResultSet result = db.selecttable(query);
			department.clear();

			while (result.next()) 
				department.add(new Department(result.getInt("id"), result.getInt("location_id"), result.getString("name_depart"), result.getString("job_time_start"), result.getString("job_time_end"), result.getString("job_week_start"), result.getString("job_week_end")));

			TV_Depart.refresh();

		} catch (SQLException e) {
			ApplicationUtils.alerterror("������ ", "������� ������������ �� ����������");
			return;
		}
	}
}
