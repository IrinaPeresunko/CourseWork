package ua.nure.peresunko.UI;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import ua.nure.peresunko.comparator.SortingByLastName;
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
	//private static User[] pupils;
	private static List<User> pupils = new ArrayList<User>();
	
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
		
		User[] tempPupils = UsersListThatContainsInTheSchoolBase.getPupilsList();
		pupilsData.addElement("\n");
		
		for(int i = 0; i < tempPupils.length; i++){
			pupils.add(tempPupils[i]);
		}
		
		pupils.sort(new SortingByLastName());
		
		for(User user : pupils) {
			pupilsData.addElement(getPupilDescription((Pupil) user));
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
		
		pupils.add(pupil);
		
		pupilsData.addElement(getPupilDescription(pupil));
		viewList.setModel(pupilsData);
	}
	
	public static String getPupilDescription(Pupil pupil) {
		String description;
		
		StringBuilder builder = new StringBuilder();
		description = (builder.append(pupil.getLastName())
					.append(" ").append(pupil.getName())
					.append(" -  оценка по математике = ").append(pupil.getMarkByMath())
					.append(", оценка по украинскому языку = ").append(pupil.getMarkByLanguage())).toString();
		return description;
	}

}
