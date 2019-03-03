package ChatBot.Questions;

import java.util.List;

public class SingleQuestion extends Question {
	
	@Override
	public void validateAnswers(List<String> answers) throws Exception {
		if (answers.size() != 1) {
			throw new Exception("Question must be answered with a single answer");
		}
		
		if (getAnswer(answers.get(0).toLowerCase()) == null) {
			throw new Exception("Invalid answer");
		}
	}
}
