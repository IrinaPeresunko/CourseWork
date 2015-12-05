package ua.nure.peresunko.UI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ua.nure.peresunko.answers.Answer;
import ua.nure.peresunko.answers.AnswerForChoiceQuestion;
import ua.nure.peresunko.answers.AnswerForFillTheGapQuestion;
import ua.nure.peresunko.answers.AnswerForTrueFalseQuestion;
import ua.nure.peresunko.main.Demo;
import ua.nure.peresunko.questions.ChoiceQuestion;
import ua.nure.peresunko.questions.Question;
import ua.nure.peresunko.questions.QuestionFillTheGaps;
import ua.nure.peresunko.questions.QuestionTrueFalse;
import ua.nure.peresunko.testing.TestVariant;
import ua.nure.peresunko.user.Pupil;

public class MathTestPage extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private static JFrame testPage;
	
	private JLabel introducing = new JLabel("Рады приветствовать Вас в системе. Время выполнения теста: 45 минут. "
			+ "Не забывайте отмечать выбранные ответы и заполнять все необходимые поля!");
	
	private int countOfQuestions = 15;
	
	private TestVariant test; 
	private int countOfChoiceQuestions = 5;
	private ButtonGroup[] groups = new ButtonGroup[countOfChoiceQuestions + 2];
	private int[] positionOfChoiceQuestion = {2,3,4,5,6,11,12};
	private JRadioButton[][] questionsVar = new JRadioButton[countOfChoiceQuestions + 2][];
	private JButton endTestBtn = new JButton("Завершить тест");

	private Question[] questions = createQuestions();
	private Answer[] answers = createAnswers();
	private int[] positionOfQuestionsWithTextField = {0,1,7,8,9,10,13,14};
	private JTextField[] fields = new JTextField[8];
	
	/*
	 * method to return uniqueInstance of class 
	 */
	public static JFrame getInstance() {
			if (testPage == null) {
				testPage = new JFrame("Тест");
			}
			return testPage;
	}
	
	public MathTestPage(final Pupil pupil){
		
		MathTestPage.getInstance();
		ChoiceSection.getInstance().setVisible(false);
		
		initialization();
		
		testPage.setBounds(70, 10, 1200, 600);
		testPage.setLayout(null);
		testPage.setResizable(false);
		testPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		introducing.setBounds(130, 10, 1000, 40);
		testPage.add(introducing);
		testPage.add(endTestBtn);
		
		for(int i = 0, j = 10, k = 40; i < 15; i++) {
			j = j + 20; k = k + 20;
			JLabel question = new JLabel(test.getQuestions()[i].getQuestion());
			question.setBounds(50, j, 2000, k);
			testPage.add(question);
			if(i == 14) {
				question = new JLabel("Сколько раз она сумела набросить кольцо на крючок?");
				question.setBounds(65, j+10, 2000, k+10);
				testPage.add(question);
			}
		}
		
		initializeGroups();
		int indentsForChoiceQiestions[] = {110, 140, 170, 200, 230, 380, 410};
		for(int i = 0; i < positionOfChoiceQuestion.length; i++) {
			if(positionOfChoiceQuestion[i] == 11 || positionOfChoiceQuestion[i] == 12) {
				createTrueFalseQuestions(i,positionOfChoiceQuestion[i], indentsForChoiceQiestions[i]);
			} else {
				createChoiceQuestions(i,positionOfChoiceQuestion[i], indentsForChoiceQiestions[i]);
			}
		}
		
		//print();
		
		int[] indentByX = {500,500,300,300,530,580,950,400};
		int[] indentByY = {50,80,260,290,320,350,440,490};
		for(int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField();
			fields[i].setBounds(indentByX[i], indentByY[i], 120, 20);
			testPage.add(fields[i]);
		}
		
		endTestBtn.setBounds(550, 520, 150, 30);
		
		testPage.setVisible(true);
		
		endTestBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){ 
				
				int calculatingMark = calculateMark();
				double calculatingMaxCountOfPoints = test.calculateMaxCountOfPointsInTheTest();
				int resultMark = (int) ((calculatingMark*12) / calculatingMaxCountOfPoints);
				
				pupil.setMarkByMath(resultMark);
				Demo.getUsersList().setMarkToPupilByMath(pupil, resultMark);
				Demo.getUsersList().print();
				new ResultingPage(calculatingMark , calculatingMaxCountOfPoints, resultMark);
			}
		});
	}
	
	public void initialization() {
		this.test = new TestVariant(1, "math", questions, answers);
		test.setCountOfQuestions(countOfQuestions);
	}
	
	public Question[] createQuestions() {
		
		Question[] questions = new Question[countOfQuestions];
		
		questions[0] = new QuestionFillTheGaps(
				"1) Запиши цифрами число двести тридцать восемь тысяч семьсот шесть.");
		questions[1] = new QuestionFillTheGaps(
				"2) Из чисел 7860, 10037, 10101, 3321 выбери и запиши наибольшее число.");
		
		questions[2] = new ChoiceQuestion(
				"3) Какое действие выполняется последним 470 + 360 : 60 × 19?");
		questions[3] = new ChoiceQuestion(
				"4) Какое действие выполняется первым (400 - 80 × 3) : 20?");
		questions[4] = new ChoiceQuestion(
				"5) Заполни пропуски: 5090 м = … км … м.");
		questions[5] = new ChoiceQuestion(
				"6) 12 кг печенья стоят 240 рублей. Сколько стоят 7 кг печенья?");
		questions[6] = new ChoiceQuestion(
				"7) Ширина прямоугольника 7 дм, что на 2 дм меньше, чем длина. Найди площадь прямоугольника.");
		
		questions[7] = new QuestionFillTheGaps(
				"8) Реши уравнение: х + 50 = 220.");
		questions[8] = new QuestionFillTheGaps(
				"9) Реши уравнение: 5 · х - 38 = 42.");
		questions[9] = new QuestionFillTheGaps(
				"10) Найди закономерность и запиши еще одно число 10, 2, 11, 4, 12, 6, 13, …");
		questions[10] = new QuestionFillTheGaps(
				"11) Вставь пропущенные цифры: *5* + 3*8 = 632. Найди сумму всех пропущенных цифр.");
		
		questions[11] = new QuestionTrueFalse(
				"12) Можно ли расставить скобки так, чтобы равенство 84 - 40 : 2 + 12 = 52 было верным?");
		questions[12] = new QuestionTrueFalse("13) В новогодние подарки раскладывают конфеты. Всего 199 конфет."
				+ " В каждый подарок надо положить по 5 конфет. Остаток конфет будет равен 4?");
		
		questions[13] = new QuestionFillTheGaps(
				"14) Масса слона 6700 кг, а льва 200 кг. Рысь весит в 100 раз меньше, чем слон и лев вместе."
				+ " С помощью какого выражения можно узнать массу рыси?");
		questions[14] = new QuestionFillTheGaps(
				"15) Нужно набросить кольцо на крючок. При каждом попадании даётся 2 бесплатных броска."
				+ " Ира сделала всего 16 бросков, а заплатила только за 4.");
		return questions;
	}
	
	public Answer[] createAnswers() {
		
		Answer[] answers = new Answer[countOfQuestions];
		
		answers[0] = new AnswerForFillTheGapQuestion("238706");
		answers[1] = new AnswerForFillTheGapQuestion("10101");
		
		answers[2] = new AnswerForChoiceQuestion(
				new String[] {"Сложение", "Деление", "Умножение", "Одновременно"}, "Сложение" );
		answers[3] = new AnswerForChoiceQuestion(
				new String[] {"Вычитание", "Деление", "Умножение", "Одновременно"}, "Умножение" );
		answers[4] = new AnswerForChoiceQuestion(
				new String[] {"5 км 90 м", "50 км 9 м", "50 км 90 м", "5 км 900 м"}, "5 км 90 м" );
		answers[5] = new AnswerForChoiceQuestion(
				new String[] {"120 рублей", "140 рублей", "160 рублей", "130 рублей"}, "140 рублей" );
		answers[6] = new AnswerForChoiceQuestion(
				new String[] {"75 кв. дм", "63 кв. дм", "60 кв. дм", "35 кв. дм"}, "35 кв. дм" );
		
		answers[7] = new AnswerForFillTheGapQuestion("170");
		answers[8] = new AnswerForFillTheGapQuestion("16");
		answers[9] = new AnswerForFillTheGapQuestion("8");
		answers[10] = new AnswerForFillTheGapQuestion("13"); //632 - 378 = 254
		
		answers[11] = new AnswerForTrueFalseQuestion("Да");
		answers[12] = new AnswerForTrueFalseQuestion("Да");
		
		answers[13] = new AnswerForFillTheGapQuestion("(6700 + 200) : 100");
		answers[14] = new AnswerForFillTheGapQuestion("6");
		
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
	
	public void createChoiceQuestions(int positionInTheQuestionsVar, int numberOfQuestion, int k) {
		questionsVar[positionInTheQuestionsVar] = new JRadioButton[4];
		for(int i = 0; i < questionsVar[positionInTheQuestionsVar].length; i++) {
			questionsVar[positionInTheQuestionsVar][i] = new JRadioButton(
					answers[numberOfQuestion].getVariantsOfAnswers()[i]);
		}
		
		addToGroup(groups[positionInTheQuestionsVar], questionsVar, positionInTheQuestionsVar);
		addToPage(questionsVar, positionInTheQuestionsVar);
		setBoundsToAnswers(positionInTheQuestionsVar, numberOfQuestion, k);
		
	}
	
	public void createTrueFalseQuestions(int positionInTheQuestionsVar, int numberOfQuestion, int k) {
		questionsVar[positionInTheQuestionsVar] = new JRadioButton[2];
		for(int i = 0; i < questionsVar[positionInTheQuestionsVar].length; i++) {
			questionsVar[positionInTheQuestionsVar][i] = new JRadioButton(
					answers[numberOfQuestion].getVariantsOfAnswers()[i]);
		}
		
		addToGroup(groups[positionInTheQuestionsVar], questionsVar, positionInTheQuestionsVar);
		addToPage(questionsVar, positionInTheQuestionsVar);
		setBoundsToAnswers(positionInTheQuestionsVar, numberOfQuestion, k);
		
	}
	
	private void addToGroup(ButtonGroup group, JRadioButton[][] questionsVar, int positionInTheQuestionsVar) {
		for(int i = 0; i < questionsVar[positionInTheQuestionsVar].length; i++) {
			group.add(questionsVar[positionInTheQuestionsVar][i]);		
		}
	}
	
	private void addToPage(JRadioButton[][] questionsVar, int positionInTheQuestionsVar) {
		for(int i = 0; i < questionsVar[positionInTheQuestionsVar].length; i++) {
			testPage.add(questionsVar[positionInTheQuestionsVar][i]);
		}
	}
	
	private void initializeGroups() {
		for(int i = 0; i < groups.length; i++) {
			groups[i] = new ButtonGroup();
		}
	}
	
	private void setBoundsToAnswers(int positionInTheQuestionsVar, int numberOfQuestion, int k) {
		if(numberOfQuestion == 6) {
			for(int i = 0, j = 550; i < questionsVar[positionInTheQuestionsVar].length; i++) {
				j = j + 100;
				questionsVar[positionInTheQuestionsVar][i].setBounds(j, k, 100, 20);
				if(i == 3) {
					questionsVar[positionInTheQuestionsVar][i].setBounds(j, k, 150, 20);
				}
			}
		} else if(numberOfQuestion == 11 || numberOfQuestion == 12 ) {
			int j = 0;
			if(numberOfQuestion == 11) j = 500;
			else j = 850;
			for(int i = 0; i < questionsVar[positionInTheQuestionsVar].length; i++) {
				j = j + 100;
				questionsVar[positionInTheQuestionsVar][i].setBounds(j, k, 100, 20);
			}
		} else {
			for(int i = 0, j = 350; i < questionsVar[positionInTheQuestionsVar].length; i++) {
				j = j + 100;
				questionsVar[positionInTheQuestionsVar][i].setBounds(j, k, 100, 20);
				if(i == 3) {
					questionsVar[positionInTheQuestionsVar][i].setBounds(j, k, 150, 20);
				}
			}
		}
	}
	
	private int calculateMark() {
		int mark = 0;
		for(int i = 0; i < questionsVar.length; i++) {
			if(getSelectedAnswer(i).equals(answers[positionOfChoiceQuestion[i]].getCorrectAnswer())) {
				mark = (int) (mark + questions[positionOfChoiceQuestion[i]].getNumberOfPoints());
			}
		}
		
		String correctAnswer;
		for(int i = 0; i < fields.length; i++) {
			correctAnswer = answers[positionOfQuestionsWithTextField[i]].getCorrectAnswer();
			if(fields[i].getText().equals(correctAnswer)) {
				mark = (int) (mark + questions[positionOfQuestionsWithTextField[i]].getNumberOfPoints());
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
