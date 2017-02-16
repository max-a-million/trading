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
