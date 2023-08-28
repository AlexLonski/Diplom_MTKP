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

/* �����:
 * Save_redact_Click - ����������/����������
 * ������ � ������� "������".
 * �������:
 * set_lines - ������������� ����� �����;
 * ���������:
 * set_button - ������������� ������ ������;
 * setCheck_butl - ������������� ���������
 * ��� ������;
 * set_window - ��������� �����������;
 * getCheck_butl - ������� ���������.
 * ���������� ���������:
 * tf_fio_res, tf_des_job, tf_job_time,
 * tf_des_sal, tf_city, tf_birth,
 * tf_email_res,ta_about, ta_achievs,
 * ta_skills, ta_add_info, ta_work_exp,
 * ta_an_lang, id_res - ���� ������� "������";
 * Check_butl - ����������� ������ �����;
 * m - ����� ������ ����������;
 * db - ����� ������ ������ DB.
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

	/* ����� ����������/���������� ������
	 * � ������� "������"
	 * ���������� ���������:
	 * event - �������� ������� c ������.
	 * ��������� ����������:
	 * res - ����� ������ ������ Resume
	 * text - ��������� �����������;
	 * stage - ����� ������� ������ Stage;
	 * e - ����� ������� ������� ����������.
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
					ApplicationUtils.alertinformation("������", "��������� ���� � �������");
					return;
				}
				try {
					db.insert("resume_","fio,desired_position,job_time,your_salary,city,birhday,email,about_me,achievs,skills,add_info,work_exp,another_language", res.getSQL());
					m.set_TV_Resume("resume_");
					Stage stage = (Stage) Res_button.getScene().getWindow();
					stage.close();
				} catch (SQLException e) {
					ApplicationUtils.alerterror("������", "�������� � ������������ � ���� ������");
					return;
				}

			} else
				ApplicationUtils.alertinformation("��������", "�� �� ��������� ����");
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
					ApplicationUtils.alertinformation("��������", "��������� ���� � �������");
					return;
				}
				try {

					db.update("resume_", "fio = '" + res.getFio() + "', desired_position = '" + res.getDesiredPosition() + "', job_time = '" + res.getJobTime() + "', your_salary = '" + res.getYourSalary() + "', city = '" + res.getCity() + "', birhday = '" + res.get_Birthday_access() + "',email" + " = '" + res.getEmail()+ "', about_me" + " = '" + res.getAboutMe() +  "', achievs" + " = '" + res.getAchievs() + "', skills" + " = '" + res.getSkills() + "', add_info" + " = '" + res.getAddInfo() + "', work_exp = '" + res.getWorkExp() + "', another_language = '" + res.getAnotherLanguage() + "'", res.getId().toString());

					m.set_TV_Resume("resume_");
					Stage stage = (Stage) Res_button.getScene().getWindow();
					stage.close();
				} catch (SQLException e) {
					ApplicationUtils.alerterror("������", "�������� � ������������ � ���� ������");
					return;
				}
			} else
				ApplicationUtils.alertinformation("��������", "�� �� ��������� ����");
		}

	}

	/* ������� ������������� ������
	 * ���������� ���������:
	 * r - ������ ������ Resume.
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

	/* ��������� ������������� ������
	 * ���������� ���������:
	 * controller - ����� ������ ����������� �������.
	 */
	void set_window(Controller controller)
	{
		m = controller;
	}

	/* ��������� ������������� ������
	 * ���������� ���������:
	 * value - ����� �� ������.
	 */
	void set_button(String value)
	{
		Res_button.setText(value);
	}

	/* ��������� ������������� ������
	 * ���������� �������� ���
	 * ����������/���������.
	 */
	Boolean getCheck_butl() {
		return Check_butl;
	}

	/* ��������� ������������� ������
	 * ���������� ���������:
	 * check_butl - �������� ��� �������������.
	 */
	void setCheck_butl(Boolean check_butl) {
		Check_butl = check_butl;
	}

}
