package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Date;
import java.util.ResourceBundle;

import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

/* Методы:
 * TV_Mouse_Click, TV_Loc_Click, TV_Emp_Click,
 * TV_User_Click - методы заполнения полей;
 * Add_Depart_Click, Update_Depart_Click,
 * Delete_Depart_Click, Add_Loc_Click,
 * Update_Loc_Click, Delete_Loc_Click,
 * Add_Employee_Click, Update_Employee_Click,
 * Delete_Employee_Click, Add_Resume_Click,
 * Update_Resume_Click, Delete_Resume_Click,
 * Add_Users_Click, Update_Users_Click,
 * Delete_Users_Click - методы добавления,
 * изменения, удаления данных;
 * Search_Depart_Click, Search_Loc_Click,
 * Search_Employee_Click, Search_Resume_Click,
 * Search_arth_Click,
 * Search_Users_Click - методы поиска данных;
 * Сlear_Selected_Derart_Click,
 * Clear_Selected_Loc_Click,
 * Clear_selected_Employee_Click,
 * Clear_Selected_Resume_Click,
 * Clear_Selected_Arth_Click,
 * Clear_Selected_Users_Click - методы очистки полей;
 * Функции:
 * inializible - процедура инициализации данных;
 * Глобальные параметры:
 * 
 * TV_Depart - TableView класса Department;
 * tv_depart_id, tv_depart_name, tv_depart_loc,
 * tv_depart_time_start, tv_depart_time_end,
 * tv_depart_date_start, 
 * tv_depart_date_end - столбцы TV_Depart;
 * id_depart, tf_depart_name, tf_depart_loc,
 * tf_depart_time_start, tf_depart_time_end,
 * tf_depart_date_start,
 * tf_depart_date_end - поля для данных;
 * selected_depart_id -поле для поиска;
 * 
 * TV_Location - TableView класса Location;
 * tv_location_id, tv_location_postcode,
 * tv_location_city,
 * tv_location_address - столбцы TV_Location;
 * id_location, tf_location_postcode,
 * tf_location_city,
 * tf_location_address - поля для данных;
 * selected_loc_id,
 * selected_loc_postcode - поля для поиска;
 * 
 * TV_Resume - TableView класса Resume;
 * tv_resume_id, tv_resume_fio, tv_resume_des_post,
 * tv_resume_job_time, tv_resume_des_sal,
 * tv_resume_city, tv_resume_birth,
 * tv_resume_email - столбцы TV_Resume;
 * selected_resume_id - поле для поиска;
 * 
 * TV_employee - TableView класса Employee;
 * tv_id, tv_fio, tv_department, tv_post, tv_res,
 * tv_gend, tv_sal, tv_sem_stat, tv_passpr,
 * tv_email,tv_emp_isFired - столбцы TV_employee;
 * employee_id, tf_fio, tf_post, tf_department,
 * tf_resume, cb_gender, tf_salary, tf_sem_stat,
 * tf_passport, tf_email,
 * tf_emp_isFired - поля для данных;
 * tf_id_search, tf_passport_search, cb_select_gender,
 * cb_select_post, cb_select_fam_stat - поля для 
 * поиска и фильтрации;
 * 
 * TV_Archive - TableView класса Archive;
 * tv_arch_id, tv_arch_emp, tv_arch_name, tv_arch_type,
 * tv_arch_date, tv_arch_info - столбцы TV_Archive;
 * selected_arth_emp, selected_arth_type - поля для 
 * поиска и фильтрации; 
 * 
 * TV_Users - TableView класса Users;
 * tv_user_id, tv_user_login, tv_user_pass,
 * tv_user_role - столбцы TV_Users;
 * id_users, tf_user_login, tf_user_pass,
 * tf_user_role - поля для данных;
 * selected_user_id - поле для поиска;
 * 
 * employee, resume, users, archive, department,
 *  location - List(ы) для получения данных;
 * gender_items, post_items, fam_stat_items,
 * arch_type_item, isFired_items,
 * weeks_items - параметры для инициализации значений;
 * db - новый объект класса DB.
 */

public class AdminWinController implements Initializable, Controller 
{

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
	private TextField tf_depart_name;
	@FXML
	private TextField tf_depart_loc;
	@FXML
	private TextField tf_depart_time_start;
	@FXML
	private TextField tf_depart_time_end;
	@FXML
	private ComboBox<String> tf_depart_date_start;
	@FXML
	private ComboBox<String> tf_depart_date_end;

	@FXML
	private TextField selected_depart_id;


	@FXML
	private TableView<Location> TV_Location;
	@FXML
	private TableColumn<Location,Number> tv_location_id;
	@FXML
	private TableColumn<Location,String> tv_location_postcode;
	@FXML
	private TableColumn<Location,String> tv_location_city;
	@FXML
	private TableColumn<Location,String> tv_location_address;

	@FXML
	private TextField tf_location_postcode;
	@FXML
	private TextField tf_location_city;
	@FXML
	private TextField tf_location_address;

	@FXML
	private TextField selected_loc_id;
	@FXML
	private TextField selected_loc_postcode;


	@FXML
	private TableView<Employee> TV_Employee;
	@FXML
	private TableColumn<Employee,Number> tv_emp_id;
	@FXML
	private TableColumn<Employee,String> tv_emp_fio;
	@FXML
	private TableColumn<Employee,String> tv_emp_post;
	@FXML
	private TableColumn<Employee,Number> tv_emp_dep;
	@FXML
	private TableColumn<Employee,Number> tv_emp_res;
	@FXML
	private TableColumn<Employee,String> tv_emp_gen;
	@FXML
	private TableColumn<Employee,Number> tv_emp_sal;
	@FXML
	private TableColumn<Employee,String> tv_emp_fstat;
	@FXML
	private TableColumn<Employee,String> tv_emp_passp;
	@FXML
	private TableColumn<Employee,String> tv_emp_email;
	@FXML
	private TableColumn<Employee,String> tv_emp_isFired;

	@FXML
	private TextField tf_emp_fio;
	@FXML
	private TextField tf_emp_post;
	@FXML
	private TextField tf_emp_dep;
	@FXML
	private TextField tf_emp_res;
	@FXML
	private ComboBox<String> tf_emp_gen;
	@FXML
	private TextField tf_emp_sal;
	@FXML
	private TextField tf_emp_fstat;
	@FXML
	private TextField tf_emp_passp;
	@FXML
	private TextField tf_emp_email;
	@FXML
	private ComboBox<String> tf_emp_isFired;

	@FXML
	private ComboBox<String> selected_emp_gen;
	@FXML
	private ComboBox<String> selected_emp_post;
	@FXML
	private ComboBox<String> selected_emp_fstat;
	@FXML
	private TextField selected_emp_id;
	@FXML
	private TextField selected_emp_passport;


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
	private TableView<Users> TV_Users;
	@FXML
	private TableColumn<Users,Number> tv_user_id;
	@FXML
	private TableColumn<Users,String> tv_user_login;
	@FXML
	private TableColumn<Users,String> tv_user_pass;
	@FXML
	private TableColumn<Users,String> tv_user_role;

	@FXML
	private TextField tf_user_login;
	@FXML
	private TextField tf_user_pass;
	@FXML
	private TextField tf_user_role;

	@FXML
	private TextField selected_user_id;

	private Integer id_depart = null;
	private Integer id_location = null;
	private Integer id_employee = null;
	private Integer id_users = null;

	ObservableList<Employee> employee = FXCollections.observableArrayList();
	ObservableList<Resume> resume = FXCollections.observableArrayList();
	ObservableList<Users> users = FXCollections.observableArrayList();
	ObservableList<Archive> archive = FXCollections.observableArrayList();
	ObservableList<Department> department = FXCollections.observableArrayList();
	ObservableList<Location> location = FXCollections.observableArrayList();
	ObservableList<String> gender_items = FXCollections.observableArrayList("Пусто","мужской", "женский");
	ObservableList<String> post_items = FXCollections.observableArrayList("Пусто","Разработчик", "Дизайнер", "Аналитик", "Администратор");
	ObservableList<String> fam_stat_items = FXCollections.observableArrayList("Пусто","Замужем", "Женат", "Не женат", "Не замужем");
	ObservableList<String> arch_type_items = FXCollections.observableArrayList("Пусто","Зачисление", "Увольнение", "Перемещение");
	ObservableList<String> isFired_items = FXCollections.observableArrayList("Пусто","true","false");
	ObservableList<String> weeks_items = FXCollections.observableArrayList("Пусто","Понидельник","Вторник", "Среда", "Четверг", "Пятница", "Суббота");
	DB db = new DB();


	/* Метод заполнения значений
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * str - новый объект класса Department,
	 * значения из TableView;
	 */
	@FXML
	void TV_Dep_Click(MouseEvent event) {

		// Проверка на пустую строку
		if (TV_Depart.getSelectionModel().getSelectedItem() != null) {

			Department str = (Department) TV_Depart.getSelectionModel().getSelectedItem();

			id_depart = str.getId();
			tf_depart_name.setText(str.getNameDepart());
			tf_depart_loc.setText(str.getLocation().toString());
			tf_depart_time_start.setText(str.getJobTimeStart());
			tf_depart_time_end.setText(str.getJobTimeEnd());
			tf_depart_date_start.setValue(str.getJobWeekStart());
			tf_depart_date_end.setValue(str.getJobWeekEnd()); 
		}
	}


	/* Метод добавления значений
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * dep - новый объект класса Department,
	 * новые значения из полей;
	 * e - новые объекты класса исключений.
	 */
	@FXML
	void Add_Depart_Click(MouseEvent event) {
		if (tf_depart_name.getText() != "" && tf_depart_loc.getText() != "" && tf_depart_time_start.getText() != "" && tf_depart_time_end.getText() != "")
		{
			Department dep;
			try 
			{
				dep = new Department(Integer.parseInt(tf_depart_loc.getText()),tf_depart_name.getText(),tf_depart_time_start.getText(),tf_depart_time_end.getText(),tf_depart_date_start.getValue(),tf_depart_date_end.getValue());
			}
			catch(NumberFormatException e)
			{
				ApplicationUtils.alertinformation("Ошибка", "Проверьте поля с числами");
				return;
			}
			try {
				if((db.selectcolumn("id", "location where id = '"+dep.getLocation()+"'")).next())
				{
					if(!(db.selectcolumn("location_id", "department where location_id = '"+dep.getLocation()+"'")).next())
					{
						if(dep.getJobWeekStart() == "Пусто")
							dep.setJobWeekStart(null);
						if(dep.getJobWeekEnd() == "Пусто")
							dep.setJobWeekEnd(null);
						if(db.insert("department","name_depart,location_id,job_time_start,job_time_end,job_week_start,job_week_end",dep.getSQL()))
						{
							set_TV_Department("department");
							tf_depart_name.clear();
							tf_depart_loc.clear();
							tf_depart_time_start.clear();
							tf_depart_time_end.clear();
							tf_depart_date_start.setValue("Пусто");
							tf_depart_date_end.setValue("Пусто");
						}
					} else ApplicationUtils.alertinformation("Внимание", "Данное местоположение уже занято другим департаментом");

				}else ApplicationUtils.alertinformation("Внимание", "Данного местоположения не существует");

			} catch (SQLException e) {
				ApplicationUtils.alerterror("Ошибка", "Проблема с подключением к базе данных");
				return;
			}
		} else
			ApplicationUtils.alertinformation("Внимание", "Заполните поля для добавления");
	}

	/* Метод изменения значений
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * dep - новый объект класса Department,
	 * новые значения из полей;
	 * str - новый объект класса Department,
	 * старые значения из таблицы;
	 * e - новые объекты класса исключений.
	 */
	@FXML
	void Update_Depart_Click(MouseEvent event) {
		if (tf_depart_name.getText() != "" && tf_depart_loc.getText() != "" && tf_depart_time_start.getText() != "" && tf_depart_time_end.getText() != "")
		{
			Department dep;
			try
			{
				dep = new Department(id_depart,Integer.parseInt(tf_depart_loc.getText()),tf_depart_name.getText(),tf_depart_time_start.getText(),tf_depart_time_end.getText(),tf_depart_date_start.getValue(),tf_depart_date_end.getValue());
			}
			catch(NumberFormatException e)
			{
				ApplicationUtils.alertinformation("Ошибка", "Проверьте поля с числами");
				return;
			}
			Department str = (Department) TV_Depart.getSelectionModel().getSelectedItem();

			try {
				if((db.selectcolumn("id", "location where id = '"+dep.getLocation()+"'")).next())
				{
					if(!(db.selectcolumn("location_id", "department where location_id = '"+dep.getLocation()+"'")).next() || dep.getLocation() == str.getLocation())
					{
						if(dep.getJobWeekStart() == "Пусто")
							dep.setJobWeekStart(null);
						if(dep.getJobWeekEnd() == "Пусто")
							dep.setJobWeekEnd(null);
						if(db.update("department", "name_depart= '" + dep.getNameDepart()+ "', location_id= '" + dep.getLocation() + "', job_time_start= '" + dep.getJobTimeStart() + "', job_time_end= '" + dep.getJobTimeEnd() + "', job_week_start= '" + dep.getJobWeekStart() + "', job_week_end= '" + dep.getJobWeekEnd()+"'", dep.getId().toString()));
						{
							set_TV_Department("department");
							tf_depart_name.clear();
							tf_depart_loc.clear();
							tf_depart_time_start.clear();
							tf_depart_time_end.clear();
							tf_depart_date_start.setValue("Пусто");
							tf_depart_date_end.setValue("Пусто");
						}
					} else ApplicationUtils.alertinformation("Внимание", "Данное местоположение уже занято другим департаментом");

				} else ApplicationUtils.alertinformation("Внимание", "Данного местоположения не существует");

			} catch (SQLException e) {
				ApplicationUtils.alertinformation("Ошибка", "Проблема с подключением к базе данных");
				return;
			}
		}
		else
			ApplicationUtils.alertinformation("Внимание", "Заполните поля для добавления");
	}

	/* Метод удаления данных
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * str - новый объект класса Department,
	 * значения из TableView;
	 * e - новый объект класса исключений.
	 */
	@FXML
	void Delete_Depart_Click(MouseEvent event) {
		if (TV_Depart.getSelectionModel().getSelectedItem() != null)
		{
			Department str = (Department) TV_Depart.getSelectionModel().getSelectedItem();
			try {
				if(!(db.selectcolumn("department_id", "employee where department_id = '"+str.getId()+"'")).next())
				{
					if(db.delete("department where id = '"+str.getId()+"'"))
					{
						set_TV_Department("department");
					}
				}else ApplicationUtils.alertinformation("Внимание", "Невозможно удалить департамент, где есть сотрудники");

			} catch (SQLException e) {
				ApplicationUtils.alerterror("Ошибка", "Проблема с подключением к базе данных");
				return;
			}
		}
	}

	/* Метод поиска
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * query - параметры запроса;
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

	@FXML
	void Сlear_Selected_Derart_Click(MouseEvent event) {
		selected_depart_id.setText("");
		set_TV_Department("department");
	}

	/* Метод заполнения значений
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * str - новый объект класса Location,
	 * значения из TableView;
	 */
	@FXML
	void TV_Loc_Click(MouseEvent event) {

		// Проверка на пустую строку
		if (TV_Location.getSelectionModel().getSelectedItem() != null) {

			Location str = (Location) TV_Location.getSelectionModel().getSelectedItem();

			id_location = str.getId();
			tf_location_postcode.setText(str.getPostcode());
			tf_location_city.setText(str.getCity());
			tf_location_address.setText(str.getStreetAddress());
		}
	}

	/* Метод добавления значений
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * loc - новый объект класса Location,
	 * новые значения из полей;
	 * e - новый объект класса исключений.
	 */
	@FXML
	void Add_Loc_Click(MouseEvent event) {
		if (tf_location_postcode.getText() != "" && tf_location_city.getText() != "" && tf_location_address.getText() != "")
		{
			Location loc = new Location(tf_location_postcode.getText(),	tf_location_city.getText(), tf_location_address.getText());

			try {
				if(db.insert("location","postcode,city,street_address",loc.getSQL()))
				{
					set_TV_Location("location");
					tf_location_postcode.clear();
					tf_location_city.clear();
					tf_location_address.clear();
				}
			} catch (SQLException e) {
				ApplicationUtils.alerterror("Ошибка", "Проблема с подключением к базе данных");
				return;
			}

		} else
			ApplicationUtils.alertinformation("Внимание", "Заполните поля для добавления");
	}

	/* Метод изменения значений
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * loc - новый объект класса Location,
	 * новые значения из полей;
	 * e - новый объект класса исключений.
	 */
	@FXML
	void Update_Loc_Click(MouseEvent event) {
		if (tf_location_postcode.getText() != "" && tf_location_city.getText() != "" && tf_location_address.getText() != "")
		{

			Location loc = new Location(id_location,tf_location_postcode.getText(),	tf_location_city.getText(), tf_location_address.getText());

			try {
				if(db.update("location", "postcode= '" + loc.getPostcode()+ "', city= '" + loc.getCity() + "', street_address= '" + loc.getStreetAddress()+"'", loc.getId().toString()))
				{
					set_TV_Location("location");
					tf_location_postcode.clear();
					tf_location_city.clear();
					tf_location_address.clear();
				}
			} catch (SQLException e) {
				ApplicationUtils.alertinformation("Ошибка", "Проблема с подключением к базе данных");
				return;
			}
		}
		else
			ApplicationUtils.alertinformation("Внимание", "Заполните поля для добавления");
	}

	/* Метод удаления данных
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * str - новый объект класса Location,
	 * значения из TableView;
	 * e - новый объект класса исключений.
	 */
	@FXML
	void Delete_Loc_Click(MouseEvent event) {
		if (TV_Location.getSelectionModel().getSelectedItem() != null)
		{
			Location str = (Location) TV_Location.getSelectionModel().getSelectedItem();
			try {
				if(!(db.selectcolumn("location_id", "department where location_id = '"+str.getId()+"'").next()))
				{
					if(db.delete("location where id = '"+str.getId()+"'"))
					{
						set_TV_Location("location");
					}
				} else ApplicationUtils.alertinformation("Внимание", "Невозможно удалить местоположение существующего департамента");
			} catch (SQLException e) {
				ApplicationUtils.alerterror("Ошибка", "Проблема с подключением к базе данных");
				return;
			}
		}
	}

	/* Метод поиска
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * query - параметры запроса;
	 */
	@FXML
	void Search_Loc_Click(MouseEvent event) {
		Boolean check_search = false;
		String query = "location ";
		if(selected_loc_id.getText() != "" && check_search == false)
		{
			query += "where id = "+selected_loc_id.getText()+"";
			check_search = true;
		}else
			if(selected_loc_id.getText() != "" && check_search == true)
			{
				query += " and id = "+selected_loc_id.getText()+"";
			}
		if(selected_loc_postcode.getText() != "" && check_search == false)
		{
			query += "where postcode = "+selected_loc_postcode.getText()+"";
			check_search = true;
		}else
			if(selected_loc_postcode.getText() != "" && check_search == true)
			{
				query += " and postcode = "+selected_loc_postcode.getText()+"";
			}

		set_TV_Location(query);
	}

	/* Метод очищения полей 
	 * фильтрации и поиска
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 */
	@FXML
	void Clear_Selected_Loc_Click(MouseEvent event) {
		selected_loc_id.setText("");
		selected_loc_postcode.setText("");
		set_TV_Location("location");
	}


	/* Метод заполнения значений
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * str - новый объект класса Employee,
	 * значения из TableView;
	 */
	@FXML
	void TV_Emp_Click(MouseEvent event) {

		// Проверка на пустую строку
		if (TV_Employee.getSelectionModel().getSelectedItem() != null) {

			Employee str = (Employee) TV_Employee.getSelectionModel().getSelectedItem();

			id_employee = str.getId();
			tf_emp_fio.setText(str.getFio());
			tf_emp_post.setText(str.getPost());
			tf_emp_dep.setText(str.getDepartment().toString());
			tf_emp_res.setText(str.getResume().toString());
			tf_emp_gen.setValue(str.getGender());
			tf_emp_sal.setText(String.valueOf(str.getSalary()));
			tf_emp_fstat.setText(str.getFamStatus());
			tf_emp_passp.setText(str.getPassport());
			tf_emp_email.setText(str.getEmail());
			tf_emp_isFired.setValue(str.getIsFired().toString());
		}
	}

	/* Метод фильтрации и поиска
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * query - параметры запроса;
	 */
	@FXML
	void Search_Employee_Click(ActionEvent event) {

		Boolean check_search = false;
		String query = "employee ";

		if (selected_emp_gen.getSelectionModel().getSelectedItem() != "Пусто" && check_search == false)
		{
			query += "where gender = '"+selected_emp_gen.getValue().toString()+"'";
			check_search = true;
		}else
			if (selected_emp_gen.getSelectionModel().getSelectedItem() != "Пусто" && check_search == true)
			{
				query += " and gender = '"+selected_emp_gen.getValue().toString()+"'";
			}
		if (selected_emp_post.getSelectionModel().getSelectedItem() != "Пусто" && check_search == false)
		{
			query += "where post = '"+selected_emp_post.getValue().toString()+"'";
			check_search = true;
		}else
			if (selected_emp_post.getSelectionModel().getSelectedItem() != "Пусто" && check_search == true)
			{
				query += " and post = '"+selected_emp_post.getValue().toString()+"'";
			}
		if (selected_emp_fstat.getSelectionModel().getSelectedItem() != "Пусто" && check_search == false)
		{
			query += "where Fam_status = '"+selected_emp_fstat.getValue().toString()+"'";
			check_search = true;
		}else
			if (selected_emp_fstat.getSelectionModel().getSelectedItem() != "Пусто" && check_search == true)
			{
				query += " and Fam_status = '"+selected_emp_fstat.getValue().toString()+"'";
			}
		if(selected_emp_id.getText() != "" && check_search == false)
		{
			query += "where id = '"+selected_emp_id.getText()+"'";
			check_search = true;
		}else
			if(selected_emp_id.getText() != "" && check_search == true)
			{
				query += " and id = '"+selected_emp_id.getText()+"'";
			}
		if(selected_emp_passport.getText() != "" && check_search == false)
		{
			query += "where passport = '"+selected_emp_passport.getText()+"'";
			check_search = true;
		}else
			if(selected_emp_passport.getText() != "" && check_search == true)
			{
				query += " and passport = '"+selected_emp_passport.getText()+"'";
			}
		set_TV_Employee(query);
	}

	/* Метод очищения полей 
	 * фильтрации и поиска
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 */
	@FXML
	void Clear_selected_Employee_Click(ActionEvent event) {

		selected_emp_gen.setValue("Пусто");
		selected_emp_fstat.setValue("Пусто");
		selected_emp_post.setValue("Пусто");
		selected_emp_id.setText("");
		selected_emp_passport.setText("");
		set_TV_Employee("employee");
	}

	/* Метод добавления значений
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * emp - новый объект класса Employee,
	 * новые значения из полей;
	 * e - новые объекты класса исключений.
	 */
	@FXML
	void Add_Employee_Click(MouseEvent event){
		if (tf_emp_fio.getText() != "" && tf_emp_post.getText() != "" && tf_emp_dep.getText() != "" && tf_emp_res.getText() != "" && tf_emp_gen.getValue() != "Пусто" && tf_emp_sal.getText() != "" &&	tf_emp_fstat.getText() != "" && tf_emp_passp.getText() != "")
		{
			Employee emp;
			try
			{
				emp = new Employee(tf_emp_fio.getText(),Integer.parseInt(tf_emp_dep.getText()),Integer.parseInt(tf_emp_res.getText()),tf_emp_post.getText(),tf_emp_gen.getValue(), Long.parseLong(tf_emp_sal.getText()),tf_emp_fstat.getText(),tf_emp_passp.getText(),tf_emp_email.getText(),Boolean.parseBoolean(tf_emp_isFired.getValue()));
			}
			catch(NumberFormatException e)
			{
				ApplicationUtils.alertinformation("Ошибка", "Проверьте поля с числами");
				return;
			}
			try {
				if((db.selectcolumn("id", "resume_ where id = '"+emp.getResume()+"'")).next() && (db.selectcolumn("id", "department where id = '"+emp.getDepartment()+"'")).next())
				{
					if(!(db.selectcolumn("id_resume", "employee where id_resume = '"+emp.getResume()+"'")).next())
					{
						if(db.insert("employee","department_id,FIO,post,id_resume,gender,salary,Fam_status,passport,email,isFired",emp.getSQL()))
						{
							set_TV_Employee("employee");
							id_employee = null;
							tf_emp_fio.clear();
							tf_emp_post.clear();
							tf_emp_dep.clear();
							tf_emp_res.clear();
							tf_emp_gen.setValue("Пусто");
							tf_emp_sal.clear();
							tf_emp_fstat.clear();
							tf_emp_passp.clear();
							tf_emp_email.clear();
							tf_emp_isFired.setValue("Пусто");

						}
					}else ApplicationUtils.alerterror("Ошибка", "Данное резюме занято другим сотрудником");

				}else ApplicationUtils.alerterror("Ошибка", "Номер резюме или департамента не существует");

			} catch (SQLException e) {
				ApplicationUtils.alerterror("Ошибка", "Проблема с подключением к базе данных");
				return;
			}

		} else
			ApplicationUtils.alertinformation("Внимание", "Заполните поля для добавления");
	}

	/* Метод изменения значений
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * emp - новый объект класса Employee,
	 * новые значения из полей;
	 * str - новый объект класса Employee,
	 * старые значения из таблицы;
	 * e - новые объекты класса исключений.
	 */
	@FXML
	void Update_Employee_Click(MouseEvent event) {
		if (id_employee != null && tf_emp_fio.getText() != "" && tf_emp_post.getText() != "" && tf_emp_dep.getText() != "" && tf_emp_res.getText() != "" && tf_emp_gen.getValue() != "" &&tf_emp_sal.getText() != "" && tf_emp_fstat.getText() != "" && tf_emp_passp.getText() != "")
		{
			Employee emp;
			try
			{
				emp = new Employee(id_employee, tf_emp_fio.getText(),Integer.parseInt(tf_emp_dep.getText()),Integer.parseInt(tf_emp_res.getText()),tf_emp_post.getText(),tf_emp_gen.getValue(), Long.parseLong(tf_emp_sal.getText()),tf_emp_fstat.getText(),tf_emp_passp.getText(),tf_emp_email.getText(),Boolean.parseBoolean(tf_emp_isFired.getValue()));
			}
			catch(NumberFormatException e)
			{
				ApplicationUtils.alertinformation("Ошибка", "Проверьте поля с числами");
				return;
			}
			Employee str = (Employee) TV_Employee.getSelectionModel().getSelectedItem();

			try {

				if((db.selectcolumn("id", "resume_ where id = '"+emp.getResume()+"'")).next() && (db.selectcolumn("id", "department where id = '"+emp.getDepartment()+"'")).next())
				{
					if(!(db.selectcolumn("id_resume", "employee where id_resume = '"+emp.getResume()+"'")).next() || emp.getResume() == str.getResume())
					{
						if(db.update("employee", "FIO= '" + emp.getFio()+ "', department_id= '" + emp.getDepartment()+ "', id_resume= '" + emp.getResume().toString() + "', post= '" + emp.getPost() + "', gender= '" + emp.getGender() + "', salary= '" + emp.getSalary() + "', Fam_status= '" + emp.getFamStatus()+ "', passport= '" + emp.getPassport() +  "', email= '" + emp.getEmail() +  "', isFired= " + emp.getIsFired() + "", emp.getId().toString()))
						{
							set_TV_Employee("employee");
							id_employee = null;
							tf_emp_fio.clear();
							tf_emp_post.clear();
							tf_emp_dep.clear();
							tf_emp_res.clear();
							tf_emp_gen.setValue("Пусто");
							tf_emp_sal.clear();
							tf_emp_fstat.clear();
							tf_emp_passp.clear();
							tf_emp_email.clear();
							tf_emp_isFired.setValue("Пусто");

						}else ApplicationUtils.alertinformation("Ошибка", "пыупыу");

					}else ApplicationUtils.alertinformation("Ошибка", "Данное резюме занято другим сотрудником");

				}else ApplicationUtils.alertinformation("Ошибка", "Номер резюме или департамента не существует");

			} catch (SQLException e) {
				ApplicationUtils.alertinformation("Ошибка", "Проблема с подключением к базе данных");
				return;
			}
		} else ApplicationUtils.alertinformation("Внимание", "Заполните поля для добавления");

	}

	/* Метод удаления данных
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * str - новый объект класса Employee,
	 * значения из TableView;
	 * e - новый объект класса исключений.
	 */
	@FXML
	void Delete_Employee_Click(MouseEvent event) {
		if (TV_Employee.getSelectionModel().getSelectedItem() != null)
		{
			Employee str = (Employee) TV_Employee.getSelectionModel().getSelectedItem();

			try {
				if(db.delete("archive where id_employee = '"+str.getId()+"'"))
				{
					db.delete("employee where id = '"+str.getId()+"'");
					set_TV_Employee("employee");
					set_TV_Archive("archive");
				}
			} catch (SQLException e) {
				ApplicationUtils.alerterror("Ошибка", "Проблема с подключением к базе данных");
				return;
			}
		}
	}

	/* Метод поиска
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * query - параметры запроса;
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

	/* Метод очищения полей 
	 * фильтрации и поиска
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 */
	@FXML
	void Clear_Selected_Resume_Click(MouseEvent event) {
		selected_resume_id.setText("");
		set_TV_Resume("resume_");
	}

	/* Метод добавления значений
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * m - новый объект класса
	 * RedResumeController;
	 * stage - новый объект класса Stage;
	 * win - новый объект класса FXMLLoader;
	 * e - новый объект класса исключений.
	 */
	@FXML
	void Add_Resume_Click(MouseEvent event) {
		Stage stage = new Stage();
		try {
			FXMLLoader win = new FXMLLoader(RedResumeController.class.getResource("RedResume.fxml"));
			stage.setScene(new Scene(win.load(),700,700));
			stage.setMinWidth(650);
			stage.setMinHeight(670);
			stage.setMaxWidth(870);
			RedResumeController m = win.<RedResumeController>getController();
			m.setCheck_butl(false);
			m.set_button("Добавить");
			m.set_window(this);
			stage.setTitle("Редактирование резюме");
			stage.centerOnScreen();
			stage.showAndWait();
		} catch (Exception e) {
			ApplicationUtils.alerterror("Ошибка", "Открытие меню не удалось");
			return;
		}
	}

	/* Метод изменения значений
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * m - новый объект класса
	 * RedResumeController;
	 * stage - новый объект класса Stage;
	 * win - новый объект класса FXMLLoader;
	 * e - новый объект класса исключений.
	 */
	@FXML
	void Update_Resume_Click(MouseEvent event) {
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
				m.set_button("Изменить");
				m.setCheck_butl(true);
				m.set_window(this);
				stage.setTitle("Редактирование резюме");
				stage.centerOnScreen();
				stage.showAndWait();
			} catch (Exception e) {
				ApplicationUtils.alerterror("Ошибка", "Открытие меню не удалось");
				return;
			}
		} else {
			ApplicationUtils.alertinformation("Внимание", "Вы не выбрали поле");
		}
	}

	/* Метод удаления данных
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * str - новый объект класса Resume,
	 * значения из TableView;
	 * e - новый объект класса исключений.
	 */
	@FXML
	void Delete_Resume_Click(MouseEvent event) {
		if (TV_Resume.getSelectionModel().getSelectedItem() != null)
		{
			Resume str = (Resume) TV_Resume.getSelectionModel().getSelectedItem();
			try {
				if(!(db.selectcolumn("id_resume", "employee where id_resume = '"+str.getId()+"'")).next())
				{
					db.delete("resume_ where id = '"+str.getId()+"'");
				} else ApplicationUtils.alertinformation("Внимание", "Невозможно удалить резюме существующего сотрудника");
			} catch (SQLException e) {
				ApplicationUtils.alerterror("Ошибка", "Проблема с подключением к базе данных");
				return;
			}
			set_TV_Resume("resume_");
		}
	}

	/* Метод фильтрации и поиска
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * query - параметры запроса;
	 */
	@FXML
	void Search_arth_Click(ActionEvent event) {
		String query = "archive ";
		Boolean check_search = false;
		if(selected_arth_emp.getText() != "" && check_search == false)
		{
			query += "where id_employee = "+selected_arth_emp.getText()+"";
			check_search = true;
		}else
			if(selected_arth_emp.getText() != "" && check_search == true)
			{
				query += " and id_employee = "+selected_arth_emp.getText()+"";
			}
		if (selected_arth_type.getSelectionModel().getSelectedItem() != "Пусто" && check_search == false)
		{
			query += "where archive_type = '"+selected_arth_type.getValue().toString()+"'";
			check_search = true;
		}else
			if (selected_arth_type.getSelectionModel().getSelectedItem() != "Пусто" && check_search == true)
			{
				query += " and archive_type = '"+selected_arth_type.getValue().toString()+"'";
			}

		set_TV_Archive(query);
	}

	/* Метод очищения полей 
	 * фильтрации и поиска
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 */
	@FXML
	void Clear_Selected_Arth_Click(ActionEvent event) {
		selected_arth_emp.setText("");
		selected_arth_type.setValue("Пусто");

		set_TV_Archive("archive");
	}



	/* Метод заполнения значений
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * str - новый объект класса Users,
	 * значения из TableView;
	 */
	@FXML
	void TV_User_Click(MouseEvent event) {

		// Проверка на пустую строку
		if (TV_Users.getSelectionModel().getSelectedItem() != null) {

			Users str = (Users)TV_Users.getSelectionModel().getSelectedItem();

			id_users = str.getId();
			tf_user_login.setText(str.getLogin());
			tf_user_pass.setText(str.getPass());
			tf_user_role.setText(str.getRole());
		}
	}

	/* Метод добавления значений
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * user - новый объект класса Users,
	 * новые значения из полей;
	 * e - новый объект класса исключений.
	 */
	@FXML
	void Add_Users_Click(MouseEvent event) {

		if (tf_user_login.getText() != "" && tf_user_pass.getText() != "" && tf_user_role.getText() != "")
		{

			Users user = new Users(tf_user_login.getText(), tf_user_pass.getText(),tf_user_role.getText());
			try {
				if(!(db.selectcolumn("login", "users where login = '"+user.getLogin()+"'")).next())
				{
					if(db.insert("users","login, pass, role",user.getSQL()))
					{
						set_TV_Users("users");
						tf_location_postcode.clear();
						tf_location_city.clear();
						tf_location_address.clear();
					}

				} else
					ApplicationUtils.alertinformation("Внимание", "Пользователь с таким логином уже существует");

			} catch (SQLException e) {
				e.printStackTrace();
				ApplicationUtils.alerterror("Ошибка", "Проблема с подключением к базе данных");
				return;
			}
		} else
			ApplicationUtils.alertinformation("Внимание", "Заполните поля для добавления");

	}

	/* Метод изменения значений
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * user - новый объект класса Users,
	 * новые значения из полей;
	 * str - новый объект класса Users,
	 * старые значения из таблицы;
	 * e - новый объект класса исключений.
	 */
	@FXML
	void Update_Users_Click(MouseEvent event) {
		if (tf_user_login.getText() != "" && tf_user_pass.getText() != "" && tf_user_role.getText() != "")
		{

			Users user = new Users(id_users,tf_user_login.getText(), tf_user_pass.getText(),tf_user_role.getText());
			Users str = (Users)TV_Users.getSelectionModel().getSelectedItem();

			try {
				if(!db.selectcolumn("login", "users where login = '"+user.getLogin()+"'").next() || user.getLogin().equals(str.getLogin()))
				{
					if(db.update("users", "login= '" + user.getLogin()+ "', pass= '" + user.getPass() + "', role= '" + user.getRole()+"'", user.getId().toString()));
					{
						set_TV_Users("users");
						tf_location_postcode.clear();
						tf_location_city.clear();
						tf_location_address.clear();
					}
				} else ApplicationUtils.alertinformation("Внимание", "Пользователь с таким логином уже существует");
			} catch (SQLException e) {
				ApplicationUtils.alerterror("Ошибка", "Проблема с подключением к базе данных");
				return;
			}
		}
		else
			ApplicationUtils.alertinformation("Внимание", "Заполните поля для добавления");
	}

	/* Метод удаления данных
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * str - новый объект класса Users,
	 * значения из TableView;
	 * e - новый объект класса исключений.
	 */
	@FXML
	void Delete_Users_Click(MouseEvent event) {
		if (TV_Users.getSelectionModel().getSelectedItem() != null)
		{
			Users str = (Users) TV_Users.getSelectionModel().getSelectedItem();

			try {
				if(db.delete("users where id = '"+str.getId()+"'"))
				{
					set_TV_Users("users");
				}
			} catch (SQLException e) {
				ApplicationUtils.alerterror("Ошибка", "Проблема с подключением к базе данных");
				return;
			}
		}
	}

	/* Метод поиска
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * query - параметры запроса;
	 */
	@FXML
	void Search_Users_Click(MouseEvent event) {
		String query = "users ";
		if(selected_user_id.getText() != "")
			query += "where id = "+selected_user_id.getText()+"";

		set_TV_Users(query);
	}

	/* Метод очищения полей 
	 * фильтрации и поиска
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 */
	@FXML
	void Clear_Selected_Users_Click(MouseEvent event) {
		selected_user_id.setText("");
		set_TV_Users("users");
	}

	/* Процедура инициализации данных
	 * Формальные параметры:
	 * arg1 - URL-адрес класса;
	 * arg0 - пакет ресурсов, предназначенный
	 * для хранения объектов.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tf_emp_gen.setItems(gender_items);
		tf_emp_isFired.setItems(isFired_items);
		tf_depart_date_start.setItems(weeks_items);
		tf_depart_date_end.setItems(weeks_items);
		selected_emp_gen.setItems(gender_items);
		selected_emp_post.setItems(post_items);
		selected_emp_fstat.setItems(fam_stat_items);
		selected_arth_type.setItems(arch_type_items);
		tf_emp_gen.setValue("Пусто");
		tf_emp_isFired.setValue("Пусто");
		tf_depart_date_start.setValue(" ");
		tf_depart_date_end.setValue(" ");
		selected_emp_gen.setValue("Пусто");
		selected_emp_fstat.setValue("Пусто");
		selected_emp_post.setValue("Пусто");
		selected_arth_type.setValue("Пусто");
		selected_emp_id.setText(" ");
		selected_emp_passport.setText(" ");


		set_TV_Users("users");
		tv_user_id.setCellValueFactory(new PropertyValueFactory<Users, Number>("id"));
		tv_user_login.setCellValueFactory(new PropertyValueFactory<Users, String>("login"));
		tv_user_pass.setCellValueFactory(new PropertyValueFactory<Users, String>("pass"));
		tv_user_role.setCellValueFactory(new PropertyValueFactory<Users, String>("role"));
		TV_Users.setItems(users);
		tv_user_id.setCellValueFactory(cellback -> new SimpleIntegerProperty(cellback.getValue().getId()));
		tv_user_login.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getLogin()));
		tv_user_pass.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getPass()));
		tv_user_role.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getRole()));
		tv_resume_dis_sal.setCellValueFactory(cellback -> new SimpleLongProperty(cellback.getValue().getYourSalary()));



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


		set_TV_Location("location");
		tv_location_id.setCellValueFactory(new PropertyValueFactory<Location, Number>("id"));
		tv_location_postcode.setCellValueFactory(new PropertyValueFactory<Location, String>("postcode"));
		tv_location_city.setCellValueFactory(new PropertyValueFactory<Location, String>("city"));
		tv_location_address.setCellValueFactory(new PropertyValueFactory<Location, String>("street_address"));
		TV_Location.setItems(location);
		tv_location_id.setCellValueFactory(cellback -> new SimpleIntegerProperty(cellback.getValue().getId()));
		tv_location_postcode.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getPostcode()));
		tv_location_city.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getCity()));
		tv_location_address.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getStreetAddress()));


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


		set_TV_Employee("employee");
		tv_emp_id.setCellValueFactory(new PropertyValueFactory<Employee, Number>("id"));
		tv_emp_fio.setCellValueFactory(new PropertyValueFactory<Employee, String>("fio"));
		tv_emp_post.setCellValueFactory(new PropertyValueFactory<Employee, String>("post"));
		tv_emp_dep.setCellValueFactory(new PropertyValueFactory<Employee,Number>("department"));
		tv_emp_res.setCellValueFactory(new PropertyValueFactory<Employee, Number>("resume"));
		tv_emp_gen.setCellValueFactory(new PropertyValueFactory<Employee, String>("gender"));
		tv_emp_sal.setCellValueFactory(new PropertyValueFactory<Employee, Number>("salary"));
		tv_emp_fstat.setCellValueFactory(new PropertyValueFactory<Employee, String>("famStatus"));
		tv_emp_passp.setCellValueFactory(new PropertyValueFactory<Employee, String>("passport"));
		tv_emp_email.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
		tv_emp_isFired.setCellValueFactory(new PropertyValueFactory<Employee, String>("ifFired"));
		TV_Employee.setItems(employee);
		tv_emp_id.setCellValueFactory(cellback -> new SimpleIntegerProperty(cellback.getValue().getId()));
		tv_emp_fio.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getFio()));
		tv_emp_post.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getPost()));
		tv_emp_dep.setCellValueFactory(cellback -> new SimpleIntegerProperty(cellback.getValue().getDepartment()));
		tv_emp_res.setCellValueFactory(cellback -> new SimpleIntegerProperty(cellback.getValue().getResume()));
		tv_emp_gen.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getGender()));
		tv_emp_sal.setCellValueFactory(cellback -> new SimpleLongProperty(cellback.getValue().getSalary()));
		tv_emp_fstat.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getFamStatus()));
		tv_emp_passp.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getPassport()));
		tv_emp_email.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getEmail()));
		tv_emp_isFired.setCellValueFactory(cellback -> new SimpleStringProperty(cellback.getValue().getIsFired().toString()));

	}

	/* Функция инициализации таблицы
	 * Формальные параметры:
	 * query - параметры запроса.
	 * Локальные переменные:
	 * result - новый объект класса ResultSet;
	 * e - новый объект класса исключений.
	 */
	void set_TV_Users(String query)
	{
		try {
			ResultSet result = db.selecttable(query);
			users.clear();
			while (result.next())
				users.add(new Users(result.getInt("id"), result.getString("login"), result.getString("pass"), result.getString("role")));

			TV_Users.refresh();

		} catch (SQLException e) {
			ApplicationUtils.alerterror("Ошибка ", "Таблица «Пользователи» не обновилась");
			return;
		}
	}

	/* Функция инициализации таблицы
	 * Формальные параметры:
	 * query - параметры запроса.
	 * Локальные переменные:
	 * result - новый объект класса ResultSet;
	 * e - новый объект класса исключений.
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
			ApplicationUtils.alerterror("Ошибка ", "Таблица «Архив» не обновилась");
			return;
		}
	}

	/* Функция инициализации таблицы
	 * Формальные параметры:
	 * query - параметры запроса.
	 * Локальные переменные:
	 * result - новый объект класса ResultSet;
	 * e - новый объект класса исключений.
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
			ApplicationUtils.alerterror("Ошибка ", "Таблица «Резюме» не обновилась");
			return;
		}
	}

	/* Функция инициализации таблицы
	 * Формальные параметры:
	 * query - параметры запроса.
	 * Локальные переменные:
	 * result - новый объект класса ResultSet;
	 * e - новый объект класса исключений.
	 */
	void set_TV_Location(String query)
	{
		try {
			ResultSet result = db.selecttable(query);
			location.clear();

			while (result.next()) 
				location.add(new Location(result.getInt("id"), result.getString("postcode"), result.getString("city"), result.getString("street_address")));

			TV_Location.refresh();

		} catch (SQLException e) {
			ApplicationUtils.alerterror("Ошибка ", "Таблица «Местоположение» не обновилась");
		}
		return;
	}

	/* Функция инициализации таблицы
	 * Формальные параметры:
	 * query - параметры запроса.
	 * Локальные переменные:
	 * result - новый объект класса ResultSet;
	 * e - новый объект класса исключений.
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
			ApplicationUtils.alerterror("Ошибка ", "Таблица «Департамент» не обновилась");
			return;
		}
	}

	/* Функция инициализации таблицы
	 * Формальные параметры:
	 * query - параметры запроса.
	 * Локальные переменные:
	 * result - новый объект класса ResultSet;
	 * e - новый объект класса исключений.
	 */
	void set_TV_Employee(String query)
	{
		try {
			ResultSet result = db.selecttable(query);
			employee.clear();

			while (result.next()) 
				employee.add(new Employee(result.getInt("id"), result.getString("FIO"), result.getInt("department_id"), result.getInt("id_resume"), result.getString("post"), result.getString("gender"), result.getLong("salary"), result.getString("Fam_status"), result.getString("passport"), result.getString("email"), result.getBoolean("isFired")));

			TV_Employee.refresh();
		}
		catch(SQLException ex)
		{
			ApplicationUtils.alerterror("Ошибка ", "Таблица «Сотрудники» не обновилась");
			return;
		}
	}

}
