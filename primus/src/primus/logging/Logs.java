package primus.logging;

public class Logs {
	
	private static String AppName;
	
	public static void initialize(String application) {
		AppName = application;
	}
	
	public static synchronized void message(String service, String msg, String ... info) {
		String infoMsg = "";
		for (String item : info) 
			infoMsg += " :: " + item;
		System.out.println(AppName + " <-> " + service + " : " + msg + infoMsg);
	}
}
