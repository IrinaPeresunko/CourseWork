package ua.nure.peresunko.UI;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import ua.nure.peresunko.user.Pupil;
import ua.nure.peresunko.user.User;
import ua.nure.peresunko.user.UsersListThatContainsInTheSchoolBase;

public class TeacherPage extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private static JFrame teacherPage;
	private static JList<String> viewList = new JList<String>();
	private static DefaultListModel<String> pupilsData = new DefaultListModel<String>();
	
	JButton ok = new JButton("Добавить ученика");
	JLabel label = new JLabel("                                                   "
			+ "Успеваемость учеников 4-ых классов");
	private static User[] pupils;
	
	/*
	 * method to return uniqueInstance of class 
	 */
	public static JFrame getInstance() {
			if (teacherPage == null) {
				teacherPage = new JFrame("Информация об учениках");
			}
			return teacherPage;
	}
	
	public TeacherPage(){
		
		Authentication.getInstance().setVisible(false);
		TeacherPage.getInstance();
		
		teacherPage.setBounds(300, 150, 600, 400);
		teacherPage.setLayout(new BorderLayout());
		teacherPage.setResizable(false);
		teacherPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		teacherPage.setVisible(true);

		pupils = UsersListThatContainsInTheSchoolBase.getPupilsList();
		pupilsData.addElement("\n");
		for(int i = 0; i < pupils.length; i++){
			pupilsData.addElement(getPupilDescription((Pupil) pupils[i]));
		}
		
		teacherPage.add(viewList, BorderLayout.CENTER);
		viewList.setModel(pupilsData);
	
		teacherPage.add(ok, BorderLayout.SOUTH);
		teacherPage.add(label, BorderLayout.NORTH);
		
		ok.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){ 
				new AddPupil();
			}
		});
	}
	
	public static void addPupil(Pupil pupil) {
		viewList.removeAll();
		
		Pupil[] tempPupils = new Pupil[pupils.length+1];
		for(int i = 0; i < tempPupils.length - 1; i++) {
			tempPupils[i] = (Pupil) pupils[i];
		}
		tempPupils[tempPupils.length - 1] = pupil;
		pupils = tempPupils;
		
		pupilsData.addElement(getPupilDescription(pupil));
		viewList.setModel(pupilsData);
	}
	
	public static String getPupilDescription(Pupil pupil) {
		String description;
		
		StringBuilder builder = new StringBuilder();
		description = (builder.append(pupil.getName())
					.append(" ").append(pupil.getLastName())
					.append(" -  оценка по математике = ").append(pupil.getMarkByMath())
					.append(", оценка по украинскому языку = ").append(pupil.getMarkByLanguage())).toString();
		return description;
	}

}
