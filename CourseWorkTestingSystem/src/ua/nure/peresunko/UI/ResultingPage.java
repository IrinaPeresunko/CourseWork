package ua.nure.peresunko.UI;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ua.nure.peresunko.user.Pupil;


public class ResultingPage extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private static JFrame resultingPage;
	
	private static Pupil pupil;
	private static double countOfPoints;
	private static double maxNumberOfPoints;

	/*
	 * method to return uniqueInstance of class 
	 */
	public static JFrame getInstance() {
			if (resultingPage == null) {
				resultingPage = new JFrame("Результат тестирования");
			}
			return resultingPage;
	}
	
	public ResultingPage(Pupil pupil, int countOfPoints, double maxNumberOfPoints){
		
		ResultingPage.getInstance();
		Authentication.getInstance().setVisible(false);
		MathTestPage.getInstance().setVisible(false);
		
		ResultingPage.maxNumberOfPoints = maxNumberOfPoints;
		
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        ResultingPage.countOfPoints = countOfPoints;
		ResultingPage.pupil = pupil;
		
        resultingPage.add(mainPanel);
		resultingPage.setBounds(400, 180, 500, 300);
		resultingPage.setResizable(false);
		resultingPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultingPage.setVisible(true);
		
		JPanel htmlPanel = new JPanel();
		
		Font font = new Font(null, Font.PLAIN, 11);
		JLabel htmlLabel = new JLabel();
		htmlLabel.setText(getRules());
		htmlLabel.setFont(font);
		
		htmlPanel.add(htmlLabel);
		mainPanel.add(htmlPanel, BorderLayout.NORTH);
		
	}
	
	public static String getRules() {
		StringBuffer buffer = new StringBuffer();
		double markIn12System = pupil.getMarkByMath();
		buffer.append("<html><h2> Результат тестирования: </h2>")
			.append("<font face = 'verdana' size = 3> ")
			.append("<b> Количество правильных ответов: ").append(countOfPoints)
			.append("<b>  из  ").append(maxNumberOfPoints).append("<b> возможных. <br><br>")
			.append("Итоговая оценка по 12-бальной шкале : ").append(markIn12System)
			.append("<br><br>Благодарим Вас за прохождение теста! <br><br>")
			.append("Успехов в учебе! До новых встреч! :)<br></html>");
		
		return buffer.toString();
	}
}
