package ChatBot.Questions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import ChatBot.Answer;

public abstract class Question {
	private String question;
	private HashMap<String, Answer> answers;
	private String nextQuestion;
	private String id;
	
	public Question load(JSONObject question) {
		this.question = question.getString("text");
		this.nextQuestion = question.getString("next_question");
		this.id = question.getString("id");
		loadAnswers(question.getJSONArray("answers"));
		return this;
	}
	
	private void loadAnswers(JSONArray answers) {
		this.answers = new HashMap<>();
		Iterator<Object> answersIterator = answers.iterator();
		while (answersIterator.hasNext()) {
			Answer answer = new Answer().load(((JSONObject)answersIterator.next()).getJSONObject("answer"));
			this.answers.put(answer.getId(), answer);
		}
	}
	
	public String getId() {
		return id;
	}
	
	public String getQuestion() {
		return question;
	}

	public String getNextQuestion() {
		return nextQuestion;
	}
	
	public List<String> getAnswers() {
		return answers.entrySet().stream().map((entry) -> {return entry.getValue().getAnswer();}).collect(Collectors.toList());
	}
	
	public Answer getAnswer(String answer) {
		return answers.get(answer);
	}	
	
	public abstract void validateAnswers(List<String> answers) throws Exception;
}
