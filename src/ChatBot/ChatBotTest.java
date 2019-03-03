package ChatBot;

public class ChatBotTest {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage ChatBotTest <protocol_name>");
			return;
		}
		
		new Engine(args[0]);
	}
}
