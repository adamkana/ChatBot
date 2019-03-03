package ChatBot;

import org.json.JSONObject;

public class Answer {
	private String id;
	private String answerText;
	private String followUpQuestion;
	
	public Answer load(JSONObject answer) {
		this.answerText = answer.getString("text");
		this.id = answer.getString("id");
		this.followUpQuestion = answer.getString("followup");
		
		return this;
	}
	
	public String getId() {
		return id;
	}

	public String getAnswer() {
		return answerText;
	}

	public String getFollowUpQuestion() {
		return followUpQuestion;
	}
}
