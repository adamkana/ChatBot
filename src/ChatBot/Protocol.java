package ChatBot;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import ChatBot.Questions.Question;
import ChatBot.Questions.QuestionFactory;

public class Protocol {

	private static final String FIRST_QUESTION = "1";
	HashMap<String,Question> questions;
	
	public Protocol(String protocol) throws Exception {
		try {
	        String protocolContent = new String(Files.readAllBytes(Paths.get(getClass().getResource(protocol+".json").toURI())));
			JSONObject jsonProtocol = new JSONObject(protocolContent);
			loadQuestions(jsonProtocol);
		} catch (Exception e) {
			System.out.println("Protocol could not be loaded");
			throw e;
		}
	}

	private void loadQuestions(JSONObject jsonProtocol) {
		questions = new HashMap<>();
		Iterator<Object> questionsIterator = jsonProtocol.getJSONArray("questions").iterator();
		while (questionsIterator.hasNext()) {
			Question question =  loadQuestion(((JSONObject)questionsIterator.next()).getJSONObject("question"));
			questions.put(question.getId(), question);
		}
	}
	
	private Question loadQuestion(JSONObject questionObject) {
		Question question = QuestionFactory.getQuestion(questionObject.getString("type")).load(questionObject);
		return question;
	}
	
	public Question start() {
		return getNextQuestion(FIRST_QUESTION);
	}
	
	public Question getNextQuestion(String question) {
		return questions.get(question);
	}
}
