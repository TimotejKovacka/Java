/**
 * @author Timotej Kovacka
 * Subclass of Question, object representation of True/False question
 */
public class TrueFalseQ extends Question {
	/**
	 * Overrides super's setAnswer
	 */
	@Override
	public void setAnswer(String correctAnswer) {
		if (!correctAnswer.equals("True") && !correctAnswer.equals("False")) {
			System.out.println("A valid answer must be either \"True\" or \"False\".");
		} else {
			super.setAnswer(correctAnswer);
		}
	}

	/**
	 * Overrides super's printQuestion
	 */
	@Override
	public void printQuestion() {
		super.printQuestion();
		System.out.println("Please answer \"True\" or \"False\".");
	}

}
