package application;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

/* Метод:
 * Save_redact_Click - сохранение/собавление
 * данных в таблице "Резюме".
 * Функции:
 * set_lines - инициализация полей формы;
 * Процедуры:
 * set_button - инициализация текста кнопки;
 * setCheck_butl - инициализация параметра
 * для метода;
 * set_window - настройки подключения;
 * getCheck_butl - возврат параметра.
 * Глобальные параметры:
 * tf_fio_res, tf_des_job, tf_job_time,
 * tf_des_sal, tf_city, tf_birth,
 * tf_email_res,ta_about, ta_achievs,
 * ta_skills, ta_add_info, ta_work_exp,
 * ta_an_lang, id_res - поля таблицы "Резюме";
 * Check_butl - графический модуль формы;
 * m - новый объект интерфейса;
 * db - новый объект класса DB.
 */

public class RedResumeController {
	@FXML
	private TextField tf_fio_res;
	@FXML
	private TextField tf_des_job;
	@FXML
	private TextField tf_job_time;
	@FXML
	private TextField tf_des_sal;
	@FXML
	private TextField tf_city;
	@FXML
	private DatePicker tf_birth;
	@FXML
	private TextField tf_email_res;
	@FXML
	private TextArea ta_about;
	@FXML
	private TextArea ta_achievs;
	@FXML
	private TextArea ta_skills;
	@FXML
	private TextArea ta_add_info;
	@FXML
	private TextArea ta_work_exp;
	@FXML
	private TextArea ta_an_lang;
	@FXML
	private Button Res_button;

	private int id_res;
	private Boolean Check_butl;
	Controller m = null;
	DB db = new DB();
	@FXML

	/* Метод сохранения/Добавления данных
	 * в таблице "Резюме"
	 * Формальные параметры:
	 * event - аргумент события c экрана.
	 * Локальные переменные:
	 * res - новые объекы класса Resume
	 * text - параметры подключения;
	 * stage - новые объекты класса Stage;
	 * e - новые объекты классов исключений.
	 */
	void Save_redact_Click(MouseEvent event) {
		if (Check_butl == false )
		{
			if (tf_fio_res.getText() != "" && tf_des_job.getText() != "" && tf_job_time.getText() != "" && tf_des_sal.getText() != "" && tf_city.getText() != "" && tf_birth.getValue().toString() != "" ) 
			{
				Resume res;
				try 
				{
					res = new Resume(id_res, tf_fio_res.getText(), tf_des_job.getText(), tf_job_time.getText(), Long.parseLong(tf_des_sal.getText()), tf_city.getText(), Date.from(tf_birth.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), tf_email_res.getText(), ta_about.getText(), ta_achievs.getText(), ta_skills.getText(), ta_add_info.getText(),ta_work_exp.getText(), ta_an_lang.getText());
				}
				catch(NumberFormatException e)
				{
					ApplicationUtils.alertinformation("Ошибка", "Проверьте поля с числами");
					return;
				}
				try {
					db.insert("resume_","fio,desired_position,job_time,your_salary,city,birhday,email,about_me,achievs,skills,add_info,work_exp,another_language", res.getSQL());
					m.set_TV_Resume("resume_");
					Stage stage = (Stage) Res_button.getScene().getWindow();
					stage.close();
				} catch (SQLException e) {
					ApplicationUtils.alerterror("Ошибка", "Проблема с подключением к базе данных");
					return;
				}

			} else
				ApplicationUtils.alertinformation("Внимание", "Вы не заполнили поля");
		}else
		{

			if (tf_fio_res.getText() != "" && tf_fio_res.getText() != "" && tf_des_job.getText() != "" && tf_job_time.getText() != "" && tf_des_sal.getText() != ""&& tf_city.getText() != "" && tf_birth.getValue().toString() != "" ) 
			{	
				Resume res ;
				try {
					res = new Resume(id_res, tf_fio_res.getText(), tf_des_job.getText(), tf_job_time.getText(), Long.parseLong(tf_des_sal.getText()), tf_city.getText(), Date.from(tf_birth.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), tf_email_res.getText(), ta_about.getText(), ta_achievs.getText(), ta_skills.getText(), ta_add_info.getText(),ta_work_exp.getText(), ta_an_lang.getText());
				}
				catch(NumberFormatException e)
				{
					ApplicationUtils.alertinformation("Внимание", "Проверьте поля с числами");
					return;
				}
				try {

					db.update("resume_", "fio = '" + res.getFio() + "', desired_position = '" + res.getDesiredPosition() + "', job_time = '" + res.getJobTime() + "', your_salary = '" + res.getYourSalary() + "', city = '" + res.getCity() + "', birhday = '" + res.get_Birthday_access() + "',email" + " = '" + res.getEmail()+ "', about_me" + " = '" + res.getAboutMe() +  "', achievs" + " = '" + res.getAchievs() + "', skills" + " = '" + res.getSkills() + "', add_info" + " = '" + res.getAddInfo() + "', work_exp = '" + res.getWorkExp() + "', another_language = '" + res.getAnotherLanguage() + "'", res.getId().toString());

					m.set_TV_Resume("resume_");
					Stage stage = (Stage) Res_button.getScene().getWindow();
					stage.close();
				} catch (SQLException e) {
					ApplicationUtils.alerterror("Ошибка", "Проблема с подключением к базе данных");
					return;
				}
			} else
				ApplicationUtils.alertinformation("Внимание", "Вы не заполнили поля");
		}

	}

	/* Функция инициализации данных
	 * Формальные параметры:
	 * r - объект класса Resume.
	 */
	void set_lines(Resume r)
	{
		id_res = r.getId();
		tf_fio_res.setText(r.getFio());
		tf_des_job.setText(r.getDesiredPosition());
		tf_job_time.setText(r.getJobTime());
		tf_des_sal.setText(String.valueOf(r.getYourSalary()));
		tf_city.setText(r.getCity());
		tf_birth.setValue(LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(r.getBirhday())));
		tf_email_res.setText(r.getEmail());
		ta_about.setText(r.getAboutMe().replaceAll(";","\n").replace(".",".\n").replaceAll("\n\n","\n"));
		ta_achievs.setText(r.getAchievs().replaceAll(";","\n").replace(".",".\n").replaceAll("\n\n","\n"));
		ta_skills.setText(r.getSkills().replaceAll(";","\n").replace(".",".\n").replaceAll("\n\n","\n"));
		ta_add_info.setText(r.getAddInfo().replaceAll(";","\n").replace(".",".\n").replaceAll("\n\n","\n")); 
		ta_work_exp.setText(r.getWorkExp());
		ta_an_lang.setText(r.getAnotherLanguage().replaceAll(";","\n").replace(".",".\n").replaceAll("\n\n","\n"));

	}

	/* Процедура инициализации данных
	 * Формальные параметры:
	 * controller - новый объект наследуемых классов.
	 */
	void set_window(Controller controller)
	{
		m = controller;
	}

	/* Процедура инициализации данных
	 * Формальные параметры:
	 * value - текст на кнопке.
	 */
	void set_button(String value)
	{
		Res_button.setText(value);
	}

	/* Процедура инициализации данных
	 * Возвращает параметр для
	 * добавления/изменения.
	 */
	Boolean getCheck_butl() {
		return Check_butl;
	}

	/* Процедура инициализации данных
	 * Формальные параметры:
	 * check_butl - параметр для инициализации.
	 */
	void setCheck_butl(Boolean check_butl) {
		Check_butl = check_butl;
	}

}
