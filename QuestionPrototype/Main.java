/**
 * @author Timotej Kovacka
 * running Main creates 2 objects and tests their functionality
 */
public class Main {

	public static void main(String[] args) {
		FourMultipleChoice fmc = new FourMultipleChoice();
		fmc.setText("What was the original name of Java language?");
		fmc.addChoice("Seven up", false);
		fmc.addChoice("Duke", false);
		fmc.addChoice("Oak", true);
		fmc.printQuestion();
		fmc.checkAnswer("1.2");
		fmc.checkAnswer("1.3");
		TrueFalseQ tfq = new TrueFalseQ();
		tfq.setText("Was C++ invented before Java?");
		tfq.setAnswer("True");
		tfq.printQuestion();
		tfq.checkAnswer("False");
		tfq.checkAnswer("True");
	}

}
