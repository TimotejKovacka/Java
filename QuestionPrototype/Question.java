
/**
 * @author Timotej Kovacka
 * Superclass Question, object representation of a question
 */
public class Question {
	private String text;
	private String answer;
	protected int id;

	private static int NEXT_ID = 1;

	/**
	 * Constructor for question object with no arguments
	 */
	public Question() {
		this.id = Question.NEXT_ID++;
	};

	/**
	 * Constructor for question object with following arguments
	 * @param text
	 * @param answer
	 */
	public Question(String text, String answer) {
		this.id = Question.NEXT_ID++;
		this.setText(text);
		this.setAnswer(answer);
	}

	/**
	 * Mutator function, sets text to following argument
	 * @param textQuestion
	 */
	public void setText(String textQuestion) {
		this.text = textQuestion;
	}

	/**
	 * Mutator function, sets answer to following argument
	 * @param correctAnswer
	 */
	public void setAnswer(String correctAnswer) {
		this.answer = correctAnswer;
	}

	/**
	 * Accessor function, checks if object parameter is same as argument
	 * @param response
	 * @return
	 */
	public boolean checkAnswer(String response) {
		return response.equals(this.answer);
	}

	/**
	 * Prints question
	 */
	public void printQuestion() {
		System.out.println(this.id + ". " + this.text);
	}

	/**
	 * String representation of Question object
	 */
	public String toString() {
		return "Question - " + this.text + "\n" + "Answer - " + this.answer + "\n ID - " + this.id;
	}

}
