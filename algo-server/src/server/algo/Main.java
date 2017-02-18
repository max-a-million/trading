package server.algo;

import datp.Config;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Main {

	private static Main.CmdsApi Cmds;
	
	private static void checkAdditionalOptions(String[] args) {
		System.out.println("additional options");
		try {
			Cmds = (Main.CmdsApi)Naming.lookup(Config.AlgoServerCmdsUri);
			Cmds.addOptions(args);
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
		}
	}
	
	public static void main(String[] args) {
		
		File lockFile = new File(Config.AlgoServerLockFileName);
		if (lockFile.exists()) {
			checkAdditionalOptions(args);
			return;
		}
			
		try {
			lockFile.createNewFile();
			LocateRegistry.createRegistry(Integer.parseInt(Config.AlgoServerCmdsPort));
			Cmds = new Main.AlgoServerCommans();
			Naming.rebind(Config.AlgoServerCmdsUri, Cmds);
			System.out.println("Cmds created");
		} catch (IOException e) {
			System.out.println("can't create new file...");
			return;
		}
		
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();
		while (!input.equals("exit")) {
			input = reader.nextLine();
		}
		reader.close();
		
		lockFile.delete();
		try {
			Naming.unbind(Config.AlgoServerCmdsUri);
			UnicastRemoteObject.unexportObject(Cmds, true);
			System.out.println("Cmds unbinded");
		} catch (IOException | NotBoundException e) {
			System.out.println("can't create new file...");
			return;
		}
	}

	public static class AlgoServerCommans extends UnicastRemoteObject implements CmdsApi {

		protected AlgoServerCommans() throws RemoteException {
		}
		
		public void addOptions(String[] args) throws RemoteException {
			for (String arg : args) System.out.println(arg);
		}
	}
	
	public static interface CmdsApi extends Remote {
		public void addOptions(String[] args) throws RemoteException;
	}
}
