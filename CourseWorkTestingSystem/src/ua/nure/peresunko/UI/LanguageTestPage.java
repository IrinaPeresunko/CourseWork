package ua.nure.peresunko.UI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import ua.nure.peresunko.answers.Answer;
import ua.nure.peresunko.answers.AnswerForChoiceQuestion;
import ua.nure.peresunko.main.Demo;
import ua.nure.peresunko.questions.ChoiceQuestion;
import ua.nure.peresunko.questions.Question;
import ua.nure.peresunko.testing.TestVariant;
import ua.nure.peresunko.user.Pupil;

public class LanguageTestPage extends JFrame{
	private static final long serialVersionUID = 1L;

	private static JFrame testPage;
	
	private JLabel introducing = new JLabel("Рады приветствовать Вас в системе. Время выполнения теста: 45 минут. "
			+ "Не забывайте отмечать выбранные ответы и заполнять все необходимые поля!");
	
	private TestVariant test; 
	private JButton endTestBtn = new JButton("Завершить тест");
	private int countOfQuestions = 12;
	private Question[] questions = createQuestions();
	private Answer[] answers = createAnswers();
	
	private ButtonGroup[] groups = new ButtonGroup[questions.length];
	private JRadioButton[][] questionsVar = new JRadioButton[questions.length][];
	
	/*
	 * method to return uniqueInstance of class 
	 */
	public static JFrame getInstance() {
			if (testPage == null) {
				testPage = new JFrame("Тест");
			}
			return testPage;
	}
	
	public LanguageTestPage(final Pupil pupil){
		
		LanguageTestPage.getInstance();
		ChoiceSection.getInstance().setVisible(false);
		
		initialization();
		
		testPage.setBounds(70, 10, 1200, 600);
		testPage.setLayout(null);
		testPage.setResizable(false);
		testPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		introducing.setBounds(130, 10, 1000, 40);
		testPage.add(introducing);
		testPage.add(endTestBtn);
		
		endTestBtn.setBounds(550, 520, 150, 30);
		
		int[] width = {200, 200, 600, 300, 200, 300, 500, 550, 450, 550};
		for(int i = 0, j = 20; i < width.length; i++) {
			j = j + 30;
			JLabel question = new JLabel(questions[i].getQuestion());
			question.setBounds(50, j, width[i], 20);
			testPage.add(question);
		}
		
		int heightForAnotherQuestion = 350; 
		for(int i = 0, j = 10; i < 2; i++, j++) {
			JLabel question = new JLabel(questions[j].getQuestion());
			question.setBounds(50, heightForAnotherQuestion, 550, 20);
			testPage.add(question);
			heightForAnotherQuestion = heightForAnotherQuestion + 90;
		}
		
		initializeGroups();
		int[][] indents = {{250, 50, 100, 20},
						{250, 80, 150, 20},
						{600, 110, 150, 20}, 
						{350, 140, 200, 20},
						{250, 170, 150, 20},
						{350, 200, 200, 20},
						{550, 230, 200, 20},
						{600, 260, 100, 20},
						{450, 290, 250, 20},
						{550, 320, 150, 20}};
		
		int[] koef = {100,150,150,200, 200, 200, 200,100,250,150};
		heightForAnotherQuestion = 350; //+20
		for(int i = 0; i < questions.length; i++) {
			setQuestionsToFrame(i,indents, koef, heightForAnotherQuestion);
		}
		
		testPage.setVisible(true);
		
		endTestBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){ 
				int calculatingMark = calculateMark();
				double calculatingMaxCountOfPoints = test.calculateMaxCountOfPointsInTheTest();
				int resultMark = (int) ((calculatingMark*12) / calculatingMaxCountOfPoints);
				
				pupil.setMarkByLanguage(resultMark);
				Demo.getUsersList().setMarkToPupilByLanguage(pupil, resultMark);
				Demo.getUsersList().print();
				new ResultingPage(calculatingMark , calculatingMaxCountOfPoints, resultMark);
			}
		});
	}
	
	public void setQuestionsToFrame(int numberOfQuestion, int[][] indents, int[] koef, int h) {
		questionsVar[numberOfQuestion] = new JRadioButton[3];
		
		for(int i = 0; i < questionsVar[numberOfQuestion].length; i++) {
			questionsVar[numberOfQuestion][i] = new JRadioButton(
					answers[numberOfQuestion].getVariantsOfAnswers()[i]);
		}
		
		addToGroup(numberOfQuestion);
		addToPage(numberOfQuestion);
		if(numberOfQuestion < 10) {
			setBoundsToAnswers(numberOfQuestion, indents, koef);
		} else {
			setBoundsToAnotherAnswers(numberOfQuestion,h);
		}
	}
	
	private void setBoundsToAnswers(int numberOfQuestion, int[][] indents, int[] koef) {
		int x = indents[numberOfQuestion][0],
			y = indents[numberOfQuestion][1],
			w = indents[numberOfQuestion][2],
			h = indents[numberOfQuestion][3];
		int k = -koef[numberOfQuestion]; 
		for(int i = 0; i < questionsVar[numberOfQuestion].length; i++) {
			k = k + koef[numberOfQuestion];
			questionsVar[numberOfQuestion][i].setBounds(x + k,y,w,h);			
		}
	}
	
	private void setBoundsToAnotherAnswers(int numberOfQuestion, int h) {
		if(numberOfQuestion == 11) h = 440;
		for(int i = 0; i < questionsVar[numberOfQuestion].length; i++) {
			h = h + 20;
			questionsVar[numberOfQuestion][i].setBounds(100, h, 500, 20);			
		}
	}
	
	public void initialization() {
		this.test = new TestVariant(1, "language", questions, answers);
		test.setCountOfQuestions(countOfQuestions);
	}
	
	private void addToGroup(int numberOfQuestion) {
		for(int i = 0; i < questionsVar[numberOfQuestion].length; i++) {
			groups[numberOfQuestion].add(questionsVar[numberOfQuestion][i]);		
		}
	}
	
	private void addToPage(int numberOfQuestion) {
		for(int i = 0; i < questionsVar[numberOfQuestion].length; i++) {
			testPage.add(questionsVar[numberOfQuestion][i]);
		}
	}
	
	private void initializeGroups() {
		for(int i = 0; i < groups.length; i++) {
			groups[i] = new ButtonGroup();
		}
	}
	
	public Question[] createQuestions() {
		Question[] questions = new Question[countOfQuestions];
		
		questions[0] = new ChoiceQuestion(
				"1) До тексту можна дібрати: ");
		questions[1] = new ChoiceQuestion(
				"2) Речення виражає: ");
		questions[2] = new ChoiceQuestion(
				"3) Граматичною основою речення: \"В ранковім озері купаються зірки є словосполучення\": ");		
		questions[3] = new ChoiceQuestion(
				"4) Визнач, у якій групі є тільки іменники: ");
		questions[4] = new ChoiceQuestion(
				"5) Іменники змінюються за: ");
		questions[5] = new ChoiceQuestion(
				"6) Прикметники: спритний, жвавий, прудкий є: ");
		questions[6] = new ChoiceQuestion(
				"7) Вибери правильно вжите словосполучення кількісного числівника й іменника: ");
		questions[7] = new ChoiceQuestion(
				"8) Вибери рядок, у якому записано займенники 1-ї особи у формі давального відмінка: ");	
		questions[8] = new ChoiceQuestion(
				"9) Вибери рядок, у якому подано дієслова в неозначеній формі: ");
		questions[9] = new ChoiceQuestion(
				"10) Встав потрібні дієслова у прислів'я \"Зайця ноги ...,  вовка зуби ... \" :");
		questions[10] = new ChoiceQuestion(
				"11) Визначити речення, у якому однорідні підмети: ");
		questions[11] = new ChoiceQuestion(
				"12) У реченні \"Жайвір небо торкає крипом, пахне кашка-білявка медова\" є: ");
		return questions;
	}
	
	public Answer[] createAnswers() {
		
		Answer[] answers = new Answer[countOfQuestions];
		
		answers[0] = new AnswerForChoiceQuestion(
				new String[] {"слова", "правило", "заголовок"}, "заголовок" );
		answers[1] = new AnswerForChoiceQuestion(
				new String[] {"мету", "тему", "закінчену думку"}, "закінчену думку" );	
		answers[2] = new AnswerForChoiceQuestion(
				new String[] {"зірки купаються", "в ранковім озері", "купаються в озері"}, "зірки купаються" );	
		answers[3] = new AnswerForChoiceQuestion(
				new String[] {"дерево, навколо, пліт, обрій", "калина, літак, береза, орел", "рідний, родина, рід, рідня"}, "калина, літак, береза, орел" );
		answers[4] = new AnswerForChoiceQuestion(
				new String[] {"родами, відмінками", "числами, відмінками", "числами, родами"}, "числами, відмінками" );
		answers[5] = new AnswerForChoiceQuestion(
				new String[] {"синонімами", "антонімами", "спільнокореневими словами"}, "синонімами" );	
		answers[6] = new AnswerForChoiceQuestion(
				new String[] {"двадцятий ряд", "шість година", "дві сестри"}, "дві сестри" );	
		answers[7] = new AnswerForChoiceQuestion(
				new String[] {"мене, нас", "тобі, вам", "мені, нам"}, "мені, нам" );		
		answers[8] = new AnswerForChoiceQuestion(
				new String[] {"сприймати, малюють, зробили", "світить, читати, мчати", "збудувати, думати, мріяти"}, "збудувати, думати, мріяти" );	
		answers[9] = new AnswerForChoiceQuestion(
				new String[] {"тримають, болять", "стрибають, годують", "носять, годують"}, "носять, годують" );
		answers[10] = new AnswerForChoiceQuestion(
				new String[] {"Косар стомився, сів під деревом, перепочив;", "До чудової пісні прислухалися і пташки, і дерева, і квіти;",
						"Цього року ми вчасно зібрали картоплю, моркву, буряки."}, "До чудової пісні прислухалися і пташки, і дерева, і квіти;" );
		answers[11] = new AnswerForChoiceQuestion(
				new String[] {"одна граматична основа;", "дві граматичні основи;","три граматичні основи."}, "дві граматичні основи;" );
		return answers;
	}
	
	public void print() {
		for(int i = 0; i < questionsVar.length; i++) {
			for(int j = 0; j < questionsVar[i].length; j++) {
				System.out.print(questionsVar[i][j].getText() + " ");
			}
			System.out.println();
		}
	}
	
	private int calculateMark() {
		int mark = 0;
		for(int i = 0; i < questionsVar.length; i++) {
			if(getSelectedAnswer(i).equals(answers[i].getCorrectAnswer())) {
				mark = (int) (mark + questions[i].getNumberOfPoints());
			}
		}
		return mark;
	}
	
	private String getSelectedAnswer(int numbOfQuestion) {
		for(int i = 0; i < questionsVar[numbOfQuestion].length; i++) {
			if(questionsVar[numbOfQuestion][i].isSelected()) {
				return questionsVar[numbOfQuestion][i].getText();
			}
		}
		return "not choosed";
	}
}
