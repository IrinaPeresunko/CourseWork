package ua.nure.peresunko.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ua.nure.peresunko.user.Pupil;

public class ChoiceSection extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private static JFrame choiceSection;
	private JLabel introducing = new JLabel("Выберите ОДИН предмет для тестирования:  ");
	
	private ButtonGroup group = new ButtonGroup();
	private JRadioButton math = new JRadioButton("Математика");
	private JRadioButton language = new JRadioButton("Украинский язык");
	
	private  JButton ok = new JButton("Начать тест");

	/*
	 * method to return uniqueInstance of class 
	 */
	public static JFrame getInstance() {
			if (choiceSection == null) {
				choiceSection = new JFrame("Выбор предмета для тестирования");
			}
			return choiceSection;
	}
	
	public ChoiceSection(final Pupil pupil){
		
		ChoiceSection.getInstance();
		Authentication.getInstance().setVisible(false);
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
		
        choiceSection.add(mainPanel);
		choiceSection.setBounds(350, 150, 700, 330);
		choiceSection.setResizable(false);
		choiceSection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		choiceSection.setVisible(true);
		
		JPanel introducePanel = new JPanel(new FlowLayout());
		introducePanel.add(introducing);
		group.add(math);
		group.add(language);
		introducePanel.add(math);
		introducePanel.add(language);
		mainPanel.add(introducePanel, BorderLayout.NORTH);
		
		JPanel htmlPanel = new JPanel();
		
		Font font = new Font(null, Font.PLAIN, 11);
		JLabel htmlLabel = new JLabel();
		htmlLabel.setText(getRules());
		htmlLabel.setFont(font);
		
		htmlPanel.add(htmlLabel);
		mainPanel.add(htmlPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(ok);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
		ok.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){ 
				if(math.isSelected()) {
					new MathTestPage(pupil);
				} else if(language.isSelected()) {
					
				} else {
					JOptionPane.showMessageDialog(choiceSection, "Выберите предмет для тестирования!");
				}
			}
		});
		
	}
	
	public static String getRules() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<html><h4> Ознакомьтесь, пожалуйста, с правилами прохождения тестирования: </h4>")
			.append("<font face = 'verdana' size = 2,5> ")
			.append("1) Разговоры, хождения, самовольная пересадка с мест запрещены.<br><br>")
			.append("2) Так же запрещено пользоваться учебниками, конспектами и электронной аппаратурой.<br><br>")
			.append("3) При нарушении дисциплины преподаватель имеет право удалять нарушителей с тестирования.<br><br>")
			.append("4) Для ответа на вопрос нужно с помощью мыши выделить верный вариант ответа. <br><br>")
			.append("5) В заданиях с выбором ответов может быть несколько правильных ответов. <br><br>")
			.append("6) В задании, где необходимо ввести ответ - вводите только его значение, не добавляя объяснений. </html>");
		
		return buffer.toString();
	}
}
