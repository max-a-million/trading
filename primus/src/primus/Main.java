package primus;

import java.rmi.RemoteException;
import java.util.*;

import primus.broker.*;
import primus.logging.Logs;

public class Main {
	
	public static Map<String, String> Keys = new HashMap<String, String>();
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws RemoteException {
		
		Logs.initialize("datp");
		
		for (int idx = 0; idx < args.length; idx ++) {
			
			switch (args[idx]) {
			
			case "--finam-tc-login":
				if (args.length >= idx + 2) {
					Config.FinamTC.UserName = args[idx + 1];
					Logs.message("datp", "user name", Config.FinamTC.UserName);
				}
				break;
			
			case "--finam-tc-password":
				if (args.length >= idx + 2)
					Config.FinamTC.UserPassword = args[idx + 1];
				break;
			}
		}
		
		//datp.broker.Broker.FinamTC(Stage.START);
		primus.broker.Broker.FinamHistorical(Stage.START);
		
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();
		while (!input.equals("exit")) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//datp.broker.Broker.FinamTC(Stage.STOP);
		primus.broker.Broker.FinamHistorical(Stage.STOP);
		
		Thread.yield();
		
		Logs.message("main", "stoped");
	}
}
