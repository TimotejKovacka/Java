import java.util.ArrayList;

/**
 * @author Timotej Kovacka
 * Subclass of Question, object representation of Multiple choice question
 */
public class FourMultipleChoice extends Question {
	private ArrayList<String> choices = new ArrayList<>();

	public void addChoice(String choice, boolean correct) {
		if (choices.size() < 4) {
			choices.add(choice);
			if (correct) {
				super.setAnswer(this.id + "." + choices.size());
			}
		}
	}

	@Override
	public void printQuestion() {
		super.printQuestion();
		for (String choice : choices) {
			System.out.println(this.id + "." + (choices.indexOf(choice) + 1) + " " + choice);
		}
	}
}
