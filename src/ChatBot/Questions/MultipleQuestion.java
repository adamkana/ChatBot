package ChatBot.Questions;

import java.util.List;

public class MultipleQuestion extends Question {

	@Override
	public void validateAnswers(List<String> answers) throws Exception {
		if (answers.size() < 1) {
			throw new Exception("Question must be answered");
		}
		
		for (String answer : answers) {
			if (getAnswer(answer.toLowerCase()) == null) {
				throw new Exception("Answer \"" + answer + "\" is invalid answer");
			}
		}
	}

}
