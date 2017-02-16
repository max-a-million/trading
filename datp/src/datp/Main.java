package datp;

import datp.logging.Logs;
import datp.broker.*;

import java.rmi.RemoteException;
import java.util.*;

public class Main {
	
	public static Map<String, String> Keys = new HashMap<String, String>();
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws RemoteException {
		
		Logs.initialize("datp");
		
		for (int idx = 0; idx < args.length; idx ++) {
			
			switch (args[idx]) {
			
			case "--login":
				if (args.length >= idx + 2) {
					Config.Finam.UserName = args[idx + 1];
					Logs.message("datp", "user name", Config.Finam.UserName);
				}
				break;
			
			case "--password":
				if (args.length >= idx + 2)
					Config.Finam.UserPassword = args[idx + 1];
				break;
			}
		}
		
		datp.broker.Broker.FinamTC(Stage.START);
		
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();
		while (!input.equals("exit")) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		datp.broker.Broker.FinamTC(Stage.STOP);
		
		Thread.yield();
		
		Logs.message("main", "stoped");
	}
}
