package ChatBot.Questions;

public class QuestionFactory {

	enum QuestionsEnum {
		open,
		single,
		multiple
	}
	
	public static Question getQuestion(String type) {
		if (QuestionsEnum.multiple.toString().equals(type)) {
			return new MultipleQuestion();
		}
		if (QuestionsEnum.single.toString().equals(type)) {
			return new SingleQuestion();
		}
		
		return new OpenQuestion();
	}
}
