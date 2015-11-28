package ua.nure.peresunko.UI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ua.nure.peresunko.main.Demo;
import ua.nure.peresunko.user.Pupil;
import ua.nure.peresunko.user.User;

public class AddPupil extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static JFrame addFrame;
	
	static JTextField name = new JTextField();
	static JTextField lastName = new JTextField();
	static JTextField login = new JTextField();
	static JPasswordField password = new JPasswordField();
	
	JLabel labelName = new JLabel("Укажите имя: ");
	JLabel labelLastName = new JLabel("Укажите фамилию: ");
	JLabel labelLogin = new JLabel("Введите логин: ");
	JLabel labelPassword = new JLabel("Пароль: ");
	
	JButton ok = new JButton("Добавить");
	
	/*
	 * method to return uniqueInstance of class 
	 */
	public static JFrame getInstance() {
			if (addFrame == null) {
				addFrame = new JFrame("Добавление ученика");
			}
			return addFrame;
	}
	
	public AddPupil() {
		
		AddPupil.getInstance();
		
		addFrame.setBounds(450, 150, 400, 300);
		addFrame.setLayout(null);
		addFrame.setResizable(false);
		addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addFrame.setVisible(true);
		
		labelName.setBounds(50, 30, 200, 30);
		labelLastName.setBounds(30, 70, 200, 30);
		labelLogin.setBounds(50, 110, 200, 30); 
		labelPassword.setBounds(80, 150, 200, 30); 
		
		name.setBounds(150, 30, 220, 30);
		lastName.setBounds(150, 70, 220, 30);
		login.setBounds(150, 110, 220, 30);
		password.setBounds(150, 150, 220, 30);
		
		addFrame.add(labelName);
		addFrame.add(labelLastName);
		addFrame.add(labelLogin);
		addFrame.add(labelPassword);
		
		addFrame.add(name);
		addFrame.add(lastName);
		addFrame.add(login);
		addFrame.add(password);
		
		password.setEchoChar('*');
		
		ok.setBounds(150, 220, 120, 30);
		addFrame.add(ok);
		
		ok.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){ 
				User user = new Pupil(name.getText(), lastName.getText(),
							login.getText(), String.valueOf(password.getPassword()));
				
				boolean isEmptyFields = name.getText().equals("") || lastName.getText().equals("")
						|| login.getText().equals("") || String.valueOf(password.getPassword()).equals("");

				if(isEmptyFields) {
					JOptionPane.showMessageDialog(addFrame, "Все поля являются обязательными для ввода! ");
				} else {
					if(Demo.getUsersList().contains(user)) {
						JOptionPane.showMessageDialog(addFrame, "Данный пользователь уже существует в системе.");
					} else {
						Demo.getUsersList().add(user);
						TeacherPage.addPupil((Pupil) user);
						name.setText(null);
						lastName.setText(null);
						password.setText(null);
						login.setText(null);
						
						addFrame.setVisible(false);
					}
				}
			}
		});
		
	}

}
