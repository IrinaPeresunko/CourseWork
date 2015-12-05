package ua.nure.peresunko.UI;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import ua.nure.peresunko.main.Demo;
import ua.nure.peresunko.user.Pupil;
import ua.nure.peresunko.user.Teacher;
//import ua.nure.peresunko.main.Demo;
import ua.nure.peresunko.user.User;

public class Authentication extends JFrame{
	
	private static List<User> usersWhoWereTested = new ArrayList<User>();
	
	private static final long serialVersionUID = 1L;

	private static JFrame authentication;
	
	JLabel introducing = new JLabel("Здравствуйте! Мы рады приветствовать Вас в системе тестирования! ");
	JLabel label = new JLabel("Пожалуйста, введите свои данные для входа в систему: ");
	JLabel recommendation = new JLabel("(Все поля обязательны для заполнения!!!)");
	//Please enter your details to log in the system
	
	JButton ok = new JButton("Войти");
	
	static JTextField name = new JTextField();
	static JTextField lastName = new JTextField();
	static JTextField login = new JTextField();
	static JPasswordField password = new JPasswordField();
	
	JLabel labelName = new JLabel("Укажите свое имя: ");
	JLabel labelLastName = new JLabel("Укажите свою фамилию: ");
	JLabel labelLogin = new JLabel("Введите логин: ");
	JLabel labelPassword = new JLabel("Пароль: ");

	/*
	 * method to return uniqueInstance of class 
	 */
	public static JFrame getInstance() {
			if (authentication == null) {
				authentication = new JFrame("Аутентификация");
			}
			return authentication;
	}
	
	public Authentication(){
		
		Authentication.getInstance();
		
		authentication.setBounds(350, 150, 600, 400);
		//authentication.setBounds(300, 300, 1200, 700);
		authentication.setLayout(null);
		authentication.setResizable(false);
		authentication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		introducing.setBounds(130, 10, 1000, 40);
		label.setBounds(150, 50, 400, 40);
		recommendation.setBounds(200, 70, 400, 40);
		
		authentication.add(introducing);
		authentication.add(label);
		authentication.add(recommendation);
		
		authentication.setVisible(true);
		
		labelName.setBounds(120, 120, 400, 30);
		labelLastName.setBounds(80, 160, 400, 30);
		labelLogin.setBounds(130, 200, 400, 30); 
		labelPassword.setBounds(150, 240, 400, 30); 
		
		name.setBounds(240, 120, 250, 30);
		lastName.setBounds(240, 160, 250, 30);
		login.setBounds(240, 200, 250, 30);
		password.setBounds(240, 240, 250, 30);
		
		authentication.add(labelName);
		authentication.add(labelLastName);
		authentication.add(labelLogin);
		authentication.add(labelPassword);
		
		authentication.add(name);
		authentication.add(lastName);
		authentication.add(login);
		authentication.add(password);
		
		password.setEchoChar('*');
		
		ok.setBounds(250, 300, 120, 30);
		
		authentication.add(ok);
		
		ok.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){ 
				
				User user = null;
				//new TeacherPage();
				//new ChoiceSection((Pupil) user);
				boolean isTeacher = Demo.getUsersList().isTeacher(new Teacher(name.getText(), lastName.getText(),
						login.getText(), String.valueOf(password.getPassword())));
				
				if(isTeacher) {
					user = new Teacher(name.getText(), lastName.getText(),
							login.getText(), String.valueOf(password.getPassword()));
				} else {
					user = new Pupil(name.getText(), lastName.getText(),
							login.getText(), String.valueOf(password.getPassword()));
				}
				System.out.println(isTeacher);
				
				boolean isEmptyFields = name.getText().equals("") || lastName.getText().equals("")
						|| login.getText().equals("") || String.valueOf(password.getPassword()).equals("");

				if(isEmptyFields) {
					JOptionPane.showMessageDialog(authentication, "Все поля являются обязательными для ввода! ");
				} else {
					if(Demo.getUsersList().contains(user) && usersWhoWereTested.contains(user) == false) {
						if(isTeacher) {
							new TeacherPage();
						} else {
							usersWhoWereTested.add(user);
							new ChoiceSection((Pupil) user);
						}
					} else {
						JOptionPane.showMessageDialog(authentication, "Данный пользователь не найден в системе."
								+ " Проверьте правильность ввода и попробуйте еще раз! ");
					}
				}
			}	
		});
		
	}
	
}

