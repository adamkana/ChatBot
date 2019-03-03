package ChatBot;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import ChatBot.Questions.Question;

public class Engine {

	Protocol protocol;
	JSONObject converstion;
	JSONArray conversationList;
	Scanner scanner;
	
	public Engine(String protocol) {
		try {
			this.protocol = new Protocol(protocol);
		} catch (Exception e) {
			return;
		}
		conversationList = new JSONArray();
		converstion = new JSONObject().put("conversation", conversationList);
		scanner = new Scanner(System.in);
		answerQuestion(this.protocol.start());
		scanner.close();
		printConversation();
	}
	
	private void answerQuestion(Question question) {
		if (question == null) {
			return;
		}
				
		printQuestion(question);
		getQuestionAnswers(question);
	}

	private void getQuestionAnswers(Question question) {
		String answersLine = "";
		while (answersLine.replaceAll("\\s", "").equals("")) {
			answersLine = scanner.nextLine();
		}
		
		List<String> answers = Arrays.asList(answersLine.split(","));

		try {
			question.validateAnswers(answers);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			getQuestionAnswers(question);
			return;
		}
		
		conversationList.put(new JSONObject().put(question.getQuestion(), answersLine));
		
		for (String answerName : answers) {
			Answer answer = question.getAnswer(answerName.toLowerCase());
			if (answer != null) {
				answerQuestion(protocol.getNextQuestion(answer.getFollowUpQuestion()));
			}
		}
		
		answerQuestion(protocol.getNextQuestion(question.getNextQuestion()));
	}
	
	private void printQuestion(Question question) {
		String optionalAnswers = question.getAnswers().stream().collect(Collectors.joining(","));
		System.out.println(question.getQuestion() + (!optionalAnswers.equals("") ? "(" + optionalAnswers + ")" : ""));
	}
	
	private void printConversation() {
		Iterator<Object> iterator = conversationList.iterator();
		while (iterator.hasNext()) {
			JSONObject line = (JSONObject)iterator.next();
			System.out.println(line);
		}
	}
}
